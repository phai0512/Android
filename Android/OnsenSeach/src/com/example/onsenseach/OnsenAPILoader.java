package com.example.onsenseach;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
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
		    XmlPullParser xmlPullParser = Xml.newPullParser();

		    URL url = new URL(API_URL);
		    URLConnection connection = url.openConnection();
		    xmlPullParser.setInput(connection.getInputStream(), "UTF-8");

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

	@Override
	protected void onStartLoading() {
		Log.d("OnsenAPILoader", "onStartLoading");
		forceLoad();
		super.onStartLoading();
	}

}
