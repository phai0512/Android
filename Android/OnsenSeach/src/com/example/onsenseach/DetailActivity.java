package com.example.onsenseach;

import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class DetailActivity extends Activity implements LoaderCallbacks<List>{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("DetailActivity", "onCreate");
		setContentView(R.layout.activity_detail);
		//フラグメントトランザクションを開始する
		Log.d("DetailActivity", "transaction start");
		FragmentManager manager = getFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();

		DetailListFragment fragment = new DetailListFragment();
		// パラメータ読込み
		Log.d("DetailActivity", "intent check");
		if(getIntent() != null && getIntent().getExtras() != null) {
			//インテントがあればその値をフラグメントに設定
			fragment.setArguments(getIntent().getExtras());
			Log.d("DetailActivity","intent ok");
		} else {
			Log.d("DetailActivity","intent ng");
		}
		transaction.add(R.id.container, fragment);
		transaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

	@Override
	public Loader<List> onCreateLoader(int arg0, Bundle arg1) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void onLoadFinished(Loader<List> arg0, List arg1) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onLoaderReset(Loader<List> arg0) {
		// TODO 自動生成されたメソッド・スタブ
	}

}
