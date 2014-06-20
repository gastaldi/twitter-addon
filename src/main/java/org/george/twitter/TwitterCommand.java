/**
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.george.twitter;

import javax.inject.Inject;

import org.jboss.forge.addon.ui.annotation.Command;
import org.jboss.forge.addon.ui.annotation.Option;
import org.jboss.forge.furnace.util.Lists;
import org.jboss.forge.furnace.util.Strings;

import twitter4j.Twitter;

/**
 * Tweets a command
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
public class TwitterCommand
{

   @Inject
   private Twitter twitter;

   @Command(value = "Twitter: Tweet", help = "Updates the status for the configured twitter account", categories = "Twitter")
   public void updateStatus(@Option("arguments") Iterable<String> arguments) throws Exception
   {
      String message = Strings.join(Lists.toList(arguments).toArray());
      twitter.updateStatus(message);
   }
}
