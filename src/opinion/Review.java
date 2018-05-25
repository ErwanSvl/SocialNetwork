/**
 * @author Erwan SEVELLEC
 * @author Killian THEZELAIS
 */
package opinion;

import java.util.ArrayList;
import java.util.Iterator;

public class Review {

	private float mark;
	private String comment;
	private Member member;
	private Item item;
	private ArrayList<Opinion> opinions = new ArrayList<Opinion>();

	public Review(float mark, String comment, Member member, Item item) {
		this.mark = mark;
		this.comment = comment;
		this.member = member;
		this.item = item;
	}

	public void addOpinion(Member member, float mark) {
		opinions.add(new Opinion(member, mark));
		member.updateKarma();
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

	/**
	 * 
	 * @param member
	 * @return
	 */
	public Opinion getExistingOpinion(Member member) {
		for (Iterator<Opinion> iterator = opinions.iterator(); iterator.hasNext();) {
			Opinion opinion = (Opinion) iterator.next();
			if (member.isEquals(opinion.getAuthor().getLogin())) {
				return opinion;
			}
		}
		return null;
	}
	
	public String toString() {
		return "mark : " + mark + "; comment : " + comment + "; membre : " + member;
	}

	public float getOpinionMean() {
		float sum = 0;
		for (Iterator<Opinion> iterator = opinions.iterator(); iterator.hasNext();) {
			Opinion opinion = (Opinion) iterator.next();
			sum += opinion.getMark();
		}
		return sum / opinions.size();
	}
}