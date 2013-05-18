package com.example.idost.util;

import android.content.Context;

public class AppCommonExceptionClass extends Exception{

	private static final long serialVersionUID = 3602265767031781701L;
	
	public AppCommonExceptionClass(Context context,Exception ex)
	{
		super(ex);
	}
	
	
	/*public void showSettingsAlert(){
		
		// Logger.getLogger(AppCommonExceptionClass.class.getName()).log(Level.SEVERE, exceptionMsg, null);
		 
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
 
        // Setting Dialog Title
        alertDialog.setTitle("Exception Occured");
 
        // Setting Dialog Message
        alertDialog.setMessage(this.exceptionMsg + " occured at " + exceptionClass + "Class in " + exceptionMethod + " method");
 
        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
        	
            public void onClick(DialogInterface dialog,int which) {
            	dialog.cancel();
            	exceptionMsg = null;
            	exceptionClass = null;
            	exceptionMethod = null;
            }
            
        });
	
 	 // on pressing cancel button
    alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
        dialog.cancel();
        exceptionMsg = null;
    	exceptionClass = null;
    	exceptionMethod = null;
        }
    });

    
    // Showing Alert Message
    alertDialog.show();
	}
*/

}
