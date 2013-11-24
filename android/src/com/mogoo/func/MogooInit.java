package com.mogoo.func;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

/**
 * 初始化SDK
 * @author Rect
 * @version  Time：2013-5-8 
 */
public class MogooInit implements FREFunction {

	private String TAG = "MogooInit";
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub
		_context = context;
		FREObject result = null; 
		// TODO Auto-generated method stub
		//--------------------------------
		String appID;
		String appKey;
		try
		{
			appID = arg1[0].getAsString();
			appKey = arg1[1].getAsString();
		}
		catch (Exception e) {
			// TODO: handle exception
			callBack("init arg1 err");
			return null;
		}
		MogooManger.getInstance().MInit(_context, TAG, appID, appKey);
		
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
