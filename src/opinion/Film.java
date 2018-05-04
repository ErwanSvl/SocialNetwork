package opinion;

public class Film extends Item {
	private String director;
	private String scenarist;
	private int duration;

	public Film(String title, String kind, String director, String scenarist, int duration) {
		super(title, kind);
		this.director = director;
		this.scenarist = scenarist;
		this.duration = duration;
	}

	@Override
	public String toString() {
		String book = new String();
		book = super.toString();
		book += " réalisateur du film : " + this.director + ", scénariste : " + this.scenarist + ", durée : "
				+ this.duration;
		return book;
	}
}
