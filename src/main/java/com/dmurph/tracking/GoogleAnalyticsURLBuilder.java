/**
 * Created at Jul 20, 2010, 4:56:30 AM
 */
package com.dmurph.tracking;

/**
 * @author Daniel Murphy
 *
 */
public interface GoogleAnalyticsURLBuilder {
	
	public void setFirstRequest(boolean argIsFirstRequest);
	
	public int getGoogleAnalyticsVersion();
	
	public String buildURL(AnalyticsRequestData argData);
}
