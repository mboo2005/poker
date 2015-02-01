package poker;

import java.util.Arrays;
import java.util.Collections;

@SuppressWarnings("serial")
public class HandArray implements Comparable<Hand>, java.io.Serializable {
	
	private Card[] h;
	private HandType type;
	
	public HandArray(Card[] five) {
		
		h = Arrays.copyOf(five,5);
		Arrays.sort(h, Collections.reverseOrder());
		
		int[] diff = new int[4]; int t = 0;
		for(int i=0; i<4; i++) diff[i] = h[i].compareTo(h[i+1]);
		
		if((diff[0]==1 || diff[0]==9) && diff[1]==1 && diff[2]==1 && diff[3]==1) { t+=4; if(diff[0]==9) rotate(h,0,5,1); }
		if(h[0].suit()==h[1].suit() && h[0].suit()==h[2].suit() && h[0].suit()==h[3].suit() && h[0].suit()==h[4].suit()) t+=5;
		if(t==9 && h[4].rank()!=Rank.Ten) t--;
			
		if(t==0) {
			
			int kind1 = 1, kind2 = 0;
			int[] kind = new int[5];
			
			for(int i=0; i<4; i++) {
				if(diff[i]==0) {
					kind1++;
					if(i==3 || diff[i+1]!=0) {
						kind[kind1]++;
						rotate(h, kind2*(5-kind1-kind2), i+2, kind1+kind2*(3-kind1)-i-2);
						kind2 = kind1; kind1 = 1;
					}
				}
			}
			
			t = kind[2] + 3*kind[3] + 2*(kind[2]*kind[3]) + 7*kind[4];
						
		}
		
		type = HandType.values()[t];
		
	}
	
	private void rotate(Card[] cards, int start, int finish, int times) {
		
		Card first;
		for(int n=0; n<times; n++) {
			first = cards[start];
			for(int i=start; i<finish; i++) cards[i] = cards[i+1];
			cards[finish-1] = first;
		}

	}
		
	public HandType type() { return type; }
	public Card card(int i) { return h[i]; }

	public int compareTo(Hand h2) {
		
		int diff;
		if((diff=type.compareTo(h2.type()))!=0) return diff;
		for(int i=0; i<5; i++) if((diff=h[i].compareTo(h2.card(i)))!=0) return diff;		
		return 0;
		
	}
	
}