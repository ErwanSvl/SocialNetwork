package tests;

import opinion.ISocialNetwork;
import opinion.SocialNetwork;

import exceptions.BadEntryException;

public class AddItemBookTest {
	
	/**
	 * Check that trying to add this new item book (login, password, title,
			kind, author, nbPages) raises a
	 * BadEntry exception and does not change content of the
	 * <i>ISocialNetwork</i>. If OK, the method just returns 0. If not OK,
	 * displays an error message and returns 1.
	 * 
	 * @param sn
	 *            - the <i>ISocialNetwork</i>
	 * @param login
	 *            - member login
	 * @param pwd
	 *            - member password
	 * @param title
	 *            - new item book's title
	 * @param kind
	 * 			  - new item book's kind
	 * @param author
	 * 			  - new item book's author
	 * @param nbPages
	 * 			  - new item book's number of pages
	 * @param testId
	 *            - the test ID that will prefix any error message displayed by
	 *            this method
	 * @param errorMessage
	 *            - the error message that will be displayed if no exception is
	 *            thrown when adding this member
	 * @return 0 if the test is OK, 1 if not
	 */
	private static int addItemBookBadEntryTest(ISocialNetwork sn, String login, String password, String title,
			String kind, String author, int nbPages, String testId, String errorMessage) {
		int nbItemBooks = sn.nbBooks();
		try {
			sn.addItemBook(login, password, title, kind, author, nbPages);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (BadEntryException e) {
			if (sn.nbMembers() != nbItemBooks) {	
				System.out.println("Err "+ testId + " : BadEntry was thrown but the number of item books was changed");
				return 1;
			} else
				return 0;
		}
		catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}
}