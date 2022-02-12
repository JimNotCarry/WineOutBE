package com.WineOutBE.Repo;

import com.WineOutBE.Entity.DiaryPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<DiaryPost, Integer> {
}
