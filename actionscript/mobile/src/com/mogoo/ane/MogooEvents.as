package com.mogoo.ane 
{ 
	/**
	 * 
	 * @author Rect  2013-5-6 
	 * 
	 */
	public class MogooEvents 
	{ 
		public function MogooEvents()
		{
		} 
		/**************************平台通知************************************/
		/**
		 *init 
		 */		
		public static const MOGOO_INIT_STATUS:String = "MogooInit";
		public static const MOGOO_LOGIN_STATUS : String = "MogooLogin";
		public static const MOGOO_LOGOUT_STATUS : String = "MogooExit";
		public static const MOGOO_PAY_STATUS : String = "MogooPay";
		public static const MOGOO_MEM_STATUS : String = "MogooMember";
		public static const MOGOO_SEND_STATUS : String = "MogooSendID";
	} 
}