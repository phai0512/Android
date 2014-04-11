package com.example.onsenseach;

import com.google.android.gms.maps.model.LatLng;

public class Location {

	private String name;

	private LatLng location;

	private String mapName;

	public Location(String name, LatLng location, String mapName) {
		super();
		this.name = name;
		this.location = location;
		this.mapName = mapName;
	}

	/*
	 * ロケーション名称取得
	 * @return ロケーション名称
	 */
	public String getName() {
		return name;
	}

	/*
	 * ロケーション座標値取得
	 * @return ロケーション座標値
	 */
	public LatLng getLocation() {
		return location;
	}

	/*
	 * このオブジェクトの文字表現の規定
	 * @return このオブジェクトの文字表現
	 */
	public String toString() {
		return mapName;
	}

}
