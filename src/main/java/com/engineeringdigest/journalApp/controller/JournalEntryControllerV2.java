package com.engineeringdigest.journalApp.controller;
import java.time.LocalDateTime;
import java.util.*;

import com.engineeringdigest.journalApp.Service.JournalEntryService;
import com.engineeringdigest.journalApp.entity.JournalEntryV2;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    // create obj of Service
    @Autowired
    private JournalEntryService journalEntryService;



    @GetMapping
    public ResponseEntity<List<JournalEntryV2>> getAllJournalEntries(){

        List<JournalEntryV2> listEntries = journalEntryService.getAllEntries();
        if(listEntries != null && !listEntries.isEmpty()){
            return new ResponseEntity<>(listEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/id/{EntryId}")
    public ResponseEntity<JournalEntryV2> getJournalEntryById(@PathVariable ObjectId EntryId){
        Optional<JournalEntryV2> journalentry = journalEntryService.getEntryById(EntryId);

        if(journalentry.isPresent()){ // there exists a value in journalentry
            ResponseEntity<JournalEntryV2> response = new ResponseEntity<>(journalentry.get(), HttpStatus.OK);
            return response;
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // given journal entry ID is not found - 404
    }

    @PostMapping
    public ResponseEntity<JournalEntryV2> createJournalEntry(@RequestBody JournalEntryV2 myEntry){

        try{
            // user doesn't have to enter the date
            myEntry.setJournalDate(LocalDateTime.now());
            journalEntryService.createJournalEntry(myEntry);
            return new ResponseEntity<JournalEntryV2>(myEntry,HttpStatus.CREATED);
        }catch( Exception e ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }


    }











    @DeleteMapping("/id/{EntryId}")
    public ResponseEntity<String> deleteJournalEntryById(@PathVariable ObjectId EntryId){
        boolean returnValue = journalEntryService.deleteEntryById(EntryId);
        if(returnValue){
            return new ResponseEntity<>("Entry is deleted",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Entry couldn't be deleted", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/id/{givenId}")
    public ResponseEntity<JournalEntryV2> modifyEntryById(@PathVariable ObjectId givenId, @RequestBody JournalEntryV2 newEntry){
        // check if entry with that Id exists - if exists modify it
        JournalEntryV2 oldEntry = journalEntryService.getEntryById(givenId).orElse(null);
        // if Id exists, check if the body sent is not null and not empty then replace value or else don't change
        if(oldEntry != null){
            oldEntry.setContent((newEntry.getContent() != null && !newEntry.getContent().equals("")) ? newEntry.getContent() : oldEntry.getContent());
            oldEntry.setTitle((newEntry.getTitle() != null && !newEntry.getTitle().equals("")) ? newEntry.getTitle() : oldEntry.getTitle());
            journalEntryService.createJournalEntry(oldEntry);
            return new ResponseEntity<JournalEntryV2>(oldEntry,HttpStatus.OK);
            // if old entry exists - modify
        }
        journalEntryService.createJournalEntry(newEntry);
        return new ResponseEntity<>(newEntry,HttpStatus.CREATED);
        // if doesn't exist - create a new entry
        // or -  return new ResponseEntity<>(HttpStatus.NOT_FOUND); - the entry requested doesn't exist for modification
    }






}
