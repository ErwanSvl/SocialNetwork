/**
 * @author Erwan SEVELLEC
 * @author Killian THEZELAIS
 */

package tests;

import exceptions.BadEntryException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotReviewException;
import exceptions.NotTestReportException;
import opinion.Book;
import opinion.ISocialNetworkPremium;
import opinion.ISocialNetworkPremium.Itemtype;
import opinion.Item;
import opinion.Member;
import opinion.Opinion;
import opinion.Review;
import opinion.SocialNetwork;

public class reviewOpinionTest {

	private static int reviewOpinionBadEntryTest(ISocialNetworkPremium sn, String login, String password,
			String reviewAuthor, String title, Itemtype itemtype, float mark, String testId, String errorMessage) {
		try {
			sn.reviewOpinion(login, password, reviewAuthor, title, itemtype, mark);
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

	private static int reviewOpinionNotMemberExceptionTest(ISocialNetworkPremium sn, String login, String password,
			String reviewAuthor, String title, Itemtype itemtype, float mark, String testId, String errorMessage) {
		try {
			sn.reviewOpinion(login, password, reviewAuthor, title, itemtype, mark);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (NotMemberException e) {
			return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}

	private static int reviewOpinionNotItemExceptionTest(ISocialNetworkPremium sn, String login, String password,
			String reviewAuthor, String title, Itemtype itemtype, float mark, String testId, String errorMessage) {
		try {
			sn.reviewOpinion(login, password, reviewAuthor, title, itemtype, mark);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (NotItemException e) {
			return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}

	private static int reviewOpinionNotReviewExceptionTest(ISocialNetworkPremium sn, String login, String password,
			String reviewAuthor, String title, Itemtype itemtype, float mark, String testId, String errorMessage) {
		try {
			sn.reviewOpinion(login, password, reviewAuthor, title, itemtype, mark);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (NotReviewException e) {
			return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}

	private static int reviewOpinionOKTest(ISocialNetworkPremium sn, String login, String password, String reviewAuthor,
			String title, Itemtype itemtype, float mark, String testId) {
		try {
			sn.reviewOpinion(login, password, reviewAuthor, title, itemtype, mark);
			return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}

	public static TestReport test() {
		int nbTests = 0;
		int nbErrors = 0;
		ISocialNetworkPremium sn = new SocialNetwork();

		// add members in the socialNetwork
		try {
			sn.addMember("esevellec", "1234", "beginner reader");
			sn.addMember("kthezelais", "1234", "confirmed reader");
		} catch (Exception e) {
			System.out.println("Err : addMember() throw an exception");
		}

		// add films in the socialNetwork
		try {
			sn.addItemFilm("esevellec", "1234", "Dune", "Science-fiction", "David Lynch", "David Lynch", 130);
		} catch (Exception e) {
			System.out.println("addItemFilm() throw an exception");
			e.printStackTrace();
		}

		// add books in the socialNetwork
		try {
			sn.addItemBook("esevellec", "1234", "Dune", "Science-fiction", "Frank Herbert", 500);
			sn.addItemBook("esevellec", "1234", "Alice's Adventures in Wonderland", "fantasy", "Lewis Carroll", 44);
		} catch (Exception e) {
			System.out.println("addItemBook() throw an exception");
			e.printStackTrace();
		}

		// add reviews for books
		try {
			sn.reviewItemBook("esevellec", "1234", "Dune", 4, "Great book!");
			sn.reviewItemBook("kthezelais", "1234", "Dune", 3, "I hate science fiction");
		} catch (Exception e) {
			System.out.println("reviewItemBook() throw an exception");
			e.printStackTrace();
		}

		// add reviews for films
		try {
			sn.reviewItemFilm("kthezelais", "1234", "Dune", 2, "I hate science fiction");
		} catch (Exception e) {
			System.out.println("reviewItemFilm() throw an exception");
			e.printStackTrace();
		}

		// save the state of the socialNetwork
		int nbBooks = sn.nbBooks();
		int nbFilms = sn.nbFilms();
		int nbMembers = sn.nbMembers();

		// Test n°x

		// Tests for BadEntryException
		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, null, "1234", "kthezelais", "Dune", Itemtype.BOOK, 5, "x.1",
				"reviewOpinion() doesn't reject null logins");

		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "    ", "1234", "kthezelais", "Dune", Itemtype.BOOK, 5, "x.2",
				"reviewOpinion() doesn't reject logins that don't contain at least one character other than space");

		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "esevellec", "1234", null, "Dune", Itemtype.BOOK, 5, "x.2",
				"reviewOpinion() doesn't reject null logins for the review author");

		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "esevellec", "1234", "     ", "Dune", Itemtype.BOOK, 5, "x.2",
				"reviewOpinion() doesn't reject logins that don't contain at least one character other than space for the review author");

		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "esevellec", null, "kthezelais", "Dune", Itemtype.BOOK, 5, "x.3",
				"reviewOpinion() doesn't reject null passwords");

		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "esevellec", "    234   ", "kthezelais", "Dune", Itemtype.BOOK, 5,
				"x.4",
				"reviewOpinion() doesn't reject passwords that don't contain at least 4 characters (not taking into account leading or trailing blanks)");

		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "esevellec", "1234", "kthezelais", null, Itemtype.BOOK, 5, "x.5",
				"reviewOpinion() doesn't reject null titles");

		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "esevellec", "1234", "kthezelais", "    ", Itemtype.BOOK, 5, "x.6",
				"reviewOpinion() doesn't reject only space titles");

		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "esevellec", "1234", "kthezelais", "Dune", null, 5, "x.7",
				"reviewOpinion() doesn't reject null itemtypes");

		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "esevellec", "1234", "kthezelais", "Dune", Itemtype.BOOK, -1, "x.8",
				"reviewOpinion() doesn't reject lower than 0 marks");

