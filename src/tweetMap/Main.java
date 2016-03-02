package tweetMap;

import java.util.List;

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
public class Main {
	public static void main(String[] args) {

		final Twitter twitter = new TwitterFactory().getInstance();
		final AccessToken accessToken = new AccessToken("39964732-rBS2TnH2wQ52VuQZjodzyJwku49byY8WdWf3h9USA",
				"cfTLD9cEpKaypEJ6Xzt1BPZyRondv8lqtQtjlBFmCfRlb");
		twitter.setOAuthConsumer("wexQs0nZ1Mfq5rZAAiXFgVHnz", "2df8sukTaanmptybHxEa1Xi1KxzVxlgkPmjUpwcu6LPdgKH3nX");
		twitter.setOAuthAccessToken(accessToken);

		try {
			Query query = new Query("#oscar");
			 query.geoCode(new GeoLocation(37.781157,-122.398720),3900.0,"mi");
			 
			QueryResult result;
			System.out.println("Searching...");
			int count = 0;

			do {
				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					{  if(tweet.getGeoLocation()!=null)
						System.out.println(tweet.getText()+" "+ tweet.getGeoLocation()+"\n");
					
						count++;
					}
				}
				
			} while ((query = result.nextQuery()) != null);
			System.out.println(count);
			System.out.println(result.getRateLimitStatus());

		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
			System.exit(-1);
		}
		
	}
	

}
