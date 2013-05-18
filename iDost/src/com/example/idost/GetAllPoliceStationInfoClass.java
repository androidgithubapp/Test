package com.example.idost;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.idost.pojo.AllPoliceStationInfoBean;
import com.example.idost.pojo.AppCommonBean;
import com.example.idost.util.AppCommonExceptionClass;
import com.example.idost.util.AppReflectUtilityClass;
import com.example.idost.util.GetUrlUtilityClass;


public class GetAllPoliceStationInfoClass {

     public static List<AllPoliceStationInfoBean> allPoliceStationInfoBeanArrayList;
   
    public void findAllPoliceStationInfo(double latitude, double longitude,String placeSpacification) throws AppCommonExceptionClass 
    {

    	try {
    		String urlString = this.makeUrl(latitude, longitude,placeSpacification);
        
            AppReflectUtilityClass.invokeMethod("com.example.idost.util.GetUrlUtilityClass", "getUrlConnContents",new Class[] {String.class}, new Object[] {urlString});
            String jsonAllPoliceStationInfo = GetUrlUtilityClass.urlContent;

            JSONObject jsonObj = new JSONObject(jsonAllPoliceStationInfo);
            JSONArray jsonObjArr = jsonObj.getJSONArray("results");

            allPoliceStationInfoBeanArrayList = new ArrayList<AllPoliceStationInfoBean>();
            for (int i = 0; i < jsonObjArr.length(); i++) {
                try {
                	//AllPoliceStationInfoBean allPoliceStationInfoBean = this.jsonToAllPoliceRefBean((JSONObject) jsonObjArr.get(i));
                	allPoliceStationInfoBeanArrayList.add(this.jsonToAllPoliceRefBean((JSONObject) jsonObjArr.get(i)));
                } catch (Exception e) {
                	throw new AppCommonExceptionClass(AppCommonBean.mContext, e);
                }
            }
            
        } catch (JSONException ex) {
        	throw new AppCommonExceptionClass(AppCommonBean.mContext, ex);
        }catch(Exception e)
        {
        	throw new AppCommonExceptionClass(AppCommonBean.mContext, e);
        }
    }
    
    private String makeUrl(double latitude, double longitude,String placeSpacification) throws AppCommonExceptionClass {
    	String retUrl= null;
    	try{
		    	String url = "https://maps.googleapis.com/maps/api/place/search/json?";
		    	
		    	ArrayList<String> params = new ArrayList<String>();
		    	params.add("&location="+Double.toString(latitude)+","+Double.toString(longitude));
		    	params.add("&radius=5000");
		    	params.add("&rankBy=distance");
		    	params.add("&types="+placeSpacification);
		    	params.add("&sensor=true");
		    	params.add("&key=" + AppCommonBean.API_KEY);
		    	
		    	AppReflectUtilityClass.invokeMethod("com.example.idost.util.GetUrlUtilityClass", "getUrl",new Class[] {ArrayList.class,String.class}, new Object[] {params, url});
		    	retUrl = GetUrlUtilityClass.urlStr;
		    	
    	}catch(Exception e){
    		throw new AppCommonExceptionClass(AppCommonBean.mContext, e);
    	}
    	
    	return retUrl;
	}

    
    @SuppressWarnings("static-access")
	private AllPoliceStationInfoBean jsonToAllPoliceRefBean(JSONObject jsonObj) throws AppCommonExceptionClass {
        try {
        	AllPoliceStationInfoBean allPlaceBean = new AllPoliceStationInfoBean();
        	Iterator<?> iter = jsonObj.keys();
        	 while(iter.hasNext()){
            
        	String jsonkey = (String)iter.next();
            String value = jsonObj.getString(jsonkey);
	            if(jsonkey.equalsIgnoreCase("icon"))
	            {
	            	allPlaceBean.icon = value;
	            }
	            if(jsonkey.equalsIgnoreCase("name"))
	            {
	            	allPlaceBean.name = value;
	            }
	            if(jsonkey.equalsIgnoreCase("vicinity"))
	            {
	            	allPlaceBean.vicinity = value;
	            }
	            if(jsonkey.equalsIgnoreCase("id"))
	            {
	            	allPlaceBean.id = value;
	            }
	            if(jsonkey.equalsIgnoreCase("reference"))
	            {
	            	allPlaceBean.reference = value;
	            }
	               
        	 }
        	
            return allPlaceBean;
        } catch (JSONException ex) {
        	throw new AppCommonExceptionClass(AppCommonBean.mContext, ex);
        }
    }
	
    
}