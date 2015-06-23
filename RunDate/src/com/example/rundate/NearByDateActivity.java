package com.example.rundate;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;

public class NearByDateActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_near_by_date);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.near_by_date, menu);
		return true;
	}

}
