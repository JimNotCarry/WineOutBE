package com.WineOutBE.service;

import com.WineOutBE.entity.DiaryPost;
import com.WineOutBE.entity.User;
import com.WineOutBE.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final UserRepository userRepository;


    @Override
    public User savePost(DiaryPost post) {
        return null;
    }
}
