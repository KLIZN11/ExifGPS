package com.drew.metadata;

public class ImageInfoBean {
	private String latitude;//纬度
	private String longitude ;//经度
	private String imgName;  //文件名称
	
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String toString (){
		return "[图片GPS信息]文件名称："+ this.imgName+"   纬度："+this.latitude+"  经度："+this.longitude;
	}

}
