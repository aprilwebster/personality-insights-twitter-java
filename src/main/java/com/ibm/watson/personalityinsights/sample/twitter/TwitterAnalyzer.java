package com.ibm.watson.personalityinsights.sample.twitter;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import twitter4j.Status;

public class TwitterAnalyzer {

	public static void main(String[] args) throws Exception {
		//System.out.println("DEBUG TwitterAnalyzer HELP: args are " + args[0]);
		//String handle = args.length < 1 ? "jschoudt" : args[0];
		String handle = "@Gap";
		
		List<String> lines = FileUtils.readLines(new File("/Users/april/git/vibha-forks/food-coach/tmp/twitters.txt"), "utf-8");
		System.out.println("DEBUG: number of handles: " + new Integer(lines.size()).toString());
		for (int i = 0; i < lines.size(); i++){
		  System.out.println(lines.get(i));
		}
		
		System.out.println("DEBUG TwitterAnalyzer HELP: handle is " + handle);
		Properties props = new Properties();
		props.load(FileUtils.openInputStream(new File("twittersample.properties")));
		
		System.out.println("DEBUG TwitterAnalyzer: TwitterAnalyzer called.");
		System.out.println("DEBUG TwitterAnalyzer: properties are " + props);
		
		Twitter4JHelper twitterHelper = new Twitter4JHelper(props);
		PersonalityInsightsHelper piHelper = new PersonalityInsightsHelper(props);
		
		HashSet<String> langs = new HashSet<String>();
		langs.add("en");
		langs.add("es");
		
		List<Status> tweets = twitterHelper.getTweets(handle, langs, 200);
		System.out.println("DEBUG TwitterAnalyzer: first tweet " + tweets.get(0).toString());
		String contentItemsJson = twitterHelper.convertTweetsToPIContentItems(tweets);
		//System.out.println("DEBUG TwitterAnalyzer: content items json " + contentItemsJson);
		String profile = piHelper.getProfileJSON(contentItemsJson, false);
		//String profile = piHelper.getProfileCSV(contentItemsJson, false);
		System.out.println("DEBUG TwitterAnalyzer" + profile);
	}

}
