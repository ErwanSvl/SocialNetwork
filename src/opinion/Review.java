/**
 * @author Erwan SEVELLEC
 * @author Killian THEZELAIS
 */
package opinion;

import java.util.ArrayList;
import java.util.Iterator;

import exceptions.NotTestReportException;
import tests.TestReport;

public class Review {

	private float mark;
	private String comment;
	private Member author;
	private Item item;
	private ArrayList<Opinion> opinions = new ArrayList<Opinion>();

	public Review(float mark, String comment, Member member, Item item) {
		this.mark = mark;
		this.comment = comment;
		this.author = member;
		this.item = item;
	}

	/**
	 * Add a new opinion or update the existing one if the member have already an
	 * opinion for this review
	 * 
	 * @param member
	 *            The author of the opinion
	 * @param mark
	 *            The mark of the opinion for the review
	 */
	public void addOpinion(Member member, float mark) {
		for (Iterator<Opinion> iterator = opinions.iterator(); iterator.hasNext();) {
			Opinion opinion = (Opinion) iterator.next();
			if (member.isEquals(opinion.getAuthor().getLogin())) {
				// The member have already an opinion for this review : we update the mark
				opinion.setMark(mark);
				this.author.updateKarma(); // Update karma attribut for the author of the review (not the author of the
											// opinion)
				return;
			}
		}
		// Opinion from a new member : new opinion object
		opinions.add(new Opinion(member, mark));
		author.updateKarma();// Update karma attribut for the author of the review (not the author of the
								// opinion)
	}

	public Member getAuthor() {
		return author;
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
		String review = new String();
		review += "mark : " + mark + "; comment : " + comment + "; membre : " + author;
		for (Iterator<Opinion> it = opinions.iterator(); it.hasNext();) {
			Opinion opinion = (Opinion) it.next();
			review += "\n   - " + opinion;
		}
		return review;
	}

	/**
	 * Return the mean of all the opinions for this review
	 * 
	 * @return the mean of all the opinions (0 if no opinions)
	 */
	public float getOpinionMean() {
		if (opinions.size() == 0) {
			return 0;
		}
		float sum = 0;
		for (Iterator<Opinion> iterator = opinions.iterator(); iterator.hasNext();) {
			Opinion opinion = (Opinion) iterator.next();
			sum += opinion.getMark();
		}
		return sum / opinions.size();
	}

	public int getNbOpinions() {
		return opinions.size();
	}

	// Test the Review class
	public static TestReport test() {
		int nbTests = 0;
		int nbErrors = 0;

		Member member1 = new Member("erwan", "1234", "test member");
		Member member2 = new Member("Killian", "1234", "test member");
		Item item1 = new Book("Test book", "kind", "author", 10);
		Review review1 = new Review(5, "test review", member1, item1);

		float mean0 = review1.getOpinionMean();
		nbTests++;
		if (mean0 != 0) {
			nbErrors++;
			System.out.println("The mean return by getOpinionMean() in Review with 0 opinions should be 0");
		}

		review1.addOpinion(member1, 4);
		review1.addOpinion(member2, 5);

		float mean = review1.getOpinionMean();
		nbTests++;
		if (mean != 4.5) {
			nbErrors++;
			System.out.println("The mean return by getOpinionMean() in Review is wrong : " + mean + " should be 4.5");
		}

		review1.addOpinion(member2, 3);
		mean = review1.getOpinionMean();
		if (mean != 3.5) {
			nbErrors++;
			System.out.println("The mean return by getOpinionMean() in Review is wrong : " + mean + " should be 3.5");
		}

		System.out.println("Final state of the review : " + review1);

		try {
			TestReport tr = new TestReport(nbTests, nbErrors);
			System.out.println("Review : " + tr);
			return tr;
		} catch (NotTestReportException e) {
			System.out.println("Unexpected error in Review test code - Can't return valuable test results");
			return null;
		}
	}

	public static void main(String[] args) {
		test();
	}
}