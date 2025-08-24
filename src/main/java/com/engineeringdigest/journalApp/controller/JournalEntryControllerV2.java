package com.engineeringdigest.journalApp.controller;
import java.time.LocalDateTime;
import java.util.*;

import com.engineeringdigest.journalApp.Service.JournalEntryService;
import com.engineeringdigest.journalApp.entity.JournalEntryV2;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    // create obj of Service
    @Autowired
    private JournalEntryService journalEntryService;


    @PostMapping
    public JournalEntryV2 createJournalEntry(@RequestBody JournalEntryV2 myEntry){
       // user doesn't have to enter the date
        myEntry.setJournalDate(LocalDateTime.now());
        journalEntryService.createJournalEntry(myEntry);
        return myEntry;
    }

    @GetMapping
    public List<JournalEntryV2> getAllJournalEntries(){
        return journalEntryService.getAllEntries();
    }

    @GetMapping("/id/{EntryId}")
    public JournalEntryV2 getJournalEntryById(@PathVariable ObjectId EntryId){
        return journalEntryService.getEntryById(EntryId).orElse(null);
    }

    @DeleteMapping("/id/{EntryId}")
    public String deleteJournalEntryById(@PathVariable ObjectId EntryId){
        return journalEntryService.deleteEntryById(EntryId);
    }

    @PutMapping("/id/{givenId}")
    public JournalEntryV2 modifyEntryById(@PathVariable ObjectId givenId, @RequestBody JournalEntryV2 newEntry){
        // check if entry with that Id exists - if exists modify it
        JournalEntryV2 oldEntry = journalEntryService.getEntryById(givenId).orElse(null);
        // if Id exists, check if the body sent is not null and not empty then replace value or else don't change
        if(oldEntry != null){
            oldEntry.setContent((newEntry.getContent() != null && !newEntry.getContent().equals("")) ? newEntry.getContent() : oldEntry.getContent());
            oldEntry.setTitle((newEntry.getTitle() != null && !newEntry.getTitle().equals("")) ? newEntry.getTitle() : oldEntry.getTitle());
        }
        journalEntryService.createJournalEntry(oldEntry);
        return oldEntry;
    }






}
