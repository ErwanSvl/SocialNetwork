/**
 * @author Erwan SEVELLEC
 * @author Killian THEZELAIS
 */
package opinion;

public class Opinion {
	
	private float mark;
	private Member author;
	
	public Opinion(Member member, float mark) {
		this.author = member;
		this.mark = mark;
	}
	
	public float getMark() {
		return mark;
	}
	
	public Member getAuthor() {
		return author;
	}
	
	public void setMark(float mark) {
		this.mark = mark;
	}
	
	@Override
	public String toString() {
		return author + " : " + mark;
	}
	
	//No test main, only getters and setters
}
