/**
 * @author Erwan SEVELLEC
 * @author Killian THEZELAIS
 */

package opinion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;

public class SocialNetwork implements ISocialNetwork {

	// Generate an empty list of members and an empty list of items (film or book)
	private ArrayList<Member> members = new ArrayList<Member>();
	private ArrayList<Item> items = new ArrayList<Item>();

	private enum Itemtype {
		BOOK, FILM
	}

	/**
	 * Test if a Item with the same type and the same title exist
	 * 
	 * @param title
	 * @param itemtype
	 * @return the item with this type and this title
	 * @throws NotItemException
	 */
	private Item testItemExist(String title, Itemtype itemtype) throws NotItemException {
		for (Iterator<Item> iterator = items.iterator(); iterator.hasNext();) {
			Item item = (Item) iterator.next();
			if (item.getTitle().equalsIgnoreCase(title.trim())) {
				// Test if the item is the same type as the requested one
				if (itemtype == Itemtype.BOOK) {
					if (item instanceof Book) {
						return item;
					}
				} else if (itemtype == Itemtype.FILM) {
					if (item instanceof Film) {
						return item;
					}
				}
			}
		}
		throw new NotItemException("There is no item with this title");
	}

	/**
	 * Test if a Book with the same title already exist
	 * 
	 * @param title
	 *            the book's title which have to be tested
	 * @throws ItemBookAlreadyExistsException
	 *             a book with the same title is already registered in the
	 *             <i>SocialNetwork</i> (same title : not case-sensitive and
	 *             leadings/trailings blanks are not taken into account)
	 * 
	 */
	private void testBookExist(String title) throws ItemBookAlreadyExistsException {
		for (Iterator<Item> it = items.iterator(); it.hasNext();) {
			Item item = (Item) it.next();
			if (item.getTitle().equalsIgnoreCase(title.trim())) {
				if (item instanceof Book) {
					throw new ItemBookAlreadyExistsException();
				}
			}
		}
	}

	/**
	 * Test if a Film with the same title already exist
	 * 
	 * @param title
	 *            the film's title which have to be tested
	 * @throws ItemFilmAlreadyExistsException
	 *             a film with the same title is already registered in the
	 *             <i>SocialNetwork</i> (same title : not case-sensitive and
	 *             leadings/trailings blanks are not taken into account)
	 * 
	 */
	private void testFilmExist(String title) throws ItemFilmAlreadyExistsException {
		for (Iterator<Item> it = items.iterator(); it.hasNext();) {
			Item item = (Item) it.next();
			if (item.getTitle().equalsIgnoreCase(title.trim())) {
				if (item instanceof Film) {
					throw new ItemFilmAlreadyExistsException();
				}
			}
		}
	}

