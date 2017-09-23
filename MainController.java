/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.allstarblog;

import com.sg.dao.HashTagDaoInterface;
import com.sg.dao.PostDaoInterface;
import com.sg.dao.ProfileDaoInterface;
import com.sg.model.HashTag;
import com.sg.model.Post;
import com.sg.model.Profile;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    private PasswordEncoder encoder;
    PostDaoInterface postDao;
    ProfileDaoInterface profileDao;
    HashTagDaoInterface hashTagDao;
    LocalDate today = LocalDate.now();
    List<Post> blogList;
    List<Post> pageList;
    Profile currentProfile;
    boolean isLoggedIn = false;
    int postID;

    @Inject
    public MainController(PostDaoInterface PostDao, ProfileDaoInterface ProfileDao, HashTagDaoInterface HashTagDao) {
        this.postDao = PostDao;
        this.profileDao = ProfileDao;
        this.hashTagDao = HashTagDao;
        this.encoder=encoder;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHome(Model model) {
        List<Post> pendingList = new ArrayList();
        blogList = postDao.getAllBlogPosts();
        for (Post currentPost : blogList) {
            if (currentPost.isPending() == true) {
                pendingList.add(currentPost);
            }
        }

        blogList.removeAll(pendingList);
        pageList = postDao.getAllStaticPages();
        Collections.reverse(blogList);
        model.addAttribute("profile", currentProfile);
        model.addAttribute("pageList", pageList);
        model.addAttribute("blogList", blogList);
        model.addAttribute("isLoggedIn", isLoggedIn);

        return "index";
    }

    @RequestMapping(value = "/addProfilePage", method = RequestMethod.GET)
    public String displayAddProfilePage(Model model
    ) {
        
        model.addAttribute("profile", currentProfile);
        return "addProfile";
    }

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String displayLoginPage(Model model
    ) {
        return "loginPage";
    }

    @RequestMapping(value = "/addPost", method = RequestMethod.GET)
    public String addPost(Model model) {

        model.addAttribute("profile", currentProfile);
        model.addAttribute("isLoggedIn", isLoggedIn);
        return "addPost";
    }

    @RequestMapping(value = "addBlogPost", method = RequestMethod.POST)
    public String addBlog(HttpServletRequest request, Model model) {
        List<HashTag> hashList = new ArrayList<>();
        HashTag hashTag = new HashTag();
        String[] tags = request.getParameterValues("tags");
        for (int i = 0; i < tags.length; i++) {
            hashTag = hashTagDao.getHashTagByTag(tags[i]);
            if (hashTag == null) {
                HashTag hash = new HashTag();
                hash.setTag(tags[i]);
                hashTagDao.addHashTag(hash);
                hashList.add(hash);
            } else {
                hashList.add(hashTag);
            }
        }
        Post blogPost = new Post();
        blogPost.setBlog(Boolean.parseBoolean(request.getParameter("blogPost")));
        blogPost.setPending(Boolean.parseBoolean(request.getParameter("pending")));
        blogPost.setTitle(request.getParameter("title"));
        blogPost.setDescription(request.getParameter("texteditor"));
        blogPost.setProfile(currentProfile);
        blogPost.setHashTags(hashList);
        blogPost.setDate(today);
        blogPost.setPicture(request.getParameter("picture"));
        if (currentProfile.isAdminID() == false) {
            blogPost.setPending(true);
        }
        postDao.addPost(blogPost);

//        String pictureURL = request.getParameter("picture");
//        String pictureID = Integer.toString(blogPost.getPostID());
//        savePicture(pictureURL, pictureID);
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("profile", currentProfile);
        return"redirect:/";

    }

    @RequestMapping(value = "goLogin", method = RequestMethod.POST)
    public String login(HttpServletRequest request, Model model
    ) {
        String userName = request.getParameter("j_username");
        String password = request.getParameter("j_password");
        try {
            Profile profile = profileDao.getProfileByUserName(userName);
            if (profile.getPassword().equals(password)) {
                currentProfile = profile;
                isLoggedIn = true;
                return "redirect:/";
            }
        } catch (EmptyResultDataAccessException e) {
            return "redirect:/loginPage";
        }
        
        return "redirect:/loginPage";
    }

    @RequestMapping(value = "createProfile", method = RequestMethod.POST)
    public String createProfile(HttpServletRequest request, Model model
    ) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String hashPw = encoder.encode(password);
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        
        
        

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String retrievedDate = request.getParameter("datepicker");
        LocalDate birthday = LocalDate.parse(retrievedDate, formatter);

        Profile profile = new Profile();
        profile.setUserName(userName);
        profile.setPassword(hashPw);
        profile.setFirstName(firstName);
        profile.setLastName(lastName);
        profile.setEmail(email);
        profile.setBirthday(birthday);
        profile.setAdminID(false);

        profileDao.addProfile(profile);
       

        return "redirect:/loginPage";
    }

    @RequestMapping(value = "categorizeBlogPosts", method = RequestMethod.GET)
    public String categorizeBlogPosts(HttpServletRequest request, Model model
    ) {
        HashTag hashTag = new HashTag();
        hashTag = hashTagDao.getHashTagByTag(request.getParameter("hashTagSearch"));
        if (hashTag == null) {
            return "redirect:/";
        } else {
            blogList = postDao.getPostsByHashTagID(hashTag.getHashTagID());
            model.addAttribute("profile", currentProfile);
            model.addAttribute("blogList", blogList);
            model.addAttribute("isLoggedIn", isLoggedIn);
            return "index";
        }
    }

    @RequestMapping(value = "goHome", method = RequestMethod.GET)
    public String goHome(Model model
    ) {
        blogList = postDao.getAllBlogPosts();

        return "redirect:/";
    }

    @RequestMapping(value = "deletePost", method = RequestMethod.POST)
    public String deleteBlog(HttpServletRequest request
    ) {
        Post post = new Post();
        post = postDao.getPostByID(Integer.parseInt(request.getParameter("deleteButton")));
        if (currentProfile.isAdminID() == true) {
        postDao.deletePost(Integer.parseInt(request.getParameter("deleteButton")));
        } else {
            post.setPending(true);
        }

        return "redirect:/";
    }

    @RequestMapping(value = "updatePost", method = RequestMethod.GET)
    public String updatePost(HttpServletRequest request, Model model
    ) {
        Post post = new Post();
        post = postDao.getPostByID(Integer.parseInt(request.getParameter("editButton")));

        model.addAttribute("post", post);
        return "editPost";
    }

    @RequestMapping(value = "editPost", method = RequestMethod.POST)
    public String editBlog(HttpServletRequest request, Model model
    ) {
        Post post = postDao.getPostByID(Integer.parseInt(request.getParameter("id")));
        post.setTitle(request.getParameter("title"));
        post.setDescription(request.getParameter("texteditor"));
        if (currentProfile.isAdminID() == false) {
            post.setPending(true);
        }
        postDao.editPost(post);
        

        return "redirect:/";
    }

    @RequestMapping(value = "pendingList", method = RequestMethod.GET)
    public String displayPendingList(HttpServletRequest request, Model model
    ) {
        List<Post> pendingList = new ArrayList();
        blogList = postDao.getAllBlogPosts();
        for (Post currentPost : blogList) {
            if (currentPost.isPending() == false) {
                pendingList.add(currentPost);
            }
        }
        blogList.removeAll(pendingList);
        
        model.addAttribute("profile", currentProfile);
        model.addAttribute("blogList", blogList);
        return "pending";
    }

    @RequestMapping(value = "approvePost", method = RequestMethod.POST)
    public String approvePost(HttpServletRequest request, Model model
    ) {
        Post post = postDao.getPostByID(Integer.parseInt(request.getParameter("approveButton")));
        post.setPending(false);
        postDao.editPost(post);

        return "redirect:/pendingList";
    }

    @RequestMapping(value = "displayBlogPost", method = RequestMethod.POST)
    public String displayBlogPost(HttpServletRequest request, Model model
    ) {
        postID = parseInt(request.getParameter("blogInput"));
        
        model.addAttribute("profile", currentProfile);
        return "redirect:/displayBlogPostPage";
    }

    @RequestMapping(value = "/displayBlogPostPage", method = RequestMethod.GET)
    public String displayBlogPostPage(HttpServletRequest request, Model model
    ) {
        model.addAttribute("post", postDao.getPostByID(postID));
        model.addAttribute("profile", currentProfile);
        return "displayBlogPost";
    }

    @RequestMapping(value = "logOut", method = RequestMethod.GET)
    public String logOut(Model model) {
        isLoggedIn = false;

        return "redirect:/";
    }

    public void savePicture(String pictureURL, String pictureID) {
        try {
            
            
            URL url = new URL(pictureURL);
            InputStream in = new BufferedInputStream(url.openStream());
            OutputStream out = new BufferedOutputStream(new FileOutputStream(""));

            for (int i; (i = in.read()) != -1;) {
                out.write(i);
            }
            in.close();
            out.close();

        } catch (IOException e) {
        }
    }

    @RequestMapping(value = "account", method = RequestMethod.GET)
    public String displayAccount(HttpServletRequest request, Model model) {

        model.addAttribute("profile", currentProfile);
        return "account";
    }

}
