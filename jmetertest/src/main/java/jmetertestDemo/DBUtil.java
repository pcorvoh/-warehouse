package jmetertestDemo;



import jmetertestDemo.Studentinfo;
import jmetertestDemo.Connter;
import jmetertestDemo.Constants;

/**
 * 
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



/**
 * @author Administrator
 * 
 */
public class DBUtil {

    /**
     * 查询所有学生信息
     */
    @SuppressWarnings({ "rawtypes" })
	public static Mssg getAllStuInfo() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Studentinfo> stus = new ArrayList<Studentinfo>();
        Mssg result = new Mssg();
        try {
            conn = Connter.getConnection(Constants.URL,
                    Constants.USERNAME, Constants.PASSWORD);
            String sql = "select * from tb_student;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.last();
            int rowCount =rs.getRow();
            rs.beforeFirst();
            while (rs.next()) {
                int studentNo = rs.getInt("studentNo");
                String studentName = rs.getString("studentName");
                String sex = rs.getString("sex");
                String birthday = rs.getString("birthday");
                String nativePlace = rs.getString("nativePlace");
                String nation = rs.getString("nation");
                String classNo = rs.getString("classNo");
                Studentinfo s = new Studentinfo(studentNo,studentName,sex,birthday,nativePlace,nation,classNo);
                stus.add(s);
            }
            result = ResultUtil.success(rowCount, stus);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (null != rs) {
                    rs.close();
                }
                if (null != ps) {
                    ps.close();
                }
                if (null != conn) {
                    Connter.closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    /**
     * 根据id查询学生的信息
     */
    public static Studentinfo getStuInfoById(int studentNo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Studentinfo info = null;
        try {
            conn = Connter.getConnection(Constants.URL,
                    Constants.USERNAME, Constants.PASSWORD);
            String sql = "SELECT * FROM tb_student where studentNo = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentNo);
            rs = ps.executeQuery();
            if (rs.next()) {
                String studentName = rs.getString("studentName");
                String sex = rs.getString("sex");
                String birthday = rs.getString("birthday");
                String nativePlace = rs.getString("nativePlace");
                String nation = rs.getString("nation");
                String classNo = rs.getString("classNo");
                info = new Studentinfo(studentNo,studentName,sex,birthday,nativePlace,nation,classNo);
            }else {
            	info = null;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != rs) {
                    rs.close();
                }
                if (null != ps) {
                    ps.close();
                }
                if (null != conn) {
                	Connter.closeConnection(conn);
                }
            } catch (Exception e2) {
            }
        }
        return info;
    }


    /**
     * 根据id删除学生信息
     * @return 
     */
    public static int deleteStuInfo(int studentNo) {
        Connection conn = null;
        PreparedStatement ps = null;
        int deleteCount = -1;
        try {
            conn = Connter.getConnection(Constants.URL,
                    Constants.USERNAME, Constants.PASSWORD);
            String sql = "delete from tb_student where studentNo = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentNo);
            deleteCount = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != ps) {
                    ps.close();
                }
                if (null != conn) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return deleteCount;
    }

    /**
     * 根据id修改学生信息
     */
    public static int modifyStuInfo(Studentinfo stu) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
        	conn = Connter.getConnection(Constants.URL,
                    Constants.USERNAME, Constants.PASSWORD);
            String sql = "update tb_student set studentName = ?,sex = ? ,birthday = ?, nativePlace = ?, nation = ?, classNo = ? where studentNo = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, stu.getStudentName());
            ps.setString(2, stu.getSex());
            ps.setString(3, stu.getBirthday());
            ps.setString(4, stu.getNativePlace());
            ps.setString(5, stu.getNation());
            ps.setString(6, stu.getClassNo());
            ps.setInt(7, stu.getStudentNo());
            count = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != ps) {
                    ps.close();
                }
                if (null != conn) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return count;
    }
    
    /**
     * 根据用户ID删除用户
     */
    public static int deleteUser(String username) {
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
        	conn = Connter.getConnection(Constants.URL,
                    Constants.USERNAME, Constants.PASSWORD);
            String sql = "update tb_user set stutas = 0 where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            count = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != ps) {
                    ps.close();
                }
                if (null != conn) {
                    conn.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return count;
    }
    
   /*
    * 注册用户
    */
  public static int saveUserInfo(Userinfo stu) {
  Connection conn = null;
  PreparedStatement ps = null;
  int insertCount = 0;
  try {
      conn = Connter.getConnection(Constants.URL,
              Constants.USERNAME, Constants.PASSWORD);
      String sql = "insert into tb_user (username,pwd,phone,studentNo) values (?,?,?,?)";
      ps = conn.prepareStatement(sql);
      ps.setString(1, stu.getUsername());
      ps.setString(2, stu.getPwd());
      ps.setString(3, stu.getPhone());
      ps.setString(4, stu.getStudentNo());
      insertCount = ps.executeUpdate();
      
  } catch (Exception e) {
      e.printStackTrace();
  } finally {
      try {
          if (null != ps) {
              ps.close();
          }
          if (null != conn) {
              conn.close();
          }
      } catch (Exception e2) {
          e2.printStackTrace();
      }
  }
  return insertCount;
}
  
  /*
   * 新增学生
   */
 public static int saveStudentInfo(Studentinfo stu) {
 Connection conn = null;
 PreparedStatement ps = null;
 int insertCount = -1;
 try {
     conn = Connter.getConnection(Constants.URL,
             Constants.USERNAME, Constants.PASSWORD);
     String sql = "insert into tb_student (studentNo,studentName,sex,birthday,nativePlace,nation,classNo) values (?,?,?,?,?,?,?)";
     ps = conn.prepareStatement(sql);
     ps.setInt(1, stu.getStudentNo());
     ps.setString(2, stu.getStudentName());
     ps.setString(3, stu.getSex());
     ps.setString(4, stu.getBirthday());
     ps.setString(5, stu.getNativePlace());
     ps.setString(6, stu.getNation());
     ps.setString(7, stu.getClassNo());
     insertCount = ps.executeUpdate();
     
 } catch (Exception e) {
     e.printStackTrace();
 } finally {
     try {
         if (null != ps) {
             ps.close();
         }
         if (null != conn) {
             conn.close();
         }
     } catch (Exception e2) {
         e2.printStackTrace();
     }
 }
 return insertCount;
}
 
 /*
  * 通过学生编号查询学生是否存在
  */
