package com.mogoo.ane;

import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.mogoo.func.MogooExit;
import com.mogoo.func.MogooInit;
import com.mogoo.func.MogooLogin;
import com.mogoo.func.MogooMember;
import com.mogoo.func.MogooPay;
import com.mogoo.func.MogooSendID;

/**
 * @author Rect
 * @version  Time：2013-5-8 
 */
public class MogooContext extends FREContext {
	/**
	 * INIT sdk
	 */
	public static final String MOGOO_FUNCTION_INIT = "mogoo_function_init";
	/**
	 * 登录Key
	 */
	public static final String MOGOO_FUNCTION_LOGIN = "mogoo_function_login";
	/**
	 * 付费Key
	 */
	public static final String MOGOO_FUNCTION_PAY = "mogoo_function_pay";
	
	public static final String MOGOO_FUNCTION_MEMBER = "mogoo_function_member";
	public static final String MOGOO_FUNCTION_SENDID = "mogoo_function_sendid";
	/**
	 * 退出Key
	 */
	public static final String MOGOO_FUNCTION_EXIT = "mogoo_function_exit";
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, FREFunction> getFunctions() {
		// TODO Auto-generated method stub
		Map<String, FREFunction> map = new HashMap<String, FREFunction>();
//	       //映射
		   map.put(MOGOO_FUNCTION_INIT, new MogooInit());
	       map.put(MOGOO_FUNCTION_LOGIN, new MogooLogin());
	       map.put(MOGOO_FUNCTION_PAY, new MogooPay());
	       map.put(MOGOO_FUNCTION_MEMBER, new MogooMember());
	       map.put(MOGOO_FUNCTION_SENDID, new MogooSendID());
	       map.put(MOGOO_FUNCTION_EXIT, new MogooExit());
	       return map;
	}

}
