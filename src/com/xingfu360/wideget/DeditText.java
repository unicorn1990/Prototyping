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
import android.widget.EditText;
import android.widget.RelativeLayout;

/**
 * @author unicorn
 * @version 2014年10月20日 下午5:29:10
 */
public class DeditText extends EditText {
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
		return background;
	}

	/**
	 * @param background the background to set
	 */
	public void setBackground_(String background) {
		this.background = background;
	}

	/**
	 * @return the textColor
	 */
	public int getTextColor_() {
		return textColor;
	}

	/**
	 * @param textColor the textColor to set
	 */
	public void setTextColor_(int textColor) {
		this.textColor = textColor;
	}

	/**
	 * @return the textSize
	 */
	public int getTextSize_() {
		return textSize;
	}

	/**
	 * @param textSize the textSize to set
	 */
	public void setTextSize_(int textSize) {
		this.textSize = textSize;
	}

	/**
	 * @return the hint
	 */
	public String getHint_() {
		return hint;
	}

	/**
	 * @param hint the hint to set
	 */
	public void setHint_(String hint) {
		this.hint = hint;
	}

	String location="";//坐标 “x1，y1，x2,y2”
	boolean finish=false;//点击后是否结束当前activity
	String background="";//按钮背景
	int textColor;//颜色
	int textSize;//字体大小
	String hint;//文字提示内容
	

	String demoName="";//demo名
	
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
	 * @param context
	 */
	public DeditText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public DeditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public DeditText(Context context, AttributeSet attrs, int defStyle) {
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
		this.setHint(hint);
		this.setTextColor(textColor);
		this.setTextSize(textSize);
		
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
		
		this.setGravity(Gravity.CENTER|Gravity.LEFT);
		this.setLayoutParams(lp);
	}
}
