package com.example.idost.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.idost.R;
import com.example.idost.R.id;
import com.example.idost.R.layout;
import com.example.idost.R.menu;
import com.example.idost.util.PreferUtilityClass;

public class ContactActivity extends Activity {

	private TextView txtvw;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact);
		txtvw=(TextView)findViewById(R.id.conView);
		String condata=PreferUtilityClass.GetContact(ContactActivity.this);
		ShowContactData(condata);
	}
	 public void ShowContactData(String condata)
	 {
		 String[] strarr=condata.split(";");
		 txtvw.setText("");
		 try {
			for(int i=0;i<strarr.length;i++)
				 txtvw.append(strarr[i]+"\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contact, menu);
		return true;
	}

}
