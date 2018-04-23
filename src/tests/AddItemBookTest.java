package tests;

import opinion.ISocialNetwork;
import opinion.SocialNetwork;

import exceptions.BadEntryException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import exceptions.ItemBookAlreadyExistsException;

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
	
	/**
	 * Check that trying to add this new item book (login, password, title,
			kind, author, nbPages) raises a
	 * NotMemberException exception and does not change content of the
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
	private static int addItemBookNotMemberExceptionTest(ISocialNetwork sn, String login, String password, String title,
			String kind, String author, int nbPages, String testId, String errorMessage) {
		int nbItemsBooks = sn.nbBooks();
		
		try {
			sn.addItemBook(login, password, title, kind, author, nbPages);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (NotMemberException e) {
			if(nbItemsBooks != sn.nbBooks()) {
				System.out.println("Err "+ testId + " : NotMemberException was thrown but the number of item books was changed");
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}
	
	/**
	 * Check that trying to add this new item book (login, password, title,
			kind, author, nbPages) raises a
	 * ItemBookAlreadyExistsException exception and does not change content of the
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
	private static int addItemItemBookAleadyExistsException(ISocialNetwork sn, String login, String password, String title,
			String kind, String author, int nbPages, String testId, String errorMessage) {
		int nbItemsBooks = sn.nbBooks();
		
		try {
			sn.addItemBook(login, password, title, kind, author, nbPages);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (ItemBookAlreadyExistsException e) {
			if(nbItemsBooks != sn.nbBooks()) {
				System.out.println("Err "+ testId + " : ItemBookAlreadyExistsException was thrown but the number of item books was changed");
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}
	
	/**
	  * Check that a new item book can be added if parameter are correct
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
	private static int addItemBookOKTest(ISocialNetwork sn, String login, String password, String title,
			String kind, String author, int nbPages, String testId) {
		int nbItemBooks = sn.nbBooks();
		try {
			sn.addItemBook(login, password, title, kind, author, nbPages);
			if (nbItemBooks + 1 != sn.nbBooks()) {
				System.out.println("Err " + testId + " : the number of item books (" + nbItemBooks + ") was not incremented");
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception " + e);
			e.printStackTrace();
			return 1;
		}
	}
	
	public static TestReport test() {
		ISocialNetwork sn = new SocialNetwork();
		
		try {
			sn.addMember("esevellec", "1234", "Lecteur assidu");
		} catch (Exception e) {
			System.out.println("addMember does not working");
			e.printStackTrace();
		}
		
		int nbBooks = sn.nbBooks();
		int nbFilms = sn.nbFilms();
		
		int nbTests = 0;
		int nbErrors = 0;
		
		System.out.print("Testing AddItemBook()");
		
		//Test n°3
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, null, "1234", "Lord of the ring", "heroic fantasy", "J. R. R. Tolkien", 30, "3.1", "addItemBook() doesn't reject null logins");
		
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, " ", "1234", "Lord of the ring", "heroic fantasy", "J. R. R. Tolkien", 30, "3.2", "addItemBook() doesn't reject logins that don't contain at least one character other than space");
		
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "Erwan", null, "Lord of the ring", "heroic fantasy", "J. R. R. Tolkien", 30, "3.3", "addItemBook() doesn't reject null password");
		
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "Erwan", "    123 ", "Lord of the ring", "heroic fantasy", "J. R. R. Tolkien", 30, "3.4", "addItemBook() doesn't reject that don't contain at least 4 characters (not taking into account leading or trailing blanks)");
		
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "Erwan", "1234", null, "heroic fantasy", "J. R. R. Tolkien", 30, "3.5", "addItemBook() doesn't reject null title");
		
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "Erwan", "1234", "     ", "heroic fantasy", "J. R. R. Tolkien", 30, "3.6", "addItemBook() doesn't reject only space title");
		
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "Erwan", "1234", "Lord of the ring", null, "J. R. R. Tolkien", 30, "3.7", "addItemBook() doesn't reject null kind");
		
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "Erwan", "1234", "Lord of the ring", "heroic fantasy", null, 30, "3.8", "addItemBook() doesn't reject null author");
		
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "Erwan", "1234", "Lord of the ring", "heroic fantasy", "J. R. R. Tolkien", 0, "3.9", "addItemBook() doesn't reject null number of pages");
		
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "Erwan", "1234", "Lord of the ring", "heroic fantasy", "J. R. R. Tolkien", -5, "3.10", "addItemBook() doesn't reject negative number of pages");
		
		//Test n°4		
		
		nbTests++;
		nbErrors += addItemBookNotMemberExceptionTest(sn, "Erwan", "1234", "Lord of the ring", "heroic fantasy", "J. R. R. Tolkien", 30, "4.1", "addItemBook() doesn't reject a not member login");
		
		nbTests++;
		nbErrors += addItemBookNotMemberExceptionTest(sn, "esevellec", "12345", "Lord of the ring", "heroic fantasy", "J. R. R. Tolkien", 30, "4.2", "addItemBook() doesn't reject a not matching password");
		
		nbTests++;
		nbErrors += addItemBookOKTest(sn, "esevellec", "1234", "Lord of the ring", "heroic fantasy", "J. R. R. Tolkien", 30, "4.2");
		
		nbTests++;
		nbErrors += addItemBookOKTest(sn, "esevellec", "1234", "Alice’s Adventures in Wonderland", "fantasy", "Lewis Carroll", 44, "4.2");
		
		nbTests++;
		nbErrors += addItemItemBookAleadyExistsException(sn, "esevellec", "1234", "Lord of the ring", "heroic fantasy", "J. R. R. Tolkien", 30, "4.2", "addItemBook() doesn't reject a already existing book title");
		
		nbTests++;
		nbErrors += addItemItemBookAleadyExistsException(sn, "esevellec", "1234", "Alice’s Adventures in Wonderland", "fantasy", "Lewis Carroll", 44, "4.2", "addItemBook() doesn't reject a already existing book title");
		
		nbTests++;
		nbErrors += addItemItemBookAleadyExistsException(sn, "esevellec", "1234", "Lord Of tHe rInG", "heroic fantasy", "J. R. R. Tolkien", 30, "4.2", "addItemBook() doesn't reject a already existing book title with a different case");
		
		nbTests++;
		nbErrors += addItemItemBookAleadyExistsException(sn, "esevellec", "1234", "   Lord of the ring    ", "heroic fantasy", "J. R. R. Tolkien", 30, "4.2", "addItemBook() doesn't reject a already existing book title without taking into account leading or trailing blanks");
		
		nbTests++;
		// check that 'sn' was not modified
		if (nbFilms != sn.nbFilms()) {
			System.out
					.println("Error : the number of films was unexepectedly changed by addMember()");
			nbErrors++;
		}
		
		// Display final state of 'sn'
		System.out.println("Final state of the social network : " + sn);
		
		try{
			TestReport tr = new TestReport(nbTests, nbErrors);	
			System.out.println("AddItemBook : " + tr);
			return tr;	
		}
		catch (NotTestReportException e){ //This shouldn't happen
			System.out.println("Unexpected error in AddMemberTest test code - Can't return valuable test results");
			return null;
		}
	}
	
	public static void main(String[] args) {
		test();
	}
}