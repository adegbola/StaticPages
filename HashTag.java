/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.model;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class HashTag {
    private int hashTagID;
    private String tag;
    private List<Post>posts;

    public int getHashTagID() {
        return hashTagID;
    }

    public void setHashTagID(int hashTagID) {
        this.hashTagID = hashTagID;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.hashTagID;
        hash = 29 * hash + Objects.hashCode(this.tag);
        hash = 29 * hash + Objects.hashCode(this.posts);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HashTag other = (HashTag) obj;
        if (this.hashTagID != other.hashTagID) {
            return false;
        }
        if (!Objects.equals(this.tag, other.tag)) {
            return false;
        }
        if (!Objects.equals(this.posts, other.posts)) {
            return false;
        }
        return true;
    }
   
}
