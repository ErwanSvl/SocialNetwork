package opinion;

import java.util.ArrayList;

public abstract class Item {
	private String title;
	private String kind;
	private ArrayList<Review> listReviews = new ArrayList<Review>();

	public Item(String title, String kind) {
		this.title = title;
		this.kind = kind;
	}

	public boolean addReview(Review review) {
		return true;
	}

	public boolean deleteReview(Review review) {
		return true;
	}

	@Override
	public String toString() {
		return this.title + " genre : " + this.kind;
	}
	
	public String getTitle() {
		return title;
	}
}
