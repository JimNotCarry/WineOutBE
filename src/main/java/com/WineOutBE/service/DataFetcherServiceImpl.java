package com.WineOutBE.service;

import com.WineOutBE.entity.DiaryPost;
import com.WineOutBE.entity.User;
import com.WineOutBE.graphql.inputEntities.DiaryInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;

@Service
public class DataFetcherServiceImpl implements DataFetcherService {

    @Autowired
    UserService userService;

    public <T> T objectMapper(DataFetchingEnvironment env, String arg, Class<T> reqClass) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.findAndRegisterModules();

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
        post.setOccasionDate(newPost.getOccasionDate());
        post.setPostDate(ZonedDateTime.now());

        User currentuser = userService.getUser(env.getArgument("username"));

        Collection<DiaryPost> diaryPosts = currentuser.getDiaryPost();
        diaryPosts.add(post);
        currentuser.setDiaryPost(diaryPosts);

        return currentuser;
    }
}
