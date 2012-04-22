package com.android.salesapp.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.android.salesapp.db.DDL;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;



public class Utils {
	
	public static void systemUpgrade() {
		
		int level = Integer.parseInt(Pref.getValue("LEVEL", "0"));
		DDL.upgrade(level);
		level++;
		Pref.setValue("LEVEL", level + "");
	}
	
	public static boolean isOnline() {
	
		try {
		    ConnectivityManager cm = (ConnectivityManager) Const.CONTEXT
			    .getSystemService(Context.CONNECTIVITY_SERVICE);
	
		    if (cm != null) {
			return cm.getActiveNetworkInfo().isConnectedOrConnecting();
		    } else {
			return false;
		    }
		} catch (Exception e) {
		    return false;
		}
	}
	
	public static String getDeviceID(Context context){
		return ((TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
	}
	
	public static String doPost(String url, ArrayList<BasicNameValuePair> params) throws Exception{
	
		String strXML = null;
		HttpEntity httpentity = null;
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
	
		httppost.setEntity(new UrlEncodedFormEntity(params));
		HttpResponse response = httpclient.execute(httppost);
	
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
	
			httpentity = response.getEntity();
			strXML = new String(EntityUtils.toString(httpentity));
		}
	
		//release
		httpentity = null;
		response = null;
		httppost = null;
		httpclient = null;
		
		return strXML;
	}	
	

	public static String doGet(String url)throws Exception{
		URL myURL = null;
		URLConnection conn = null;
		String strXML=null;
		
		myURL = new URL(url.replaceAll(" ", "%20"));
		
		conn = myURL.openConnection();
		
		strXML = Utils.readStream(conn.getInputStream());
			
		myURL = null;
		conn = null;
		
		return strXML;
	}
	
	public static String readStream(InputStream is) throws IOException {
		int ch = 0;
		String str = new String();
	
		while ((ch = is.read()) != -1) {
			str += (char) ch;
		}
	
		is.close();
	
		return str;
	}	
	
	 public static int indexOfArray(String[] strArray, String strFind)
	{
		int index;
		
		for(index = 0; index < strArray.length; index++)
			if(strArray[index].equals(strFind))
				break;
		
		return index;
	}
	 
	/*	public static String doWS(String wsURL, String namespace, String operation, ArrayList<Param> params)throws Exception{		
			String response = null;
			SoapObject request;
			SoapSerializationEnvelope envelope;
			HttpTransportSE httpTransport;
			Param param;
			PropertyInfo pi;
			
			//For Testing
		//wsURL = "http://naveenbalani.com/WassupAndroid.asmx";
		//namespace = "http://www.naveenbalani.com/webservices/WassupAndroidService/";
		//operation = "todaysMessage";
		//params = null;
		
		//Create SOAP request
		request = new SoapObject(namespace, operation);
		
		//Add Parameters
		if (params != null){
			for (int ele=0; ele < params.size(); ele++){
				//get parameter
				param = params.get(ele);
				
				System.out.println("Param : " + param.name + " :: Value : " + param.value);
				
				pi = new PropertyInfo();
				pi.setName(param.name);
				pi.setValue(param.value);
				//pi.setType(param.type);
				
				//set to request
				request.addProperty(pi);
			}
		}
		
		envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		
		//Prepare web service call
		httpTransport = new HttpTransportSE(wsURL);
		httpTransport.call(namespace + operation, envelope);
		
		response = envelope.getResponse().toString();
		
		//Clean up
		request = null;
		envelope = null;
		httpTransport = null;
		
		//send response
			return response;
		}*/
	 
	public static String[] arrListToArray(ArrayList<Object> arrList,String strMName) {
		 
				String[] objStr = null;
				Method m = null;
				try {
				    if (arrList != null) {
					objStr = new String[arrList.size()];
					for (int index = 0; index < arrList.size(); index++) {
						Object obj = arrList.get(index);
						m = obj.getClass().getMethod(strMName);
						objStr[index] = (String) m.invoke(obj,new Object[0]);
					}
			    }
	
				} catch (Exception e) {
				    e.printStackTrace();
				}
	
				return objStr;
	
	    }
	public static Bitmap downloadThumbnail(String intClubId,String paraURL) throws IOException {
	
			Bitmap bitmap = null;
			URL url = null;
			HttpURLConnection conn = null;
			InputStream is = null;
	
			url = new URL(paraURL+intClubId);
			Log.print("Bitmap URL ::: ",url+"");
		conn = (HttpURLConnection) url.openConnection();
	
		if ((conn).getResponseCode() == HttpURLConnection.HTTP_OK) {
			is = conn.getInputStream();
			if (is != null) {
				Log.print("Utils :::",""+HttpURLConnection.HTTP_OK);
					bitmap = BitmapFactory.decodeStream(is);
					is.close();
					
				}
			}
			
			conn.disconnect();
			
			url = null;
			conn = null;
			is = null;
	
			return bitmap;
		}
	public static String getCharacterDataFromElement(Element e) {
		Node child = e.getFirstChild();
		if (child instanceof CharacterData) {
			CharacterData cd = (CharacterData) child;
			return cd.getData();
		}
		return "";
	}
	public static Long dateToMilisec(String strDate, String formate){
		
		/*
		 * Return @Param : LongMilisecond
		 * Pass   @Param : DateFormate : mm/dd/yyyy HH:mm 24 Hours
		 * 
		 */
		
		Date dte = new Date();
		try{
			dte = convertStringToDate(strDate, formate);
			System.out.println("MILISECOUND IS " + dte.getTime());
			return dte.getTime();
			
		}catch (Exception e) {
			e.printStackTrace();
			Log.print("DateToMilisecond",e.toString());
		}
		return dte!= null ? dte.getTime() : 0;
		 
	}
	public static String milisecToDate(String strMilisec,int intMode){
		
		/*
		 * IF MODE = 0 THEN DATE AS STRING 
		 * IF MODE = 1 THEN TIME AS STRING
		 * 
		 */
		if(intMode == 0)
		{
			Long lngMilisecond = Long.valueOf(strMilisec);
			SimpleDateFormat sdf = null;
			Date dte = new Date();
			try{
				
				sdf = new SimpleDateFormat("MM/dd/yyyy");
				dte = new Date(lngMilisecond);
				
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				System.out.println("CURRENT MILISECOND "+ strMilisec);
				System.out.println("CURRENT DATE       " + sdf.format(dte));
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				
				
				return sdf.format(dte); 
				
			}catch (Exception e) {
				e.printStackTrace();
				Log.print("Utils",e.toString());
				return "";
			}
		}
		
		if(intMode == 1){
			
			Long lngMilisecond = Long.valueOf(strMilisec);
			SimpleDateFormat sdf = null;
			Date dte = new Date();
			try{
				
				sdf = new SimpleDateFormat("HH:mm");
				dte = new Date(lngMilisecond);
				
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				System.out.println("CURRENT MILISECOND "+ strMilisec);
				System.out.println("CURRENT DATE       " + sdf.format(dte));
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				
				return sdf.format(dte); 
				
			}catch (Exception e) {
				e.printStackTrace();
				Log.print("Utils",e.toString());
				return "";
			}
		}
		return "";
		 
	}

	public static String convertDateToString(Date objDate, String parseFormat)
	{
		try {
			return new SimpleDateFormat(parseFormat).format(objDate);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static Date convertStringToDate(String strDate, String parseFormat)
	{
		try {
			return new SimpleDateFormat(parseFormat).parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String convertDateStringToString(String strDate, String currentFormat, String parseFormat)
	{
		try {
			return convertDateToString(convertStringToDate(strDate, currentFormat), parseFormat);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
