package jmetertestDemo;
/*
 * 班级信息及获取方法
 */
public class Classinfo {
	private String ClassNo;    //班级号
	private String ClassName;   //班级名称
	private String department;   //学院名称
	private String grade;    //年级
	private String classNum;    //班级人数
	
	public Classinfo(String ClassNo,String ClassName,String department,String grade,String classNum) {
		super();
        this.ClassNo = ClassNo;
        this.ClassName = ClassName;
        this.department = department;
        this.grade = grade;
        this.classNum =classNum;
	}
	
	public Classinfo(String ClassName,String department,String grade,String classNum) {
		super();
        this.ClassName = ClassName;
        this.department = department;
        this.grade = grade;
        this.classNum =classNum;
	}
	
	//获取班级信息方法
    public String getClassNo() {
        return ClassNo;
    }
    public void setClassNo(String ClassNo) {
        this.ClassNo = ClassNo;
    }
	
    public String getClassName() {
        return ClassName;
    }
    public void setClassName(String ClassName) {
        this.ClassName = ClassName;
    }
    
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    public String getClassNum() {
        return classNum;
    }
    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }
    
}
