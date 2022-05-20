package com.WineOutBE.service;

import com.WineOutBE.entity.DiaryPost;
import com.WineOutBE.entity.User;

public interface DiaryService {
    User savePost(DiaryPost post);
}
