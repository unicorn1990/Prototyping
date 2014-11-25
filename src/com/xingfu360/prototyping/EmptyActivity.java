/**
 * 
 */
package com.xingfu360.prototyping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;

import com.xingfu360.constant.Constants;
import com.xingfu360.wideget.Dbutton;
import com.xingfu360.wideget.DeditText;
import com.xingfu360.wideget.DtextView;
import com.xingfu360.wideget.Dview;
import com.xingfu360.wideget.DviewPager;
import com.xingfu360.wideget.util.WidgetFactory;
import com.xingfu360.wideget.util.XmlHelper;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * @author unicorn
 * @version 2014年10月22日 上午11:27:12
 */
public class EmptyActivity extends FragmentActivity {
	ViewGroup rootView;
	String xmlFName;
	WidgetFactory factory;

	String demoName;
	String xmlPath;

	public static String XML_FNAME = "xml_res";

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		rootView = (ViewGroup) LayoutInflater.from(this).inflate(
				R.layout.empty_activity, null);
		setContentView(rootView);

		load();

	}

	/**
	 * 加载
	 */
	private void load() {
		xmlFName = getIntent().getStringExtra(XML_FNAME);
		factory = WidgetFactory.getInstance();

		demoName = factory.getDemoName();
		xmlPath = Constants.AppRootDir + File.separator + demoName
				+ File.separator + xmlFName;

		loadLanguage();
		
		loadBackground();

		try {
			loadWidget();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载语言类型
	 */
	private void loadLanguage() {
		List<String> keyList = new ArrayList<String>();
		keyList.add(WidgetFactory.LANGUAGE);
		
		Map<String, String> map = null;

		try {
			map = XmlHelper.pullParse(new FileInputStream(new File(xmlPath)),
					keyList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String language = map.get(WidgetFactory.LANGUAGE);
		factory.setLanguage(language);
	}

	/**
	 * @throws IOException
	 * @throws XmlPullParserException
	 * 
	 */
	private void loadWidget() throws XmlPullParserException, IOException {

		factory.setDemoName("demo1");

		List<Dbutton> dbuttons = factory.generaWidget(this,
				new FileInputStream(new File(xmlPath)), Dbutton.class);
		List<DeditText> deditTexts = factory.generaWidget(this,
				new FileInputStream(new File(xmlPath)), DeditText.class);
		List<DtextView> dtextViews = factory.generaWidget(this,
				new FileInputStream(new File(xmlPath)), DtextView.class);
		List<Dview> dviews = factory.generaWidget(this, new FileInputStream(
				new File(xmlPath)), Dview.class);
		List<DviewPager> dviewPagers = factory.generaWidget(this,
				new FileInputStream(new File(xmlPath)), DviewPager.class);
		if (dviews != null)
			for (Dview dview : dviews) {
				rootView.addView(dview);
				dview.load();
			}
		if (dbuttons != null)
			for (final Dbutton dbutton : dbuttons) {

				rootView.addView(dbutton);
				dbutton.load();

				dbutton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if(!TextUtils.isEmpty(dbutton.getJump()))
						{
							Intent intent=new Intent(getApplicationContext(),EmptyActivity.class);
							intent.putExtra(EmptyActivity.XML_FNAME, dbutton.getJump());
							startActivity(intent);
						}
						
						if(dbutton.isFinish())
							finish();
					}
				});
			}
		if (deditTexts != null)
			for (DeditText deditText : deditTexts) {
				rootView.addView(deditText);
				deditText.load();
				
			}
		if (dtextViews != null)
			for (final DtextView dtextView : dtextViews) {
				rootView.addView(dtextView);
				dtextView.load();
				
				dtextView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if(!TextUtils.isEmpty(dtextView.getJump()))
						{
							Intent intent=new Intent(getApplicationContext(),EmptyActivity.class);
							intent.putExtra(EmptyActivity.XML_FNAME, dtextView.getJump());
							startActivity(intent);
						}
						if(dtextView.isFinish())
							finish();
					}
				});
			}

		if (dviewPagers != null)
			for (DviewPager dviewPager : dviewPagers) {
				rootView.addView(dviewPager);
				dviewPager.load();
			}

	}

	/**
	 * 加载背景与语言
	 */
	private void loadBackground() {

		List<String> keyList = new ArrayList<String>();
		keyList.add(WidgetFactory.BACKGROUND);
		
		Map<String, String> map = null;

		try {
			map = XmlHelper.pullParse(new FileInputStream(new File(xmlPath)),
					keyList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String background = map.get(WidgetFactory.BACKGROUND);
		background = background.trim();
		// 是图片
		if (background.contains("jpg") || background.contains("png")
				|| background.contains("jpeg")) {
			String path1 = Constants.AppRootDir + File.separator + demoName
					+ File.separator + background;
			BitmapDrawable norDrawable = new BitmapDrawable(getResources(),
					path1);
			rootView.setBackground(norDrawable);
		}// 是颜色值
		else if (!TextUtils.isEmpty(background)) {
			background = background.substring(2);
			rootView.setBackgroundColor(new BigInteger(background, 16)
					.intValue());
		} else {

		}
	}

}
