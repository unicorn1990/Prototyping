/**
 * 
 */
package com.xingfu360.wideget.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author unicorn
 * @version 2014年9月23日 下午3:56:31
 */
public class BitmapUtil {

	 /**
	    * 获取本地图片
	    * @param url 本地图片路径，如："/sdcard/tubiao.jpg"
	    * @return
	    */
	    public static Bitmap getLoacalBitmap(String url) {
	         try {
	              FileInputStream fis = new FileInputStream(url);
	              return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片        

	           } catch (FileNotFoundException e) {
	              e.printStackTrace();
	              return null;
	         }
	    }

	    /**
	     * 
	     * @param path 文件路径	
	     * @param width 宽度
	     * @param height 高度
	     * @return Bitmap
	     */
	    public static Bitmap getProperBitmap(String path,int width,int height)
	    {
	    	BitmapFactory.Options options = new BitmapFactory.Options();
	    	options.inJustDecodeBounds = true;
	    	
	    	BitmapFactory.decodeFile(path, options);
	    	
	    	int inSampleSize=calculateInSampleSize(options,width,height);
	    	options.inSampleSize=inSampleSize;
	    	
	    	options.inJustDecodeBounds = false;
	    	
	    	return BitmapFactory.decodeFile(path, options);
	    }
	    
	    
	    public static int calculateInSampleSize(
	            BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    // Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;

	    if (height > reqHeight || width > reqWidth) {

	        // Calculate ratios of height and width to requested height and width
	        final int heightRatio = Math.round((float) height / (float) reqHeight);
	        final int widthRatio = Math.round((float) width / (float) reqWidth);

	        // Choose the smallest ratio as inSampleSize value, this will guarantee
	        // a final image with both dimensions larger than or equal to the
	        // requested height and width.
	        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
	    }

	    return inSampleSize;
	}
}
