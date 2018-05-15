package tests;

import exceptions.BadEntryException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;

public class ReviewItemBookTest {

	private static int reviewItemBookBadEntryTest(ISocialNetwork sn, String login, String password, String title,
			float mark, String comment, String testId, String errorMessage) {
		try {
			sn.reviewItemBook(login, password, title, mark, comment);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (BadEntryException e) {
			return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}

	private static int reviewItemBookNotMemberExceptionTest(ISocialNetwork sn, String login, String password,
			String title, float mark, String comment, String testId, String errorMessage) {
		try {
			sn.reviewItemBook(login, password, title, mark, comment);
			System.out.println("Err" + testId + " : " + errorMessage);
			return 1;
		} catch (NotMemberException e) {
			return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}

	private static int reviewItemBookNotItemExceptionTest(ISocialNetwork sn, String login, String password,
			String title, float mark, String comment, String testId, String errorMessage) {
		try {
			sn.reviewItemBook(login, password, title, mark, comment);
			System.out.println("Err" + testId + " : " + errorMessage);
			return 1;
		} catch (NotItemException e) {
			return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}

	private static int reviewItemBookOKTest(ISocialNetwork sn, String login, String password, String title, float mark,
			String comment, float expectedMean, String testId) {
		try {
			float returnedMark = sn.reviewItemBook(login, password, title, mark, comment);
			if (returnedMark != expectedMean) {
				System.out.println(
						"Err " + testId + " : the mean of the marks returned is not correct (" + returnedMark + ")");
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

	private static TestReport test() {
		int nbTests = 0;
		int nbErrors = 0;

		ISocialNetwork sn = new SocialNetwork();
		try {
			sn.addMember("esevellec", "1234", "Beginner reader");
			sn.addMember("kthezelais", "1234", "Confirmed reader");
		} catch (Exception e) {
			System.out.println("Err : addMember() throw an exception");
			e.printStackTrace();
		}

		try {
			sn.addItemBook("esevellec", "1234", "Alice's Adventures in Wonderland", "fantasy", "Lewis Carroll", 44);
		} catch (Exception e) {
			System.out.println("addItemBook() throw an exception");
			e.printStackTrace();
		}

		int nbBooks = sn.nbBooks();
		int nbMembers = sn.nbMembers();
		int nbFilms = sn.nbFilms();

		System.out.println("Testint reviewItemBook()");

		// test 5

		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, null, "1234", "Alice's Adventures in Wonderland", 3, "Great book!",
				"5.1", "reviewItemBook() doesn't reject null login");

		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, " ", "1234", "Alice's Adventures in Wonderland", 3, "Great book!",
				"5.2",
				"reviewItemBook() doesn't reject logins that don't contain at least one character other than space");

		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "esevellec", null, "Alice's Adventures in Wonderland", 3,
				"Great book!", "5.3", "reviewItemBook() doesn't reject null password");

		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "esevellec", "  123 ", "Alice's Adventures in Wonderland", 3,
				"Great book!", "5.4",
				"reviewItemBook() doesn't reject that don't contain at least 4 characters (not taking into account leading or trailing blanks)");

		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "esevellec", "1234", null, 3, "Great book!", "5.5",
				"reviewItemBook() doesn't reject null title");

		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "esevellec", "1234", "    ", 3, "Great book!", "5.6",
				"reviewItemBook() doesn't reject only space title");

		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "esevellec", "1234", "Alice's Adventures in Wonderland", -1,
				"Great book!", "5.7", "reviewItemBook() doesn't reject a negative mark");

		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "esevellec", "1234", "Alice's Adventures in Wonderland", 6,
				"Great book!", "5.8", "reviewItemBook() doesn't reject a mark greater than 5");

		nbTests++;
		nbErrors += reviewItemBookBadEntryTest(sn, "esevellec", "1234", "Alice's Adventures in Wonderland", 3, null,
				"5.9", "reviewItemBook() doesn't reject null comments");

		// Test n�6

		nbTests++;
		nbErrors += reviewItemBookNotMemberExceptionTest(sn, "Erwan", "1234", "Alice's Adventures in Wonderland", 3,
				"Great Book!", "6.1", "reviewItemBook() doesn't reject a bad login");

		nbTests++;
		nbErrors += reviewItemBookNotMemberExceptionTest(sn, "esevellec", "12345", "Alice's Adventures in Wonderland",
				3, "Great Book!", "6.2", "reviewItemBook() doesn't reject a bad password");

		nbTests++;
		nbErrors += reviewItemBookNotItemExceptionTest(sn, "esevellec", "1234", "Lord Of The Rings", 3, "Great Book!",
				"6.3", "reviewItemBook() doesn't reject a non existing book title");

		nbTests++;
		nbErrors += reviewItemBookOKTest(sn, "esevellec", "1234", "Alice's Adventures in Wonderland", 0, "Great Book!",
				0, "6.4");

		nbTests++;
		nbErrors += reviewItemBookOKTest(sn, "kthezelais", "1234", "Alice's Adventures in Wonderland", 5, "Great Book!",
				(float) 2.5, "6.5");

		nbTests++;
		if (nbFilms != sn.nbFilms()) {
			System.out.println("Error : the number of films was unexepectedly changed by addItemBook()");
			nbErrors++;
		}

		nbTests++;
		if (nbMembers != sn.nbMembers()) {
			System.out.println("Error : the number of members was unexepectedly chaged by addItemBook()");
		}

		nbTests++;
		if (nbBooks != sn.nbBooks()) {
			System.out.println("Error : the number of books was unexepectedly chaged by review ItemBook()");
		}

		// Display final state of 'sn'
		System.out.println("Final state of the social network : " + sn);

		try {
			TestReport tr = new TestReport(nbTests, nbErrors);
			System.out.println("ReviewItemBook : " + tr);
			return tr;
		} catch (NotTestReportException e) {
			System.out.println("Unexpected error in ReviewItemBookTest test code - Can't return valuable test results");
			return null;
		}
	}

	public static void main(String[] args) {
		test();
	}
}
