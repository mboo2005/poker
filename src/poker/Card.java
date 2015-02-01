package poker;

@SuppressWarnings("serial")
public class Card implements Comparable<Card>, java.io.Serializable {
	
	private Rank rank;
	private Suit suit;
	private String name, image;
	
	public Card(Rank r, Suit s) {
				
		rank = r;
		suit = s;
		name = rank+" of "+suit;
		image = s.ordinal()+"_"+r.ordinal()+".gif";
		
	}
		
	public Rank rank() { return rank; }
	public Suit suit() { return suit; }
	public String name() {	return name; }
	public String image() {	return image; }
	public int compareTo(Card c) { return rank.compareTo(c.rank()); }

}