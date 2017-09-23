/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dao;

import com.sg.model.HashTag;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface HashTagDaoInterface {
    
    public void addHashTag(HashTag hashtag);

    public void deleteHashTag(int hashTagId);

    public HashTag getHashTagByID(int id);

    public List<HashTag> getAllHashTags();
    
    public List<HashTag> getAllHashTagsByPostID(int id);
    
    public HashTag getHashTagByTag(String tag);
}
