/**
 * 
 */
package com.xingfu360.wideget;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.xingfu360.constant.Constants;
import com.xingfu360.wideget.util.LocationUtil;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;



	
/**
 * @author unicorn
 * @version 2014年10月20日 下午4:39:33
 */
public class DviewPager extends ViewPager {
		//放BitmapDrawable资源
		List<BitmapDrawable> drawRes=new ArrayList<BitmapDrawable >();
		
		//demo名
		String demoName="";
		
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


		//放路径（纯文件名，不带路径的）
		List<String > viewPaths=new ArrayList<String >();
		
		String rawPath;
		
		/**
		 * @return the rawPath
		 */
		public String getRawPath() {
			return rawPath;
		}


		/**
		 * 设置viewpager的内容
		 * @param  for example(view1.jpg,view2.jpg,view3.jpg)
		 */
		public void setRawPath(String rawPath) {
			this.rawPath = rawPath;
		}


		String location;
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


		private void addViewPaths(String path)
		{
			viewPaths.add(path);
		}
		
		
	/**
	 * @param context
	 */
	public DviewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public DviewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void load()
	{	
		parseRawPath();
		
		loadPosition();
		loadres();
		
	}
	
	
	/**
	 * 解析rawPath 到viewPaths
	 */
	private void parseRawPath() {
		rawPath=rawPath.trim();
		rawPath.replaceAll("，",",");
		String[] splits=rawPath.split(",");
		for(int i=0;i<splits.length;i++)
		{
			addViewPaths(splits[i]);
		}
	}


	/**
	 * 
	 */
	private void loadPosition() {
		
		LocationUtil locationUtil=new LocationUtil(location);
		Point startP = locationUtil.getStartPoint();
		Point endP = locationUtil.getEndPoint();
		
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(endP.x-startP.x, endP.y-startP.y);
		lp.setMargins(startP.x, startP.y, 0, 0);
		this.setPageMargin(10);
		this.setLayoutParams(lp);
	}


	/**
	 * 加载Res资源
	 */
	private void loadres() {
		String fullpath="";
		BitmapDrawable bDrawable;
		for(String path:viewPaths)
		{
			 fullpath=Constants.AppRootDir+File.separator+demoName+File.separator+path;
			
			 bDrawable= new BitmapDrawable(this.getContext().getResources(), fullpath);
			drawRes.add(bDrawable);
		}
		
		this.setAdapter(new ViewPagerAdapter());
	}


	/**
	 * ViewPager 适配器
	 * 
	 * @author unicorn
	 * @version 2014年10月9日 下午8:21:06
	 */
	class ViewPagerAdapter extends PagerAdapter {

		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			ImageView imageView = new ImageView(container.getContext());
			imageView.setImageDrawable(drawRes.get(position));
			//imageView.setPadding(15, 0, 15, 0);
			container.addView(imageView, LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT);

			return imageView;
		}

		
		@Override
		public int getCount() {
			return drawRes.size();
		}

		
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			View view = (View) object;
			container.removeView(view);
		}
	}
	
}
