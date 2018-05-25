package opinion;

public class Opinion {
	
	private int mark;
	private Member author;
	
	public Opinion(Member member, int mark) {
		this.author = member;
		this.mark = mark;
	}
}
