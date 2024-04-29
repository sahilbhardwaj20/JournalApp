package com.abacus.journalApp.repository;

import com.abacus.journalApp.entity.ConfigEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ConfigRepo extends MongoRepository<ConfigEntity, ObjectId> {

}
