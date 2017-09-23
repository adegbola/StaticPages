/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.model.HashTag;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author apprentice
 */
public class HashTagDao implements HashTagDaoInterface {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addHashTag(HashTag hashTag) {
        jdbcTemplate.update(Query.SQL_INSERT_HASHTAG, hashTag.getTag());
        int newID = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        hashTag.setHashTagID(newID);

    }



    @Override
    public void deleteHashTag(int HashTagId) {
        jdbcTemplate.update(Query.SQL_DELETE_HASHTAG, HashTagId);
    }

    @Override
    public HashTag getHashTagByID(int id) {
        try {
            return jdbcTemplate.queryForObject(Query.SQL_SELECT_HASHTAG, new Mapper.HashTagMapper(), id);
        } catch (EmptyResultDataAccessException ex) {

            return null;
        }
    }

    @Override
    public List<HashTag> getAllHashTags() {
        return jdbcTemplate.query(Query.SQL_SELECT_ALL_HASHTAGS, new Mapper.HashTagMapper());
    }
    
    @Override
    public List<HashTag> getAllHashTagsByPostID(int id) {
        return jdbcTemplate.query(Query.SQL_SELECT_HASHTAGS_FROM_POST, new Mapper.HashTagMapper(), id);
    }
    
    @Override
    public HashTag getHashTagByTag(String tag) {
        try {
            return jdbcTemplate.queryForObject(Query.SQL_SELECT_HASHTAG_FROM_TAG, new Mapper.HashTagMapper(), tag);
        } catch (EmptyResultDataAccessException ex) {
            
            return null;
        }
    }

}
