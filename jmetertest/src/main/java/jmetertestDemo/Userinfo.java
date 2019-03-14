/*
 * 用户信息及获取方法
 */

package jmetertestDemo;

import javax.validation.constraints.NotNull;

public class Userinfo {
    private int userid;
    
    @NotNull(message = "用户名不能为空")
    private String username;
    
    @NotNull(message = "密码不能为空")
    private String pwd;
    
    private String phone;
    private String studentNo;
    private String status;

    public Userinfo(int userid, String username, String pwd,String phone,String studentNo,String status) {
    	super();
        this.userid = userid;
        this.username = username;
        this.pwd = pwd;
        this.phone = phone;
        this.studentNo = studentNo;
        this.status = status;
    }
    
    public Userinfo(String username, String pwd,String phone,String studentNo) {
    	super();
        this.username = username;
        this.pwd = pwd;
        this.phone = phone;
        this.studentNo = studentNo;
    }
    
    //获取用户信息方法
    public int getUserid() {
        return userid;
    }
    public void setUserid(int userid) {
        this.userid = userid;
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPwd() {
    	String pwd = EncryptionByMD5.getMD5("test".getBytes());
        return pwd;
    }
    public void setPwd(String pwd) {
    	String pwd1 = EncryptionByMD5.getMD5("test".getBytes());
        this.pwd = pwd1;
    }
    
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStudentNo() {
        return studentNo;
    }
    public void setStudentno(String studentNo) {
        this.studentNo = studentNo;
    }
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}