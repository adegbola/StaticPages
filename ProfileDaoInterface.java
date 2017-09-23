/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.model.Profile;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface ProfileDaoInterface {
    public void addProfile(Profile profile);
    public void deleteProfile(int profileID);
    public void updateProfile(Profile profile);
    public List<Profile> getAllProfiles();
    public Profile getProfileByID(int profileID);
    public Profile getProfileByPostID(int postID);
    public Profile getProfileByUserName(String userName);
}
