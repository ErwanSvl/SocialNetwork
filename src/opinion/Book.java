package opinion;

public class Book extends Item {
	private String author;
	private int nbPages;

	public Book(String title, String kind, String author, int nbPages) {
		super(title, kind);
		this.author = author;
		this.nbPages = nbPages;
	}

	@Override
	public String toString() {
		String book = new String();
		book = super.toString();
		book += "   L'auteur du livre est " + this.author + " et il contient " + this.nbPages + " pages";
		return book;
	}
}
