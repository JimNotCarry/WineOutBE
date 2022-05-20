package com.WineOutBE.repo;

import com.WineOutBE.entity.FriendID;
import com.WineOutBE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Query("select username from User where username like %:keyword%")
    List<String> search(@Param("keyword") String keyword);

    @Query("select u from User u where u.friendID.friendid = :friendId")
    User findByFriendId(@Param("friendId") String keyword);
}
