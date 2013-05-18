package com.example.idost;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.idost.pojo.AllPoliceStationInfoBean;
import com.example.idost.pojo.AppCommonBean;
import com.example.idost.pojo.NearestPoliceInfoBean;
import com.example.idost.util.AppCommonExceptionClass;
import com.example.idost.util.AppReflectUtilityClass;
import com.example.idost.util.GetUrlUtilityClass;

public class NearestPoliceStationInfoClass {
	
	public static NearestPoliceInfoBean nearestPoliceInfoBean ;
	
	
	public void findNearPoliceStationInfo(ArrayList<AllPoliceStationInfoBean> placeList) throws AppCommonExceptionClass 
	    {
		 nearestPoliceInfoBean = null;
		     try {
	        	nearestPoliceInfoBean = this.getNearestPoliceInfo(placeList.get(0));
	        	/*if(false)
	        	{
	        		while(policeInfo.getPoliceFrmattedPhNo()==null || ("").equalsIgnoreCase(policeInfo.getPoliceFrmattedPhNo()))
	        		{
	        			policeInfo = this.getNearestPoliceInfo(placeList.get(++index));	
	        		}
	        	}*/	        	
	            
	       } catch (AppCommonExceptionClass e) {
	        	throw new AppCommonExceptionClass(AppCommonBean.mContext, e);
		 	       
			}
	      
	    }
	 
	 private NearestPoliceInfoBean getNearestPoliceInfo(AllPoliceStationInfoBean allPoliceStationInfoBean) throws AppCommonExceptionClass
	 {
		 
		 try{
			 String urlString = this.makeUrl(allPoliceStationInfoBean);
		  
			 AppReflectUtilityClass.invokeMethod("com.example.idost.util.GetUrlUtilityClass", "getUrlConnContents",new Class[] {String.class}, new Object[] {urlString});
			 String jsonNearestPoliceStationInfo = GetUrlUtilityClass.urlContent;

		  
			 JSONObject jsonObj = new JSONObject(jsonNearestPoliceStationInfo);
			 JSONObject jsonObjResult = jsonObj.getJSONObject("result");
			 return this.jsonToNearestPoliceInfoBean(jsonObjResult);
          
		 }catch(JSONException jse)
		 {
			 throw new AppCommonExceptionClass(AppCommonBean.mContext, jse);
		 }
		 catch(Exception e)
		 {
			 throw new AppCommonExceptionClass(AppCommonBean.mContext, e);
		 }
         
         
     }
	 
	    @SuppressWarnings("static-access")
		private String makeUrl(AllPoliceStationInfoBean allPoliceStationInfoBean) throws AppCommonExceptionClass {
	        String retUrl;
	    	try{
	    	String url = "https://maps.googleapis.com/maps/api/place/details/json?";
	    	
	    	ArrayList<String> params = new ArrayList<String>();
	    	params.add("reference="+allPoliceStationInfoBean.reference);
	    	params.add("&sensor=true");
	    	params.add("&key=" + AppCommonBean.API_KEY);
	    	
	    	
	    	AppReflectUtilityClass.invokeMethod("com.example.idost.util.GetUrlUtilityClass", "getUrl",new Class[] {ArrayList.class,String.class}, new Object[] {params, url});
	    	retUrl = GetUrlUtilityClass.urlStr;
	    	
	    	}catch(Exception e)
	          {
	    		throw new AppCommonExceptionClass(AppCommonBean.mContext, e);
	        	
	          }
	    	return retUrl;
	    }

	 
	    @SuppressWarnings("static-access")
		private NearestPoliceInfoBean jsonToNearestPoliceInfoBean(JSONObject jsonObj) throws AppCommonExceptionClass {
	    	NearestPoliceInfoBean result =null;
	    	try {
	        	
	        	Iterator<?> iter = jsonObj.keys();
	        	result = new NearestPoliceInfoBean();
	            while(iter.hasNext()){
	                String jsonkey = (String)iter.next();
	                String value = jsonObj.getString(jsonkey);
	                if(jsonkey.equalsIgnoreCase("id"))
	                {
	                	result.policeId = value;
	                }
	                if(jsonkey.equalsIgnoreCase("name"))
	                {
	                	  result.policeNm = value;
	  		          
	                }
	                if(jsonkey.equalsIgnoreCase("formatted_address"))
	                {
	                	  result.policeFrmattedAdd = value;
		  		          
	                }
	                if(jsonkey.equalsIgnoreCase("international_phone_number"))
	                {
	                	  result.policeIntFrmattedPhNo = value;
		  		               
	                }
	                if(jsonkey.equalsIgnoreCase("formatted_phone_number"))
	                {
	                	  result.policeFrmattedPhNo = value;
		  		     
	                }
	                if(jsonkey.equalsIgnoreCase("vicinity"))
	                {
	                    result.policeVicinity = value;
			  		     
	                }
	                
	             }
	        	
	        } catch (JSONException ex) {
	        	throw new AppCommonExceptionClass(AppCommonBean.mContext, ex);
	 	        }
	    	return result;
	    }
		
}
