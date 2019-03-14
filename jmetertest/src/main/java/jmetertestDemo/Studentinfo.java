package jmetertestDemo;
/*
 * 学生信息及获取方法
 */
public class Studentinfo {
	private int studentNo;
    private String studentName;
    private String sex;
    private String birthday;
    private String nativePlace;
    private String nation;
    private String classNo;
    

    /*
     * 学生全部信息
     */
    public Studentinfo(int studentNo, String studentName,String sex, String birthday,String nativePlace,String nation,String classNo) {
    	super();
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.sex = sex;
        this.birthday = birthday;
        this.nativePlace = nativePlace;
        this.nation = nation;
        this.classNo = classNo;
    }
    
    /*
     * 学生主要信息
     */
    public Studentinfo(int studentNo, String studentName, String birthday,String nation,String classNo) {
    	super();
        this.studentNo = studentNo;
        this.studentName = studentName;
        this.birthday = birthday;
        this.nation = nation;
        this.classNo = classNo;
    }

    
    //获取学生信息方法
    public int getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    
    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }
    
    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
    
    public String getClassNo() {
    	return classNo;
    }
    
    public void setClassNo(String classNo) {
    	this.classNo = classNo;
    }
    
}
