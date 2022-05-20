package com.WineOutBE.repo;

import com.WineOutBE.entity.FriendRequest;
import com.WineOutBE.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    @Query(value = "from FriendRequest where reciever = :recieverFriendId AND status = 0")
    List<FriendRequest> checkFriendRequest(@Param("recieverFriendId") String recieverFriendId);

    @Query(value = "from FriendRequest where reciever = :recieverFriendId AND requester = :requesterFriendId AND status = 0")
    FriendRequest checkFriendRequestSingle(@Param("recieverFriendId") String recieverFriendId,@Param("requesterFriendId") String requesterFriendId);

    @Query(value = "from FriendRequest where reciever = :recieverFriendId AND requester = :requesterFriendId AND status = 0")
    boolean requestAlreadyExist(@Param("recieverFriendId") String recieverFriendId,@Param("requesterFriendId") String requesterFriendId);
}
