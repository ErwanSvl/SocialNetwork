package opinion;

public class Review {

	private float mark;
	private String comment;
	private Member member;
	private Item item;

	public Review(float mark, String comment, Member member, Item item) {
		this.mark = mark;
		this.comment = comment;
		this.member = member;
		this.item = item;
	}
	
	public Item getItem() {
		return item;
	}
	
	public float getMark() {
		return mark;
	}

	public String toString() {
		return "mark : " + mark + "; comment : " + comment + "; membre : "
				+ member + "; item : " + item;
	}
}