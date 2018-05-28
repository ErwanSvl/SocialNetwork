/**
 * @author Erwan SEVELLEC
 * @author Killian THEZELAIS
 */
package opinion;

import java.util.ArrayList;
import java.util.Iterator;

import exceptions.NotTestReportException;
import tests.TestReport;

public class Member {
	private String login;
	private String password;
	private String profile;
	private float karma = (float) 2.5;
	private ArrayList<Review> reviews = new ArrayList<Review>();

	public Member(String login, String password, String profile) {
		this.login = login;
		this.password = password;
		this.profile = profile;
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	public Review getExistingReview(Item item) {
		for (Iterator<Review> it = reviews.iterator(); it.hasNext();) {
			Review review = (Review) it.next();
			if (review.getItem().getTitle() == item.getTitle()) {
				if (review.getItem() instanceof Book && item instanceof Book) {
					return review;
				} else if (review.getItem() instanceof Film && item instanceof Film) {
					return review;
				}
			}
		}
		return null;
	}

	public void addReview(Review review) {
		reviews.add(review);
	}

	@Override
	public String toString() {
		return this.login + " " + this.password + " : " + this.profile + ". Karma : " + karma;
	}

	public boolean isEquals(String login) {
		return this.login.trim().equalsIgnoreCase(login.trim());
	}

	public String getLogin() {
		return login;
	}

	/**
	 * Recalculates the value of the karma attribute and set the value
	 */
	public void updateKarma() {
		float sum = 0; // The sum of the member's review opinion mean
		int nbOpinionsTotal = 0; // The number of opinion for the member (more a review has opinion, more the
									// opinion mean for this review count)
		for (Iterator<Review> iterator = reviews.iterator(); iterator.hasNext();) {
			Review review = (Review) iterator.next();
			int nbOpinions = review.getNbOpinions(); // The number of opinion for this review
			sum += review.getOpinionMean() * nbOpinions;
			nbOpinionsTotal += nbOpinions;
		}
		if(nbOpinionsTotal == 0)
			this.karma = (float) 2.5;
		this.karma = (float) Math.round((sum / nbOpinionsTotal) * 100) / 100; // Keep only 2 digit after comma
	}

	public float getKarma() {
		return karma;
	}

	public static TestReport test() {
		int nbTests = 0;
		int nbErrors = 0;

		// Test checkPassword
		Member member1 = new Member("erwan", "1234", "test member");

		nbTests++;
		if (member1.checkPassword("123") != false) {
			System.out.println("Error : checkPassword in member return true for a false password");
			nbErrors++;
		}

		nbTests++;
		if (member1.checkPassword("1234") != true) {
			System.out.println("Error : checkPassword in member return false for a true password");
			nbErrors++;
		}

		// Test getExistingItem
		Book book1 = new Book("test book", "kind", "author", 15);
		Book book2 = new Book("unrewieved book", "kind", "author", 15);
		Review review1 = new Review(5, "test comment", member1, book1);
		member1.addReview(review1);

		nbTests++;
		if (member1.getExistingReview(book2) != null) {
			System.out.println(
					"Error : getExistingReview in member doesn't return null for an item non reviewed by the member");
			nbErrors++;
		}

		nbTests++;
		if (member1.getExistingReview(book1) != review1) {
			System.out.println("Error : getExistingReview in member doesn't return the review");
			nbErrors++;
		}

		// Test updateKarma
		nbTests++;
		if (member1.getKarma() != 2.5) {
			System.out.println("Error : The karma for the member should be 2.5 whith no opinion on his review");
			nbErrors++;
		}

		review1.addOpinion(member1, 3);

		nbTests++;
		if (member1.getKarma() != 3) {
			System.out.println(
					"Error : The karma for the member should be the value of the only opinion he have on his review");
			nbErrors++;
		}

		Member member2 = new Member("Killian", "1234", "test member");
		review1.addOpinion(member2, 1);

		nbTests++;
		if (member1.getKarma() != 2) {
			System.out.println("Error : The karma for the member should be the mean of the opinions on his review : "
					+ member1.getKarma() + " instead of 2");
			nbErrors++;
		}

		// Test if the karma is good for 2 review with the same number of opinions
		Film film1 = new Film("test film", "kind", "director", "scenarist", 150);
		Review review2 = new Review(3, "test review", member1, film1);
		member1.addReview(review2);
		review2.addOpinion(member1, 3);
		review2.addOpinion(member2, 5);

		nbTests++;
		if (member1.getKarma() != 3) {
			System.out.println("Error : The karma for the member is wrong : " + member1.getKarma() + " instead of 3");
			nbErrors++;
		}

		// Test if the karma is good for reviews with different number of opinions
		Member member3 = new Member("member3", "1234", "test member");
		review2.addOpinion(member3, 3);

		nbTests++;
		if (member1.getKarma() != 3) {
			System.out.println("Error : The karma for the member is wrong : " + member1.getKarma() + " instead of 3");
			nbErrors++;
		}

		System.out.println("Final state of the member : " + member1);

		try {
			TestReport tr = new TestReport(nbTests, nbErrors);
			System.out.println("Member : " + tr);
			return tr;
		} catch (NotTestReportException e) {
			System.out.println("Unexpected error in Member test code - Can't return valuable test results");
			return null;
		}
	}

	public static void main(String[] args) {
		test();
	}
}