		nbTests++;
		nbErrors += reviewOpinionBadEntryTest(sn, "esevellec", "1234", "kthezelais", "Dune", Itemtype.BOOK, 6, "x.9",
				"reviewOpinion() doesn't reject greater than 5 marks");

		// Test n°y

		// Tests NotMemberException
		nbTests++;
		nbErrors += reviewOpinionNotMemberExceptionTest(sn, "Erwan", "1234", "kthezelais", "Dune", Itemtype.BOOK, 3,
				"y.1", "reviewOpinion() doesn't reject a bad login");

		nbTests++;
		nbErrors += reviewOpinionNotMemberExceptionTest(sn, "esevellec", "1234", "Killian", "Dune", Itemtype.BOOK, 3,
				"y.1", "reviewOpinion() doesn't reject a bad login");

		nbTests++;
		nbErrors += reviewOpinionNotMemberExceptionTest(sn, "esevellec", "12345", "kthezelais", "Dune", Itemtype.BOOK,
				3, "y.2", "reviewOpinion() doesn't reject a bad password");

		// Tests NotItemException
		nbTests++;
		nbErrors += reviewOpinionNotItemExceptionTest(sn, "esevellec", "1234", "kthezelais", "Lord of the Rings",
				Itemtype.BOOK, 3, "y.3", "reviewOpinion() doesn't reject a non existing book title");

		nbTests++;
		nbErrors += reviewOpinionNotItemExceptionTest(sn, "esevellec", "1234", "kthezelais",
				"Alice's Adventures in Wonderland", Itemtype.FILM, 3, "y.4",
				"reviewOpinion() doesn't reject a non existing film title if a book have the same title");

		// Tests NotReviewException
		nbTests++;
		nbErrors += reviewOpinionNotReviewExceptionTest(sn, "esevellec", "1234", "kthezelais",
				"Alice's Adventures in Wonderland", Itemtype.BOOK, 5, "y.5",
				"reviewOpinion() doesn't reject a non existing review");

		// A user can't give an opinion on his own review
		nbTests++;
		nbErrors += reviewOpinionNotReviewExceptionTest(sn, "esevellec", "1234", "esevellec", "Dune", Itemtype.BOOK, 5,
				"y.6", "reviewOpinion() doesn't reject a opinion on the own");

		// Normals cases
		nbTests++;
		nbErrors += reviewOpinionOKTest(sn, "esevellec", "1234", "kthezelais", "Dune", Itemtype.BOOK, 2, "y.7");


		// test if the state of the system change
		nbTests++;
		if (nbMembers != sn.nbMembers()) {
			System.out.println("Error : the number of members was unexepectedly chaged by reviewOpinion()");
		}

		nbTests++;
		if (nbBooks != sn.nbBooks()) {
			System.out.println("Error : the number of books was unexepectedly chaged by review reviewOpinion()");
		}

		nbTests++;
		if (nbFilms != sn.nbFilms()) {
			System.out.println("Error : the number of films was unexepectedly chaged by review reviewOpinion()");
		}

		// Display final state of 'sn'
		System.out.println("Final state of the social network : " + sn);

		try {
			TestReport tr = new TestReport(nbTests, nbErrors);
			System.out.println("reviewOpinion : " + tr);
			return tr;
		} catch (NotTestReportException e) {
			System.out.println("Unexpected error in reviewOpinionTest test code - Can't return valuable test results");
			return null;
		}
	}

	public static void main(String[] args) {
		test();
	}
}
