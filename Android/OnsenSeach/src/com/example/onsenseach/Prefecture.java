package com.example.onsenseach;

public class Prefecture {

	private String prefectureName;

	private String prefectureCode;

	public Prefecture(String code, String name) {
		super();
		this.prefectureCode = code;
		this.prefectureName = name;
	}

	/*
	 * 都道府県の名称を取得
	 * @return 都道府県名
	 */
	public String getPrefectureName() {
		return prefectureName;
	}

	/*
	 * 都道府県のコードを取得
	 * @return 都道府県コード
	 */
	public String getPrefectureCode() {
		return prefectureCode;
	}
	/*
	 * このオブジェクトの文字列表現を規定
	 * @return 都道府県名
	 */
	public String toString() {
		return prefectureName;
	}
}
