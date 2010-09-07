/**
 * Created at Jul 20, 2010, 4:04:22 AM
 */
package com.dmurph.tracking;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Daniel Murphy
 *
 */
public class JGoogleAnalyticsTracker {
	private static JGoogleAnalyticsTracker tracker = null;
	
	public static enum GoogleAnalyticsVersion{
		V_4
	};
	
	private GoogleAnalyticsVersion gaVersion;
	private GoogleAnalyticsURLBuilder builder;
	private AnalyticsRequestData reqData;
	
	private JGoogleAnalyticsTracker(){
		gaVersion = GoogleAnalyticsVersion.V_4;
	}
	
	public void initialize(String argTrackingCode, String argAppName, GoogleAnalyticsVersion argVersion){
		gaVersion = argVersion;
		createBuilder();
		reqData = defaultData();
	}
	
	private AnalyticsRequestData defaultData(){
		AnalyticsRequestData data = new AnalyticsRequestData();
		data.setEncoding("UTF-8");
		data.setUserLanguage("en-us");
		return data;
	}
	
	private void createBuilder(){
		switch (gaVersion) {
			case V_4:
				builder = new GoogleAnalyticsV4();
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
