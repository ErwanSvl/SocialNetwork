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
import opinion.ISocialNetwork;

public class SocialNetwork implements ISocialNetwork {

	private ArrayList<Member> members = new ArrayList<Member>();
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<Review> reviews = new ArrayList<Review>();

	private enum Itemtype {
		BOOK, FILM
	}

	/**
	 * 
	 * @param login
	 * @param password
	 * @param title
	 * @param kind
	 * @param director
	 * @param scenarist
	 * @param duration
	 * @throws BadEntryException
	 */
	private void testFilmParameterCorrect(String title, String kind, String director, String scenarist, int duration)
			throws BadEntryException {
		testItemParameterCorrect(title, kind);
		if (scenarist == null) {
			throw new BadEntryException("Author must be instantiate");
		} else if (director == null) {
			throw new BadEntryException("Director must be instantiate");
		} else if (duration <= 0) {
			throw new BadEntryException("Duration must be instantiate");
		}
	}

	/**
	 * Test if a Item with the same type and the same title exist
	 * 
	 * @param title
	 * @param itemtype
	 * @throws NotItemException
	 */
	private Item testItemExist(String title, Itemtype itemtype) throws NotItemException {
		for (Iterator<Item> iterator = items.iterator(); iterator.hasNext();) {
			Item item = (Item) iterator.next();
			if (item.getTitle().toLowerCase().equals(title.trim().toLowerCase())) {
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
	 * 
	 * @param title
	 * @throws ItemBookAlreadyExistsException
	 */
	private void testBookExist(String title) throws ItemBookAlreadyExistsException {
		for (Iterator<Item> it = items.iterator(); it.hasNext();) {
			Item item = (Item) it.next();
			if (item.getTitle().toLowerCase().equals(title.trim().toLowerCase())) {
				if (item instanceof Book) {
					throw new ItemBookAlreadyExistsException();
				}
				return;
			}
		}
	}

	/**
	 * Test if an item with the same title exist
	 * 
	 * @param title
	 * @param itemType
	 *            the type of item you try to insert
	 * @throws ItemBookAlreadyExistsException
	 *             throw an exception if there is an item of the same type with the
	 *             same title
	 */
	private void testFilmExist(String title) throws ItemFilmAlreadyExistsException {
		for (Iterator<Item> it = items.iterator(); it.hasNext();) {
			Item item = (Item) it.next();
			if (item.getTitle().toLowerCase().equals(title.trim().toLowerCase())) {
				if (item instanceof Film) {
					throw new ItemFilmAlreadyExistsException();
				}
				return;
			}
		}
	}

	/**
	 * Test if a member with the same login exist in the <i>SocialNetwork</i>
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
	 * @param password
	 * @throws NotMemberException
	 *             throw an exception if the member doesn't exist or if the password
	 *             isn't correct
	 */
	private Member testMemberCorrect(String login, String password) throws BadEntryException, NotMemberException {
		if (login == null || login.trim().length() == 0) {
			throw new BadEntryException("Login must be instantiate and contains at least one non-space character");
		} else if (password == null || password.trim().length() < 4) {
			throw new BadEntryException(
					"Password must be instantiate and contains at least 3 characters (not taking into account leading or trailing blanks)");
		}

		for (Iterator<Member> it = members.iterator(); it.hasNext();) {
			Member member = (Member) it.next();
			if (member.getLogin().toLowerCase().equals(login.trim().toLowerCase())) {
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
	private void testItemParameterCorrect(String title, String kind) throws BadEntryException {
		if (title == null || title.trim().length() == 0) {
			throw new BadEntryException("Title must be instantiate and contains at least one non-space character");
		} else if (kind == null) {
			throw new BadEntryException("Kind of book must be instantiate");
		}
	}

	/**
	 * 
	 * @param title
	 * @param kind
	 * @param author
	 * @param nbPages
	 * @throws BadEntryException
	 */
	private void testBookParameterCorrect(String title, String kind, String author, int nbPages)
			throws BadEntryException {
		testItemParameterCorrect(title, kind);
		if (author == null) {
			throw new BadEntryException("Author must be instantiate");
		} else if (nbPages <= 0) {
			throw new BadEntryException("Number of page must be instantiate");
		}
	}

	private void testReviewParameter(String title, float mark, String comment) throws BadEntryException {
		if (title == null || title.trim().length() == 0) {
			throw new BadEntryException("Title must be instantiate and contains at least one non-space character");
		} else if (mark < 0 || mark > 5) {
			throw new BadEntryException("Mark must be between 0 and 5");
		} else if (comment == null) {
			throw new BadEntryException("Comment must be instantiate");
		}
	}

	private float getMean(Item item) {
		float sum = 0;
		int nbReview = 0;
		for (Iterator<Review> it = reviews.iterator(); it.hasNext();) {
			Review review = (Review) it.next();
			if (review.getItem().equals(item)) {
				sum += review.getMark();
				nbReview++;
			}
		}
		return sum / nbReview;
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
		testFilmParameterCorrect(title, kind, director, scenarist, duration);
		testMemberCorrect(login, password);
		testFilmExist(title);
		items.add(new Film(title.trim(), kind.trim(), director.trim(), scenarist.trim(), duration));
	}

	@Override
	public void addItemBook(String login, String password, String title, String kind, String author, int nbPages)
			throws BadEntryException, NotMemberException, ItemBookAlreadyExistsException {
		testBookParameterCorrect(title, kind, author, nbPages);
		testMemberCorrect(login, password);
		testBookExist(title);
		items.add(new Book(title.trim(), kind.trim(), author.trim(), nbPages));
	}

	@Override
	public float reviewItemFilm(String login, String password, String title, float mark, String comment)
			throws BadEntryException, NotMemberException, NotItemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float reviewItemBook(String login, String password, String title, float mark, String comment)
			throws BadEntryException, NotMemberException, NotItemException {
		testReviewParameter(title, mark, comment);
		Item item = testItemExist(title, Itemtype.BOOK);
		Member member = testMemberCorrect(login, password);
		reviews.add(new Review(mark, comment, member, item));
		return getMean(item);
	}

	@Override
	public LinkedList<String> consultItems(String title) throws BadEntryException {
		// TODO Auto-generated method stub
		return null;
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

		if (reviews.size() == 0) {
			socialNetwork += "Aucune évaluations dans le système\n";
		} else {
			socialNetwork += "Liste des évaluations :\n";
			for (Iterator<Review> it = reviews.iterator(); it.hasNext();) {
				socialNetwork += " - " + it.next() + "\n";

			}
			socialNetwork += "\n";
		}
		return socialNetwork;
	}

}
