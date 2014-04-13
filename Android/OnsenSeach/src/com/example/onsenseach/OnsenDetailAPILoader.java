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

public class OnsenDetailAPILoader extends AsyncTaskLoader<List> {

	private final static String API_URL = "http://jws.jalan.net/APICommon/OnsenSearch/V1/?key=gem145004d6287&count=100";

	private String pref;

	private final static String API_PARAM = "&pref=";

	private final static String API_PARAM_END = "&xml_ptn=1";

	public OnsenDetailAPILoader(Context context, String code) {
		super(context);
		Log.d("OnsendetailAPILoader", "start");
		Log.d("OnsendetailAPILoader", code);
		this.pref = code;
		// TODO 自動生成されたコンストラクター・スタブ
	}
	@Override
	protected void onStartLoading() {
		forceLoad();
		super.onStartLoading();
	}
	@Override
	public List loadInBackground() {
		Log.d("OnsendetailAPILoader", "loadInBackground");
		List result = new ArrayList();
		try{
		    XmlPullParser xmlPullParser = Xml.newPullParser();

		    StringBuffer sb = new StringBuffer();
		    sb.append(API_URL).append(API_PARAM).append(pref).append(API_PARAM_END);

		    URL url = new URL(sb.toString());
		    URLConnection connection = url.openConnection();
		    xmlPullParser.setInput(connection.getInputStream(), "UTF-8");

		    int eventType;
		    String onsenName = "";
		    String onsenID = "";
		    String onsenLargeArea = "";
		    String onsenAddress = "";
		    String natureOfOnsen = "";
		    String onsenAreaNameOnsenAreaURL = "";
		    String onsenAreaCaption = "";

		    while ((eventType = xmlPullParser.next()) != XmlPullParser.END_DOCUMENT) {
		        if (eventType == XmlPullParser.START_TAG && "OnsenName".equals(xmlPullParser.getName())) {
		            onsenName = xmlPullParser.nextText();
		        	Log.d("XmlPullParserSampleUrl", onsenName);
		        } else if(eventType == XmlPullParser.START_TAG && "OnsenID".equals(xmlPullParser.getName())) {
		            onsenID = xmlPullParser.nextText();
		        	Log.d("XmlPullParserSampleUrl", onsenID);
		        } else if(eventType == XmlPullParser.START_TAG && "LargeArea".equals(xmlPullParser.getName())) {
		        	onsenLargeArea = xmlPullParser.nextText();
		        	Log.d("XmlPullParserSampleUrl", onsenLargeArea);
		        } else if(eventType == XmlPullParser.START_TAG && "OnsenAddress".equals(xmlPullParser.getName())) {
		            onsenAddress = xmlPullParser.nextText();
		        	Log.d("XmlPullParserSampleUrl", onsenAddress);
		        } else if(eventType == XmlPullParser.START_TAG && "NatureOfOnsen".equals(xmlPullParser.getName())) {
		            natureOfOnsen = xmlPullParser.nextText();
		        	Log.d("XmlPullParserSampleUrl", natureOfOnsen);
		        } else if(eventType == XmlPullParser.START_TAG && "OnsenAreaURL".equals(xmlPullParser.getName())) {
		            onsenAreaNameOnsenAreaURL = xmlPullParser.nextText();
		        	Log.d("XmlPullParserSampleUrl", onsenAreaNameOnsenAreaURL);
		        } else if(eventType == XmlPullParser.START_TAG && "OnsenAreaCaption".equals(xmlPullParser.getName())) {
		            onsenAreaCaption = xmlPullParser.nextText();
		        	Log.d("XmlPullParserSampleUrl", onsenAreaCaption);
				    Map map = new HashMap();
		            map.put("onsenName", onsenName);
		            map.put("onsenID", onsenID);
		            map.put("largeArea", onsenLargeArea);
		            map.put("onsenAddress", onsenAddress);
		            map.put("natureOfOnsen", natureOfOnsen);
		            map.put("onsenAreaNameOnsenAreaURL", onsenAreaNameOnsenAreaURL);
		            map.put("onsenAreaCaption", onsenAreaCaption);
		            result.add(map);
		        }
		    }
		} catch (Exception e){
		    Log.d("OnsendetailAPILoader", "Error");
		}
			return result;
	}

}
