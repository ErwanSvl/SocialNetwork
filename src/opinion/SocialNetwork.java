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

	private int nbFilms = 0;
	private int nbBooks = 0;

	private static enum ItemType {
		BOOK, FILM
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
	private void testItemExist(String title, ItemType itemType) throws ItemBookAlreadyExistsException {
		for (Iterator<Item> it = items.iterator(); it.hasNext();) {
			Item item = (Item) it.next();
			if (item.getTitle().toLowerCase().equals(title.trim().toLowerCase())) {
				if (ItemType.BOOK.equals(itemType)) {
					if (item instanceof Book) {
						throw new ItemBookAlreadyExistsException();
					}
					return;
				} else {
					if (item instanceof Film) {
						throw new ItemBookAlreadyExistsException();
					}
					return;
				}
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
	private boolean isMemberExist(String login) {
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
	private void testMemberCorrect(String login, String password) throws NotMemberException {
		for (Iterator<Member> it = members.iterator(); it.hasNext();) {
			Member member = (Member) it.next();
			if (member.getLogin().toLowerCase().equals(login.trim().toLowerCase())) {
				if (!member.getPassword().equals(password))
					throw new NotMemberException("Password is not correct for this member");

				else
					return;
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

	private void testItemParameterCorrect(String login, String password, String title, String kind)
			throws BadEntryException {
		if (login == null || login.trim().length() == 0) {
			throw new BadEntryException("Login must be instantiate and contains at least one non-space character");
		} else if (password == null || password.trim().length() < 4) {
			throw new BadEntryException(
					"Password must be instantiate and contains at least 3 characters (not taking into account leading or trailing blanks)");
		} else if (title == null || title.trim().length() == 0) {
			throw new BadEntryException("Title must be instantiate and contains at least one non-space character");
		} else if (kind == null) {
			throw new BadEntryException("Kind of book must be instantiate");
		}
	}
	
	private void testBookParameterCorrect(String login, String password, String title, String kind, String author, int nbPages) throws BadEntryException {
		testItemParameterCorrect(login, password, title, kind);
		if (author == null) {
			throw new BadEntryException("Author must be instantiate");
		} else if (nbPages <= 0) {
			throw new BadEntryException("Number of page must be instantiate");
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
		return nbFilms;
	}

	@Override
	public int nbBooks() {
		return nbBooks;
	}

	@Override
	public void addMember(String login, String password, String profile)
			throws BadEntryException, MemberAlreadyExistsException {
		testMemberParametersCorrect(login, password, profile);
		if (isMemberExist(login)) {
			throw new MemberAlreadyExistsException();
		}
		members.add(new Member(login.trim(), password, profile.trim()));
	}

	@Override
	public void addItemFilm(String login, String password, String title, String kind, String director, String scenarist,
			int duration) throws BadEntryException, NotMemberException, ItemFilmAlreadyExistsException {
		// TODO Auto-generated method stub
	}

	@Override
	public void addItemBook(String login, String password, String title, String kind, String author, int nbPages)
			throws BadEntryException, NotMemberException, ItemBookAlreadyExistsException {
		testBookParameterCorrect(login, password, title, kind, author, nbPages);
		testMemberCorrect(login, password);
		testItemExist(title, ItemType.BOOK);
		items.add(new Book(title.trim(), kind.trim(), author.trim(), nbPages));
		nbBooks++;
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
		// TODO Auto-generated method stub
		return 0;
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
		return socialNetwork;
	}

}
