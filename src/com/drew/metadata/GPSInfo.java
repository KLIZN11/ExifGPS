package com.drew.metadata;

import java.io.File;
import java.io.IOException;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;

public class GPSInfo {
	/**
	    * 图片信息获取metadata元数据信息
	    * @param fileName 需要解析的文件
	    * @return
	    */
	    public ImageInfoBean parseImgInfo (String fileName)
	    {
	        File file = new File(fileName);
	        ImageInfoBean imgInfoBean = null;
	        try {
	            Metadata metadata = ImageMetadataReader.readMetadata(file);
	            imgInfoBean = printImageTags(file, metadata);
	        } catch (ImageProcessingException e) {
	            System.err.println("error 1a: " + e);
	        } catch (IOException e) {
	            System.err.println("error 1b: " + e);
	        }
	        return imgInfoBean;
	    }
	 
	    /**
	     * 读取metadata里面的信息
	     * @param sourceFile 源文件
	     * @param metadata metadata元数据信息
	     * @return
	     */
	    private ImageInfoBean printImageTags(File sourceFile, Metadata metadata)
	    {
	        ImageInfoBean imgInfoBean = new ImageInfoBean ();
	        imgInfoBean.setImgName(sourceFile.getName());
	        for (Directory directory : metadata.getDirectories()) {
	            for (Tag tag : directory.getTags()) {
	            	String tagName = tag.getTagName();
	            	String desc = tag.getDescription();
					if (tagName.equals("GPS Latitude")) {
						//纬度
						imgInfoBean.setLatitude(pointToLatlong(desc));
					} else if (tagName.equals("GPS Longitude")) {
						//经度
						imgInfoBean.setLongitude(pointToLatlong(desc));
					}
	            }
	            for (String error : directory.getErrors()){
	            	System.err.println("ERROR: " + error);
	            }
	        }
	        return imgInfoBean;
	    }
	 
	    /**
	     * 经纬度转换  度分秒转换
	     * @param point 坐标点
	     * @return
	     */
	    public String pointToLatlong (String point ) {
	    	Double du = Double.parseDouble(point.substring(0, point.indexOf("°")).trim());
	    	Double fen = Double.parseDouble(point.substring(point.indexOf("°")+1, point.indexOf("'")).trim());
	    	Double miao = Double.parseDouble(point.substring(point.indexOf("'")+1, point.indexOf("\"")).trim());
	    	Double duStr = du + fen / 60 + miao / 60 / 60 ;
	    	return duStr.toString();
	    }
	   
	    public static void main(String[] args)
	    {
	        ImageInfoBean imgInfoBean = new GPSInfo().parseImgInfo("C:\\Users\\Lenovo\\Desktop\\12345.jpg");
	        System.out.println(imgInfoBean.toString());
	    }


}
