package com.WineOutBE.Service;

import com.WineOutBE.Entity.DiaryPost;
import com.WineOutBE.Entity.User;
import com.WineOutBE.graphql.InputEntities.DiaryInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class DataFetcherServiceImpl implements DataFetcherService {

    @Autowired
    UserService userService;

    public <T> T objectMapper(DataFetchingEnvironment env, String arg, Class<T> reqClass) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            String input = objectMapper.writeValueAsString(env.getArgument(arg));

            return objectMapper.readValue(input, reqClass);
        } catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public User setPost(DataFetchingEnvironment env, DiaryPost post, DiaryInput newPost) {

        post.setWinename(newPost.getWinename());
        post.setVintage(newPost.getVintage());
        post.setRegion(newPost.getRegion());
        post.setDistrict(newPost.getDistrict());
        post.setProducer(newPost.getProducer());
        post.setPercentage(newPost.getPercentage());
        post.setGrape(newPost.getGrape());

        User currentuser = userService.getUser(env.getArgument("username"));

        Collection<DiaryPost> diaryPosts = currentuser.getDiaryPost();
        diaryPosts.add(post);
        currentuser.setDiaryPost(diaryPosts);

        return currentuser;
    }
}
