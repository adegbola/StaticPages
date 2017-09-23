/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;
import com.sg.model.Profile;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author apprentice
 */
public class ProfileDao implements ProfileDaoInterface {

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addProfile(Profile profile) {
        jdbcTemplate.update(Query.SQL_INSERT_PROFILE,
                profile.getUserName(),
                profile.getPassword(),
        profile.getFirstName(),
        profile.getLastName(),
        profile.getEmail(),
        profile.getBirthday().toString(),
        profile.isAdminID());
        profile.setProfileID(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
    }

    @Override
    public void deleteProfile(int profileID) {
        jdbcTemplate.update(Query.SQL_DELETE_PROFILE, profileID);
    }

    @Override
    public void updateProfile(Profile profile) {
        jdbcTemplate.update(Query.SQL_UPDATE_PROFILE,
                profile.getUserName(),
                profile.getPassword(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getEmail(),
                profile.getBirthday().toString(),
                profile.isAdminID(),
                profile.getProfileID());
    }

    @Override
    public List<Profile> getAllProfiles() {
        List<Profile> profileList = jdbcTemplate.query(Query.SQL_SELECT_ALL_PROFILES,
                new Mapper.ProfileMapper());
        return profileList;
    }

    @Override
    public Profile getProfileByID(int profileID) {
        try {
        Profile profile = jdbcTemplate.queryForObject(Query.SQL_SELECT_PROFILE,
                new Mapper.ProfileMapper(),
                profileID);

        return(profile);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Profile getProfileByPostID(int postID) {
        Profile profile = jdbcTemplate.queryForObject(Query.SQL_SELECT_PROFILE_BY_POST_ID,
                new Mapper.ProfileMapper(),
                postID);
        return(profile); 
    }
        @Override
    public Profile getProfileByUserName(String userName) {
        Profile profile = jdbcTemplate.queryForObject(Query.SQL_SELECT_PROFILE_BY_USERNAME,
                new Mapper.ProfileMapper(),
                userName);
        return(profile); 
    }
}
