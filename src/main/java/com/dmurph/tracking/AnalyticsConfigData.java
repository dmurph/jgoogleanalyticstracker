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
 * Created at Jul 22, 2010, 11:37:36 PM
 */
package com.dmurph.tracking;

/**
 * @author Daniel Murphy
 *
 */
public class AnalyticsConfigData {

	private final String trackingCode;
	private String encoding = "UTF-8";
	private String screenResolution = null;
	private String colorDepth = null;
	private String userLanguage = "en-us";
	private String flashVersion = null;
	
	public AnalyticsConfigData(String argTrackingCode){
		if(argTrackingCode == null){
			throw new RuntimeException("Tracking code cannot be null");
		}
		trackingCode = argTrackingCode;
	}
	/**
	 * @return the colorDepth
	 */
	public String getColorDepth() {
		return colorDepth;
	}
	/**
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}
	/**
	 * @return the flashVersion
	 */
	public String getFlashVersion() {
		return flashVersion;
	}
	/**
	 * @return the screenResolution
	 */
	public String getScreenResolution() {
		return screenResolution;
	}
	/**
	 * @return the trackingCode
	 */
	public String getTrackingCode() {
		return trackingCode;
	}
	/**
	 * @return the userLanguage
	 */
	public String getUserLanguage() {
		return userLanguage;
	}
	/**
	 * @param argColorDepth the colorDepth to set
	 */
	public void setColorDepth(String argColorDepth) {
		colorDepth = argColorDepth;
	}
	/**
	 * @param argEncoding the encoding to set
	 */
	public void setEncoding(String argEncoding) {
		encoding = argEncoding;
	}
	/**
	 * @param argFlashVersion the flashVersion to set
	 */
	public void setFlashVersion(String argFlashVersion) {
		flashVersion = argFlashVersion;
	}
	/**
	 * @param argScreenResolution the screenResolution to set
	 */
	public void setScreenResolution(String argScreenResolution) {
		screenResolution = argScreenResolution;
	}
	/**
	 * @param argUserLanguage the userLanguage to set
	 */
	public void setUserLanguage(String argUserLanguage) {
		userLanguage = argUserLanguage;
	}
}