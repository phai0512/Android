package com.example.onsenseach;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ContentActivity extends Activity implements OnClickListener,LoaderCallbacks<List> {

	private Location location;
	private String lat;
	private String lng;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("ContentActivity", "start");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);
		progressDialog = new ProgressDialog(this);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progressDialog.setMessage("Loading...");
		progressDialog.setCancelable(true);
		progressDialog.show();

		//API呼び出し
		LoaderManager manager = getLoaderManager();
		manager.initLoader(1, null, this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.content, menu);
		return true;
	}
	@Override
	public Loader<List> onCreateLoader(int id, Bundle args) {
		Log.d("OnsenMapFragment", "onCreateLoader");
		Loader<List> loader = null;
		switch(id) {
		case 1:
			setProgressBarVisibility(true);
			//エリア検索API呼び出し
			loader = new OnsenMapAPILoader(this, getIntent().getStringExtra("onsenName"));
			break;
		default:
			break;
		}
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<List> loader, List result) {
		// TODO 自動生成されたメソッド・スタブ
		Log.d("OnsenMapFragment", "onLoadFinished");
		//位置情報の取得
		if(result == null) {
			Log.d("onLoadFinished", "result is null");
		} else {
			Iterator ite = result.iterator();
			Log.d("onLoadFinished", "while start");
			Map map_result = new HashMap();
			while(ite.hasNext()) {
				Object obj = ite.next();
				map_result = (Map) obj;
				Log.d("OnsenMapFragment", (String)map_result.get("address"));
				Log.d("OnsenMapFragment", (String)map_result.get("lat"));
				Log.d("OnsenMapFragment", (String)map_result.get("lng"));
				Log.d("OnsenMapFragment", (String)map_result.get("lat_dms"));
				Log.d("OnsenMapFragment", (String)map_result.get("lng_dms"));
				Log.d("OnsenMapFragment", (String)map_result.get("google_maps"));
				lat = (String)map_result.get("lat");
				lng = (String)map_result.get("lng");
			}
		}
		//選択された詳細値の設定
		Intent intent = getIntent();
		TextView onsenName = (TextView) findViewById(R.id.text_onsenName);
		TextView onsenAddress = (TextView) findViewById(R.id.text_onsenAddress);
		TextView natureOfOnsen = (TextView) findViewById(R.id.text_natureOfOnsen);
		TextView onsenAreaNameOnsenAreaURL = (TextView) findViewById(R.id.text_onsenAreaNameOnsenAreaURL);
		TextView onsenAreaCaption = (TextView) findViewById(R.id.text_onsenAreaCaption);
		TextView onsenLat = (TextView) findViewById(R.id.text_lat);
		TextView onsenLng = (TextView) findViewById(R.id.text_lng);

		//view設定
		onsenName.setText(intent.getStringExtra("onsenName"));
		onsenAddress.setText(intent.getStringExtra("onsenAddress"));
		natureOfOnsen.setText(intent.getStringExtra("natureOfOnsen"));
		onsenAreaNameOnsenAreaURL.setText(intent.getStringExtra("onsenAreaNameOnsenAreaURL"));
		onsenAreaCaption.setText(intent.getStringExtra("onsenAreaCaption"));
		onsenLat.setText(lat);
		onsenLat.setVisibility(View.INVISIBLE);
		onsenLng.setText(lng);
		onsenLng.setVisibility(View.INVISIBLE);

		Linkify.addLinks(onsenAreaNameOnsenAreaURL, Linkify.ALL);

		//イベントハンドラの設定
		Button button_map = (Button) findViewById(R.id.button_map);
		button_map.setOnClickListener(this);

/*		AQuery aq = new AQuery(this);
		aq.id(R.id.text_onsenName).text(intent.getStringExtra("onsenName"));
		aq.id(R.id.text_onsenAddress).text(intent.getStringExtra("onsenAddress"));
		aq.id(R.id.text_natureOfOnsen).text(intent.getStringExtra("natureOfOnsen"));
		aq.id(R.id.text_onsenAreaCaption).text(intent.getStringExtra("onsenAreaCaption"));
		aq.id(R.id.lat).text(location.getLocation().toString());
		aq.id(R.id.lng).text(location.getLocation().toString());
		aq.id(R.id.button_map).clicked(this, "onClick");
		TextView onsenAreaNameOnsenAreaURL = (TextView) findViewById(R.id.text_onsenAreaNameOnsenAreaURL);
		Linkify.addLinks(onsenAreaNameOnsenAreaURL, Linkify.ALL);
*/		progressDialog.dismiss();
	}

	@Override
	public void onClick(View v) {
		Log.d("ContentActivity", "onClick_start");
        // テキストビューのテキストを取得します
        String onsenName = ((TextView) findViewById(R.id.text_onsenName)).getText().toString();
        String onsenAddress = ((TextView) findViewById(R.id.text_onsenAddress)).getText().toString();
        String lat = ((TextView) findViewById(R.id.text_lat)).getText().toString();
        String lng = ((TextView) findViewById(R.id.text_lng)).getText().toString();
        Log.d("ContentActivity", onsenName);
		Log.d("ContentActivity", onsenAddress);
		Log.d("ContentActivity", lat);
		Log.d("ContentActivity", lng);
		Bundle args = new Bundle();
		args.putString("onsenName", onsenName);
		args.putString("onsenAddress",onsenAddress);
		args.putString("lat", lat);
		args.putString("lng", lng);
		Intent intent = new Intent(this, OnsenMapActivity.class);
		intent.putExtras(args);
		startActivity(intent);
	}

	@Override
	public void onLoaderReset(Loader<List> arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
