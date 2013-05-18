package com.example.idost.util;

import android.content.Context;
import android.widget.Toast;

public class ShowToastActivityUtilityClass {

	Context mcontext;
	public ShowToastActivityUtilityClass(Context context)
	{
		this.mcontext = context;
	}
	
	public void showToast(String txtVal)
	{
		Toast toast = Toast.makeText(mcontext, txtVal, Toast.LENGTH_LONG);
		toast.show();
	}
	
}
