package com.mogoo.func;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

/**
 * @author Rect
 * @see rectvv@gmail.com
 * @see www.shadowkong.com
 * @see github.com/platformanes
 * @version 2013-11-24
 */
public class MogooSendID implements FREFunction {

	private String TAG = "MogooSendID";
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub
		_context = context;
		FREObject result = null; 
		// TODO Auto-generated method stub
		//--------------------------------
		String serverID;
		try
		{
			serverID = arg1[0].getAsString();
		}
		catch (Exception e) {
			// TODO: handle exception
			callBack("MogooSendID arg1 err");
			return null;
		}
		MogooManger.getInstance().MSendID(_context, TAG, serverID);
		callBack("success");
		//--------------------------------
		
		return result;
	}

	/**
	 * 结果传给AS端
	 */
	public void callBack(String status){
		Log.d(TAG, "-----status----"+status);
		_context.dispatchStatusEventAsync(TAG, "status:"+status);
	}
}
