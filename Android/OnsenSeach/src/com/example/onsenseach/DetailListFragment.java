package com.example.onsenseach;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.ListFragment;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DetailListFragment extends ListFragment implements LoaderCallbacks<List>{

	private String code = "";

	public DetailListFragment() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("DetailListFragment", "onCreate");
		super.onCreate(savedInstanceState);

		//パラメータから文字列取得
		Bundle param = getArguments();
		if(param != null) {
			Log.d("DetailListFragment", "param ok");
			code = param.getString("code");
		} else {
			Log.d("DetailListFragment", "param ng");
		}
		//API呼び出し
		LoaderManager manager = getLoaderManager();
		manager.initLoader(1, null, this);
	}

	@Override
	public Loader<List> onCreateLoader(int id, Bundle args) {
		Log.d("DetailListFragment", "onCreateLoader");
		Loader<List> loader = null;
		switch(id) {
		case 1:
			//エリア検索API呼び出し
			loader = new OnsenDetailAPILoader(getActivity(), code);
			break;
		default:
			break;
		}
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<List> loader, List result) {
		// TODO 自動生成されたメソッド・スタブ
		Log.d("DetailListFragment", "onLoadFinished");
		//位置情報の取得
		ArrayAdapter<OnsenDetail> list = new ArrayAdapter<OnsenDetail>(getActivity(), android.R.layout.simple_list_item_1);
		if(result == null) {
			Log.d("DetailListFragment", "result is null");
		} else {
			Iterator ite = result.iterator();
			Log.d("onLoadFinished", "while start");
			Map map_result = new HashMap();
			while(ite.hasNext()) {
				Object obj = ite.next();
				map_result = (Map) obj;
				Log.d("onLoadFinished", (String)map_result.get("onsenName"));
				Log.d("onLoadFinished", (String)map_result.get("onsenID"));
				Log.d("onLoadFinished", (String)map_result.get("onsenAddress"));
				Log.d("onLoadFinished", (String)map_result.get("natureOfOnsen"));
				Log.d("onLoadFinished", (String)map_result.get("onsenAreaNameOnsenAreaURL"));
				Log.d("onLoadFinished", (String)map_result.get("onsenAreaCaption"));
				list.add(new OnsenDetail((String)map_result.get("onsenName"),
						                 (String)map_result.get("onsenID"),
						                 (String)map_result.get("onsenAddress"),
						                 (String)map_result.get("natureOfOnsen"),
						                 (String)map_result.get("onsenAreaNameOnsenAreaURL"),
						                 (String)map_result.get("onsenAreaCaption")));
			}
		}
		//リスト登録
		setListAdapter(list);
	}

	@Override
	public void onLoaderReset(Loader<List> arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		//画面遷移
		ArrayAdapter<OnsenDetail> list = (ArrayAdapter<OnsenDetail>) getListAdapter();
		OnsenDetail detail = list.getItem(position);
		Log.d("onListItemClick", detail.getOnsenName());
		Log.d("onListItemClick", detail.getOnsenAddress());
		Log.d("onListItemClick", detail.getNature());
		Log.d("onListItemClick", detail.getAreaURL());
		Log.d("onListItemClick", detail.getCaption());

		//引数用意
		Bundle args = new Bundle();
		args.putString("onsenName",    detail.getOnsenName());
		args.putString("onsenAddress", detail.getOnsenAddress());
		args.putString("natureOfOnsen", detail.getNature());
		args.putString("onsenAreaNameOnsenAreaURL", detail.getAreaURL());
		args.putString("onsenAreaCaption", detail.getCaption());

		Intent intent = new Intent(getActivity(), ContentActivity.class);
		intent.putExtras(args);
		startActivity(intent);
	}

}
