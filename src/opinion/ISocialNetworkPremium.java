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
	 * 
	 * @param login
	 * @param password
	 * @param reviewAuthor TODO
	 * @param title
	 * @param mark
	 * @return
	 * @throws BadEntryException
	 * @throws NotMemberException
	 * @throws NotItemException
	 * @throws NotReviewException
	 */
	public void reviewOpinion(String login, String password, String reviewAuthor, String title, Itemtype itemtype, float mark)
			throws BadEntryException, NotMemberException, NotItemException, NotReviewException;
}
