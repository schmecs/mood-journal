package com.moodjournal.model;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Journal {
  private Map<String,Post> mPosts;
  
  public Journal() {
    mPosts = new HashMap<String,Post>();
  }

  public void addPost(Post post) {
    mPosts.put(post.getID(),post);
  }

  public Post getPost(String postID) {
    return mPosts.get(postID);
  }
  
  public int getPostCount() {
    return mPosts.size();
  }

  public void getAllPosts() {
    for (Map.Entry<String,Post> entry : mPosts.entrySet()) {
      Post post = getPost(entry.getKey());
      System.out.printf("%s: %n%s - %s %n",
                        post.getDate(),
                        post.getID(),
                        post.getText());
    }
  }
  
  // FIXME:  This should be cached!
  private Map<String, List<Post>> byAuthor() {
    Map<String, List<Post>> byAuthor = new HashMap<>();
    for (Post post : mPosts.values()) {
      List<Post> authorPosts = byAuthor.get(post.getAuthor());
      if (authorPosts == null) {
        authorPosts = new ArrayList<Post>();
        byAuthor.put(post.getAuthor(), authorPosts);
      }
      authorPosts.add(post);
    }
    return byAuthor;
  }

}