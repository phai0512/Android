package com.example.onsenseach;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ContentActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("ContentActivity", "start");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_content);

		//選択された詳細値の設定
		Intent intent = getIntent();
		TextView onsenName = (TextView) findViewById(R.id.text_onsenName);
		TextView onsenAddress = (TextView) findViewById(R.id.text_onsenAddress);
		TextView natureOfOnsen = (TextView) findViewById(R.id.text_natureOfOnsen);
		TextView onsenAreaNameOnsenAreaURL = (TextView) findViewById(R.id.text_onsenAreaNameOnsenAreaURL);
		TextView onsenAreaCaption = (TextView) findViewById(R.id.text_onsenAreaCaption);
		
		//view設定
		onsenName.setText(intent.getStringExtra("onsenName"));
		onsenAddress.setText(intent.getStringExtra("onsenAddress"));
		natureOfOnsen.setText(intent.getStringExtra("natureOfOnsen"));
		onsenAreaNameOnsenAreaURL.setText(intent.getStringExtra("onsenAreaNameOnsenAreaURL"));
		onsenAreaCaption.setText(intent.getStringExtra("onsenAreaCaption"));
		Linkify.addLinks(onsenAreaNameOnsenAreaURL, Linkify.ALL);

		//イベントハンドラの設定
		Button button_map = (Button) findViewById(R.id.button_map);
		button_map.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.content, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Log.d("ContentActivity", "onClick_start");
        TextView textView = (TextView) findViewById(R.id.text_onsenName);
        // テキストビューのテキストを取得します
        String onsenName = textView.getText().toString();
		Log.d("ContentActivity", onsenName);
		Bundle args = new Bundle();
		args.putString("onsenName", onsenName);
		Intent intent = new Intent(this, OnsenMapActivity.class);
		intent.putExtras(args);
		startActivity(intent);
	}

}
