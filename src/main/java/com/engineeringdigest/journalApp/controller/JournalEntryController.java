package com.engineeringdigest.journalApp.controller;
import java.util.*;
import com.engineeringdigest.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

@RestController // creates a bean and also serves endpoints
@RequestMapping("/journal")
public class JournalEntryController {

    // Initially there does not exist a database to store all journal entries
    private Map<Long, JournalEntry> journalEntries = new HashMap<>();
    // hash map = JournalId,Entry

    // we create an endpoint that will retrieve all journal entries

    @GetMapping //read operation - http://localhost:8080/journal + Get
    public List<JournalEntry> getAllJournalEntries(){
        // anonymous arraylist creation with constructor arguments as collection provided by journalEntries.values()
            return new ArrayList<>(journalEntries.values());
    }

    @PostMapping // create operation = http://localhost:8080/journal + post
    public boolean createJournalEntry(@RequestBody JournalEntry myEntry){
        // the @RequestBody is responsible for converting the incoming JSON object to a java object
        // add the object in the list
        journalEntries.put(myEntry.getJournalId(),myEntry);
        return true; // to indicate the journal entry has been successfully added
        // if a database exists (three tier architecture - add the entry in db)

    }

    // get with query params - gets the query params value to retrieve
    // additional end point strings must be given since a get mapping of /journal already exists
    @GetMapping("/id/{myId}") // myId is a path variable -
    public JournalEntry getJournalEntryById(@PathVariable Long myId){
        return journalEntries.get(myId);
        // the Long id - if from map ds
    }

    // delete a specific entry by Id - returns true if deleted entry or not
    // can use @GetMapping also with different path - since one get mapping already exists
    @DeleteMapping("/id/{myId}")
    public JournalEntry deleteEntryById(@PathVariable Long myId){

        return journalEntries.remove(myId);
        // the remove method of hashmap is responsible for deleting that specific key-value and returning that entry

    }

    @PutMapping("/id/{myId}")
    public String modifyEntryById(@PathVariable Long myId, @RequestBody JournalEntry myEntry){
        journalEntries.put(myId,myEntry);
        // override the value
        return "Entry is modified";

    }

}
