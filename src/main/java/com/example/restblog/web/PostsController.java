package com.example.restblog.web;

import com.example.restblog.data.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    ArrayList<Post> newPosts = new ArrayList<>();

    @GetMapping
    public ArrayList<Post> getAll() {
        newPosts.clear();
        newPosts.add( new Post(1L, "YUM", " Piectures you can smell"));
        newPosts.add(new Post(2L, "blahBlahblahblaahhhBLAAAAaahhh", "BLAH blah "));
        newPosts.add(new Post(3L, "Macbook Air", "Apple Pen"));
        return newPosts;
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable long id){
        for (Post post: getAll()) {
            if (Objects.equals(post.getId(), id)){
                return post;
            }
        }
        return new Post();
    }

    @PostMapping
    public void createPost(@RequestBody Post newPost){
        System.out.println(newPost);
    }

    @PutMapping("{id}")
    public void updatePost(@RequestBody Post post , @PathVariable long id){
        for (Post oldPost: getAll()) {
            if (Objects.equals(oldPost.getId(), id)){
                System.out.println(oldPost);
                post.setId(id);
                oldPost.setContent(post.getContent());
                oldPost.setTitle(post.getTitle());
                System.out.println(post);
            }
        }
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable long id){
        Post postToRemove = null;
        for (Post postToDelete: getAll()) {
            if (Objects.equals(postToDelete.getId(),id))
                postToDelete = postToRemove;
            System.out.println(postToDelete+" getting deleted");

        }
    }
}