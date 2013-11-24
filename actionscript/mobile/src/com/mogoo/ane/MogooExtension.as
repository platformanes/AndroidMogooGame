package com.mogoo.ane 
{ 
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	
	/**
	 * 
	 * @author Rect  2013-5-6 
	 * 
	 */
	public class MogooExtension extends EventDispatcher 
	{ 
		
		
		private static const MOGOO_FUNCTION_INIT:String = "mogoo_function_init";//与java端中Map里的key一致
		private static const MOGOO_FUNCTION_LOGIN:String = "mogoo_function_login";//与java端中Map里的key一致
		private static const MOGOO_FUNCTION_PAY:String = "mogoo_function_pay";//与java端中Map里的key一致
		private static const MOGOO_FUNCTION_EXIT:String = "mogoo_function_exit";//与java端中Map里的key一致
		private static const MOGOO_FUNCTION_MEMBER:String = "mogoo_function_member";//与java端中Map里的key一致
		private static const MOGOO_FUNCTION_SENDID:String = "mogoo_function_sendid";//与java端中Map里的key一致
		
		private static const EXTENSION_ID:String = "com.mogoo.ane";//与extension.xml中的id标签一致
		private var extContext:ExtensionContext;
		
		/**单例的实例*/
		private static var _instance:MogooExtension; 
		public function MogooExtension(target:IEventDispatcher=null)
		{
			super(target);
			if(extContext == null) {
				extContext = ExtensionContext.createExtensionContext(EXTENSION_ID, "");
				extContext.addEventListener(StatusEvent.STATUS, statusHandler);
			}
			
		} 
		
		//第二个为参数，会传入java代码中的FREExtension的createContext方法
		/**
		 * 获取实例
		 * @return DLExtension 单例
		 */
		public static function getInstance():MogooExtension
		{
			if(_instance == null) 
				_instance = new MogooExtension();
			return _instance;
		}
		
		/**
		 * 转抛事件
		 * @param event 事件
		 */
		private function statusHandler(event:StatusEvent):void
		{
			dispatchEvent(event);
		}
		
		/**
		 *init发送函数  
		 * @param key 暂时传什么都可以  留着可能要用
		 * @return 
		 * 
		 */		
		public function MogooInit(appID:String,appKey:String):String{
			if(extContext ){
				return extContext.call(MOGOO_FUNCTION_INIT,appID,appKey) as String;
			}
			return "call login failed";
		} 
		
		/**
		 *登录发送函数  
		 * @param key 暂时传什么都可以  留着可能要用
		 * @return 
		 * 
		 */		
		public function MogooLogIn(key:int):String{
			if(extContext ){
				return extContext.call(MOGOO_FUNCTION_LOGIN,key) as String;
			}
			return "call login failed";
		} 
		/**
		 *付费发送函数 
		 * @param key 暂时传什么都可以 留着以后可能要用
		 * @return 
		 * 
		 */		 
		public function MogooPay(serverID:String,ext:String):String{
			if(extContext){ 
				return extContext.call(MOGOO_FUNCTION_PAY,serverID,ext)as String;
			}
			return "call pay failed";
		}
		
		public function MogooMember(key:int):String{
			if(extContext){ 
				return extContext.call(MOGOO_FUNCTION_MEMBER,key)as String;
			}
			return "call 	MogooMember failed";
		}
		
		public function MogooSendID(serverID:String):String{
			if(extContext){ 
				return extContext.call(MOGOO_FUNCTION_SENDID,serverID)as String;
			}
			return "call MogooSendID failed";
		}
		
		/**
		 *退出SDK时候调用   这个函数只在退出游戏的时候调用  
		 * @param key
		 * @return 
		 * 
		 */		
		public function MogooExit(key:int):String{
			if(extContext){ 
				return extContext.call(MOGOO_FUNCTION_EXIT,key) as String;
			}
			return "call exit failed";
		}
	} 
}