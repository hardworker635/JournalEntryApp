package com.engineeringdigest.journalApp.Service;

import com.engineeringdigest.journalApp.entity.JournalEntryV2;
import com.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    // ask for bean of repository - through field dependency injection
    @Autowired
    private JournalEntryRepository journalentryrepo;

    public void createJournalEntry(JournalEntryV2 journalentry){
        journalentryrepo.save(journalentry);
    }

    public List<JournalEntryV2> getAllEntries() {
        return journalentryrepo.findAll();
    }

    public Optional<JournalEntryV2> getEntryById(ObjectId entryId) {
        return journalentryrepo.findById(entryId);
        // findById returns of Optional type - if not present provides a way of handling it
        // optional -
    }

    public boolean deleteEntryById(ObjectId id) {
        if(journalentryrepo.findById(id).isPresent()){
            journalentryrepo.deleteById(id);
            return true;
        }
        return false;
    }

    public void modifyEntryById(ObjectId givenId){
        // we can just save - it gets modified
    }
}

