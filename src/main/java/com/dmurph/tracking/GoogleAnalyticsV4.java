/**
 * Created at Jul 20, 2010, 4:39:49 AM
 */
package com.dmurph.tracking;

import java.net.InetAddress;

/**
 * @author Daniel Murphy
 *
 */
public class GoogleAnalyticsV4 implements GoogleAnalyticsURLBuilder{

	/**
	 * @see com.dmurph.tracking.GoogleAnalyticsURLBuilder#setFirstRequest(boolean)
	 */
	public void setFirstRequest(boolean argIsFirstRequest) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see com.dmurph.tracking.GoogleAnalyticsURLBuilder#getGoogleAnalyticsVersion()
	 */
	public int getGoogleAnalyticsVersion() {
		return 1;
	}
	
	/**
	 * @see com.dmurph.tracking.GoogleAnalyticsURLBuilder#buildURL(com.dmurph.tracking.AnalyticsRequestData)
	 */
	public String buildURL(AnalyticsRequestData argData) {
		return null;
	}
}
