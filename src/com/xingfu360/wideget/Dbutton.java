/**
 * 
 */
package com.xingfu360.wideget;

import java.io.File;
import java.math.BigInteger;

import com.xingfu360.constant.Constants;
import com.xingfu360.prototyping.R;
import com.xingfu360.prototyping.R.drawable;
import com.xingfu360.wideget.util.LocationUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * @author unicorn
 * @version 2014年10月20日 上午11:57:52
 */
public class Dbutton extends Button {
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
	 * @return the finish
	 */
	public boolean isFinish() {
		return finish;
	}

	/**
	 * @param finish the finish to set
	 */
	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	/**
	 * @return the background
	 */
	public String getBackground_() {
		return background_;
	}

	/**
	 * @param background the background to set
	 */
	public void setBackground_(String background) {
		this.background_ = background;
	}

	/**
	 * @return the background_press
	 */
	public String getBackground_press() {
		return background_press;
	}

	/**
	 * @param background_press the background_press to set
	 */
	public void setBackground_press(String background_press) {
		this.background_press = background_press;
	}

	/**
	 * @return the jump
	 */
	public String getJump() {
		return jump;
	}

	/**
	 * @param jump the jump to set
	 */
	public void setJump(String jump) {
		this.jump = jump.trim();
	}

	//Context context;
	String demoName="";//demo名
	
	String location="";//坐标 “x1，y1，x2,y2”
	boolean finish=false;//点击后是否结束当前activity
	String background_="";//按钮背景
	String background_press="";//按钮点击背景
	String jump="";//跳转页面
	
	BitmapDrawable norDrawable;
	BitmapDrawable pressDrawable;
	
	/**
	 * @param context
	 */
	public Dbutton(Context context) {
		this(context,null);
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public Dbutton(Context context, AttributeSet attrs) {
		this(context, attrs,-1);
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public Dbutton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
	}

	public void load()
	{
		
		
		loadPosition();
		
		
		loadBackground();
	}
	
	/**
	 * 初始化位置
	 */
	private void loadPosition() {
		LocationUtil locationUtil=new LocationUtil(location);
		Point startP = locationUtil.getStartPoint();
		Point endP = locationUtil.getEndPoint();
		
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(endP.x-startP.x, endP.y-startP.y);
		lp.setMargins(startP.x, startP.y, 0, 0);
		
		Dbutton.this.setGravity(Gravity.CENTER);
		Dbutton.this.setLayoutParams(lp);
	}

	/**
	 * 初始化背景
	 */
	private void loadBackground()
	{
		//是图片
		if(background_.contains("jpg")||background_.contains("png")||background_.contains("jpeg"))
		{
			String path1=Constants.AppRootDir+File.separator+demoName+File.separator+background_;
			norDrawable=new BitmapDrawable(Dbutton.this.getContext().getResources(), path1);
			Dbutton.this.setBackground(norDrawable);
		}//是颜色值
		else
		{
			background_=background_.substring(2);
			Dbutton.this.setBackgroundColor(Integer.valueOf(background_));
		}
		
		//是图片
		if(background_press.contains("jpg")||background_press.contains("png")||background_press.contains("jpeg"))
		{
			String path2=Constants.AppRootDir+File.separator+demoName+File.separator+background_press;
			pressDrawable=new BitmapDrawable(Dbutton.this.getContext().getResources(), path2);
		}//是颜色值
		else
		{
			background_press=background_press.substring(2);
			
		}
	}
	
	/* (non-Javadoc)
	 * @see android.widget.TextView#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			//是图片
			if(background_press.contains("jpg")||background_press.contains("png")||background_press.contains("jpeg"))
			{
				Dbutton.this.setBackground(pressDrawable);
			}//是颜色值
			else if(!TextUtils.isEmpty(background_press))
			{
				
				Dbutton.this.setBackgroundColor(new BigInteger(background_press, 16).intValue());
				
			}
			else
			{}
			break;

		case MotionEvent.ACTION_UP:
			//是图片
			if(background_.contains("jpg")||background_.contains("png")||background_.contains("jpeg"))
			{
				Dbutton.this.setBackground(norDrawable);
			}//是颜色值
			else if(!TextUtils.isEmpty(background_))
			{
				Dbutton.this.setBackgroundColor(new BigInteger(background_, 16).intValue());
			}
			else
			{
				
			}
			break;
		default:
			break;
		}
		
		return super.onTouchEvent(event);
	}
}
