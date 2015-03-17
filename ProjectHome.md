# Introduction #

`JGoogleAnalyticsTracker` is a lightweight Java library for tracking your Java application with [Google Analytics](http://www.google.com/analytics/).  It was made so I could use Google’s fantastic analytics software on java gui applications, as Google never made a java implementation for sending tracking data.

Currently the library supports page view tracking (which can have a referrer from a page or a search engine) and event tracking. See the usage section for a basic setup (It’s very simple).  `JGoogleAnalyticsTracker` will also gracefully ignore requests when there is no internet connection, and logs errors with [SLF4J](http://www.slf4j.org/).  Please feel free to post any feature requests or contact me about questions.

Current maintainers:
  * Rob Blake
  * Daniel Murphy

# Usage #

This utility is fairly straightforward. First, you make your config data.  `AnayticsConfigData` automatically populates most of the user information from the client’s system, the only thing it can’t figure out is the Flash version.

```
AnalyticsConfigData config = new AnalyticsConfigData("MyTrackingCode");
// if you want to set your own config parameters:
config.setFlashVesion("9.0 r24");
// etc
```

You set up the JGoogleAnalyticsTracker with the config and a version (right now only 4.7.2, but it’s there for future analytics versions):

```
JGoogleAnalyticsTracker tracker = new JGoogleAnalyticsTracker(config, GoogleAnalyticsVersion.V_4_7_2);
```
and then you can send tracking data with the `trackPageView` and `trackEvent` methods, or make your own request the `makeCustomRequest(AnalyticsRequestData)` method. You can track referrals or searches by calling the `trackPageViewFromReferrer` or `trackPageViewFromSearch` methods.

For complete documentation, see the [javadocs](http://www.dmurph.com/jgoogleanalyticstracker/index.html), or just view the source, it’s very straightforward. For more information on the tracking parameters take a look at Google’s [Troubleshooting Guide](http://code.google.com/apis/analytics/docs/tracking/gaTrackingTroubleshooting.html) (although I don't think it's fully updated...)

## Other Features ##

  * The tracker can be enabled/disabled by calling `setEnabled(boolean)`.
  * The cookie data can be reset by calling `resetSession()`.
  * Logging with [SLF4J](http://www.slf4j.org/)
  * Proxy support
  * The tracker can dispatch requests in three different modes:
    * Synchronous mode, where each tracking call waits till the http request finishes before returning
    * Multi-thread mode, where each call spawns a new thread to make the http request
    * Single-thread mode, where each call is added to a queue and a single background thread then makes the http requests from the queue.  This mode is default.
  * Deployed in Maven

## Thanks ##

Special thanks to Stefan for helping polish off the project, by adding the proxy support, dispatch modes, and logging.  Thanks! :D

## Comparison ##
So I recently discovered someone made a similar tool, [JGoogleAnalytics](http://code.google.com/p/jgoogleanalytics/).  The differences are (as of 1/11):
My library:
  * Sends event tracking
  * Correctly gives referrer information
  * Sends search tracking data\keyword data
  * Correctly encodes all info (It has to be encoded with URI encoding, not URL)
  * Collects basic info about the host machine
  * Built/deployed with Maven 2
My library doesnt:
  * ~~Have a logging adapter.  I think it's unneeded, but if you want it I can put it in there.~~

## Implementations ##
I made this library to use in my [Java MVC library](http://code.google.com/p/java-simple-mvc/), which I then use to develop java application like:
  * [TRiCYCLE](http://www.tridas.org/tricycle/) and
  * [Corina](http://dendro.cornell.edu/corina/index.php).

Other users:
**[Import List for Moneydance](http://my-flow.github.com/importlist/)**

If you use this library in your application, let me know, I'd love to add you to the list!


---

I maintain this project on my own time, so any [donations](http://www.dmurph.com/projects.php) would be appreciated :)