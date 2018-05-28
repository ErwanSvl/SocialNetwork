/**
 * @author Erwan SEVELLEC
 * @author Killian THEZELAIS
 */
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
		book += "   Le réalisateur du film est " + this.director + ", son scénariste est " + this.scenarist + " et le film dure "
				+ this.duration + " minutes.";
		return book;
	}
	
	//No test main only a constructor
}
