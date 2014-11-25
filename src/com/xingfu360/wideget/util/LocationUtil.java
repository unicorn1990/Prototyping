/**
 * 
 */
package com.xingfu360.wideget.util;

import java.io.IOException;

import android.graphics.Point;

/**位置解析工具类
 * <p>  “x1，y1，x2,y2”转换到startP，endP </p>
 * @author unicorn
 * @version 2014年10月20日 下午3:11:29
 */
public class LocationUtil{
	Point startP=new Point();
	Point endP=new Point();
	
	public LocationUtil()
	{
		super();
	}
	
	public LocationUtil(String s) throws IllegalArgumentException
	{
		super();
		convert(s);
	}
	
	public void setLocation(String s) throws IllegalArgumentException
	{
		convert(s);
	}
	
	
	public Point getStartPoint()
	{
		return startP;
	}
	
	/**
	 * @param startP the startP to set
	 */
	public void setStartP(Point startP) {
		this.startP.x = startP.x;
		this.startP.y = startP.y;
	}
	
	/**
	 * @param endP the endP to set
	 */
	public void setEndP(Point endP) {
		this.endP.x = endP.x;
		this.endP.y = endP.y;
	}
	
	
	public Point getEndPoint()
	{
		return endP;
	}
	
	/**
	 * “x1，y1，x2,y2”装换到startP，endP
	 * @param s “x1，y1，x2,y2”
	 */
	private void convert(String s) throws IllegalArgumentException
	{
		s=s.replaceAll("，", ",");
		
		String[] strings=s.split(",");
		if(strings.length!=4)
			throw new IllegalArgumentException("location数值有误！~");
		
		startP.set(Integer.valueOf(strings[0].trim()), Integer.valueOf(strings[1].trim()));
		endP.set(Integer.valueOf(strings[2].trim()), Integer.valueOf(strings[3].trim()));
	}
}
