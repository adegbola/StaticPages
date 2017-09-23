/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.model.Post;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface PostDaoInterface {
    
    
    public void addPost(Post post);
    
    public void deletePost(int id);
    
    public void editPost(Post post);
    
    public Post getPostByID(int id);
    
    public List<Post> getAllBlogPosts();
    
    public List<Post> getAllStaticPages();
    
    public List<Post> getPostsByDate(LocalDate date);
    
    public List<Post> getPostsByHashTagID(int id);
    
    public List<Post> getPostsByProfileID(int id);
}
