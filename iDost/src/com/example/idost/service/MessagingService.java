package com.example.idost.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.example.idost.util.PreferUtilityClass;

public class MessagingService extends IntentService {

	
	public MessagingService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}


	private static final int DELAY = 30000; //time in milli sec
	private static final String TAG = "MessagingService";
	boolean running = false;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}


	/*public int onStartCommand(Intent intent, int flags, int startId)
	{
		running = true;
		
		String currentAddress = CurAddPolAddiDostService.appCommonBean.getCurrentAddressBean().getAddressLine() + 
								CurAddPolAddiDostService.appCommonBean.getCurrentAddressBean().getLocality();
		
		final String msgContent = "I am in deep trouble. Please help! I am currently at the following location : " + 
								currentAddress;
		new Thread()
		{
			public void run()
			{
				String[] msg = {"9836156052",
						msgContent};
				while(running)
				{
					new SendSms().execute(msg);
					try{
						Thread.sleep(DELAY);
					}
					catch(InterruptedException iex)
					{
						iex.printStackTrace();
					}
				}
			}
		}.start();
		
		return super.onStartCommand(intent, flags, startId);
	}*/
		
	class SendSms extends AsyncTask<String, Void, String>
		{
			
			@Override
			protected String doInBackground(String... params) {
				// TODO Auto-generated method stub
				try	
				{
					//creating message body
					
					String currentAddress = CurAddPolAddiDostService.appCommonBean.currentAddressBean.addressLine + "\n" +  
							CurAddPolAddiDostService.appCommonBean.currentAddressBean.locality;
					
					String nearestPolInfo = "Police station address : \n" + 
							CurAddPolAddiDostService.appCommonBean.nearestPoliceInfoBean.policeNm + " \n" +
							CurAddPolAddiDostService.appCommonBean.nearestPoliceInfoBean.policeVicinity + "\n\n" +
							"Contact Number : \n" +
							CurAddPolAddiDostService.appCommonBean.nearestPoliceInfoBean.policeIntFrmattedPhNo;
					
					final String msgContent = "I am in deep trouble. Please help! I am currently at the following location : " + 
												currentAddress + "\n" + 
												"The following are the details of the nearest Police Station : \n" + 
												nearestPolInfo;
					
					int SmsRcvrCount = 0;
					if(params!=null)
					{
						
						if(params.length>0)
						{
							if(params.length<=5)
								SmsRcvrCount = params.length;
							else
								SmsRcvrCount = 5;
							
						
							for(int i=0;i<SmsRcvrCount;i++)
							{
								SmsManager smsManager = SmsManager.getDefault();
								smsManager.sendTextMessage(params[i], null, msgContent, null, null);
								
							}
							return "message sent to five of your closest people!";
						}
						else
						{
							return "Oops! seems like you are emergency list is not configured." +
									"Please go to menu and add contacts to the emergency list";
						}
					}
					else
					{
						return "Oops! seems like you are emergency list is not configured." +
								"Please go to menu and add contacts to the emergency list";
					}
					//Toast.makeText(StatusActivity.this, "Message sent!", Toast.LENGTH_SHORT).show();
				}	
				catch(Exception e)
				{
					//Toast.makeText(StatusActivity.this, "failed to send message!", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
					return "message sending failed!";
				}
			}
			public void onPostExecute(String result)
			{
				super.onPostExecute(result);
				Toast.makeText(getApplicationContext(), 
						result, Toast.LENGTH_SHORT).show();
			}
		}
	
	
	/*public void onDestroy() 
	{
		super.onDestroy();
		running = false;
	}*/


	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		
		
		
		String[] contactNos = PreferUtilityClass.GetContactNumber(this);

		String currentAddress = CurAddPolAddiDostService.appCommonBean.currentAddressBean.addressLine + "\n" +  
				CurAddPolAddiDostService.appCommonBean.currentAddressBean.locality;
		
		String nearestPolInfo = "Police station address : \n" + 
				CurAddPolAddiDostService.appCommonBean.nearestPoliceInfoBean.policeNm + " \n" +
				CurAddPolAddiDostService.appCommonBean.nearestPoliceInfoBean.policeVicinity + "\n\n" +
				"Contact Number : \n" +
				CurAddPolAddiDostService.appCommonBean.nearestPoliceInfoBean.policeIntFrmattedPhNo;
		
		final String msgContent = "I am in deep trouble. Please help! I am currently at the following location : " + 
									currentAddress + "\n" + 
									"The following are the details of the nearest Police Station : \n" + 
									nearestPolInfo;
		
		int SmsRcvrCount = 0;
		if(contactNos!=null)
		{
			
			if(contactNos.length>0)
			{
				if(contactNos.length<=5)
					SmsRcvrCount = contactNos.length;
				else
					SmsRcvrCount = 5;
				
			
				for(int i=0;i<SmsRcvrCount;i++)
				{
					SmsManager smsManager = SmsManager.getDefault();
					smsManager.sendTextMessage(contactNos[i], null, msgContent, null, null);
					
				}
				//return "message sent to five of your closest people!";
			}
			else
			{
				/*return "Oops! seems like you are emergency list is not configured." +
						"Please go to menu and add contacts to the emergency list";*/
			}
		}
				//new SendSms().execute(contactNos);
		}

}
