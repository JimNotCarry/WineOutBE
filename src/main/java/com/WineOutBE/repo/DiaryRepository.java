package com.WineOutBE.repo;

import com.WineOutBE.entity.DiaryPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<DiaryPost, Integer> {
}
