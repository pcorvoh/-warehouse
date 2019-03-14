package jmetertestDemo;
/*
 * 分数类
 */
public class Scoreinfo {
	private int studentNo;
	private int courseNo;
	private int score;
	
	public Scoreinfo(int studentNo,int courseNo,int score) {
		super();
        this.studentNo = studentNo;
        this.courseNo = courseNo;
        this.score = score;
	}
	
	public Scoreinfo(int courseNo,int score) {
		super();
        this.courseNo = courseNo;
        this.score = score;
	}
	
	//获取分数信息方法
	public int getStudentNo() {
        return studentNo;
    }
    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }
	
	public int getCourseNo() {
        return courseNo;
    }
    public void setCourseNo(int courseNo) {
        this.courseNo = courseNo;
    }
    
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
}
