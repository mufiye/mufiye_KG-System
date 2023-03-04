package com.mufiye.dao;

import com.mufiye.domain.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

// 负责User的crud
@Repository
public interface UserDao extends Neo4jRepository<User,Long> {

   @Override
   User save(User user);


}
