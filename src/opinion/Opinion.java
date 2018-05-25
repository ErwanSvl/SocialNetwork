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
}
