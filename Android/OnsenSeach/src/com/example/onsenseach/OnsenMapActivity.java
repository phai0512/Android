package com.example.onsenseach;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class OnsenMapActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("OnsenMapActivity", "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_onsen_map);
		//フラグメントトランザクションの開始
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();

		OnsenMapFragment fragment = new OnsenMapFragment();
		if(getIntent() != null && getIntent().getExtras() != null) {
			fragment.setArguments(getIntent().getExtras());
		}
		transaction.add(R.id.container, fragment);
		transaction.commit();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.onsen_map, menu);
		return true;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

}
