/**
 * @author Erwan SEVELLEC
 * @author Killian THEZELAIS
 */
package opinion;

import java.util.ArrayList;
import java.util.Iterator;

public class Member {
	private String login;
	private String password;
	private String profile;
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
				if(review.getItem() instanceof Book && item instanceof Book) {
					return review;
				}
				else if (review.getItem() instanceof Film && item instanceof Film) {
					return review;
				}
			}
		}
		return null;
	}

	public void addReview(Review review) {
		reviews.add(review);
	}

	public void modifyReview(Review review, float mark, String comment) {
		review.setComment(comment);
		review.setMark(mark);
	}

	@Override
	public String toString() {
		return this.login + " " + this.password + " : " + this.profile;
	}

	public boolean isEquals(String login) {
		return this.login.trim().equalsIgnoreCase(login.trim());
	}

	public String getLogin() {
		return login;
	}
}
