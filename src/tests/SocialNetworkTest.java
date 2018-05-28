package tests;

import opinion.EfficiencyTest;
import opinion.Item;
import opinion.Member;
import opinion.Review;

/**
 * This class launches a test suite for the SocialNetwork
 * 
 * @author B. Prou, GO
 * @version V2.0 - April 2018
 * 
 */
public class SocialNetworkTest {

	/**
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {

		try {
			TestReport testSuiteReport = new TestReport(0, 0);
			TestReport tr;
			tr = InitTest.test();
			testSuiteReport.add(tr);
			System.out.println(
					"\n\n **********************************************************************************************\n");
			//
			tr = AddMemberTest.test();
			testSuiteReport.add(tr);
			System.out.println(
					"\n\n **********************************************************************************************\n");
			//
			tr = AddItemBookTest.test();
			testSuiteReport.add(tr);
			System.out.println(
					"\n\n **********************************************************************************************\n");
			//
			tr = AddItemFilmTest.test();
			testSuiteReport.add(tr);
			System.out.println(
					"\n\n **********************************************************************************************\n");
			//
			tr = ReviewItemBookTest.test();
			testSuiteReport.add(tr);
			System.out.println(
					"\n\n **********************************************************************************************\n");
			//
			tr = ReviewItemBookTest.test();
			testSuiteReport.add(tr);
			System.out.println(
					"\n\n **********************************************************************************************\n");
			//
			tr = consultItemsTest.test();
			testSuiteReport.add(tr);
			System.out.println(
					"\n\n **********************************************************************************************\n");
			//
			tr = reviewOpinionTest.test();
			testSuiteReport.add(tr);
			System.out.println(
					"\n\n **********************************************************************************************\n");
			/* ---------------- Test the public methods in class ---------------------*/
			//
			tr = Review.test();
			testSuiteReport.add(tr);
			System.out.println(
					"\n\n **********************************************************************************************\n");
			//
			tr = Member.test();
			testSuiteReport.add(tr);
			System.out.println(
					"\n\n **********************************************************************************************\n");
			//
			tr = Item.test();
			testSuiteReport.add(tr);
			System.out.println(
					"\n\n **********************************************************************************************\n");
			/* ---------------- Efficiency test ---------------------*/
			//
			tr = EfficiencyTest.test();
			testSuiteReport.add(tr);
			System.out.println(
					"\n\n **********************************************************************************************\n");
			// End of the test suite : give some feedback to the tester
			System.out.println("Global tests results :   \n" + testSuiteReport);
		} catch (Exception e) {
			System.out.println("ERROR : Some exception was throw unexpectedly");
		}

	}

}
