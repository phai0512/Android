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

public class PrefectureListFragment extends ListFragment implements LoaderCallbacks<List>{

	public PrefectureListFragment() {
		Log.d("PrefectureListFragment", "constractor");
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		Log.d("PrefectureListFragment", "onCreate");
		super.onCreate(savedInstanceState);
		//API呼び出し
		LoaderManager manager = getLoaderManager();
		Log.d("PrefectureListFragment", "getLoaderManager");
		manager.initLoader(1, null, this);
	}

	@Override
	public Loader<List> onCreateLoader(int id, Bundle args) {
		Log.d("PrefectureListFragment", "onCreateLoader");
		Loader<List> loader = null;
		switch(id) {
		case 1:
			//エリア検索API呼び出し
			loader = new OnsenAPILoader(getActivity(), id);
			break;
		default:
			break;
		}
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<List> loader, List result) {
		// TODO 自動生成されたメソッド・スタブ
		Log.d("onLoadFinished", "start");
		//位置情報の取得
		ArrayAdapter<Prefecture> list = new ArrayAdapter<Prefecture>(getActivity(), android.R.layout.simple_list_item_1);
		if(result == null) {
			Log.d("onLoadFinished", "result is null");
		} else {
			Iterator ite = result.iterator();
			Log.d("onLoadFinished", "while start");
			Map map_result = new HashMap();
			while(ite.hasNext()) {
				Object obj = ite.next();
				map_result = (Map) obj;
				Log.d("onLoadFinished", (String)map_result.get("code"));
				Log.d("onLoadFinished", (String)map_result.get("name"));
				list.add(new Prefecture((String)map_result.get("code"), (String)map_result.get("name")));
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
		ArrayAdapter<Prefecture> list = (ArrayAdapter<Prefecture>) getListAdapter();
		Prefecture pref = list.getItem(position);
		Log.d("onListItemClick", pref.getPrefectureCode());
		Log.d("onListItemClick", pref.getPrefectureName());
		//引数用意
		Bundle args = new Bundle();
		args.putString("code", pref.getPrefectureCode());
		args.putString("name", pref.getPrefectureName());
		Intent intent = new Intent(getActivity(), DetailActivity.class);
		intent.putExtras(args);
		startActivity(intent);
	}
}
