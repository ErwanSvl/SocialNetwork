/**
 * @author Erwan SEVELLEC
 * @author Killian THEZELAIS
 */
package tests;

import exceptions.BadEntryException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import opinion.ISocialNetwork;
import opinion.SocialNetwork;

public class AddItemFilmTest {

	private static int addItemFilmBadEntryTest(ISocialNetwork sn, String login, String password, String title,
			String kind, String director, String scenarist, int duration, String testId, String errorMessage) {
		int nbItemFilms = sn.nbFilms();
		try {
			sn.addItemFilm(login, password, title, kind, director, scenarist, duration);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (BadEntryException e) {
			if (sn.nbFilms() != nbItemFilms) {
				System.out.println("Err " + testId + " : BadEntry was thrown but the number of item films was changed");
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

	private static int addItemFilmNotMemberExceptionTest(ISocialNetwork sn, String login, String password, String title,
			String kind, String director, String scenarist, int duration, String testId, String errorMessage) {
		int nbItemsFilms = sn.nbFilms();

		try {
			sn.addItemFilm(login, password, title, kind, director, scenarist, duration);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (NotMemberException e) {
			if (nbItemsFilms != sn.nbFilms()) {
				System.out.println(
						"Err " + testId + " : NotMemberException was thrown but the number of item films was changed");
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

	private static int addItemFilmAleadyExistsException(ISocialNetwork sn, String login, String password, String title,
			String kind, String director, String scenarist, int duration, String testId, String errorMessage) {
		int nbItemsFilms = sn.nbFilms();

		try {
			sn.addItemFilm(login, password, title, kind, director, scenarist, duration);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (ItemFilmAlreadyExistsException e) {
			if (nbItemsFilms != sn.nbFilms()) {
				System.out.println("Err " + testId
						+ " : ItemFilmAlreadyExistsException was thrown but the number of item films was changed");
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

	private static int addItemFilmOKTest(ISocialNetwork sn, String login, String password, String title, String kind,
			String director, String scenarist, int duration, String testId) {
		int nbItemFilms = sn.nbFilms();
		try {
			sn.addItemFilm(login, password, title, kind, director, scenarist, duration);
			if (nbItemFilms + 1 != sn.nbFilms()) {
				System.out.println(
						"Err " + testId + " : the number of item films (" + nbItemFilms + ") was not incremented");
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
			System.out.println("Err : addMember() throw an exception");
			e.printStackTrace();
		}

		int nbBooks = sn.nbBooks();
		int nbMembers = sn.nbMembers();

		int nbTests = 0;
		int nbErrors = 0;

		System.out.println("Testing AddItemFilm()");

		// Test n°7
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, null, "1234", "Star Wars: Episode IV – A New Hope", "science-fiction",
				"George Lucas", "George Lucas", 121, "7.1", "addItemFilm() doesn't reject null login");

		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, " ", "1234", "Star Wars: Episode IV – A New Hope", "science-fiction",
				"George Lucas", "George Lucas", 121, "7.2",
				"addItemBook() doesn't reject logins that don't contain at least one character other than space");

		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "esevellec", null, "Star Wars: Episode IV – A New Hope",
				"science-fiction", "George Lucas", "George Lucas", 121, "7.3",
				"addItemFilm() doesn't reject null password");

		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "esevellec", "    123 ", "Star Wars: Episode IV – A New Hope",
				"science-fiction", "George Lucas", "George Lucas", 121, "7.4",
				"addItemFilm() doesn't reject that don't contain at least 4 characters (not taking into account leading or trailing blanks)");

		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "Erwan", "1234", null, "science-fiction", "George Lucas",
				"George Lucas", 121, "7.5", "addItemFilm() doesn't reject null title");

		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "esevellec", "1234", "       ", "science-fiction", "George Lucas",
				"George Lucas", 121, "7.6", "addItemFilm() doesn't reject only space title");

		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "esevellec", "1234", "Star Wars: Episode IV – A New Hope", null,
				"George Lucas", "George Lucas", 121, "7.7", "addItemFilm() doesn't reject null kind");

		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "esevellec", "1234", "Star Wars: Episode IV – A New Hope",
				"science-fiction", null, "George Lucas", 121, "7.8", "addItemFilm() doesn't reject null director");

		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "esevellec", "1234", "Star Wars: Episode IV – A New Hope",
				"science-fiction", "George Lucas", null, 121, "7.9", "addItemFilm() doesn't reject null scenarist");

		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "esevellec", "1234", "Star Wars: Episode IV – A New Hope",
				"science-fiction", "George Lucas", "George Lucas", 0, "7.10",
				"addItemBook() doesn't reject null duration");

		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "esevellec", "1234", "Star Wars: Episode IV – A New Hope",
				"science-fiction", "George Lucas", "George Lucas", -5, "7.11",
				"addItemBook() doesn't reject negative duration");

		// Test n°8

		nbTests++;
		nbErrors += addItemFilmNotMemberExceptionTest(sn, "Erwan", "1234", "Star Wars: Episode IV – A New Hope",
				"science-fiction", "George Lucas", "George Lucas", 121, "8.1",
				"addItemFilm() doesn't reject a bad login");

		nbTests++;
		nbErrors += addItemFilmNotMemberExceptionTest(sn, "esevellec", "12345", "Star Wars: Episode IV – A New Hope",
				"science-fiction", "George Lucas", "George Lucas", 121, "8.2",
				"addItemFilm() doesn't reject a bad password");

		nbTests++;
		nbErrors += addItemFilmOKTest(sn, "esevellec", "1234", "Star Wars: Episode IV – A New Hope", "science-fiction",
				"George Lucas", "George Lucas", 121, "8.3");

		nbTests++;
		nbErrors += addItemFilmOKTest(sn, "esevellec", "1234", "Dune", "science-fiction", "David Lynch", "David Lynch",
				130, "8.3");

		nbTests++;
		nbErrors += addItemFilmAleadyExistsException(sn, "esevellec", "1234", "Star Wars: Episode IV – A New Hope",
				"science-fiction", "George Lucas", "George Lucas", 121, "8.4",
				"addItemBook() doesn't reject a already existing film title");

		nbTests++;
		nbErrors += addItemFilmAleadyExistsException(sn, "esevellec", "1234", "Dune", "science-fiction", "David Lynch",
				"David Lynch", 130, "8.5", "addItemBook() doesn't reject a already existing film title");

		nbTests++;
		nbErrors += addItemFilmAleadyExistsException(sn, "esevellec", "1234", "StAr WARS: epIsode IV – A NEw HopE",
				"science-fiction", "George Lucas", "George Lucas", 121, "8.6",
				"addItemBook() doesn't reject a already existing film title with a different case");

		nbTests++;
		nbErrors += addItemFilmAleadyExistsException(sn, "esevellec", "1234",
				"     Star Wars: Episode IV – A New Hope  ", "science-fiction", "George Lucas", "George Lucas", 121,
				"8.7",
				"addItemBook() doesn't reject a already existing film title without taking into account leading or trailing blanks");

		nbTests++;
		if (nbBooks != sn.nbBooks()) {
			System.out.println("Error : the number of books was unexepectedly changed by addItemBook()");
			nbErrors++;
		}

		nbTests++;
		if (nbMembers != sn.nbMembers()) {
			System.out.println("Error : the number of members was unexepectedly chaged by addItemBook()");
		}

		// Display final state of 'sn'
		System.out.println("Final state of the social network : \n" + sn);

		try {
			TestReport tr = new TestReport(nbTests, nbErrors);
			System.out.println("AddItemFilm : " + tr);
			return tr;
		} catch (NotTestReportException e) {
			System.out.println("Unexpected error in AddItemFilmTest test code - Can't return valuable test results");
			return null;
		}
	}

	public static void main(String[] args) {
		test();
	}

}
