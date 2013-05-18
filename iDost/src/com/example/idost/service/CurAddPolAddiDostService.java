package com.example.idost.service;

import java.util.ArrayList;
import java.util.List;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;

import com.example.idost.GetAllPoliceStationInfoClass;
import com.example.idost.GetCurrentAddrLocClass;
import com.example.idost.GetLocationClass;
import com.example.idost.NearestPoliceStationInfoClass;
import com.example.idost.pojo.AllPoliceStationInfoBean;
import com.example.idost.pojo.AppCommonBean;
import com.example.idost.pojo.CurrentAddressBean;
import com.example.idost.pojo.NearestPoliceInfoBean;
import com.example.idost.receiver.ResponseIdostReceiver;
import com.example.idost.util.AppReflectUtilityClass;

public class CurAddPolAddiDostService extends IntentService{

	public static AppCommonBean appCommonBean ;
	public static boolean isExceptionOccured;
	public static boolean fireSMSService = false;
	
	public CurAddPolAddiDostService() {
		super("CurAddPolAddiDostService");
	}
	
	@Override
	  public void onCreate() {
	    super.onCreate();
	  }
	
	@SuppressWarnings("static-access")
	@Override
	protected void onHandleIntent(Intent intent) {
		appCommonBean = new AppCommonBean();
		isExceptionOccured = false;
		try{
			
			Location location = GetLocationClass.location;
				
			AppReflectUtilityClass.invokeMethod("com.example.idost.GetCurrentAddrLocClass", "getGeoLocation",new Class[] {Double.TYPE,Double.TYPE}, new Object[] {location.getLatitude(),location.getLongitude()});
			CurrentAddressBean currentAddressBean = GetCurrentAddrLocClass.currentAddressBean;
			
			
			AppReflectUtilityClass.invokeMethod("com.example.idost.GetAllPoliceStationInfoClass", "findAllPoliceStationInfo",new Class[] {Double.TYPE,Double.TYPE,String.class}, new Object[] {location.getLatitude(),location.getLongitude(),"police"});
			List<AllPoliceStationInfoBean> allPoliceStationInfoBean = GetAllPoliceStationInfoClass.allPoliceStationInfoBeanArrayList;
			
			
			NearestPoliceInfoBean nearestPoliceInfoBean = null;
			
			if(allPoliceStationInfoBean != null && allPoliceStationInfoBean.size()>0)
			{
				AppReflectUtilityClass.invokeMethod("com.example.idost.NearestPoliceStationInfoClass", "findNearPoliceStationInfo",new Class[] {ArrayList.class}, new Object[] {allPoliceStationInfoBean});
				nearestPoliceInfoBean = NearestPoliceStationInfoClass.nearestPoliceInfoBean;
			}
		
			appCommonBean.currentAddressBean = currentAddressBean;
			appCommonBean.allPoliceStationInfoBean = allPoliceStationInfoBean;
			appCommonBean.nearestPoliceInfoBean = nearestPoliceInfoBean;
			
			
		}catch(Exception ex)
		{
			isExceptionOccured = true;
		}
		
		Intent broadcastIntent = new Intent();
		broadcastIntent.setAction(ResponseIdostReceiver.ACTION_RESP);
		sendBroadcast(broadcastIntent);
		
		stopSelf();
				
	}
	
	@Override
	  public void onDestroy() {
	    super.onDestroy();
	  }

}
