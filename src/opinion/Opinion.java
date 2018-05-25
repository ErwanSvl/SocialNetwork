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
	
	public void modifyOpinion(float mark, Member author) {
		this.mark = mark;
		this.author = author;
	}
}
