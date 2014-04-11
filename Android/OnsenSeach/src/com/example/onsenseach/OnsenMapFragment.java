package com.example.onsenseach;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class OnsenMapFragment extends MapFragment implements LoaderCallbacks<List> {

	private String onsenName = "";

	private Location location;

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
		} else {
			Log.d("OnsenMapFragment", "param ng");
		}
		//API呼び出し
		LoaderManager manager = getLoaderManager();
		manager.initLoader(1, null, this);
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
	public Loader<List> onCreateLoader(int id, Bundle args) {
		Log.d("OnsenMapFragment", "onCreateLoader");
		Loader<List> loader = null;
		switch(id) {
		case 1:
			//エリア検索API呼び出し
			loader = new OnsenMapAPILoader(getActivity(), onsenName);
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
				location = new Location((String)map_result.get("address"),
										new LatLng((Integer)map_result.get("lat"),(Integer)map_result.get("lng")),
										(String)map_result.get("google_maps"));
			}
		}
	}

	@Override
	public void onResume() {
		Log.d("OnsenMapFragment", "onResume");
		super.onResume();
/*		GoogleMap map = getMap();

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
*/
		//Locationに格納された座標を指定する
		if(location != null) {
			LatLng latlng = location.getLocation();
			String name = location.toString();

			//マップの中心点（カメラ位置）を指定
			CameraPosition position = new CameraPosition(latlng, 13.0f, 0, 0);
			CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
//			map.moveCamera(update);

			//マーカーの配置を設定
			MarkerOptions options = new MarkerOptions();
			options.position(latlng);
			options.title(name);
			options.snippet(latlng.toString());
			Log.d("OnsenMapFragment", name);
			Log.d("OnsenMapFragment", latlng.toString());
//			map.addMarker(options);
		}
	}

	@Override
	public void onLoaderReset(Loader<List> arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
