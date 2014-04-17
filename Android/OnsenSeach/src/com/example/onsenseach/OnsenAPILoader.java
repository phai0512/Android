package com.example.onsenseach;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;
import android.util.Xml;

public class OnsenAPILoader extends AsyncTaskLoader<List> {

	private final static String API_URL = "http://jws.jalan.net/APICommon/AreaSearch/V1/?key=gem145004d6287";

	private int reg;

	private final static String API_PARAM = "&reg=";

	public OnsenAPILoader(Context context, int id) {
		super(context);
		Log.d("OnsenAPILoader", "constract");
		this.reg = id;
	}

	@Override
	public List loadInBackground() {
		Log.d("OnsenAPILoader", "loadInBackground");
		List result = new ArrayList();
		try{
			File cashefile = new File(getContext().getCacheDir(), "prefecture.xml");
			Log.d("loadInBackground", getContext().getCacheDir().toString());
			Calendar calendar = Calendar.getInstance();
			if(cashefile.exists()) {
				Log.d("loadInBackground", "cashefile.exists()1");
				calendar.setTime(new Date(cashefile.lastModified()));
				calendar.add(Calendar.HOUR, 1);
			}
			String prefecture;
		    XmlPullParser xmlPullParser = Xml.newPullParser();

			if(!cashefile.exists() || calendar.getTime().before(new Date())) {
				Log.d("loadInBackground", "!cashefile.exists()");
			    URL url = new URL(API_URL);
			    URLConnection connection = url.openConnection();
				InputStream stream = connection.getInputStream();
			    prefecture = loadInputStream(stream);
			    FileOutputStream cashe = null;
			    try {
			    	cashe = new FileOutputStream(cashefile);
			    	cashe.write(prefecture.getBytes());
			    } finally {
			    	if(cashe != null) {
					    xmlPullParser.setInput(stream, "UTF-8");
			    		cashe.close();
			    	}
			    }
			} else {
				Log.d("loadInBackground", "cashefile.exists()");
				InputStream stream = new FileInputStream(cashefile);
			    xmlPullParser.setInput(stream, "UTF-8");
			}
		    int eventType;
		    while ((eventType = xmlPullParser.next()) != XmlPullParser.END_DOCUMENT) {
		        if (eventType == XmlPullParser.START_TAG && "Prefecture".equals(xmlPullParser.getName())) {
//		            Log.d("XmlPullParserSampleUrl", xmlPullParser.getAttributeValue (null, "code"));
//		            Log.d("XmlPullParserSampleUrl", xmlPullParser.getAttributeValue (null, "name"));
				    Map map = new HashMap();
					map.put("code", xmlPullParser.getAttributeValue (null, "cd"));
		            map.put("name", xmlPullParser.getAttributeValue (null, "name"));
		            result.add(map);
		        }
		    }
		} catch (Exception e){
		    Log.d("XmlPullParserSampleUrl", "Error");
		}
			return result;
	}

	private String loadInputStream(InputStream inputStream) throws IOException{
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(inputStream, "UTF-8"));
		// レスポンス文字列を取得する
		String line;
		StringBuilder lines = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			lines.append(line);
		}
		return lines.toString();
	}

	@Override
	protected void onStartLoading() {
		Log.d("OnsenAPILoader", "onStartLoading");
		forceLoad();
		super.onStartLoading();
	}

}
