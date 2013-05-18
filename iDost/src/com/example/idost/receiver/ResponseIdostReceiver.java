package com.example.idost.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.example.idost.service.CurAddPolAddiDostService;

public class ResponseIdostReceiver extends BroadcastReceiver{

	public static TextView input;
	public static String ACTION_RESP = "com.example.idost.service.RESPONSE";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		if(!CurAddPolAddiDostService.isExceptionOccured)
		{
			input.setText("Service loaded everyThing");
		}
		else{
			input.setText("there is an exception in service");
		}
		
	}

}
