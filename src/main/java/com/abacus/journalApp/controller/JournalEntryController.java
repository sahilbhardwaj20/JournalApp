package com.abacus.journalApp.controller;


import com.abacus.journalApp.entity.JournalEntity;
import com.abacus.journalApp.service.JournalEntryService;
import com.abacus.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUserEntries(){
        return journalEntryService.findUserEntries();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntity> getEntryById(@PathVariable ObjectId id){
       return journalEntryService.findEntryById(id);
    }

    @PostMapping
    public ResponseEntity<JournalEntity> createUserEntry(@RequestBody JournalEntity journalEntity){
        return journalEntryService.saveUserEntry(journalEntity);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteUserEntry(@PathVariable ObjectId id){
        return journalEntryService.deleteEntryById(id);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<JournalEntity> updateUserEntry(
            @PathVariable ObjectId id,
            @RequestBody JournalEntity journalEntity){
        return journalEntryService.updateEntryById(id,journalEntity);
    }

}