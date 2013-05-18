package com.example.idost.util;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.example.idost.R;
import com.example.idost.pojo.ContactBean;

public class PreferUtilityClass {

	SharedPreferences shrpref;
	
	public static void StoreContact(Context context, ContactBean conobj) 
	{
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		Editor edit = prefs.edit();
		String phndata=getData(context);
		if(phndata.equals(""))
		{
		//	edit.putString(context.getString(R.string.CONVAL),conobj.getPhn());
			
			edit.putString(context.getString(R.string.CONVAL),conobj.getName()+":"+conobj.getPhn());
		}
		else
		edit.putString(context.getString(R.string.CONVAL),phndata+";"+conobj.getName()+":"+conobj.getPhn());
		edit.commit();
	}
	
	public static String GetContact(Context context) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		return prefs.getString(context.getString(R.string.CONVAL), "");

		
	}
	
	public static String[] GetContactNumber(Context context)
	{
		List<String> conNumList=new ArrayList<String>();
		try 
		{
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(context);
			String[] conNum= prefs.getString(context.getString(R.string.CONVAL), "").split(";");
			
			for(int i=0;i<conNum.length;i++)
			{
				String num=conNum[i].trim();
				int strtIndx = conNum[i].lastIndexOf(":")+1;
				int endIndx = conNum[i].length();
				conNumList.add(num.substring(strtIndx, endIndx));
			}
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] contactList = conNumList.toArray(new String[conNumList.size()]);
		return contactList;
	}
	
	public static String getData(Context context)
	{
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		String data = prefs.getString(context.getString(R.string.CONVAL), null);
		if(data==null)
			return "";
		else
		return data;
	}
}
