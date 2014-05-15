package org.george.twitter;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jboss.forge.addon.configuration.Configuration;
import org.jboss.forge.addon.configuration.Subset;
import org.jboss.forge.addon.ui.annotation.Command;
import org.jboss.forge.addon.ui.annotation.Option;
import org.jboss.forge.addon.ui.hints.InputType;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterSetupCommand
{
   @Inject
   @Subset("twitter")
   private Configuration config;

   @Command("Twitter: Setup")
   public void setup(
            @Option(value = "consumerKey", label = "Consumer Key", required = true) String consumerKey,
            @Option(value = "consumerSecret", label = "Consumer Secret", type = InputType.SECRET, required = true) String consumerSecret,
            @Option(value = "accessToken", label = "Access Token", required = true) String accessToken,
            @Option(value = "accessTokenSecret", label = "Access Token Secret", type = InputType.SECRET, required = true) String accessTokenSecret)
   {
      config.setProperty("consumerKey", consumerKey);
      config.setProperty("consumerSecret", consumerSecret);
      config.setProperty("accessToken", accessToken);
      config.setProperty("accessTokenSecret", accessTokenSecret);
   }

   @Produces
   public Twitter produceTwitter()
   {
      // If consumerKey is configured
      if (config.containsKey("consumerKey"))
      {
         ConfigurationBuilder cb = new ConfigurationBuilder();
         cb.setDebugEnabled(true)
                  .setOAuthConsumerKey(config.getString("consumerKey"))
                  .setOAuthConsumerSecret(config.getString("consumerSecret"))
                  .setOAuthAccessToken(config.getString("accessToken"))
                  .setOAuthAccessTokenSecret(config.getString("accessTokenSecret"));
         TwitterFactory tf = new TwitterFactory(cb.build());
         return tf.getInstance();
      }
      else
      {
         return TwitterFactory.getSingleton();
      }
   }
}