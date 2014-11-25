/**
 * 
 */
package com.xingfu360.prototyping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xingfu360.constant.Constants;
import com.xingfu360.wideget.util.BitmapUtil;
import com.xingfu360.wideget.util.WidgetFactory;
import com.xingfu360.wideget.util.XmlHelper;

/**
 * @author unicorn
 * @version 2014年10月23日 下午4:42:27
 */
class GridAdapter extends BaseAdapter implements OnItemClickListener{
	private Context context;

	WidgetFactory factory;
	public GridAdapter(Context context)
	{
		super();
		this.context=context;
	}
	
	
	String demoName;
	List<String> backgrounds;
	String[] xmls;
	public void setDemoName(String demoPath) {
		this.demoName = demoPath;

		xmls = new File(Constants.AppRootDir, demoName)
				.list(new FilenameFilter() {

					@Override
					public boolean accept(File dir, String filename) {
						if (filename.contains("xml"))
							return true;
						else
							return false;
					}
				});
		backgrounds = new ArrayList<String>();

		for (int i = 0; i < xmls.length; i++) {
			// 加载语言
			List<String> keyList = new ArrayList<String>();
			keyList.add(WidgetFactory.LANGUAGE);

			Map<String, String> map = null;

			try {
				map = XmlHelper.pullParse(new FileInputStream(new File(
						Constants.AppRootDir + File.separator
								+ demoName, xmls[i])), keyList);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String language = map.get(WidgetFactory.LANGUAGE);
			factory = WidgetFactory.getInstance();
			factory.setLanguage(language);

			// 加载出background
			keyList.clear();
			keyList.add(WidgetFactory.BACKGROUND);
			try {
				map = XmlHelper.pullParse(new FileInputStream(new File(
						Constants.AppRootDir + File.separator
								+ demoName, xmls[i])), keyList);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (XmlPullParserException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			String background = map.get(WidgetFactory.BACKGROUND);
			backgrounds.add(background);
		}

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return backgrounds.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return backgrounds.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout linearLayout = new LinearLayout(context);

		ImageView imageView = new ImageView(context);
		TextView textView = new TextView(context);

		linearLayout.addView(imageView);
		linearLayout.addView(textView);

		linearLayout.setGravity(Gravity.CENTER);
		linearLayout.setOrientation(LinearLayout.VERTICAL);

		LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) imageView
				.getLayoutParams();

		String background = backgrounds.get(position);
		textView.setText(xmls[position]);

		
		if(background.contains("jpg")||background.contains("png")||background.contains("jpeg"))
		{
			String path1=Constants.AppRootDir+File.separator+demoName+File.separator+background;
//			BitmapDrawable norDrawable=new BitmapDrawable(context.getResources(), path1);
			imageView.setImageBitmap(BitmapUtil.getProperBitmap(path1, 200, 300));
		}//是颜色值
		else if(!TextUtils.isEmpty(background))
		{	background=background.substring(2);
			imageView.setBackgroundColor(new BigInteger(background, 16).intValue());
		}
		else
		{
			
		}
		
		
		
		
		
		
		lp.width=270;
		lp.height=480;
		imageView.setLayoutParams(lp);

		linearLayout.setPadding(10, 10, 10, 10);
		return linearLayout;
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		WidgetFactory factory = WidgetFactory.getInstance();
		factory.setDemoName(demoName);

		Intent intent = new Intent(context,
				EmptyActivity.class);
		intent.putExtra(EmptyActivity.XML_FNAME, xmls[position]);
		context.startActivity(intent);
	}

}