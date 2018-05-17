/**
 * @author Erwan SEVELLEC
 * @author Killian THEZELAIS
 */
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

	public float getMark() {
		return mark;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public void setMark(float mark) {
		this.mark = mark;
	}

	public Item getItem() {
		return item;
	}
	
	public String toString() {
		return "mark : " + mark + "; comment : " + comment + "; membre : " + member;
	}
}