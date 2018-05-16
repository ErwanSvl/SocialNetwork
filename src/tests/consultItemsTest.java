package tests;

import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;

public class consultItemsTest {

	private static int consultItemsBadEntryTest(ISocialNetwork sn, String title, String testId, String errorMessage) {
		try {
			sn.consultItems(title);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (BadEntryException e) {
			return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception " + e);
			e.printStackTrace();
			return 1;
		}
	}

	private static int consultItemOKTest(ISocialNetwork sn, String title, int expectedNbItems, String testId) {
		try {
			LinkedList<String> items = sn.consultItems(title);
			if (items.size() == expectedNbItems) {
				return 0;
			} else {
				System.out.println("Err " + testId + " : consultItem don't return the correct number of items ("
						+ items.size() + " instead of " + expectedNbItems + ")");
				return 1;
			}
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception " + e);
			e.printStackTrace();
			return 1;
		}
	}

	public static TestReport test() {
		ISocialNetwork sn = new SocialNetwork();
		int nbTests = 0;
		int nbErrors = 0;

		// add a member and items in the social network
		try {
			sn.addMember("esevellec", "1234", "Lecteur assidu");
		} catch (Exception e) {
			System.out.println("Err : addMember() throw an exception");
			e.printStackTrace();
		}

		try {
			sn.addItemBook("esevellec", "1234", "Dune", "Science-fiction", "Frank Herbert", 500);
		} catch (Exception e) {
			System.out.println("addItemBook() throw an exception");
			e.printStackTrace();
		}

		try {
			sn.addItemFilm("esevellec", "1234", "Dune", "Science-fiction", "David Lynch", "David Lynch", 130);
		} catch (Exception e) {
			System.out.println("addItemBook() throw an exception");
			e.printStackTrace();
		}

		try {
			sn.addItemBook("esevellec", "1234", "Alice's Adventures in Wonderland", "fantasy", "Lewis Carroll", 44);
		} catch (Exception e) {
			System.out.println("addItemBook() throw an exception");
			e.printStackTrace();
		}

		// save the number of book and film in the socialNetwork
		int nbBooks = sn.nbBooks();
		int nbFilms = sn.nbFilms();

		nbTests++;
		nbErrors += consultItemsBadEntryTest(sn, null, "x.1", "consultItems() doesn't reject a null login");

		nbTests++;
		nbErrors += consultItemsBadEntryTest(sn, "    ", "x.2",
				"consultItems() doesn't reject logins that don't contain at least one character other than space");

		nbTests++;
		nbErrors += consultItemOKTest(sn, "Lord Of The Rings", 0, "x.3");

		nbTests++;
		nbErrors += consultItemOKTest(sn, "Alice's Adventures in Wonderland", 1, "x.4");

		nbTests++;
		nbErrors += consultItemOKTest(sn, "Dune", 2, "x.5");

		nbTests++;
		if (nbBooks != sn.nbBooks()) {
			System.out.println("Err x.6 : the number of book was unexpected changed by consultItem()");
			nbErrors++;
		}

		nbTests++;
		if (nbFilms != sn.nbFilms()) {
			System.out.println("Err x.7 : the number of film was unexpected changed by consultItem()");
			nbErrors++;
		}

		// Display final state of the social network
		System.out.println("Final state of the social network : \n" + sn);

		try {
			TestReport tr = new TestReport(nbTests, nbErrors);
			System.out.println("consultItem : " + tr);
			return tr;
		} catch (NotTestReportException e) {
			System.out.println("Unexpected error in consultItemTest test code - Can't return valuable test results");
			return null;
		}

	}

	public static void main(String[] args) {
		test();
	}
}
