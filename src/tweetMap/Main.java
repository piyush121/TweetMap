package tweetMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author Piyush Chaudhary
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {

		ConfigurationBuilder builder = new ConfigurationBuilder();
		builder.setOAuthConsumerKey("wexQs0nZ1Mfq5rZAAiXFgVHnz");
		builder.setOAuthConsumerSecret("2df8sukTaanmptybHxEa1Xi1KxzVxlgkPmjUpwcu6LPdgKH3nX");
		Configuration configuration = builder.build();
		TwitterFactory factory = new TwitterFactory(configuration);
		Twitter twitter = factory.getInstance();
		final AccessToken accessToken = new AccessToken("39964732-rBS2TnH2wQ52VuQZjodzyJwku49byY8WdWf3h9USA",
				"cfTLD9cEpKaypEJ6Xzt1BPZyRondv8lqtQtjlBFmCfRlb");
		twitter.setOAuthAccessToken(accessToken);
		// Delimiter used in CSV file
		String COMMA_DELIMITER = ",";
		String NEW_LINE_SEPARATOR = "\n";

		// CSV file header
		String FILE_HEADER = "latitude,longitude,username,tweettext";
		FileWriter fileWriter = null;

		try {
			Query query = new Query("#oscar");
			query.geoCode(new GeoLocation(37.781157, -122.398720), 3900.0, "mi");

			QueryResult result;
			System.out.println("Searching...");
			int count = 0;
			File file = new File("C:\\Users\\piyus\\workspace\\TweetMap\\src\\tweetMap\\tweets.csv");
			fileWriter = new FileWriter(file);
			// Write the CSV file header
			fileWriter.write(FILE_HEADER.toString());

			fileWriter.append(NEW_LINE_SEPARATOR);

			do {

				result = twitter.search(query);
				List<Status> tweets = result.getTweets();
				for (Status tweet : tweets) {
					{
						if (tweet.getGeoLocation() != null) {
							System.out.println(tweet.getText() + " " + tweet.getGeoLocation() + "\n");
							count++;
							
							fileWriter.append((String.valueOf(tweet.getGeoLocation().getLatitude())));
							fileWriter.append(COMMA_DELIMITER);
							fileWriter.append(String.valueOf(tweet.getGeoLocation().getLongitude()));
							fileWriter.append(COMMA_DELIMITER);
							fileWriter.append(tweet.getUser().getName());
							fileWriter.append(COMMA_DELIMITER);
							fileWriter.append(tweet.getText());
							fileWriter.append(NEW_LINE_SEPARATOR);

						}
					}
					
				}

			} while ((query = result.nextQuery()) != null);
			System.out.println(count);
			System.out.println(result.getRateLimitStatus());

		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
			System.exit(-1);
		} finally {

			fileWriter.flush();
			fileWriter.close();

		}
	}
}
