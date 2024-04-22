package com.abacus.journalApp.service;

import com.abacus.journalApp.entity.JournalEntity;
import com.abacus.journalApp.entity.User;
import com.abacus.journalApp.repository.JournalEntryRepo;
import com.abacus.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    UserRepo userRepo;


    @Transactional
    public ResponseEntity<JournalEntity> saveUserEntry(JournalEntity journalEntity){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            User user = userRepo.findByUserName(userName);

            journalEntity.setDate(LocalDateTime.now());
            JournalEntity saved = journalEntryRepo.save(journalEntity);
            user.getJournalEntities().add(saved);

            userRepo.save(user);
            //userService.saveUser(user);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> findUserEntries(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user = userRepo.findByUserName(userName);
        List<JournalEntity> all = user.getJournalEntities();

        if (all != null && !all.isEmpty())
            return new ResponseEntity<>(all, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<JournalEntity> findEntryById(ObjectId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user = userRepo.findByUserName(userName);

        List<JournalEntity> collect = user.getJournalEntities().stream().filter(x -> x.getId().equals(id)).toList();

        if(!collect.isEmpty())
            return new ResponseEntity<>(journalEntryRepo.findById(id).get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Transactional
    public ResponseEntity<?> deleteEntryById(ObjectId id){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            User user = userRepo.findByUserName(userName);

            boolean removed = user.getJournalEntities().removeIf(x -> x.getId().equals(id));

            if(removed) {
                userRepo.save(user);
                journalEntryRepo.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            throw new RuntimeException("Error while deleting ", e);
        }
    }

    public ResponseEntity<JournalEntity> updateEntryById(ObjectId id, JournalEntity newEntity){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user = userRepo.findByUserName(userName);

        List<JournalEntity> collect = user.getJournalEntities().stream().filter(x -> x.getId().equals(id)).toList();

        if(!collect.isEmpty()){
            JournalEntity myEntity = journalEntryRepo.findById(id).get();
            myEntity.setTitle(newEntity.getTitle() != null && !newEntity.getTitle().equals("") ? newEntity.getTitle() : myEntity.getTitle());
            myEntity.setContent(newEntity.getContent() != null && !newEntity.getContent().equals("") ? newEntity.getContent() : myEntity.getContent());
            return new ResponseEntity<>(journalEntryRepo.save(myEntity),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}