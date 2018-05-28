/**
 * @author Erwan SEVELLEC
 * @author Killian THEZELAIS
 */
package opinion;

import java.util.ArrayList;
import java.util.Iterator;

import exceptions.NotTestReportException;
import tests.TestReport;

public abstract class Item {
	private String title;
	private String kind;
	private ArrayList<Review> reviews = new ArrayList<Review>();

	public Item(String title, String kind) {
		this.title = title;
		this.kind = kind;
	}

	/**
	 * 
	 * @param review
	 *            the review to add in the ArrayList review
	 */
	public void addReview(Review review) {
		reviews.add(review);
	}

	@Override
	public String toString() {
		String item = new String();
		item = "\"" + this.title + "\" fait parti de la catégorie : " + this.kind;
		if (reviews.size() == 0) {
			item += ". Aucune évaluations pour ce produit.\n";
		} else {
			item += ". \n   Liste des évaluations pour ce produit (moyenne : " + getMean() + "):\n";
			for (Iterator<Review> it = reviews.iterator(); it.hasNext();) {
				item += " 	* " + it.next() + "\n";

			}
		}
		return item;
	}

	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @return return the global mean of all reviews on the item, 0 if no review
	 */
	public float getMean() {
		if (reviews.size() == 0)
			return 0;
		float sum = 0;
		float totalKarma = 0;
		for (Iterator<Review> it = reviews.iterator(); it.hasNext();) {
			Review review = (Review) it.next();
			float currentKarma = review.getAuthor().getKarma();
			sum += review.getMark() * currentKarma;
			totalKarma += currentKarma;
		}
		return (float) Math.round((sum / totalKarma) * 100) / 100; // Keep only 2 digit after comma
	}

	public static TestReport test() {
		int nbTests = 0;
		int nbErrors = 0;

		// Test getMean with no opinions (lot 1)
		Member member1 = new Member("member1", "1234", "profile");
		Book book1 = new Book("book1", "kind", "member1", 15);
		Review review1 = new Review(3, "comment", member1, book1);
		book1.addReview(review1);
		member1.addReview(review1);

		nbTests++;
		if (book1.getMean() != 3) {
			System.out.println("Err : getMean in item return a wrong mean for one review without opinions");
			nbErrors++;
		}

		Member member2 = new Member("member2", "1234", "profile");
		Review review2 = new Review(5, "comment", member2, book1);
		book1.addReview(review2);
		member2.addReview(review2);
		nbTests++;
		if (book1.getMean() != 4) {
			System.out.println("Err : getMean in item return a wrong mean for two review without opinions");
			nbErrors++;
		}

		// Test getMean with opinions (lot 2)
		review1.addOpinion(member2, 5); // Karma of member1 is 5
		nbTests++;
		if (book1.getMean() != (float) 3.67) { // mean = (5 * 3 + 2.5 * 5)/ 7.5
			System.out.println("Err : getMean in item return a wrong mean reviews with opinions : " + book1.getMean()
					+ " should be 3.67");
			nbErrors++;
		}

		System.out.println("Final state of the member : " + book1);

		try {
			TestReport tr = new TestReport(nbTests, nbErrors);
			System.out.println("Item : " + tr);
			return tr;
		} catch (NotTestReportException e) {
			System.out.println("Unexpected error in Item test code - Can't return valuable test results");
			return null;
		}
	}

	public static void main(String[] args) {
		test();
	}
}
