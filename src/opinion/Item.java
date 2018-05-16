package opinion;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class Item {
	private String title;
	private String kind;
	private ArrayList<Review> listReviews = new ArrayList<Review>();

	public Item(String title, String kind) {
		this.title = title;
		this.kind = kind;
	}

	public void addReview(Review review) {
		listReviews.add(review);
	}

	@Override
	public String toString() {
		String item = new String();
		item = "\"" + this.title + "\" fait parti de la catégorie : " + this.kind;
		if (listReviews.size() == 0) {
			item += ". Aucune évaluations pour ce produit.\n";
		} else {
			item += ". \n   Liste des évaluations pour ce produit (moyenne : " + getMean() + "):\n";
			for (Iterator<Review> it = listReviews.iterator(); it.hasNext();) {
				item += " 	* " + it.next() + "\n";

			}
		}
		return item;
	}

	public String getTitle() {
		return title;
	}

	public float getMean() {
		if (listReviews.size() == 0)
			return 0;
		int sum = 0;
		for (Iterator<Review> it = listReviews.iterator(); it.hasNext();) {
			Review review = (Review) it.next();
			sum += review.getMark();
		}
		return sum / (float) listReviews.size();
	}
}
