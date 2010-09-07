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
 * Created at Jul 20, 2010, 4:39:49 AM
 */
package com.dmurph.tracking;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

/**
 * http://code.google.com/apis/analytics/docs/tracking/gaTrackingTroubleshooting.html#gifParameters
 * @author Daniel Murphy
 *
 */
public class GoogleAnalyticsV4_7_2 implements GoogleAnalyticsURLBuilder{
	public static final String URL_PREFIX = "http://www.google-analytics.com/__utm.gif";

	private AnalyticsConfigData config;
	private Random random = new Random((long)(Math.random()*Long.MAX_VALUE));
	private int cookie1;
	private int cookie2;
	
	public GoogleAnalyticsV4_7_2(AnalyticsConfigData argConfig){
		config = argConfig;
		resetSession();
	}

	/**
	 * @see com.dmurph.tracking.GoogleAnalyticsURLBuilder#getGoogleAnalyticsVersion()
	 */
	public String getGoogleAnalyticsVersion() {
		return "4.7.2";
	}
	
	/**
	 * @see com.dmurph.tracking.GoogleAnalyticsURLBuilder#buildURL(com.dmurph.tracking.AnalyticsRequestData)
	 */
	public String buildURL(AnalyticsRequestData argData) {
		StringBuilder sb = new StringBuilder();
		sb.append(URL_PREFIX);
		
		long now = System.currentTimeMillis();
		 
		sb.append("?utmwv="+getGoogleAnalyticsVersion()); // version
	    sb.append("&utmn=" + random.nextInt()); // random int so no caching
	   
	    if(argData.getHostName() != null){
	    	String encoded;
	    	try { // we have to encode it for a url
				encoded = URLEncoder.encode(argData.getHostName(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
	    	sb.append("&utmhn=" + encoded); // hostname
	    }
	    
	    if(argData.getEventAction() != null && argData.getEventCategory() != null){
	    	sb.append("&utmt=event");
	    	sb.append("&utme=5("+argData.getEventCategory()+"*"+argData.getEventAction());
	    	if(argData.getEventLabel() != null){
	    		sb.append("*"+argData.getEventLabel());
	    	}
	    	sb.append(")");
	    	if(argData.getEventValue() != null){
	    		sb.append("("+argData.getEventValue()+")");
	    	}
	    }else if(argData.getEventAction() != null || argData.getEventCategory() != null){
	    	throw new IllegalArgumentException("Event tracking must have both a category and an action");
	    }
	    
	    if(config.getEncoding() != null){
	    	sb.append("&utmcs="+ config.getEncoding()); // encoding
	    }else{
	    	sb.append("&utmcs=-");
	    }
	    if(config.getScreenResolution() != null){
	    	sb.append("&utmsr=" + config.getScreenResolution()); // screen resolution
	    }
	    if(config.getColorDepth() != null){
	    	sb.append("&utmsc=" + config.getColorDepth()); // color depth
	    }
	    if(config.getUserLanguage() != null){
	    	sb.append("&utmul="+ config.getUserLanguage()); // language
	    }
	    sb.append("&utmje=1"); // java enabled (probably)
	    
	    if(config.getFlashVersion() != null){
	    	String encoded;
	    	
	    	try { // we have to encode it for a url
				encoded = URLEncoder.encode(config.getFlashVersion(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
	    	sb.append("&utmfl="+encoded); // flash version
	    }
	    
	    if(argData.getPageTitle() != null){
	    	String encoded;
	    	
	    	try { // we have to encode it for a url
				encoded = URLEncoder.encode(argData.getPageTitle(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
	    	sb.append("&utmdt=" + encoded); // page title
	    }
	    
	    sb.append("&utmhid="+random.nextInt());
	    
	    if(argData.getPageURL() != null){
	    	String encoded = null;
	    	
	    	try { // we have to encode it for a url
				encoded = URLEncoder.encode(argData.getPageURL(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
	    	
	    	sb.append("&utmp=" + encoded); // page url
	    }
	    
	    sb.append("&utmac=" + config.getTrackingCode()); // tracking code
	    
	    // cookie data
	    // utmccn=(organic)|utmcsr=google|utmctr=snotwuh |utmcmd=organic
	    String utmcsr;
		String utmccn;
		String utmctr = null;
		String utmcmd;
		String utmcct = null;
		
		try{
			utmcsr = URLEncoder.encode(argData.getUtmcsr(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		try{
			utmccn = URLEncoder.encode(argData.getUtmccn(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		if(argData.getUtmctr() != null){
			try{
				utmctr = URLEncoder.encode(argData.getUtmctr(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		try{
			utmcmd = URLEncoder.encode(argData.getUtmcmd(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		if(argData.getUtmcct() != null){
			try{
				utmcct = URLEncoder.encode(argData.getUtmcct(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}			
		}
	    
	    sb.append("&utmcc=__utma%3D"+cookie1+"."+cookie2+"."+now+"."+now+"."+now+"."+"13%3B%2B__utmz%3D"+cookie1+"."+now+".1.1.utmcsr%3D"+utmcsr+"%7Cutmccn%3D"+utmccn+"%7utmcmd%3D"+utmcmd+(utmctr != null?"%7Cutmctr%3D"+utmctr:"")+(utmcct != null?"%7Cutmcct%3D"+utmcct:"")+"%3B&gaq=1");
	    return sb.toString();
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
	
	// tracking url:
	/* http://www.google-analytics.com/__utm.gif
	 * ?utmwv=4.7.2
	 * &utmn=480124034
	 * &utmhn=www.dmurph.com
	 * &utmt=event
	 * &utme=5(Videos*Play)
	 * &utmcs=ISO-8859-1
	 * &utmsr=1280x800
	 * &utmsc=24-bit
	 * &utmul=en-us
	 * &utmje=1
	 * &utmfl=10.1%20r53
	 * &utmdt=Hello
	 * &utmhid=166062212
	 * &utmr=0
	 * &utmp=%2Ftest%2Ftest.php
	 * &utmac=UA-17109202-5
	 * &utmcc=__utma%3D143101472.2118079581.1279863622.1279863622.1279863622.1%3B%2B__utmz%3D143101472.1279863622.1.1.utmcsr%3D(direct)%7Cutmccn%3D(direct)%7Cutmcmd%3D(none)%3B&gaq=1
	 */

	/**
	 * @see com.dmurph.tracking.GoogleAnalyticsURLBuilder#resetSession()
	 */
	public void resetSession() {
		cookie1 = random.nextInt();
		cookie2 = random.nextInt();
	}
}
