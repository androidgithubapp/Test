package com.example.idost;

import java.io.IOException;
import java.util.List;

import android.location.Address;
import android.location.Geocoder;

import com.example.idost.constant.AppCommonConstantsClass;
import com.example.idost.pojo.AppCommonBean;
import com.example.idost.pojo.CurrentAddressBean;
import com.example.idost.util.AppCommonExceptionClass;

public class GetCurrentAddrLocClass{
	
	double latitude;
	double longitude;
	public static CurrentAddressBean currentAddressBean = null;
	
	
	@SuppressWarnings("static-access")
	public void getGeoLocation(double latitude,double longitude) throws AppCommonExceptionClass
	{
		 Geocoder geocoder = new Geocoder(AppCommonBean.mContext); 
		            
          try {          
	        	  
        	  List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1); 
	        
		         	if (addresses != null && addresses.size() > 0) {    
		         		Address address = addresses.get(0);         
		         		currentAddressBean = new CurrentAddressBean();
		         		if(address.getAddressLine(0) != null)
		         		{
			         		currentAddressBean.addressLine = address.getAddressLine(0);
			         		currentAddressBean.locality = address.getLocality();
			         		currentAddressBean.adminArea = address.getAdminArea();
			         		currentAddressBean.countryCode = address.getCountryCode();
			         		currentAddressBean.countryNm = address.getCountryName();
			         		currentAddressBean.postalCode = address.getPostalCode();
			         		currentAddressBean.postalCode = address.getPhone();
		         		}
		         		else{
		         			throw new Exception(AppCommonConstantsClass.ADDR_NULL);
		         		}
		         		 
		         	}
		         	else
		         	{
		         		throw new Exception(AppCommonConstantsClass.ADDR_NULL);
		         	}
		         	
		        }
          		catch (IOException e) {
          			throw new AppCommonExceptionClass(AppCommonBean.mContext, e);
          		}catch(Exception e) {
		   			throw new AppCommonExceptionClass(AppCommonBean.mContext, e);
          		}
               
	}

	
}
