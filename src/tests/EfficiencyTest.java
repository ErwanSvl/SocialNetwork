/**
 * @author Erwan SEVELLEC
 * @author Killian THEZELAIS
 */
package tests;

import exceptions.NotTestReportException;
import opinion.ISocialNetworkPremium;
import opinion.ISocialNetworkPremium.Itemtype;
import opinion.SocialNetwork;

public class EfficiencyTest {

	public static TestReport test() {
		int nbTests = 0;
		int nbErrors = 0;

		ISocialNetworkPremium sn = new SocialNetwork();

		try {
			for (int i = 0; i < 500; i++) {
				sn.addMember("member" + i, "1234", "profil");
			}
			for (int i = 0; i < 2500; i++) {
				sn.addItemBook("member1", "1234", "book" + i, "kind", "author", 5);
			}
			for (int i = 0; i < 2500; i++) {
				sn.addItemFilm("member1", "1234", "film" + i, "kind", "director", "scenarist", 15);
			}
		} catch (Exception e) {
			System.out.println("Err : there was an error during adding members and items in the social Network");
			e.printStackTrace();
		}

		// Test nbMember
		long startTime = System.nanoTime();
		nbTests++;
		sn.nbMembers();
		long endTime = System.nanoTime();
		float nbMilliSec = (endTime - startTime) / 1000000;
		if (nbMilliSec > 1000) { // Time in millisecond
			System.out.println("Err : nbMembers take " + nbMilliSec + " milliseconds");
			nbErrors++;
		}

		// Test nbBooks
		startTime = System.nanoTime();
		nbTests++;
		sn.nbBooks();
		endTime = System.nanoTime();
		nbMilliSec = (endTime - startTime) / 1000000;
		if (nbMilliSec > 1000) { // Time in millisecond
			System.out.println("Err : nbBooks take " + nbMilliSec + " milliseconds");
			nbErrors++;
		}

		// Test nbFilms
		startTime = System.nanoTime();
		nbTests++;
		sn.nbFilms();
		endTime = System.nanoTime();
		nbMilliSec = (endTime - startTime) / 1000000;
		if (nbMilliSec > 1000) { // Time in millisecond
			System.out.println("Err : nbFilms take " + nbMilliSec + " milliseconds");
			nbErrors++;
		}

		// Test addMember
		startTime = System.nanoTime();
		nbTests++;
		try {
			sn.addMember("onemore", "1234", "fast pls");
		} catch (Exception e) {
			System.out.println("Err : there was an error during adding member in the social Network");
			e.printStackTrace();
		}
		endTime = System.nanoTime();
		nbMilliSec = (endTime - startTime) / 1000000;
		if (nbMilliSec > 1000) { // Time in millisecond
			System.out.println("Err : addMember take " + nbMilliSec + " milliseconds");
			nbErrors++;
		}

		// Test AddItemBook
		startTime = System.nanoTime();
		nbTests++;
		try {
			sn.addItemBook("member1", "1234", "superbook", "kind", "author", 20);
		} catch (Exception e) {
			System.out.println("Err : there was an error during adding book in the social Network");
			e.printStackTrace();
		}
		endTime = System.nanoTime();
		nbMilliSec = (endTime - startTime) / 1000000;
		if (nbMilliSec > 1000) { // Time in millisecond
			System.out.println("Err : addItemBook take " + nbMilliSec + " milliseconds");
			nbErrors++;
		}

		// Test AddItemFilm
		startTime = System.nanoTime();
		nbTests++;
		try {
			sn.addItemFilm("Member1", "1234", "superfilm", "kind", "director", "scenarist", 47);
		} catch (Exception e) {
			System.out.println("Err : there was an error during adding film in the social Network");
			e.printStackTrace();
		}
		endTime = System.nanoTime();
		nbMilliSec = (endTime - startTime) / 1000000;
		if (nbMilliSec > 1000) { // Time in millisecond
			System.out.println("Err : addItemFilm take " + nbMilliSec + " milliseconds");
			nbErrors++;
		}

		// Test reviewItemBook
		startTime = System.nanoTime();
		nbTests++;
		try {
			sn.reviewItemBook("member1", "1234", "superbook", 3, "comment");
		} catch (Exception e) {
			System.out.println("Err : there was an error during reviewing book in the social Network");
			e.printStackTrace();
		}
		endTime = System.nanoTime();
		nbMilliSec = (endTime - startTime) / 1000000;
		if (nbMilliSec > 1000) { // Time in millisecond
			System.out.println("Err : reviewItemBook take " + nbMilliSec + " milliseconds");
			nbErrors++;
		}

		// Test reviewItemFilm
		startTime = System.nanoTime();
		nbTests++;
		try {
			sn.reviewItemFilm("member1", "1234", "superfilm", 3, "comment");
		} catch (Exception e) {
			System.out.println("Err : there was an error during reviewing film in the social Network");
			e.printStackTrace();
		}
		endTime = System.nanoTime();
		nbMilliSec = (endTime - startTime) / 1000000;
		if (nbMilliSec > 1000) { // Time in millisecond
			System.out.println("Err : reviewItemFilm take " + nbMilliSec + " milliseconds");
			nbErrors++;
		}

		// Test consultItem
		startTime = System.nanoTime();
		nbTests++;
		try {
			sn.consultItems("superfilm");
		} catch (Exception e) {
			System.out.println("Err : there was an error during consultItems film in the social Network");
			e.printStackTrace();
		}
		endTime = System.nanoTime();
		nbMilliSec = (endTime - startTime) / 1000000;
		if (nbMilliSec > 1000) { // Time in millisecond
			System.out.println("Err : consultItems take " + nbMilliSec + " milliseconds");
			nbErrors++;
		}

		// Test reviewOpinion
		startTime = System.nanoTime();
		nbTests++;
		try {
			sn.reviewOpinion("member2", "1234", "member1", "superBook", Itemtype.BOOK, 3);
		} catch (Exception e) {
			System.out.println("Err : there was an error during reviewOpinion film in the social Network");
			e.printStackTrace();
		}
		endTime = System.nanoTime();
		nbMilliSec = (endTime - startTime) / 1000000;
		if (nbMilliSec > 1000) { // Time in millisecond
			System.out.println("Err : reviewOpinion take " + nbMilliSec + " milliseconds");
			nbErrors++;
		}

		try {
			TestReport tr = new TestReport(nbTests, nbErrors);
			System.out.println("Rendement Test : " + tr);
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