	/**
	 * Test if a member with the same login already exist in the
	 * <i>SocialNetwork</i>
	 * 
	 * @param login
	 *            login that must be verified
	 * @return true if the member already exist.
	 */
	private boolean isMemberLoginExist(String login) {
		for (Iterator<Member> it = members.iterator(); it.hasNext();) {
			Member member = (Member) it.next();
			if (member.isEquals(login)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Test if a member exist and if the password is correct
	 * 
	 * @param login
	 *            login of the member which have to be tested
	 * @param password
	 *            password of the member which have to be tested
	 * @return the member with this login and this password
	 * @throws BadEntryException
	 *             <ul>
	 *             <li>if login is not instantiated or contains less than one
	 *             non-space character</li>
	 *             <li>if password is not instantiated or contains less than four
	 *             characters (not taking into account leading or trailing
	 *             blanks)</li>
	 *             <li>if title is not instantiated or contains less than one
	 *             non-space character</li> <br>
	 * @throws NotMemberException
	 *             throw an exception if the member doesn't exist or if the password
	 *             isn't correct
	 * 
	 */
	private Member testMemberCorrect(String login, String password) throws BadEntryException, NotMemberException {
		if (login == null || login.trim().length() == 0) {
			throw new BadEntryException("Login must be instantiate and contains at least one non-space character");
		} else if (password == null || password.trim().length() < 4) { // Space in password are not trim
			throw new BadEntryException(
					"Password must be instantiate and contains at least 3 characters (not taking into account leading or trailing blanks)");
		}

		for (Iterator<Member> it = members.iterator(); it.hasNext();) {
			Member member = (Member) it.next();
			if (member.getLogin().equalsIgnoreCase(login.trim())) {
				if (!member.getPassword().equals(password))
					throw new NotMemberException("Password is not correct for this member");

				else
					return member;
			}
		}
		throw new NotMemberException("This member doesn't exist");
	}

	/**
	 * Test if members parameters are correct
	 * 
	 * @param login
	 * @param password
	 * @param profile
	 * @throws BadEntryException
	 *             throw an exception if a least one parameter is not correct
	 */
	private void testMemberParametersCorrect(String login, String password, String profile) throws BadEntryException {
		if (login == null || login.trim().length() == 0) {
			throw new BadEntryException("Login must be instantiate and contains at least one non-space character");
		} else if (password == null || password.trim().length() < 4) {
			throw new BadEntryException(
					"Password must be instantiate and contains at least 3 characters (not taking into account leading or trailing blanks)");
		} else if (profile == null) {
			throw new BadEntryException("Profile must be instantiate");
		}
	}

	/**
	 * Test if parameters for an item are correct
	 * 
	 * @param title
	 * @param kind
	 * @throws BadEntryException
	 *             throw an exception if at least one parameter is not correct
	 */
	private void testItemParameters(String title, String kind) throws BadEntryException {
		if (title == null || title.trim().length() == 0) {
			throw new BadEntryException("Title must be instantiate and contains at least one non-space character");
		} else if (kind == null) {
			throw new BadEntryException("Kind of book must be instantiate");
		}
	}

	/**
	 * Test if parameters for a book are correct
	 * 
	 * @param title
	 * @param kind
	 * @param author
	 * @param nbPages
	 * @throws BadEntryException
	 */
	private void testBookParameters(String title, String kind, String author, int nbPages) throws BadEntryException {
		testItemParameters(title, kind);
		if (author == null) {
			throw new BadEntryException("Author must be instantiate");
		} else if (nbPages <= 0) {
			throw new BadEntryException("Number of page must be instantiate");
		}
	}

	/**
	 * Test if the parameters for a film is correct
	 * 
	 * @param title
	 *            the new film's title
	 * @param kind
	 *            the new film's kind (adventure, thriller, etc.)
	 * @param director
	 *            the new film's director
	 * @param scenarist
	 *            the new film's scenarist
	 * @param duration
	 *            the new film's duration (in minutes)
	 * @throws BadEntryException
	 *             <ul>
	 *             <li>if title is not instantiated or contains less than one
	 *             non-space character</li>
	 *             <li>if kind is not instantiated</li>
	 *             <li>if scenarist is not instantiated</li>
	 *             <li>if director is not instantiated</li>
	 *             <li>if duration is not strictly positive</li>
	 *             </ul>
	 *             <br>
	 */
	private void testFilmParameters(String title, String kind, String director, String scenarist, int duration)
			throws BadEntryException {
		testItemParameters(title, kind);
		if (scenarist == null) {
			throw new BadEntryException("Author must be instantiate");
		} else if (director == null) {
			throw new BadEntryException("Director must be instantiate");
		} else if (duration <= 0) {
			throw new BadEntryException("Duration must be instantiate");
		}
	}

	/**
	 * Test if the parameters for a review are correct
	 * 
	 * @param title
	 * @param mark
	 * @param comment
	 * @throws BadEntryException
	 */
	private void testReviewParameters(String title, float mark, String comment) throws BadEntryException {
		if (title == null || title.trim().length() == 0) {
			throw new BadEntryException("Title must be instantiate and contains at least one non-space character");
		} else if (mark < 0 || mark > 5) {
			throw new BadEntryException("Mark must be between 0 and 5");
		} else if (comment == null) {
			throw new BadEntryException("Comment must be instantiate");
		}
	}

	/**
	 * return the number of member in the <i>SocialNetwork</i>
	 */
	@Override
	public int nbMembers() {
		return members.size();
	}

	@Override
	public int nbFilms() {
		int nbFilms = 0;
		for (Iterator<Item> iterator = items.iterator(); iterator.hasNext();) {
			Item item = (Item) iterator.next();
			if (item instanceof Film) {
				nbFilms++;
			}
		}
		return nbFilms;
	}

	@Override
	public int nbBooks() {
		int nbBooks = 0;
		for (Iterator<Item> iterator = items.iterator(); iterator.hasNext();) {
			Item item = (Item) iterator.next();
			if (item instanceof Book) {
				nbBooks++;
			}
		}
		return nbBooks;
	}

	@Override
	public void addMember(String login, String password, String profile)
			throws BadEntryException, MemberAlreadyExistsException {
		testMemberParametersCorrect(login, password, profile);
		if (isMemberLoginExist(login)) {
			throw new MemberAlreadyExistsException();
		}
		members.add(new Member(login.trim(), password, profile.trim()));
	}

	@Override
	public void addItemFilm(String login, String password, String title, String kind, String director, String scenarist,
			int duration) throws BadEntryException, NotMemberException, ItemFilmAlreadyExistsException {
		testFilmParameters(title, kind, director, scenarist, duration);
		testMemberCorrect(login, password);
		testFilmExist(title);
		items.add(new Film(title.trim(), kind.trim(), director.trim(), scenarist.trim(), duration));
	}

	@Override
	public void addItemBook(String login, String password, String title, String kind, String author, int nbPages)
			throws BadEntryException, NotMemberException, ItemBookAlreadyExistsException {
		testBookParameters(title, kind, author, nbPages);
		testMemberCorrect(login, password);
		testBookExist(title);
		items.add(new Book(title.trim(), kind.trim(), author.trim(), nbPages));
	}

	@Override
	public float reviewItemFilm(String login, String password, String title, float mark, String comment)
			throws BadEntryException, NotMemberException, NotItemException {
		testReviewParameters(title, mark, comment);
		Item item = testItemExist(title, Itemtype.FILM);
		Member member = testMemberCorrect(login, password);
		Review review = member.getExistingReview(item);
		if (review!= null) {
			member.modifyReview(review, mark, comment);
		} else {
			item.addReview(new Review(mark, comment, member, item));
			member.addReview(new Review(mark, comment, member, item));
		}
		return item.getMean();
	}

	@Override
	public float reviewItemBook(String login, String password, String title, float mark, String comment)
			throws BadEntryException, NotMemberException, NotItemException {
		testReviewParameters(title, mark, comment);
		Item item = testItemExist(title, Itemtype.BOOK);
		Member member = testMemberCorrect(login, password);
		item.addReview(new Review(mark, comment, member, item));
		return item.getMean();
	}

	@Override
	public LinkedList<String> consultItems(String title) throws BadEntryException {
		if (title == null || title.trim().length() < 1) {
			throw new BadEntryException("Title must be instantiate and contains at least one non-space character");
		}
		LinkedList<String> list = new LinkedList<String>();
		for (Iterator<Item> it = items.iterator(); it.hasNext();) {
			Item item = (Item) it.next();
			if (item.getTitle().equalsIgnoreCase(title)) {
				list.add(item.toString());
			}
		}
		return list;
	}

	@Override
	public String toString() {
		String socialNetwork = new String();
		if (members.size() == 0) {
			socialNetwork = "Aucun membre dans le système\n";
		} else {
			socialNetwork = "Liste des membres :\n";
			for (Iterator<Member> iterator = members.iterator(); iterator.hasNext();) {
				socialNetwork += " - " + iterator.next() + "\n";
			}
			socialNetwork += "\n";
		}
		if (items.size() == 0) {
			socialNetwork += "Aucun item dans le système\n";
		} else {
			socialNetwork += "Liste des items :\n";
			for (Iterator<Item> it = items.iterator(); it.hasNext();) {
				socialNetwork += " - " + it.next() + "\n";

			}
			socialNetwork += "\n";
		}
		return socialNetwork;
	}

}
