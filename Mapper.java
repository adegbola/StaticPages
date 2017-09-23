/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.model.HashTag;
import com.sg.model.Post;
import com.sg.model.Profile;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author apprentice
 */
public class Mapper {
    
    public static final class ProfileMapper implements RowMapper<Profile> {
        
        @Override
        public Profile mapRow(ResultSet rs, int i) throws SQLException {
            Profile profile = new Profile();
            profile.setProfileID(rs.getInt("ProfileID"));
            profile.setUserName(rs.getString("UserName"));
            profile.setPassword(rs.getString("Password"));
            profile.setFirstName(rs.getString("FirstName"));
            profile.setLastName(rs.getString("LastName"));
            profile.setEmail(rs.getString("Email"));
            profile.setBirthday(rs.getTimestamp("Birthday").toLocalDateTime().toLocalDate());
            profile.setAdminID(rs.getBoolean("AdminID"));
            return profile;
        }
        
    }
    
    public static final class PostMapper implements RowMapper<Post> {
    
        @Override
        public Post mapRow(ResultSet rs, int i) throws SQLException {
            Post post = new Post();
            post.setPostID(rs.getInt("PostID"));
            post.setTitle(rs.getString("Title"));
            post.setDescription(rs.getString("Description"));
            post.setPending(rs.getBoolean("Pending"));
            post.setBlog(rs.getBoolean("Blog"));
            post.setDate(rs.getTimestamp("Date").toLocalDateTime().toLocalDate());
            post.setTitle(rs.getString("Title"));
            post.setPicture(rs.getString("Picture"));
            return post;
        }
    }
    
    public static final class HashTagMapper implements RowMapper<HashTag> {
        
        @Override
        public HashTag mapRow(ResultSet rs, int i) throws SQLException {
            HashTag ht = new HashTag();
            ht.setHashTagID(rs.getInt("HashTagID"));
            ht.setTag(rs.getString("Tag"));
            return ht;
        }
    }
}
