/**
 * 
 */
package com.xingfu360.wideget;

import java.io.File;
import java.math.BigInteger;

import com.xingfu360.constant.Constants;
import com.xingfu360.wideget.util.LocationUtil;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * @author unicorn
 * @version 2014年10月20日 下午5:32:06
 */
public class Dview extends View {
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the background
	 */
	public String getBackground_() {
		return background;
	}

	/**
	 * @param background the background to set
	 */
	public void setBackground_(String background) {
		this.background = background;
	}

	/**
	 * @return the demoName
	 */
	public String getDemoName() {
		return demoName;
	}

	/**
	 * @param demoName the demoName to set
	 */
	public void setDemoName(String demoName) {
		this.demoName = demoName;
	}

	String location="";//坐标 “x1，y1，x2,y2”
	String background="";//按钮背景
	String demoName="";//demo名
	
	/**
	 * @param context
	 */
	public Dview(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public Dview(Context context, AttributeSet attrs) {
		this(context, attrs,-1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public Dview(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
	}
	public void load()
	{
		
		loadPosition();
		loadRes();
	}
	
	/**
	 * 加载资源
	 */
	private void loadRes() {
		
		
		//是图片
				if(background.contains("jpg")||background.contains("png")||background.contains("jpeg"))
				{
					String path1=Constants.AppRootDir+File.separator+demoName+File.separator+background;
					BitmapDrawable norDrawable=new BitmapDrawable(this.getContext().getResources(), path1);
					this.setBackground(norDrawable);
				}//是颜色值
				else if(!TextUtils.isEmpty(background))
				{
					background=background.substring(2);
					this.setBackgroundColor(new BigInteger(background, 16).intValue());
				}
				else
				{
					
				}
	}

	/**
	 * 加载位置
	 */
	private void loadPosition() {
		LocationUtil locationUtil=new LocationUtil(location);
		Point startP = locationUtil.getStartPoint();
		Point endP = locationUtil.getEndPoint();
		
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(endP.x-startP.x, endP.y-startP.y);
		lp.setMargins(startP.x, startP.y, 0, 0);
		
		this.setLayoutParams(lp);
	}
}
