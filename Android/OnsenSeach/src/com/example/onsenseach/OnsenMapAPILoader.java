package com.example.onsenseach;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;
import android.util.Xml;

public class OnsenMapAPILoader extends AsyncTaskLoader<List> {

	private final static String GEO_API_URL = "http://www.geocoding.jp/api/?v=1.1";

	private String pointName;

	private final static String API_PARAM = "&q=";

	public OnsenMapAPILoader(Context context, String name) {
		super(context);
		Log.d("OnsenMapAPILoader", "start");
		Log.d("OnsenMapAPILoader", name);
		this.pointName = name;
	}

	@Override
	protected void onStartLoading() {
		forceLoad();
		super.onStartLoading();
	}

	@Override
	public List loadInBackground() {
		Log.d("OnsenMapAPILoader", "loadInBackground");
		List result = new ArrayList();
		try{
		    XmlPullParser xmlPullParser = Xml.newPullParser();

		    StringBuffer sb = new StringBuffer();
		    String encName = "";
		    try {
		    	encName = URLEncoder.encode(pointName, "UTF-8");
		    } catch(UnsupportedEncodingException e) {
		    	e.printStackTrace();
		    }
		    sb.append(GEO_API_URL).append(API_PARAM).append(encName);

			Log.d("OnsenMapAPILoader", sb.toString());
		    URL url = new URL(sb.toString());
		    URLConnection connection = url.openConnection();
		    xmlPullParser.setInput(connection.getInputStream(), "UTF-8");

		    int eventType;
		    String address = "";
		    String lat = "";
		    String lng = "";
		    String lat_dms = "";
		    String lng_dms = "";
		    String google_maps = "";

		    while ((eventType = xmlPullParser.next()) != XmlPullParser.END_DOCUMENT) {
		        if (eventType == XmlPullParser.START_TAG && "address".equals(xmlPullParser.getName())) {
		        	address = xmlPullParser.nextText();
		        	Log.d("address", address);
		        } else if(eventType == XmlPullParser.START_TAG && "lat".equals(xmlPullParser.getName())) {
		        	lat = xmlPullParser.nextText();
		        	Log.d("lat", lat);
		        } else if(eventType == XmlPullParser.START_TAG && "lng".equals(xmlPullParser.getName())) {
		        	lng = xmlPullParser.nextText();
		        	Log.d("lng", lng);
		        } else if(eventType == XmlPullParser.START_TAG && "lat_dms".equals(xmlPullParser.getName())) {
		        	lat_dms = xmlPullParser.nextText();
		        	Log.d("lat_dms", lat_dms);
		        } else if(eventType == XmlPullParser.START_TAG && "lng_dms".equals(xmlPullParser.getName())) {
		        	lng_dms = xmlPullParser.nextText();
		        	Log.d("lng_dms", lng_dms);
		        } else if(eventType == XmlPullParser.START_TAG && "google_maps".equals(xmlPullParser.getName())) {
		        	google_maps = xmlPullParser.nextText();
		        	Log.d("google_maps", google_maps);
				    Map map = new HashMap();
		            map.put("address", address);
		            map.put("lat", lat);
		            map.put("lng", lng);
		            map.put("lat_dms", lat_dms);
		            map.put("lng_dms", lng_dms);
		            map.put("google_maps", google_maps);
		            result.add(map);
		        }
		    }
		} catch (Exception e){
		    Log.d("OnsenMapAPILoader", "Error");
		    Log.d("OnsenMapAPILoader", e.getMessage());
		}
			return result;
	}

}
