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

import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

/**
 * Data that is client-specific, and should be common for all
 * tracking requests.  For convenience most of this data is
 * populated automatically by {@link #populateFromSystem()}.
 * @author Daniel Murphy
 *
 */
public class AnalyticsConfigData {

	private final String trackingCode;
	private String encoding = "UTF-8";
	private String screenResolution = null;
	private String colorDepth = null;
	private String userLanguage = null;
	private String flashVersion = null;
	
	public AnalyticsConfigData(String argTrackingCode){
		if(argTrackingCode == null){
			throw new RuntimeException("Tracking code cannot be null");
		}
		trackingCode = argTrackingCode;
		populateFromSystem();
	}
	
	/**
	 * Populates user language, color depth, screen resolution, 
	 * and character encoding.  Can't get flash version.
	 */
	public void populateFromSystem(){
		encoding = System.getProperty("file.encoding");
		userLanguage = System.getProperty("user.language")+"-"+System.getProperty("user.region");
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();

		int screenHeight = 0;
		int screenWidth = 0;
		// Get size of each screen
		for (int i=0; i<gs.length; i++) {
		    DisplayMode dm = gs[i].getDisplayMode();
		    screenWidth += dm.getWidth();
		    screenHeight += dm.getHeight();
		}
		if(screenHeight != 0 && screenWidth != 0){
			screenResolution = screenWidth+"x"+screenHeight;
		}
		
		if(gs[0] != null){
			colorDepth = gs[0].getDisplayMode().getBitDepth()+"";
			for(int i=1; i<gs.length; i++){
				colorDepth += ", "+gs[i].getDisplayMode().getBitDepth()+"";
			}
		}
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
