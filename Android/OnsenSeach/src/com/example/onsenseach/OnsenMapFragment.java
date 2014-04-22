package com.example.onsenseach;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class OnsenMapFragment extends MapFragment {

	private Location location;

	private String onsenName = "";

	public OnsenMapFragment() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("OnsenMapFragment", "onCreate");

		//パラメータから文字列取得
		Bundle param = getArguments();
		if(param != null) {
			Log.d("OnsenMapFragment", "param ok");
			onsenName = param.getString("onsenName");
			location = new Location(param.getString("onsenName"),
					new LatLng(Double.parseDouble(param.getString("lat")),
								Double.parseDouble(param.getString("lng"))),
								param.getString("onsenAddress"));
		} else {
			Log.d("OnsenMapFragment", "param ng");
		}
	}

/*	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d("OnsenMapFragment", "onCreateView");
		super.onCreateView(inflater, container, savedInstanceState);

		//パラメータから文字列の取得
		//テキストボックス作成
		TextView view = new TextView(getActivity());

		Bundle param = getArguments();
		if(param != null) {
			view.setText(param.getString("onsenName"));
		}
		return view;
	}
*/
	@Override
	public void onResume() {
		Log.d("OnsenMapFragment", "onResume");
		super.onResume();
		GoogleMap map = getMap();

		//マップ基本設定
		//屋内マップ表示無効
		map.setIndoorEnabled(false);
		//ノーマルタイプ表示
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		//現在地表示ボタン有効
		map.setMyLocationEnabled(true);
		//渋滞状況無効
		map.setTrafficEnabled(false);

		//マップUI設定
		UiSettings ui = map.getUiSettings();
		//拡大縮小ボタン表示有効
		ui.setZoomControlsEnabled(true);
		//ズームジェスチャー有効
		ui.setZoomGesturesEnabled(true);

		//Locationに格納された座標を指定する
		if(location != null) {
			LatLng latlng = location.getLocation();
			String name = location.toString();

			//マップの中心点（カメラ位置）を指定
			CameraPosition position = new CameraPosition(latlng, 13.0f, 0, 0);
			CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
			map.moveCamera(update);

			//マーカーの配置を設定
			MarkerOptions options = new MarkerOptions();
			options.position(latlng);
			options.title(name);
			options.snippet(latlng.toString());
			Log.d("OnsenMapFragment", name);
			Log.d("OnsenMapFragment", latlng.toString());
			map.addMarker(options);
		}
	}

}
