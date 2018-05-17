/**
 * @author Erwan SEVELLEC
 * @author Killian THEZELAIS
 */
package tests;

import exceptions.BadEntryException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;

public class ReviewItemFilmTest {

	private static int reviewItemFilmBadEntryTest(ISocialNetwork sn, String login, String password, String title,
			float mark, String comment, String testId, String errorMessage) {
		try {
			sn.reviewItemFilm(login, password, title, mark, comment);
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

	private static int reviewItemFilmNotMemberExceptionTest(ISocialNetwork sn, String login, String password,
			String title, float mark, String comment, String testId, String errorMessage) {
		try {
			sn.reviewItemFilm(login, password, title, mark, comment);
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

	private static int reviewItemFilmNotItemExceptionTest(ISocialNetwork sn, String login, String password,
			String title, float mark, String comment, String testId, String errorMessage) {
		try {
			sn.reviewItemFilm(login, password, title, mark, comment);
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

	private static int reviewItemFilmOKTest(ISocialNetwork sn, String login, String password, String title, float mark,
			String comment, float expectedMean, String testId) {
		try {
			float returnedMark = sn.reviewItemFilm(login, password, title, mark, comment);
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

	public static TestReport test() {
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
			sn.addItemFilm("esevellec", "1234", "Star Wars: Episode IV – A New Hope", "science-fiction", "George Lucas",
					"George Lucas", 121);
		} catch (Exception e) {
			System.out.println("addItemFilm() throw an exception");
			e.printStackTrace();
		}

		int nbBooks = sn.nbBooks();
		int nbMembers = sn.nbMembers();
		int nbFilms = sn.nbFilms();

		System.out.println("Testint reviewItemFilm()");

		// test 8

		nbTests++;
		nbErrors += reviewItemFilmBadEntryTest(sn, null, "1234", "Star Wars: Episode IV – A New Hope", 3, "Great film!",
				"8.1", "reviewItemFilm() doesn't reject null login");

		nbTests++;
		nbErrors += reviewItemFilmBadEntryTest(sn, " ", "1234", "Star Wars: Episode IV – A New Hope", 3, "Great film!",
				"8.2",
				"reviewItemFilm() doesn't reject logins that don't contain at least one character other than space");

		nbTests++;
		nbErrors += reviewItemFilmBadEntryTest(sn, "esevellec", null, "Star Wars: Episode IV – A New Hope", 3,
				"Great film!", "8.3", "reviewItemfilm() doesn't reject null password");

		nbTests++;
		nbErrors += reviewItemFilmBadEntryTest(sn, "esevellec", "  123 ", "Star Wars: Episode IV – A New Hope", 3,
				"Great film!", "8.4",
				"reviewItemFilm() doesn't reject that don't contain at least 4 characters (not taking into account leading or trailing blanks)");

		nbTests++;
		nbErrors += reviewItemFilmBadEntryTest(sn, "esevellec", "1234", null, 3, "Great film!", "8.5",
				"reviewItemFilm() doesn't reject null title");

		nbTests++;
		nbErrors += reviewItemFilmBadEntryTest(sn, "esevellec", "1234", "    ", 3, "Great film!", "8.6",
				"reviewItemFilm() doesn't reject only space title");

		nbTests++;
		nbErrors += reviewItemFilmBadEntryTest(sn, "esevellec", "1234", "Star Wars: Episode IV – A New Hope", -1,
				"Great film!", "8.7", "reviewItemFilm() doesn't reject a negative mark");

		nbTests++;
		nbErrors += reviewItemFilmBadEntryTest(sn, "esevellec", "1234", "Star Wars: Episode IV – A New Hope", 6,
				"Great film!", "8.8", "reviewItemFilm() doesn't reject a mark greater than 5");

		nbTests++;
		nbErrors += reviewItemFilmBadEntryTest(sn, "esevellec", "1234", "Star Wars: Episode IV – A New Hope", 3, null,
				"8.9", "reviewItemFilm() doesn't reject null comments");

		// Test n°9

		nbTests++;
		nbErrors += reviewItemFilmNotMemberExceptionTest(sn, "Erwan", "1234", "Star Wars: Episode IV – A New Hope", 3,
				"Great Film!", "9.1", "reviewItemFilm() doesn't reject a bad login");

		nbTests++;
		nbErrors += reviewItemFilmNotMemberExceptionTest(sn, "esevellec", "12345", "Star Wars: Episode IV – A New Hope",
				3, "Great Film!", "9.2", "reviewItemFilm() doesn't reject a bad password");

		nbTests++;
		nbErrors += reviewItemFilmNotItemExceptionTest(sn, "esevellec", "1234", "Dune", 3,
				"Great Film!", "9.3", "reviewItemFilm() doesn't reject a non existing film title");

		nbTests++;
		nbErrors += reviewItemFilmOKTest(sn, "esevellec", "1234", "Star Wars: Episode IV – A New Hope", 0,
				"Great Film!", 0, "9.4");

		nbTests++;
		nbErrors += reviewItemFilmOKTest(sn, "kthezelais", "1234", "Star Wars: Episode IV – A New Hope", 5,
				"May the force be with you", (float) 2.5, "9.5");

		nbTests++;
		if (nbFilms != sn.nbFilms()) {
			System.out.println("Error : the number of films was unexepectedly changed by addItemFilm()");
			nbErrors++;
		}

		nbTests++;
		if (nbMembers != sn.nbMembers()) {
			System.out.println("Error : the number of members was unexepectedly chaged by addItemFilm()");
		}

		nbTests++;
		if (nbBooks != sn.nbBooks()) {
			System.out.println("Error : the number of Films was unexepectedly chaged by reviewItemFilm()");
		}

		// Display final state of 'sn'
		System.out.println("Final state of the social network : " + sn);

		try {
			TestReport tr = new TestReport(nbTests, nbErrors);
			System.out.println("ReviewItemFilm : " + tr);
			return tr;
		} catch (NotTestReportException e) {
			System.out.println("Unexpected error in ReviewItemFilmTest test code - Can't return valuable test results");
			return null;
		}
	}

	public static void main(String[] args) {
		test();
	}

}
