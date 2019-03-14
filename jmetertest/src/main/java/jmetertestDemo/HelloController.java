package jmetertestDemo;

import jmetertestDemo.Connter;
import jmetertestDemo.Constants;
import jmetertestDemo.Msg;

import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

import java.sql.*;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class HelloController  {

	public void testGetConnection() throws SQLException {
        Connection conn = Connter.getConnection(Constants.URL,
                Constants.USERNAME, Constants.PASSWORD);
        conn.close();
    }
	
	/*
	 * post请求
	 * 注册用户
	 * 接口编号1
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "regist",method = RequestMethod.POST)
	public @ResponseBody Msg<String> regist(@RequestParam(value="username", required=false)String username,@RequestParam(value="pwd", required=false)String pwd,@RequestParam(value="phone", required=false)String phone,@RequestParam(value="studentNo", required=false)String studentNo){
		if(username == null) {
			String registMsg = "用户名不能为空";
			return ResultUtil.error(14, "注册失败", registMsg);
		} else if(pwd == null) {
			String registMsg = "密码不能为空";
			return ResultUtil.error(15, "注册失败", registMsg);
		} else if(phone == null) {
			String registMsg = "手机号不能为空";
			return ResultUtil.error(16, "注册失败", registMsg);
		} else if(studentNo == null) {
			String registMsg = "学生编号不能为空";
			return ResultUtil.error(17, "注册失败", registMsg);
		}else {
			int userStatus = DBUtil.getUserStatus(username);
			if(userStatus == 1) {
				String registMsg = "注册失败，用户名已存在。";
				return ResultUtil.error(13, "请求正常", registMsg);
			}else {
				Userinfo userinfo = new Userinfo(username, pwd, phone, studentNo);
				int userRegist = DBUtil.saveUserInfo(userinfo);
				if(userRegist == 1) {
					String registMsg = "注册成功";
					return ResultUtil.error(11, "请求正常", registMsg);
				}else {
					String registMsg = "注册失败，请重新输入数据。";
					return ResultUtil.error(12, "请求正常", registMsg);
				}
			}
		}
	}
	
	/*
	 * post 请求
	 * 登录用户
	 * 接口编号2
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "login",method = RequestMethod.POST)
	public @ResponseBody Msg<String> login(@RequestParam(value="username", required=false)@Valid String username,@RequestParam(value="pwd", required=false)@Valid String pwd){
		String loginmsg;
		if(username == null) {
			loginmsg = "登录失败，用户名不能为空";
			return ResultUtil.success(200,"请求正常",loginmsg);
		}else if(pwd == null) {
			loginmsg = "登录失败，密码不能为空";
			return ResultUtil.success(200,"请求正常",loginmsg);
		}else {
			try {
				int lg = DBUtil.getUserlogin(username,pwd);
				if(lg==1) {
					loginmsg = "登录成功";
					return ResultUtil.success(21,"请求正常",loginmsg);
				}else if(lg==2) {
					loginmsg = "密码错误";
					return ResultUtil.success(22,"登录失败",loginmsg);
				}else if(lg==0) {
					loginmsg = "用户名不存在";
					return ResultUtil.success(23,"登录失败",loginmsg);
				}else if(lg==3) {
					loginmsg = "账号被冻结";
					return ResultUtil.success(24,"登录失败",loginmsg);
				}
			}catch (Exception e) {
				loginmsg = "异常情况";
				return ResultUtil.error(25, "服务器异常", loginmsg);
			}
		}
		
		return null;
	} 
	
	/*
	 *Get请求
	 *查询所有信息
	 *接口编号3
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "index",method = RequestMethod.GET)
	public @ResponseBody Msg<Studentinfo> index(){
		Mssg result = DBUtil.getAllStuInfo();
		int total = (int) result.getTotal();
		if(total == 0) {
			String indexMsg = "学生数据为空";
			return ResultUtil.error(32, "查询成功", indexMsg);
		}else {
			return ResultUtil.success(31, "查询成功", result);
		}
	}
	
	/*
	 * post请求
	 * 通过studentNo，查询学生信息
	 * 接口编号4
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "selectStudent",method = RequestMethod.POST)
	public @ResponseBody Msg<Studentinfo> selectStudent(@RequestParam(value="studentNo", required=false,defaultValue="0") int studentNo){
		String selectMsg;
		if(studentNo == 0) {
			selectMsg = "学生号不能为空";
			return ResultUtil.error(43, "查询失败", selectMsg);
		}else {
			Studentinfo info = DBUtil.getStuInfoById(studentNo);
			if(info != null) {
				return ResultUtil.success(41,"查询成功",info);
			}else {
				selectMsg = "该学生号不存在";
				return ResultUtil.success(42,"查询成功",selectMsg);
			}
		}
		
	} 
	
	/*
	 * post请求
	 * 通过studentNo，修改学生信息
	 * 接口编号5
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "updateStudent",method = RequestMethod.POST)
	public @ResponseBody Msg<String> updateStudent(@RequestParam(value="studentNo", required=false,defaultValue="0") int studentNo,String studentName,String sex,String birthday,String nativePlace,String nation,String classNo){
		String selectMsg;
		if(studentNo == 0) {
			selectMsg = "学生号不能为空";
			return ResultUtil.error(53, "修改失败", selectMsg);
		}else {
			Studentinfo studentinfo = new Studentinfo(studentNo,studentName,sex,birthday,nativePlace,nation,classNo);
			int info = DBUtil.modifyStuInfo(studentinfo);
			if(info == 1) {
				return ResultUtil.success(51,"修改成功",studentinfo);
			}else {
				selectMsg = "数据库修改失败";
				return ResultUtil.success(52,"修改失败",selectMsg);
			}
		}
		
	} 
	
	/*
	 * post请求
	 * 通过ClassNo，查询班级信息
	 * 接口编号6
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "selectClass",method = RequestMethod.POST)
	public @ResponseBody Msg<Classinfo> selectClass(@RequestParam(value="ClassNo", required=false) String ClassNo){
		String selectMsg;
		if(ClassNo==null) {
			selectMsg = "班级号不能为空";
			return ResultUtil.error(63, "查询失败", selectMsg);
		}else {
			Classinfo info = DBUtil.getClassinfo(ClassNo);
			if(info != null) {
				return ResultUtil.success(61,"查询成功",info);
			}else {
				selectMsg = "该班级号不存在";
				return ResultUtil.success(62,"查询成功",selectMsg);
			}
		}
		
	} 	

	/*
	 * post请求
	 * 通过courseNo，查询课程信息
	 * 接口编号7
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "selectCourse",method = RequestMethod.POST)
	public @ResponseBody Msg<Classinfo> selectCourse(@RequestParam(value="courseNo", required=false,defaultValue="0") int courseNo){
		String selectMsg;
		if(courseNo == 0) {
			selectMsg = "课程号不能为空";
			return ResultUtil.error(73, "查询失败", selectMsg);
		}else {
			Courseinfo info = DBUtil.getCourseinfo(courseNo);
			if(info != null) {
				return ResultUtil.success(71,"查询成功",info);
			}else {
				selectMsg = "该课程号不存在";
				return ResultUtil.success(72,"查询成功",selectMsg);
			}
		}
		
	}
	
	/*
	 *Post请求
	 *根据studentNo查询学生的成绩
	 *接口编号8
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "selectscore",method = RequestMethod.POST)
	public @ResponseBody Msg<Scoreinfo> selectscore(@RequestParam(value="studentNo", required=false,defaultValue="0") int studentNo){
		Mssg result = DBUtil.getScoreInfo(studentNo);
		int total = (int) result.getTotal();
		if(studentNo == 0) {
			String scoreMsg = "学生编号不能为空";
			return ResultUtil.error(81, "查询成功", scoreMsg);
		}else if(total == 0) {
			String scoreMsg = "学生成绩数据为空";
			return ResultUtil.error(82, "查询成功", scoreMsg);
		}else {
			return ResultUtil.success(83, "查询成功", result);
		}
	}
	
	/*
	 *Delete请求
	 *根据studentNo删除学生的信息
	 *接口编号9
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "deleteStudent",method = RequestMethod.DELETE)
	public @ResponseBody Msg deleteStudent(@RequestParam(value="studentNo", required=false,defaultValue="0") int studentNo){
		int result = DBUtil.deleteStuInfo(studentNo);
		if(studentNo == 0) {
			String scoreMsg = "学生编号不能为空";
			return ResultUtil.error(91, "删除失败", scoreMsg);
		}else if(result == 0) {
			String scoreMsg = "该学生不存在";
			return ResultUtil.error(92, "删除失败", scoreMsg);
		}else {
			return ResultUtil.success(93, "操作成功","该学生已被删除");
		}
	}
	
	/*
	 * post请求
	 * 新增学生
	 * 接口编号10
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "addStudent",method = RequestMethod.POST)
	public @ResponseBody Msg<String> addStudent(@RequestParam(value="studentNo", required=false,defaultValue="0")int studentNo,@RequestParam(value="studentName", required=false)String studentName,@RequestParam(value="sex", required=false)String sex,@RequestParam(value="birthday", required=false)String birthday,@RequestParam(value="nativePlace", required=false)String nativePlace,@RequestParam(value="nation", required=false)String nation,@RequestParam(value="classNo", required=false)String classNo){
		if(studentNo == 0) {
			String addStudentMsg = "学生编号不能为空";
			return ResultUtil.error(104, "新增失败", addStudentMsg);
		} else if(studentName == null) {
			String addStudentMsg = "学生姓名不能为空";
			return ResultUtil.error(105, "新增失败", addStudentMsg);
		} else if(sex == null) {
			String addStudentMsg = "性别不能为空";
			return ResultUtil.error(106, "新增失败", addStudentMsg);
		} else if(birthday == null) {
			String addStudentMsg = "生日不能为空";
			return ResultUtil.error(107, "新增失败", addStudentMsg);
		}else if(nativePlace == null) {
			String addStudentMsg = "籍贯不能为空";
			return ResultUtil.error(108, "新增失败", addStudentMsg);
		}else if(nation == null) {
			String addStudentMsg = "民族不能为空";
			return ResultUtil.error(109, "新增失败", addStudentMsg);
		}else if(classNo == null) {
			String addStudentMsg = "班级编号不能为空";
			return ResultUtil.error(110, "新增失败", addStudentMsg);
		}else {
			int userStatus = DBUtil.getStudentStatus(studentNo);
			if(userStatus == 1) {
				String addStudentMsg = "新增失败，该学生已存在。";
				return ResultUtil.error(103, "请求正常", addStudentMsg);
			}else {
				Studentinfo Studentinfo = new Studentinfo(studentNo,studentName,sex,birthday,nativePlace,nation,classNo);
				int studentRegist = DBUtil.saveStudentInfo(Studentinfo);
				if(studentRegist == 1) {
					String addStudentMsg = "新增成功";
					return ResultUtil.error(101, "请求正常", addStudentMsg);
				}else {
					String addStudentMsg = "新增失败，请重新输入数据。";
					return ResultUtil.error(102, "请求正常", addStudentMsg);
				}
			}
		}
	}
	
	/*
	 *Delete请求
	 *根据username删除用户
	 *接口编号11
	 */
	@SuppressWarnings({ "rawtypes" })
	@RequestMapping(value = "deleteUser",method = RequestMethod.DELETE)
	public @ResponseBody Msg deleteUser(@RequestParam(value="username", required=false) String username){
		int userStatuss = DBUtil.getUserStatuss(username);
		int userStatus = DBUtil.getUserStatus(username);
		int userDelete = DBUtil.deleteUser(username);
		if(username == null) {
			String deleteUserMsg = "用户名不能为空";
			return ResultUtil.error(111, "删除失败", deleteUserMsg);
		}else if(userStatus == 0) {
			String deleteUserMsg = "该用户不存在";
			return ResultUtil.error(92, "删除失败", deleteUserMsg);
		}else {
			if(userStatuss == 0) {
				String deleteUserMsg = "该用户已删除";
				return ResultUtil.error(111, "删除失败", deleteUserMsg);
			}else {
				if(userDelete == 0) {
					String deleteUserMsg = "操作异常";
					return ResultUtil.error(111, "删除失败", deleteUserMsg);
				}else {
					return ResultUtil.success(93, "操作成功","该学生已被删除");
				}
			}			
		}
	}
	
}  