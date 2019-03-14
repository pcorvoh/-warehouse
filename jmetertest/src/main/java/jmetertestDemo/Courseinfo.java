package jmetertestDemo;
/*
 * 课程信息及获取方法
 */
public class Courseinfo {
	private int courseNo;    //课程编号
	private String courseName;   //课程名称
	private int credit;   //学分
	private int courseHour;    //学时
	private int term;    //学期
	
	public Courseinfo(int courseNo,String courseName,int credit,int courseHour,int term) {
		super();
        this.courseNo = courseNo;
        this.courseName = courseName;
        this.credit = credit;
        this.courseHour = courseHour;
        this.term =term;
	}
	
	public Courseinfo(String courseName,int credit,int courseHour,int term) {
		super();
        this.courseName = courseName;
        this.credit = credit;
        this.courseHour = courseHour;
        this.term =term;
		}
	
	//获取课程信息方法
	public int getCourseNo() {
        return courseNo;
    }
    public void setCourseNo(int courseNo) {
        this.courseNo = courseNo;
    }
    
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
	public int getCredit() {
        return credit;
    }
    public void setCredit(int credit) {
        this.credit = credit;
    }
    
    public int getCourseHour() {
        return courseHour;
    }
    public void setCourseHour(int courseHour) {
        this.courseHour = courseHour;
    }
    
    public int getTerm() {
        return term;
    }
    public void setTerm(int term) {
        this.term = term;
    }
}
