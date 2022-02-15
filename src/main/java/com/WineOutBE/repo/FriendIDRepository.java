package com.WineOutBE.repo;

import com.WineOutBE.entity.FriendID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendIDRepository extends JpaRepository<FriendID, String> {
}
