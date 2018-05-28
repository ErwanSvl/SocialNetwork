/**
 * @author Erwan SEVELLEC
 * @author Killian THEZELAIS
 */

package opinion;

import exceptions.BadEntryException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotReviewException;
import opinion.ISocialNetwork;

public interface ISocialNetworkPremium extends ISocialNetwork {

	public enum Itemtype {
		BOOK, FILM
	}

	/**
	 * Add a opinion on an existing review
	 * 
	 * @param login
	 *            the login of the author of the opinion
	 * @param password
	 *            the password of the author of the opinion
	 * @param reviewAuthor
	 *            the login of the author of the review
	 * @param title
	 *            the title of the item reviewed
	 * @param itemtype
	 *            the type of the item (book or film)
	 * @param mark
	 *            the mark of the opinion
	 * @throws BadEntryException
	 * @throws NotMemberException
	 * @throws NotItemException
	 * @throws NotReviewException
	 */
	public void reviewOpinion(String login, String password, String reviewAuthor, String title, Itemtype itemtype,
			float mark) throws BadEntryException, NotMemberException, NotItemException, NotReviewException;
}
