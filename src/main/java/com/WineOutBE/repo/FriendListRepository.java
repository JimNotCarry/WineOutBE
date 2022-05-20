package com.WineOutBE.repo;

import com.WineOutBE.entity.FriendList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendListRepository extends JpaRepository<FriendList, Long> {
}
