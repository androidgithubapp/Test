package com.example.idost.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;

public class ShowAlertUtilityClass {
	Context mContext;
	
	public ShowAlertUtilityClass(Context context)
	{
		this.mContext = context;
	}

	
	public void showSettingsAlert(String alertClassification){
		AlertDialog.Builder alertDialog = null;
		if("NetOrGpsEnable".equalsIgnoreCase(alertClassification))
		{
			alertDialog = new AlertDialog.Builder(mContext);
			 
	        // Setting Dialog Title
	        alertDialog.setTitle("GPS/Network settings");
	 
	        // Setting Dialog Message
	        alertDialog.setMessage("GPS/Network is not enabled. Do you want to go to settings menu ?");
	 
	        // On pressing Settings button
	        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
	        	
	            public void onClick(DialogInterface dialog,int which) {
	                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
	                mContext.startActivity(intent);
	            }
	            
	        });
		
	 	 // on pressing cancel button
	    alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) {
	        dialog.cancel();
	        ((Activity) mContext).finish();
	        }
	    });

		}
		
	// Showing Alert Message
    alertDialog.show();
	}

}
