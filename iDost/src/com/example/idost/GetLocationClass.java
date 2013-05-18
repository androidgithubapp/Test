package com.example.idost;

import android.app.Service;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

import com.example.idost.constant.AppCommonConstantsClass;
import com.example.idost.pojo.AppCommonBean;
import com.example.idost.util.AppCommonExceptionClass;

public class GetLocationClass extends Service{
	
	private LocationManager locationManager = null;
	public static Location location;

	public void getLocation() throws AppCommonExceptionClass {
		try {
			if (locationManager == null) {
				locationManager = (LocationManager) AppCommonBean.mContext.getSystemService(LOCATION_SERVICE);
			}
			
			if(locationManager != null)
			{
				this.getProvider();
			}
			else
			{
				throw new Exception(AppCommonConstantsClass.LOC_MNG_NULL);
			}

		} catch (Exception e) {
			throw new AppCommonExceptionClass(AppCommonBean.mContext, e);
		}

	}

	/*
	 * getting the provider network/GPs
	 */
	private void getProvider() throws AppCommonExceptionClass {
		try {
			Criteria crt = new Criteria();
			crt.setAccuracy(Criteria.ACCURACY_FINE);
			crt.setCostAllowed(false);
			String provider = "";

			provider = locationManager.getBestProvider(crt, true);

			if (provider != null) {
				
				if ("gps".equalsIgnoreCase(provider)) {
					this.getGpsProvider();

				} else if ("network".equalsIgnoreCase(provider)) {
					this.getNetworkProvider();
				}

			} else {
				throw new Exception(AppCommonConstantsClass.LOC_PROVIDER_NULL);
			}

		} catch (Exception e) {
			throw new AppCommonExceptionClass(AppCommonBean.mContext, e);
		}
	}

	private void getNetworkProvider() throws Exception {
		Boolean isNetworkEnabled = false;
		location = null;
		// getting network status
		isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

		// get the location from network provider
		if (isNetworkEnabled) {
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,networkLocationListener);
			location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		} else {
			throw new Exception(AppCommonConstantsClass.LOC_NET_PROVIDER_NULL);
		}
		
		if(location == null)
		{
			throw new Exception(AppCommonConstantsClass.LOC_NULL);
		}

	}

	private void getGpsProvider() throws Exception {
		Boolean isGPSEnabled = false;
		location = null;
		// getting GPS status
		isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

		// get the location from GPS provider
		if (isGPSEnabled) {
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, gpsLocationListener);
			location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (location == null) {
				this.getNetworkProvider();
			}
		} else {
			throw new Exception(AppCommonConstantsClass.LOC_GPS_PROVIDER_NULL);
		}
	}

	
	private final LocationListener networkLocationListener = new LocationListener(){

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		
		}

		@Override
		public void onProviderEnabled(String provider) {
			
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onLocationChanged(Location loc) {
			location = loc;
			//AppCallServiceUtilityClass.getService(AppCommonBean.mContext, "com.example.idost.service.CurAddPolAddiDostService");
		}
	};

	private final LocationListener gpsLocationListener = new LocationListener() {

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onLocationChanged(Location loc) {
			location = loc;
			//AppCallServiceUtilityClass.getService(AppCommonBean.mContext, "com.example.idost.service.CurAddPolAddiDostService");
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
