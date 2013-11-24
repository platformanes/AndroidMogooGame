package com.mogoo.func;

import java.net.URLEncoder;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.adobe.fre.FREContext;
import com.mogoo.error.DialogError;
import com.mogoo.error.MogooError;
import com.mogoo.net.DialogListener;
import com.mogoo.net.Mogoo;
import com.mogoo.net.ServiceListener;
import com.mogoo.net.payment.MogooPayment;
import com.mogoo.net.payment.PaymentListener;
import com.mogoo.to.MogooPaymentTO;
import com.mogoo.util.Util;

/**
 * @author Rect
 * @see rectvv@gmail.com
 * @see www.shadowkong.com
 * @see github.com/platformanes
 * @version 2013-11-24
 */
public class MogooManger {

	private static Mogoo mogoo = null;
	private static MogooManger mInstance = null;
	private static FREContext _context;
	private String TAG = null;
	public static MogooManger getInstance()
	{
		if(null == mInstance)
			mInstance = new MogooManger();
		return mInstance;
	}
	
	public void MInit(FREContext ctx,String _TAG,String appID,String appKey)
	{
		TAG = _TAG;
		_context = ctx;
		mogoo = new Mogoo(appID, appKey);
		callBack("init is finish");
	}
	
	public void MLogin(FREContext ctx,String _TAG)
	{
		TAG = _TAG;
		_context = ctx;
		if(null != mogoo)
			mogoo.dialog(_context.getActivity(), new DialogListener() {
            @Override
            public void onCannel() {
            }

            @Override
            public void onComplete(Bundle bundle) {
                callBack("login*" + bundle.getString("mid") + "*" + bundle.getString("token"));
            }

            @Override
            public void onMogooError(MogooError error) {
            	callBack("onMogooError:" + error.getMessage());
            }

            @Override
            public void onError(DialogError error) {
            	callBack("onError:" + error.getMessage());
            }
        });
		else
			callBack("init error");
	}
	
	public void MMember(FREContext ctx,String _TAG)
	{
		TAG = _TAG;
		_context = ctx;
		Context context = _context.getActivity();
        String mid = Util.getValueFromSharedPreferencesSave(Mogoo.MG_PREFIX_STR + "mid", context);
        String username = Util.getValueFromSharedPreferencesSave(Mogoo.MG_PREFIX_STR + "username", context);
        String gid = Util.getValueFromSharedPreferencesSave(Mogoo.MG_PREFIX_STR + "gameid", context);
        callBack("mid=" + mid + "  username:" + username+ "  gameid:" + gid);
	}
	
	public void MSendID(FREContext ctx,String _TAG,String serverID)
	{
		TAG = _TAG;
		_context = ctx;
		if(null != mogoo)
		{
			//sid 服务器id  uid 用户id gid 游戏appid
			mogoo.sendCpSid(serverID, 
					Util.getValueFromSharedPreferencesSave(Mogoo.MG_PREFIX_STR + "mid", _context.getActivity()),
					Util.getValueFromSharedPreferencesSave(Mogoo.MG_PREFIX_STR + "gameid", _context.getActivity()));
		}
		else
			callBack("init error");
	}
	
	public void MLogOut(FREContext ctx,String _TAG)
	{
		TAG = _TAG;
		_context = ctx;
		if(null != mogoo)
		{
			mogoo.logoutToken(_context.getActivity(), new ServiceListener() {

                @Override
                public void onComplete(Bundle bundle) {
                    callBack("logout ok");
                }

                @Override
                public void onMogooError(MogooError error) {
                    callBack("onError:" + error.getMessage());
                }

                @Override
                public void onError(Error error) {
                    callBack("onError:" + error.getMessage());
                }
            });
		}
		else
			callBack("init error");
	}
	
	public void MPay(FREContext ctx,String _TAG,String serverID,String ext)
	{
		TAG = _TAG;
		_context = ctx;
		if(null != mogoo)
		{
			final Context context = _context.getActivity();
			MogooPayment mogooPayment=new MogooPayment();
            MogooPaymentTO payment=new MogooPaymentTO();
            payment.uid = Util.getValueFromSharedPreferencesSave(Mogoo.MG_PREFIX_STR + "mid", context);
            payment.usn = Util.getValueFromSharedPreferencesSave(Mogoo.MG_PREFIX_STR + "username", context);
            payment.gid = Util.getValueFromSharedPreferencesSave(Mogoo.MG_PREFIX_STR + "gameid", context);
            payment.sid = serverID;//cp服务器id传值
            payment.eif = ext;//cp自定义信息，数组或英文组成 
            mogooPayment.dialog(context, payment, new PaymentListener() {
                @Override
                public void onComplete(Bundle bundle) {
                    String query=Util.encodeUrl(bundle);
                    callBack("MgPayDialog onComplete query:" + query);
                    Log.e("query", query);
                }

                @Override
                public void onMogooError(MogooError error) {
                	callBack("onMoGooError:" + error.getMessage());
                }

                @Override
                public String onQueryStringCreate(MogooPaymentTO payment) { 
                    String encodeParameter=
                        "uid=" + payment.uid + "&gid=" + payment.gid + "&sid=" + payment.sid + "&usn=" + URLEncoder.encode(payment.usn)+"&timestamp="
                                + payment.timestamp + "&eif=" + payment.eif;
                    
                    callBack("payArg:"+encodeParameter);
                    
                    return encodeParameter;
                }

                @Override
                public void onError(Error error) {
                	callBack("error:" + error.getMessage());
                }
            });
		}
		else
			callBack("init error");
	}
	
	/**
	 * 结果传给AS端
	 */
	private void callBack(String status){
		Log.d(TAG, "-----status----"+status);
		_context.dispatchStatusEventAsync(TAG,status);
	}
}
