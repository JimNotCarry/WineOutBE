package com.WineOutBE.graphql.InputEntities;

import com.WineOutBE.Entity.DiaryPost;
import com.WineOutBE.Entity.DiarySettings;
import com.WineOutBE.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInput {

    private String firstname;

    private String lastname;

    private String username;

    private String password;

    private Collection<Role> roles = new ArrayList<>();

    private DiarySettings diarySettings;

    private Collection<DiaryPost> diaryPost = new ArrayList<>();
}
