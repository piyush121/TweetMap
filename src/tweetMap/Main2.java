package tweetMap;

import java.util.List;

import twitter4j.FilterQuery;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author Piyush Chaudhary
 *
 */
public class Main2 {
	public static void main(String[] args) {

		//final Twitter twitter = new TwitterFactory().getInstance();
		
	//	twitter.setOAuthConsumer("wexQs0nZ1Mfq5rZAAiXFgVHnz", "2df8sukTaanmptybHxEa1Xi1KxzVxlgkPmjUpwcu6LPdgKH3nX");
		//twitter.setOAuthAccessToken(accessToken);
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
	    cb.setDebugEnabled(true).setOAuthConsumerKey("wexQs0nZ1Mfq5rZAAiXFgVHnz")
	            .setOAuthConsumerSecret("2df8sukTaanmptybHxEa1Xi1KxzVxlgkPmjUpwcu6LPdgKH3nX")
	            .setOAuthAccessToken("39964732-rBS2TnH2wQ52VuQZjodzyJwku49byY8WdWf3h9USA")
	            .setOAuthAccessTokenSecret("cfTLD9cEpKaypEJ6Xzt1BPZyRondv8lqtQtjlBFmCfRlb");

	    TwitterStream twitterStream = new TwitterStreamFactory(cb.build())
	            .getInstance();
		
		
	    StatusListener listener = new StatusListener(){

	        @Override
	        public void onStatus(Status status) {
	                //here you do whatever you want with the tweet
	        	if(status.getGeoLocation()!=null && status.getText().contains("#oscar"))
	        	{
	        		System.out.println(status.getText());
	        	    System.out.println(status.getUser().getLocation());
	        	    System.out.println(": "+status.getGeoLocation());

	        	}
	        }

	        @Override
	        public void onException(Exception ex) {
	            ex.printStackTrace();
	        }

	        @Override
	        public void onDeletionNotice(StatusDeletionNotice arg0) {
	                  // TODO Auto-generated method stub

	        }

	        @Override
	        public void onScrubGeo(long arg0, long arg1) {

	        }

	        @Override
	        public void onStallWarning(StallWarning arg0) {
	            // TODO Auto-generated method stub
	            System.out.println(arg0);
	        }

	        @Override
	        public void onTrackLimitationNotice(int arg0) {
	            // TODO Auto-generated method stub
	            System.out.println(arg0);
	        }

	    };

	    twitterStream.addListener(listener);
	    FilterQuery filterQuery = new FilterQuery("#oscar");
	    Query query = new Query("#oscar");
		query.geoCode(new GeoLocation(37.781157,-122.398720),3900.0,"mi");
	    double[][] locations = {{-122.75,36.8},{-121.75,37.8},{-74,40},{-73,41}}; //those are the boundary from New York City
	    filterQuery.locations(locations);
	    filterQuery.track("#oscar");
	    twitterStream.filter(filterQuery);

	}
	
	

}
