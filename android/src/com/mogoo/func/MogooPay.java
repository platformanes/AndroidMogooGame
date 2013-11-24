package com.mogoo.func;

import android.util.Log;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

/**
 * 执行付费
 * @author Rect
 * @version  Time：2013-5-8 
 */
public class MogooPay implements FREFunction {

	private String TAG = "MogooPay";
	private FREContext _context;
	@Override
	public FREObject call(final FREContext context, FREObject[] arg1) {
		// TODO Auto-generated method stub
		_context = context;
		FREObject result = null; 
		// TODO Auto-generated method stub
		//--------------------------------
		String serverID;
		String ext;
		try
		{
			serverID = arg1[0].getAsString();
			ext = arg1[1].getAsString();
		}
		catch (Exception e) {
			// TODO: handle exception
			callBack("MogooPay arg1 err");
			return null;
		}
		
		MogooManger.getInstance().MPay(_context, TAG, serverID, ext);
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