public static int getStudentStatus(int studentNo) {
   Connection conn = null;
   PreparedStatement ps = null;
   ResultSet rs = null;
   int info = 0;
   try {
       conn = Connter.getConnection(Constants.URL,
               Constants.USERNAME, Constants.PASSWORD);
       String sql = "SELECT * FROM tb_student where studentNo = ?";
       ps = conn.prepareStatement(sql);
       ps.setInt(1, studentNo);
       rs = ps.executeQuery();
       if (rs.next()) {
     	  //存在该学生
     	  info = 1;
       }else {
     	//不存在该学生
       	info = 0;
       }
       
   } catch (Exception e) {
       e.printStackTrace();
   } finally {
       try {
           if (null != rs) {
               rs.close();
           }
           if (null != ps) {
               ps.close();
           }
           if (null != conn) {
           	Connter.closeConnection(conn);
           }
       } catch (Exception e2) {
       }
   }
   return info;
}
/*
 * 通过用户名查询用户状态
 */
public static int getUserStatuss(String username) {
  Connection conn = null;
  PreparedStatement ps = null;
  ResultSet rs = null;
  int info = 0;
  try {
      conn = Connter.getConnection(Constants.URL,
              Constants.USERNAME, Constants.PASSWORD);
      String sql = "SELECT status FROM tb_user where username = ?";
      ps = conn.prepareStatement(sql);
      ps.setString(1, username);
      rs = ps.executeQuery();
      info = rs.getInt(1);
  } catch (Exception e) {
      e.printStackTrace();
  } finally {
      try {
          if (null != rs) {
              rs.close();
          }
          if (null != ps) {
              ps.close();
          }
          if (null != conn) {
          	Connter.closeConnection(conn);
          }
      } catch (Exception e2) {
      }
  }
  return info;
}

    /*
     * 通过用户名查询用户是否存在
     */
  public static int getUserStatus(String username) {
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      int info = 0;
      try {
          conn = Connter.getConnection(Constants.URL,
                  Constants.USERNAME, Constants.PASSWORD);
          String sql = "SELECT * FROM tb_user where username = ?";
          ps = conn.prepareStatement(sql);
          ps.setString(1, username);
          rs = ps.executeQuery();
          if (rs.next()) {
        	  //存在该用户
        	  info = 1;
          }else {
        	//不存在该用户
          	info = 0;
          }
          
      } catch (Exception e) {
          e.printStackTrace();
      } finally {
          try {
              if (null != rs) {
                  rs.close();
              }
              if (null != ps) {
                  ps.close();
              }
              if (null != conn) {
              	Connter.closeConnection(conn);
              }
          } catch (Exception e2) {
          }
      }
      return info;
  }
    
    /*
     * 登录用户
     * 判断用户名是否存在
     * 验证密码是否正确
     */
    public static int getUserlogin(String username,String pwd) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int lg = 0;
        try {
            conn = Connter.getConnection(Constants.URL,
                    Constants.USERNAME, Constants.PASSWORD);
            String sql = "SELECT * FROM tb_user where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if(rs.next()) {
            	if(rs.getString("status").equals("1")) {
            		if(pwd.equals(rs.getString("pwd"))) {
            			//登录成功
            			lg = 1;
                	}else {
                		//登录失败，密码错误
                		lg = 2;
                	}
        		}else{
            		//登录失败，账号被冻结
        			lg = 3;
        		}
            	
            }else {
            	//登录失败，用户名不存在
            	lg = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != rs) {
                    rs.close();
                }
                if (null != ps) {
                    ps.close();
                }
                if (null != conn) {
                	Connter.closeConnection(conn);
                }
            } catch (Exception e2) {
            }
        }
        return lg;
    }
    
    /**
     * 根据编号查询班级的信息
     */
    public static Classinfo getClassinfo(String ClassNo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Classinfo info = null;
        try {
            conn = Connter.getConnection(Constants.URL,
                    Constants.USERNAME, Constants.PASSWORD);
            String sql = "SELECT * FROM tb_class where ClassNo = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, ClassNo);
            rs = ps.executeQuery();
            if (rs.next()) {
                String ClassName = rs.getString("ClassName");
                String department = rs.getString("department");
                String grade = rs.getString("grade");
                String classNum = rs.getString("classNum");
                info = new Classinfo(ClassNo,ClassName,department,grade,classNum);
            }else {
            	info = null;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != rs) {
                    rs.close();
                }
                if (null != ps) {
                    ps.close();
                }
                if (null != conn) {
                	Connter.closeConnection(conn);
                }
            } catch (Exception e2) {
            }
        }
        return info;
    }    

    /**
     * 根据编号查询课程的信息
     */
    public static Courseinfo getCourseinfo(int courseNo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Courseinfo info = null;
        try {
            conn = Connter.getConnection(Constants.URL,
                    Constants.USERNAME, Constants.PASSWORD);
            String sql = "SELECT * FROM tb_course where courseNo = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, courseNo);
            rs = ps.executeQuery();
            if (rs.next()) {
                String courseName = rs.getString("courseName");
                int credit = rs.getInt("credit");
                int courseHour = rs.getInt("courseHour");
                int term = rs.getInt("term");
                info = new Courseinfo(courseNo,courseName,credit,courseHour,term);
            }else {
            	info = null;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != rs) {
                    rs.close();
                }
                if (null != ps) {
                    ps.close();
                }
                if (null != conn) {
                	Connter.closeConnection(conn);
                }
            } catch (Exception e2) {
            }
        }
        return info;
    }
    
    /**
     * 根据studentNo查询学生的成绩
     */
    @SuppressWarnings({ "rawtypes" })
	public static Mssg getScoreInfo(int studentNo) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Scoreinfo> stus = new ArrayList<Scoreinfo>();
        Mssg result = new Mssg();
        try {
            conn = Connter.getConnection(Constants.URL,
                    Constants.USERNAME, Constants.PASSWORD);
            String sql = "select * from tb_score where studentNo = ?;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, studentNo);
            rs = ps.executeQuery();
            rs.last();
            int rowCount =rs.getRow();
            
            rs.beforeFirst();
            
            while (rs.next()) {
                int courseNo = rs.getInt("courseNo");
                int score = rs.getInt("score");
                Scoreinfo s = new Scoreinfo(studentNo,courseNo,score);
                stus.add(s);
            }
            result = ResultUtil.success(rowCount, stus);            	

            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (null != rs) {
                    rs.close();
                }
                if (null != ps) {
                    ps.close();
                }
                if (null != conn) {
                    Connter.closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return result;
    }
    
    
    /**
     * 判断操作是否成功
     */
    public static String isSuccess(int count) {
        if (count > 0) {
            return "操作成功！";
        } else {
            return "操作失败！";
        }
    }
}
