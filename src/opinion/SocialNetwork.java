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

	
	private void isItemExist(String title, String itemType) throws ItemBookAlreadyExistsException {
		for(Iterator<Item> it = items.iterator(); it.hasNext();){
			Item item = (Item)it.next();
			if (title.equals(item.getTitle())) {
				if (itemType.equals("book")) {
					if (item instanceof Book) {
						throw new ItemBookAlreadyExistsException();
					}
				} else {
					if (item instanceof Film) {
						throw new ItemBookAlreadyExistsException();
					}
				}
			}
		}
	}
	
	/**isMemberCorrect(login, password);ItemBookAlreadyExistsException
	 * Test if a member with the same login exist in the <i>SocialNetwork</i>
	 * 
	 * @param login
	 *            login that must be verified
	 * @return true if the member already exist.
	 */
	private boolean isMemberExist(String login) {
		Iterator<Member> it = members.iterator();
		while (it.hasNext()) {
			if (it.next().isEquals(login))
				return true;
		}
		return false;
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
		if (login == null || login.trim().length() == 0) {
			throw new BadEntryException("Login must be instantiate and contains at least one non-space character");
		} else if (password == null || password.trim().length() < 4) {
			throw new BadEntryException(
					"Password must be instantiate and contains at least 3 characters (not taking into account leading or trailing blanks)");
		} else if (profile == null) {
			throw new BadEntryException("Profile must be instantiate");
		}
		if (isMemberExist(login)) {
			throw new MemberAlreadyExistsException();
		}
		members.add(new Member(login, password, profile));
	}

	@Override
	public void addItemFilm(String login, String password, String title, String kind, String director, String scenarist,
			int duration) throws BadEntryException, NotMemberException, ItemFilmAlreadyExistsException {
	// TODO Auto-generated method stub
	}
	
	@Override
	public void addItemBook(String login, String password, String title, String kind, String author, int nbPages)
			throws BadEntryException, NotMemberException, ItemBookAlreadyExistsException {
		if (login == null || login.trim().length() == 0) {
			throw new BadEntryException("Login must be instantiate and contains at least one non-space character");
		} else if (password == null || password.trim().length() < 4) {
			throw new BadEntryException(
					"Password must be instantiate and contains at least 3 characters (not taking into account leading or trailing blanks)");
		} else if (title == null) {
			throw new BadEntryException("Title must be instantiate");
		} else if (kind == null) {
			throw new BadEntryException("Kind of book must be instantiate");
		} else if (author == null) {
			throw new BadEntryException("Author must be instantiate");
		} else if (nbPage <= 0) {
			throw new BadEntryException("Number of page must be instantiate");
		}
		isMemberCorrect(login, password);
		isItemExist(title, "book");
		items.add(new Book(login, password, title, kind, author, nbPages));
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
		socialNetwork = "Liste des membres :\n";
		for (Iterator<Member> iterator = members.iterator(); iterator.hasNext();) {
			socialNetwork += iterator.next() + "\n";
		}
		return socialNetwork;
	}

}
