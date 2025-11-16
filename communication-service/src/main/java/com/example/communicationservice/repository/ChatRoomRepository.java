package com.example.communicationservice.repository;

import com.example.communicationservice.entity.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ChatRoomRepository extends MongoRepository<ChatRoom, String> {

    @Query("{ 'memberCodes': { $all: [?0, ?1] } }")
    boolean existsByMemberCodes(String memberCode1, String memberCode2);

}
