/**
 * Copyright (c) 2010 Daniel Murphy
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
/**
 * Created at Jul 20, 2010, 4:04:22 AM
 */
package com.dmurph.tracking;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Daniel Murphy
 *
 */
public class JGoogleAnalyticsTracker {
	private static JGoogleAnalyticsTracker tracker = null;
	public static boolean DEBUG_PRINT = false;
	
	public static enum GoogleAnalyticsVersion{
		V_4_7_2
	};
	
	private GoogleAnalyticsVersion gaVersion = null;
	private GoogleAnalyticsURLBuilder builder = null;
	private AnalyticsConfigData configData = null;

	private JGoogleAnalyticsTracker(){
		gaVersion = GoogleAnalyticsVersion.V_4_7_2;
	}
	
	public void initialize(AnalyticsConfigData argConfigData, GoogleAnalyticsVersion argVersion){
		gaVersion = argVersion;
		configData = argConfigData;
		createBuilder();
	}
	
	/* page view url:
	 * http://www.google-analytics.com/__utm.gif
	 * ?utmwv=4.7.2
	 * &utmn=631966530
	 * &utmhn=www.dmurph.com
	 * &utmcs=ISO-8859-1
	 * &utmsr=1280x800
	 * &utmsc=24-bit
	 * &utmul=en-us
	 * &utmje=1
	 * &utmfl=10.1%20r53
	 * &utmdt=Hello
	 * &utmhid=2043994175
	 * &utmr=0
	 * &utmp=%2Ftest%2Ftest.php
	 * &utmac=UA-17109202-5
	 * &utmcc=__utma%3D143101472.2118079581.1279863622.1279863622.1279863622.1%3B%2B__utmz%3D143101472.1279863622.1.1.utmcsr%3D(direct)%7Cutmccn%3D(direct)%7Cutmcmd%3D(none)%3B&gaq=1
	 */
	public void trackPageView(String argHostName, String argPageTitle, String argPageURL){
		trackPageView(argHostName, argPageTitle, argPageURL, "http://www.dmurph.com");
	}
	
	public void trackPageView(String argHostName, String argPageTitle, String argPageURL, String argReferrerURL){
		if(builder == null){
			throw new RuntimeException("Class was not initialized");
		}
		AnalyticsRequestData data = new AnalyticsRequestData();
		data.setHostName(argHostName);
		data.setPageTitle(argPageTitle);
		data.setPageURL(argPageURL);
		data.setReferrer(argReferrerURL);
		
		makeCustomRequest(data);
	}
	
	public void trackEvent(String argCategory, String argAction){
		trackEvent(argCategory, argAction, null, null);
	}
	
	public void trackEvent(String argCategory, String argAction, String argLabel){
		trackEvent(argCategory, argAction, argLabel, null);
	}
	
	public void trackEvent(String argCategory, String argAction, String argLabel, String argValue){
		if(builder == null){
			throw new RuntimeException("Class was not initialized");
		}
		AnalyticsRequestData data = new AnalyticsRequestData();
		data.setEventCategory(argCategory);
		data.setEventAction(argAction);
		data.setEventLabel(argLabel);
		data.setEventValue(argValue);
		
		makeCustomRequest(data);
	}
	
	public void makeCustomRequest(AnalyticsRequestData argData){
		if(argData == null){
			throw new NullPointerException("Data cannot be null");
		}
		String url = builder.buildURL(argData);
		makeRequest(url);
	}
	
	private synchronized void makeRequest(String argURL){
		
		try {
			URL url = new URL(argURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setInstanceFollowRedirects(true);
			connection.setRequestMethod("GET");
			connection.connect();
			int responseCode = connection.getResponseCode();
			if (responseCode != HttpURLConnection.HTTP_OK) {
				System.err.println("JGoogleAnalytics: Error requesting url '" + argURL+"', received response code "+responseCode);
			} else {
				if(DEBUG_PRINT){
					System.out.println("JGoogleAnalytics: Tracking success for url '"+argURL+"'");
				}
			}
		} catch (Exception e) {
			System.err.println("Error making tracking request");
			e.printStackTrace();
		}
	}
	
	private void createBuilder(){
		switch (gaVersion) {
			case V_4_7_2:
				builder = new GoogleAnalyticsV4_7_2(configData);
				break;
		}
	}
	
	public synchronized static JGoogleAnalyticsTracker getInstance(){
		if(tracker == null){
			tracker = new JGoogleAnalyticsTracker();
		}
		return tracker;
	}
}
