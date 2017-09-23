/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class Post {
    private int postID;
    private String title;
    private String description;
    private boolean pending;
    private boolean blog;
    private LocalDate date;
    private Profile profile;
    private List<HashTag> HashTags;
    private String picture;

    public int getPostID() {
        return postID;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPending() {
        return pending;
    }

    public void setPending(boolean pending) {
        this.pending = pending;
    }

    public boolean isBlog() {
        return blog;
    }

    public void setBlog(boolean blog) {
        this.blog = blog;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<HashTag> getHashTags() {
        return HashTags;
    }

    public void setHashTags(List<HashTag> HashTags) {
        this.HashTags = HashTags;
    }

    

    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + this.postID;
        hash = 19 * hash + Objects.hashCode(this.description);
        hash = 19 * hash + (this.pending ? 1 : 0);
        hash = 19 * hash + Objects.hashCode(this.profile);
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
        final Post other = (Post) obj;
        if (this.postID != other.postID) {
            return false;
        }
        if (this.pending != other.pending) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.profile, other.profile)) {
            return false;
        }
        return true;
    }
    
    
    
}
