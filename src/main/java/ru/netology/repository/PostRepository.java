package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
  private final ConcurrentHashMap <Long, Post>  postMap = new ConcurrentHashMap<>();
  private final AtomicLong id = new AtomicLong();

  public Collection<Post> all() {
    return postMap.values();
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(postMap.get(id));
  }

  public Post save(Post post) {
    if (post.getId() == 0){
      long i = id.addAndGet(1);
      postMap.put(i,new Post(i,post.getContent()));
    }else{
      long i = post.getId();
      postMap.put(i, new Post(i, post.getContent()));
      id.incrementAndGet();
    }
    return post;
  }

  public void removeById(long id) {
    postMap.remove(id);
  }
}
