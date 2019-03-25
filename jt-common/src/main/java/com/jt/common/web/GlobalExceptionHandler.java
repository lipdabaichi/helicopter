package com.jt.common.web;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.JsonResult;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @ControllerAdvice 修饰的类一般为
 * spring mvc中的全局异常处理类,此类
 * 中可以定义一些异常处理方法.
 * @RestControllerAdvice 也可以修饰
 * 全局异常处理类,此注解相当于
 * 在@ControllerAdvice与
 * @ResponseBody注解的组合
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
     /**
      * @ExceptionHandler 注解描述的方法为异常处理方法
      * 注解内部给定的异常类型为此方法可以处理的异常类型
      */
	 @ExceptionHandler(ServiceException.class)
	 //@ResponseBody
	 public JsonResult doHandleServiceException(
			 ServiceException e){
		 e.printStackTrace();//也可以以日志形式输出
		 return new JsonResult(e);
	 }
	 @ExceptionHandler(RuntimeException.class)
	 //@ResponseBody
	 public JsonResult doHandleRuntimeException(
			 RuntimeException e){
		 e.printStackTrace();//也可以以日志形式输出
		 JsonResult r=new JsonResult();
		 r.setMessage("运行时错误,稍等片刻");
		 r.setState(0);
		 return r;
	 }

	@ExceptionHandler(ShiroException.class)
	//@ResponseBody
	public JsonResult doHandleShiroException(
			ShiroException e){
		JsonResult r=new JsonResult();
		r.setState(0);//执行到此方法时坑定出现异常了
		if (e instanceof UnknownAccountException) {
			r.setMessage("账户不存在");
		} else if (e instanceof LockedAccountException) {
			r.setMessage("账户已经被禁用");
		} else if (e instanceof IncorrectCredentialsException) {
			r.setMessage("密码不正确");
		} else if (e instanceof AuthorizationException) {
			r.setMessage("没有访问权限,sb");
		} else {//还有其他异常
			r.setMessage(e.getMessage());
		}
		return r;
	}
}