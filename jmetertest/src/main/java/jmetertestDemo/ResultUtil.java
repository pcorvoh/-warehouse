package jmetertestDemo;


public class ResultUtil {
	/*
	 * 请求成功返回
	 * @param object
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> Msg success(Integer code,String resultmsg,T data) {
		Msg msg = new Msg();
		msg.setCode(code);
		msg.setMsg(resultmsg);
		msg.setData(data);
		return msg;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> Msg error(Integer code,String resultmsg,T data) {
		Msg msg = new Msg();
		msg.setCode(code);
		msg.setMsg(resultmsg);
		msg.setData(data);
		return msg;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> Mssg success(T total,T data) {
		Mssg mssg = new Mssg();
		mssg.setTotal(total);
		mssg.setData(data);
		return mssg;
	} 
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> Mssg error(T data) {
		Mssg mssg = new Mssg();
		mssg.setData(data);
		return mssg;
	} 
}
