/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

/**
 *
 * @author apprentice
 */
public class Query {
    
    //Post Queries:
    public static final String SQL_SELECT_POST 
            = "select Post.* "
            + "from Post "
            + "where Post.PostID = ?";
    
    public static final String SQL_SELECT_ALL_BLOG_POSTS
            = "select Post.* from Post where Post.Blog = true";
    public static final String SQL_SELECT_ALL_STATIC_PAGES
            = "select Post.* from Post where Post.Blog = false";
    
    public static final String SQL_DELETE_POST
            = "delete from Post where PostID = ?";
    
    public static final String SQL_UPDATE_POST
            = "update Post set Title = ?, Picture=?, Description = ?, Pending = ?, Blog = ?, Date = ? where Post.PostID = ?";
    
    public static final String SQL_ADD_POST
            = "insert into Post (Title, Picture, Description, Pending, Blog, Date, ProfileID) "
            + "values (?, ?, ?, ?, ?, ?, ?)";
    
    
    
    public static final String SQL_SELECT_POSTS_FROM_HASHTAG
            = "select Post.* "
            + "from HashTag "
            + "inner join PostHashTag on HashTag.HashTagID = PostHashTag.HashTagID "
            + "inner join Post on PostHashTag.PostID = Post.PostID "
            + "where HashTag.HashTagID = ?";
    
    public static final String SQL_SELECT_POSTS_FROM_DATE
            = "select Post.* "
            + "from Post where Post.Date = ?";
    
    public static final String SQL_SELECT_POSTS_FROM_PROFILE
            = "select Post.* from Post "
            + "where Post.ProfileID = ?";
    
    
    //Profile Queries:
    public static final String SQL_INSERT_PROFILE
            = "insert into Profile (UserName, "
            + "Password, FirstName, LastName, Email, Birthday, AdminID) values (?, ?, ?, ?, ?, ?, ?)";
    

    public static final String SQL_DELETE_PROFILE
            = "delete from Profile where ProfileID = ?";

    public static final String SQL_UPDATE_PROFILE
            = "update Profile set UserName = ?, "
            + "Password = ?, FirstName = ?, LastName = ?, Email = ?, Birthday = ?, AdminID = ? where ProfileID =  ?";

    public static final String SQL_SELECT_PROFILE
            = "select * from Profile where ProfileID = ?";
    
    public static final String SQL_SELECT_PROFILE_BY_USERNAME
            = "select * from Profile where UserName = ?";

    public static final String SQL_SELECT_ALL_PROFILES
            = "select * from Profile";

    public static final String SQL_SELECT_PROFILE_BY_POST_ID
            = "select * from `Profile` Profile "
            + "join Post Post on Profile.ProfileID=Post.ProfileID "
            + "where Post.PostID = ?";
    
    //HastTag Queries:
    public static final String SQL_INSERT_HASHTAG
            = "insert into HashTag (Tag)"
            + " values (?)";
    public static final String SQL_DELETE_HASHTAG
            = "delete from HashTag where HashTagID = ?";
    public static final String SQL_SELECT_HASHTAG
            = "select * from HashTag where HashTagID = ?";
    public static final String SQL_SELECT_HASHTAG_FROM_TAG
            = "select * from HashTag where Tag = ?";
    public static final String SQL_UPDATE_HASHTAG
            = "update HashTag set "
            + "Tag = ? "
            + "where HashTagID = ?";
    public static final String SQL_SELECT_ALL_HASHTAGS
            = "select * from HashTag";
    public static final String SQL_SELECT_HASHTAGS_FROM_POST
            = "select HashTag.* "
            + "from Post "
            + "inner join PostHashTag on Post.PostID = PostHashTag.PostID "
            + "inner join HashTag on PostHashTag.HashTagID = HashTag.HashTagID "
            + "where Post.PostID = ?";
    
    //PostHashTag Queries:
    public static final String SQL_DELETE_POSTHASHTAG_FROM_POST
            = "delete from PostHashTag where PostHashTag.PostID = ?";
    public static final String SQL_INSERT_POSTHASHTAG
            = "insert into PostHashTag (PostID, HashTagID) "
            + "values (?, ?)";

}
