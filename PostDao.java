/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.model.HashTag;
import com.sg.model.Post;
import com.sg.model.Profile;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class PostDao implements PostDaoInterface {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addPost(Post post) {
        jdbcTemplate.update(Query.SQL_ADD_POST, post.getTitle(), post.getPicture(), post.getDescription(), post.isPending(), post.isBlog(), post.getDate().toString(), post.getProfile().getProfileID());
        int postid = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        post.setPostID(postid);

        insertPostHashTag(post);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deletePost(int id) {
        jdbcTemplate.update(Query.SQL_DELETE_POSTHASHTAG_FROM_POST, id);
        jdbcTemplate.update(Query.SQL_DELETE_POST, id);
    }

    @Override
    public void editPost(Post post) {
        jdbcTemplate.update(Query.SQL_UPDATE_POST, post.getTitle(), post.getPicture(), post.getDescription(), post.isPending(), post.isBlog(), post.getDate().toString(), post.getPostID());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Post getPostByID(int id) {
        try {
            Post post = jdbcTemplate.queryForObject(Query.SQL_SELECT_POST, new Mapper.PostMapper(), id);
            List<HashTag> hList = jdbcTemplate.query(Query.SQL_SELECT_HASHTAGS_FROM_POST, new Mapper.HashTagMapper(), id);
            Profile profile = jdbcTemplate.queryForObject(Query.SQL_SELECT_PROFILE_BY_POST_ID, new Mapper.ProfileMapper(), id);
            post.setProfile(profile);
            post.setHashTags(hList);

            return post;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Post> getAllBlogPosts() {
        List<Post> postList = jdbcTemplate.query(Query.SQL_SELECT_ALL_BLOG_POSTS, new Mapper.PostMapper());
        for (Post currentPost : postList) {
            Profile profile = jdbcTemplate.queryForObject(Query.SQL_SELECT_PROFILE_BY_POST_ID, new Mapper.ProfileMapper(), currentPost.getPostID());
            List<HashTag> hList = jdbcTemplate.query(Query.SQL_SELECT_HASHTAGS_FROM_POST, new Mapper.HashTagMapper(), currentPost.getPostID());
            currentPost.setProfile(profile);
            currentPost.setHashTags(hList);
        }

        return postList;
    }
        @Override
    public List<Post> getAllStaticPages() {
        List<Post> postList = jdbcTemplate.query(Query.SQL_SELECT_ALL_STATIC_PAGES, new Mapper.PostMapper());
        for (Post currentPost : postList) {
            Profile profile = jdbcTemplate.queryForObject(Query.SQL_SELECT_PROFILE_BY_POST_ID, new Mapper.ProfileMapper(), currentPost.getPostID());
            List<HashTag> hList = jdbcTemplate.query(Query.SQL_SELECT_HASHTAGS_FROM_POST, new Mapper.HashTagMapper(), currentPost.getPostID());
            currentPost.setProfile(profile);
            currentPost.setHashTags(hList);
        }

        return postList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Post> getPostsByDate(LocalDate date) {
        try {
            List<Post> postList = jdbcTemplate.query(Query.SQL_SELECT_POSTS_FROM_DATE, new Mapper.PostMapper(), date.toString());
            for (Post currentPost : postList) {
                Profile profile = jdbcTemplate.queryForObject(Query.SQL_SELECT_PROFILE_BY_POST_ID, new Mapper.ProfileMapper(), currentPost.getPostID());
                List<HashTag> hList = jdbcTemplate.query(Query.SQL_SELECT_HASHTAGS_FROM_POST, new Mapper.HashTagMapper(), currentPost.getPostID());
                currentPost.setProfile(profile);
                currentPost.setHashTags(hList);
            }

            return postList;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Post> getPostsByHashTagID(int id) {
        List<Post> postList = jdbcTemplate.query(Query.SQL_SELECT_POSTS_FROM_HASHTAG, new Mapper.PostMapper(), id);
        for (Post currentPost : postList) {
            Profile profile = jdbcTemplate.queryForObject(Query.SQL_SELECT_PROFILE_BY_POST_ID, new Mapper.ProfileMapper(), currentPost.getPostID());
            List<HashTag> hList = jdbcTemplate.query(Query.SQL_SELECT_HASHTAGS_FROM_POST, new Mapper.HashTagMapper(), currentPost.getPostID());
            currentPost.setProfile(profile);
            currentPost.setHashTags(hList);
        }

        return postList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<Post> getPostsByProfileID(int id) {
        List<Post> postList = jdbcTemplate.query(Query.SQL_SELECT_POSTS_FROM_PROFILE, new Mapper.PostMapper(), id);
        for (Post currentPost : postList) {
            Profile profile = jdbcTemplate.queryForObject(Query.SQL_SELECT_PROFILE_BY_POST_ID, new Mapper.ProfileMapper(), currentPost.getPostID());
            List<HashTag> hList = jdbcTemplate.query(Query.SQL_SELECT_HASHTAGS_FROM_POST, new Mapper.HashTagMapper(), currentPost.getPostID());
            currentPost.setProfile(profile);
            currentPost.setHashTags(hList);
        }

        return postList;
    }

    private void insertPostHashTag(Post post) {
        if (!post.getHashTags().isEmpty()) {
            for (HashTag currentHash : post.getHashTags()) {
                jdbcTemplate.update(Query.SQL_INSERT_POSTHASHTAG, post.getPostID(), currentHash.getHashTagID());
            }
        }
    }

}
