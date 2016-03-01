package tweetMap;
import java.util.List;

import twitter4j.*;
import twitter4j.auth.AccessToken;

public class Main {
	public static void main(String[] args) {
		
		final Twitter twitter = new TwitterFactory().getInstance();
	    final  AccessToken accessToken = new AccessToken("39964732-rBS2TnH2wQ52VuQZjodzyJwku49byY8WdWf3h9USA", "cfTLD9cEpKaypEJ6Xzt1BPZyRondv8lqtQtjlBFmCfRlb");
	    twitter.setOAuthConsumer("wexQs0nZ1Mfq5rZAAiXFgVHnz", "2df8sukTaanmptybHxEa1Xi1KxzVxlgkPmjUpwcu6LPdgKH3nX");
	    twitter.setOAuthAccessToken(accessToken);

	    try {
            Query query = new Query();
            query.geoCode(new GeoLocation(37.781157,-122.398720),100.0,"mi");
            QueryResult result;
            System.out.println("Searching...");
            int Count=0;

            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                	if(tweet.getGeoLocation()!=null)
                		System.out.println(tweet.getGeoLocation());
                }
            }
            while ((query = result.nextQuery()) != null);
            System.out.println(Count);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
	}
	

}
