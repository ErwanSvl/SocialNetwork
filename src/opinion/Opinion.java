package opinion;

public class Opinion {
	
	private int mark;
	private Member author;
	
	public Opinion(Member member, int mark) {
		this.author = member;
		this.mark = mark;
	}

	public Member getAuthor() {
		return author;
	}
	
	public void modifyOpinion(float mark, Member author) {
		this.mark = mark;
		this.author = author;
	}

}
