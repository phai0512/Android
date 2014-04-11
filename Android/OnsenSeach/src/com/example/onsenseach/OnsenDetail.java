package com.example.onsenseach;

public class OnsenDetail {

	private String onsenName;

	private String onsenID;

	private String onsenAddress;

	private String natureOfOnsen;

	private String onsenAreaNameOnsenAreaURL;

	private String onsenAreaCaption;

	public OnsenDetail(String name, String id, String address, String nature, String areaurl, String caption) {
		super();
		this.onsenName = name;
		this.onsenID = id;
		this.onsenAddress = address;
		this.natureOfOnsen = nature;
		this.onsenAreaNameOnsenAreaURL = areaurl;
		this.onsenAreaCaption = caption;
	}

	/*
	 * 温泉の名称を取得
	 * @return 温泉名
	 */
	public String getOnsenName() {
		return onsenName;
	}

	/*
	 * 温泉のコードを取得
	 * @return 温泉コード
	 */
	public String getOnsenID() {
		return onsenID;
	}
	/*
	 * 温泉の住所を取得
	 * @return 住所
	 */
	public String getOnsenAddress() {
		return onsenAddress;
	}

	/*
	 * 温泉の質を取得
	 * @return 質
	 */
	public String getNature() {
		return natureOfOnsen;
	}
	/*
	 * 温泉のURLを取得
	 * @return URL
	 */
	public String getAreaURL() {
		return onsenAreaNameOnsenAreaURL;
	}

	/*
	 * 温泉の説明を取得
	 * @return 説明
	 */
	public String getCaption() {
		return onsenAreaCaption;
	}

	/*
	 * このオブジェクトの文字列表現を規定
	 * @return 都道府県名
	 */
	public String toString() {
		return onsenName;
	}
}