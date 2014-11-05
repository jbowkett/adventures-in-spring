package hello;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.FriendOperations;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by jbowkett on 05/11/2014.
 */
@Controller
@RequestMapping("/")
public class HelloController {

  private final Twitter twitter;
  private final ConnectionRepository connectionRepository;

  @Inject
  public HelloController(Twitter twitter, ConnectionRepository connectionRepository) {
    this.twitter = twitter;
    this.connectionRepository = connectionRepository;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String helloTwitter(Model model){
    if(noTwitterConnection()){
      return "redirect:/connect/twitter";
    }
    try {
      model.addAttribute(twitter.userOperations().getUserProfile());
      final FriendOperations friendOperations = twitter.friendOperations();
/*
    final CursoredList<TwitterProfile> friends = friendOperations.getFriends();
    model.addAttribute("friends", friends);
*/

      final List<TwitterProfile> followers = new ArrayList<>();
      CursoredList<TwitterProfile> followersCursor = friendOperations.getFollowers();
      while (followersCursor.hasNext()) {
        followers.addAll(followersCursor);
        final long nextCursor = followersCursor.getNextCursor();
        followersCursor = friendOperations.getFollowersInCursor(nextCursor);
      }
      followers.sort((o1, o2) -> new Integer(o1.getFollowersCount()).compareTo(o2.getFollowersCount()));
      model.addAttribute("followers", followers);
    }
    finally{}
    return "hello";
  }

  private boolean noTwitterConnection() {
    return connectionRepository.findPrimaryConnection(Twitter.class) == null;
  }
}
