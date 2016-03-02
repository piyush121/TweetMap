package tweetMap;

import java.util.List;

import twitter4j.*;
import twitter4j.auth.AccessToken;

/**
 * @author Piyush Chaudhary
 *
 */
public class Main  {
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
	TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
    StatusListener listener = new StatusListener() {

        @Override
        public void onStatus(Status status) {
                //here you do whatever you want with the tweet
            System.out.println(status.getText());

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
    FilterQuery filterQuery = new FilterQuery();
    double[][] locations = {{-74,40}, {-73,41}}; //those are the boundary from New York City
    filterQuery.locations(locations);
    twitterStream.filter(filterQuery);
    twitterStream.filter(filterQuery);

}
