package com.xingfu360.wideget.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class XmlHelper {

	/**
	 * XML解析
	 * 
	 * @param xmlString
	 *            xml字符串
	 * @param keyStringList
	 *            要解析的字段
	 * 
	 * @return 返回解析map<字段，字段值>
	 * */
	public static Map<String, String> pullParse(String xmlString,
			List<String> keyStringList) throws XmlPullParserException,
			IOException {
		Map<String, String> map = new HashMap<String, String>();

		XmlPullParser parser = Xml.newPullParser();

		parser.setInput(new StringReader(xmlString));

		int event = parser.getEventType();// 产生第一个事件
		while (event != XmlPullParser.END_DOCUMENT) {
			switch (event) {
			case XmlPullParser.START_DOCUMENT:// 判断当前事件是否是文档开始事件
				break;
			case XmlPullParser.START_TAG:// 判断当前事件是否是标签元素开始事件

				for (String key : keyStringList) {
					if (parser.getName().equals(key)) {
						String name = parser.getName();
						String value = parser.nextText();
						map.put(name, value);
					}
					
				}
				break;
			case XmlPullParser.END_TAG:// 判断当前事件是否是标签元素结束事件
				// System.out.println("XmlPullParser.END_TAG");
				break;
			}
			// if(parser!=null)
			event = parser.next();// 进入下一个元素并触发相应事件

		}// end while

		return map;
	}

	/**
	 * XML解析
	 * 
	 * @param is
	 *            InputStream字符串
	 * @param keyStringList
	 *            要解析的字段
	 * 
	 * @return 返回解析map<字段，字段值>
	 * */
	public static Map<String, String> pullParse(InputStream is,
			List<String> keyStringList) throws XmlPullParserException,
			IOException {
		Map<String, String> map = new HashMap<String, String>();

		XmlPullParser parser = Xml.newPullParser();

		// parser.setInput(new StringReader(xmlString));
		parser.setInput(is, "UTF-8");

		int event = parser.getEventType();// 产生第一个事件
		while (event != XmlPullParser.END_DOCUMENT) {
			String name=parser.getName();
			switch (event) {
			case XmlPullParser.START_DOCUMENT:// 判断当前事件是否是文档开始事件

				break;
			case XmlPullParser.START_TAG:// 判断当前事件是否是标签元素开始事件

				for (String key : keyStringList) {
					if (name.equals(key)) {
						//String name = parser.getName();
						String value = parser.nextText();
						
						map.put(name, value.trim());
					}
				}

				break;
			case XmlPullParser.END_TAG:// 判断当前事件是否是标签元素结束事件
				// System.out.println("XmlPullParser.END_TAG");
				break;
			}
			// if(parser!=null)
			event = parser.next();// 进入下一个元素并触发相应事件

		}// end while

		return map;
	}

}
