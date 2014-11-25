/**
 * 
 */
package com.xingfu360.wideget.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.xingfu360.wideget.Dbutton;
import com.xingfu360.wideget.DeditText;
import com.xingfu360.wideget.DtextView;
import com.xingfu360.wideget.Dview;
import com.xingfu360.wideget.DviewPager;

import android.content.Context;
import android.text.TextUtils;
import android.util.Xml;
import android.view.View;
import android.widget.Button;

/**
 * @author unicorn
 * @version 2014年10月21日 上午11:44:28
 */
public class WidgetFactory {
	final private static WidgetFactory instance = new WidgetFactory();

	private WidgetFactory()
	{
		super();
	}
	
	public static WidgetFactory getInstance(){
		return instance;
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

	String demoName;
	
	boolean continue_state=true;
	
	@SuppressWarnings("unchecked")
	public <T> List<T> generaWidget(Context context,InputStream xmlInputStream,Class<T> t) throws XmlPullParserException, IOException
	{

		XmlPullParser parser = Xml.newPullParser();
		 List<T> list=null;
		 
		parser.setInput(xmlInputStream, "UTF-8");
		
		//开始
        int eventType=parser.getEventType();
		
		if (t == Dbutton.class ) {
			Dbutton dbutton = null;
			boolean start=false;
			 while(eventType!=XmlPullParser.END_DOCUMENT&&continue_state){
                 String nodeName=parser.getName();
                 switch (eventType) {
                 //文档开始
                 case XmlPullParser.START_DOCUMENT:
                    list=new ArrayList<T>();
                     break;
                 //开始节点
                 case XmlPullParser.START_TAG:
                	 if(BUTTONS.equals(nodeName))//开启
                		 start=true;
                	 
                	 if(!start)
                		 break;
                	 
                     //判断如果其实节点为student
                     if(BUTTON.equals(nodeName)){
                         //实例化student对象
                         dbutton=new Dbutton(context);
                         dbutton.setDemoName(demoName);
                         
                        
                         
                         for(int i=0;i<parser.getAttributeCount();i++)
                         {
                        	 String attrName=parser.getAttributeName(i);
                        	 if(attrName.equals(BACKGROUND))
                        		 dbutton.setBackground_(parser.getAttributeValue(i));
                        	 else if(attrName.equals(BACKGROUND_PRESS))
                        		 dbutton.setBackground_press(parser.getAttributeValue(i));
                        	 else if(attrName.equals(FINISH))
                        		 dbutton.setFinish(parser.getAttributeValue(i).equalsIgnoreCase("true")?true:false);
                        	 else if(attrName.equals(LOCATION))
                        		 dbutton.setLocation(parser.getAttributeValue(i));
                         }
                       
                         //String jump=parser.nextText();
                         //dbutton.setJump(jump);
                     }
                    
                     break;
                 case XmlPullParser.TEXT:
                	 if(!start)
                		 break;
                	String jump=parser.getText();
                	jump=jump.replaceAll("\n", "");
                	if(!TextUtils.isEmpty(jump.trim()))
                		dbutton.setJump(jump);
                	 break;
                 //结束节点
                 case XmlPullParser.END_TAG:
                	 if(!start)
                		 break;
                	 
                     if(BUTTON.equals(nodeName)){
                         list.add( (T) dbutton);
                         dbutton=null;
                     }
                     if(BUTTONS.equals(nodeName))//关闭
                    	 start=false;
                     break;
                 default:
                    
                     break;
                 }
                 eventType=parser.next();
             }
			
		}
		else if(t == DtextView.class)
		{
			DtextView dTextView = null;
			boolean start=false;
			 while(eventType!=XmlPullParser.END_DOCUMENT&&continue_state){
               String nodeName=parser.getName();
               switch (eventType) {
               //文档开始
               case XmlPullParser.START_DOCUMENT:
                  list=new ArrayList<T>();
                   break;
               //开始节点
               case XmlPullParser.START_TAG:
            	   if(TEXTVIEWS.equals(nodeName))//开启
              		 start=true;
              	 
              	 if(!start)
              		 break;
                   //判断如果其实节点为student
                   if(TEXTVIEW.equals(nodeName)){
                       //实例化student对象
                       dTextView=new DtextView(context);
                       dTextView.setDemoName(demoName);
                       for(int i=0;i<parser.getAttributeCount();i++)
                       {
                      	 String attrName=parser.getAttributeName(i);
                      	 if(attrName.equals(BACKGROUND))
                      		 dTextView.setBackground_(parser.getAttributeValue(i));
                      	 else if(attrName.equals(TEXT))
                      		 dTextView.setText_(parser.getAttributeValue(i));
                      	 else if(attrName.equals(TEXTSIZE))
                      		 dTextView.setTextSize_(Integer.valueOf(parser.getAttributeValue(i).trim()));
                      	 else if(attrName.equals(TEXTCOLOR))
                      		 dTextView.setTextColor_(new BigInteger(parser.getAttributeValue(i).trim().substring(2), 16).intValue());
                      	 else if(attrName.equals(FINISH))
                      		 dTextView.setFinish(parser.getAttributeValue(i).equalsIgnoreCase("true")?true:false);
                      	 else if(attrName.equals(LOCATION))
                      		 dTextView.setLocation(parser.getAttributeValue(i));
                       }
                      // dTextView.setJump(parser.getText());
                   }
                   break;
               case XmlPullParser.TEXT:
              	 if(!start)
              		 break;
              	String jump=parser.getText();
              	jump=jump.replaceAll("\n", "");
              	if(!TextUtils.isEmpty(jump.trim()))
              		dTextView.setJump(jump);
              	 break;
               //结束节点
               case XmlPullParser.END_TAG:
            	   if(!start)
              		 break;
            	   
                   if(TEXTVIEW.equals(nodeName)){
                       list.add( (T) dTextView);
                       dTextView=null;
                   }
                   
                   if(TEXTVIEWS.equals(nodeName))//关闭
                  	 start=false;
                   break;
               default:
                   break;
               }
               eventType=parser.next();
           }
			
		}
		else if(t == DeditText.class)
		{
			DeditText dEditText = null;
			 while(eventType!=XmlPullParser.END_DOCUMENT&&continue_state){
                String nodeName=parser.getName();
                switch (eventType) {
                //文档开始
                case XmlPullParser.START_DOCUMENT:
                   list=new ArrayList<T>();
                    break;
                //开始节点
                case XmlPullParser.START_TAG:
                    //判断如果其实节点为student
                    if(EDITTEXT.equals(nodeName)){
                        //实例化student对象
                        dEditText=new DeditText(context);
                        dEditText.setDemoName(demoName);
                        for(int i=0;i<parser.getAttributeCount();i++)
                        {
                       	 String attrName=parser.getAttributeName(i);
                       	 if(attrName.equals(BACKGROUND))
                       		 dEditText.setBackground_(parser.getAttributeValue(i));
                       	 else if(attrName.equals(HINT))
                       		 dEditText.setHint_(parser.getAttributeValue(i));
                       	 else if(attrName.equals(TEXTSIZE))
                       		 dEditText.setTextSize_(Integer.valueOf(parser.getAttributeValue(i).trim()));
                       	 else if(attrName.equals(TEXTCOLOR))
                       		 dEditText.setTextColor_(new BigInteger(parser.getAttributeValue(i).trim().substring(2), 16).intValue());
                       	 else if(attrName.equals(FINISH))
                       		 dEditText.setFinish(parser.getAttributeValue(i).equalsIgnoreCase("true")?true:false);
                       	 else if(attrName.equals(LOCATION))
                       		 dEditText.setLocation(parser.getAttributeValue(i));
                        }
                      
                    }
                    break;
                //结束节点
                case XmlPullParser.END_TAG:
                    if(EDITTEXT.equals(nodeName)){
                        list.add( (T) dEditText);
                        dEditText=null;
                    }
                    break;
                default:
                    break;
                }
                eventType=parser.next();
            }
			
		}
		else if(t == DviewPager.class)
		{

			DviewPager dviewPager = null;
			 while(eventType!=XmlPullParser.END_DOCUMENT&&continue_state){
                 String nodeName=parser.getName();
                 switch (eventType) {
                 //文档开始
                 case XmlPullParser.START_DOCUMENT:
                     break;
                 //开始节点
                 case XmlPullParser.START_TAG:
                	 if(VIEWPAGERS.equals(nodeName))
                         list=new ArrayList<T>();
                     //判断如果其实节点为student
                	 else  if(VIEWPAGER.equals(nodeName)){
                         //实例化student对象
                         dviewPager=new DviewPager(context);
                         dviewPager.setDemoName(demoName);
                         for(int i=0;i<parser.getAttributeCount();i++)
                         {
                        	 String attrName=parser.getAttributeName(i);
                        	  if(attrName.equals(LOCATION))
                        		 dviewPager.setLocation(parser.getAttributeValue(i));
                        	  if(attrName.equals(PAGE))
                         		 dviewPager.setRawPath(parser.getAttributeValue(i));
                         }
                     }
                    
                     break;
                 //结束节点
                 case XmlPullParser.END_TAG:
                     if(VIEWPAGER.equals(nodeName)){
                         list.add( (T) dviewPager);
                         dviewPager=null;
                     }
                     if(VIEWPAGERS.equals(nodeName))
                     {
                    	continue_state=false;
                     }
                     break;
                 default:
                     break;
                 }
                 eventType=parser.next();
             }
			
		
			
		}
		else if(t == Dview.class)
		{

			Dview dview = null;
			 while(eventType!=XmlPullParser.END_DOCUMENT&&continue_state){
                String nodeName=parser.getName();
                switch (eventType) {
                //文档开始
                case XmlPullParser.START_DOCUMENT:
                   list=new ArrayList<T>();
                    break;
                //开始节点
                case XmlPullParser.START_TAG:
                    //判断如果其实节点为student
                    if(VIEW.equals(nodeName)){
                        //实例化student对象
                        dview=new Dview(context);
                        dview.setDemoName(demoName);
                        for(int i=0;i<parser.getAttributeCount();i++)
                        {
                       	 String attrName=parser.getAttributeName(i);
                       	 if(attrName.equals(BACKGROUND))
                       		 dview.setBackground_(parser.getAttributeValue(i));
                        else if(attrName.equals(LOCATION))
                       		 dview.setLocation(parser.getAttributeValue(i));
                        }
                       
                    }
                    break;
                //结束节点
                case XmlPullParser.END_TAG:
                    if(VIEW.equals(nodeName)){
                        list.add( (T) dview);
                        dview=null;
                    }
                    break;
                default:
                    break;
                }
                eventType=parser.next();
            }
			
		
			
		}
		continue_state=true;
		xmlInputStream.close();
		return list;
	
	}
	
	
	public  <T> List<T> generaWidget(Context context,String xmlString,Class<T> t) throws XmlPullParserException, IOException
	{
		
		return generaWidget(context, new  ByteArrayInputStream(xmlString.getBytes()), t);
	}
	
	public void setLanguage(String language)
	{
		if(language.equalsIgnoreCase("中文")||language.equalsIgnoreCase("cn"))
		{   
			BACKGROUND="背景";
		   BACKGROUND_PRESS="点击背景";
		   FINISH="结束";
		   LOCATION="坐标";
		   TEXT="文本";
		   HINT="提示";
		   TEXTCOLOR="文本颜色";
		   TEXTSIZE="文本大小";
		
		   BUTTONS="按钮组";
		   BUTTON="按钮";
		   VIEWPAGERS="滑动页组";
		   VIEWPAGER="滑动页";
		   VIEW="视图";
		   TEXTVIEWS="文本框组";
		   TEXTVIEW="文本框";
		   EDITTEXTS="编辑框组";
		   EDITTEXT="编辑框";
		   VIEWS="视图组";
		   PAGE="页面";
		  }
		else
			if(language.equalsIgnoreCase("english")||language.equalsIgnoreCase("en"))
			{
				   BACKGROUND="background";
				   BACKGROUND_PRESS="background_press";
				   FINISH="finish";
				   LOCATION="location";
				   TEXT="text";
				   HINT="hint";
				   TEXTCOLOR="textcolor";
				   TEXTSIZE="textsize";
				
				
				   BUTTONS="buttons";
				   BUTTON="button";
				   VIEWPAGERS="viewpagers";
				   VIEWPAGER="viewpager";
				   VIEW="view";
				   TEXTVIEWS="textviews";
				   TEXTVIEW="textview";
				   EDITTEXTS="edittexts";
				   EDITTEXT="edittext";
				   VIEWS="views";
				   PAGE="page";
			}
		else
		{
			
		}
	}
	
	public static  String BACKGROUND="background";
	public static  String BACKGROUND_PRESS="background_press";
	public static  String FINISH="finish";
	public static  String LOCATION="location";
	public static  String TEXT="text";
	public static  String HINT="hint";
	public static  String TEXTCOLOR="textcolor";
	public static  String TEXTSIZE="textsize";
	
	
	public static  String BUTTONS="buttons";
	public static  String BUTTON="button";
	public static  String VIEWPAGERS="viewpagers";
	public static  String VIEWPAGER="viewpager";
	public static  String VIEW="view";
	public static  String TEXTVIEWS="textviews";
	public static  String TEXTVIEW="textview";
	public static  String EDITTEXTS="edittexts";
	public static  String EDITTEXT="edittext";
	public static  String VIEWS="views";
	public static  String PAGE="page";
	
	public static final String LANGUAGE="language";
}
