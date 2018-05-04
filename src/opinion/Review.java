public class Review {

	private float mark;
	private String comment;
	private Member member;
	private Item item;

	public Review(float mark, String comment, Membre membre, Item item) {
		this.mark = mark;
		this.comment = comment;
		this.membre = membre;
		this.item = item;
	}

	public String toString() {
		return "mark : " + mark + "; comment : " + comment + "; membre : "
				+ membre + "; item : " + item;
	}
}