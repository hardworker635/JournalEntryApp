package com.engineeringdigest.journalApp.entity;

import org.bson.types.ObjectId;
import org.springframework.cglib.core.Local;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "journal_Entries")
public class JournalEntryV2 {

    @Id
    private ObjectId journalId;

    private String title;
    private String content;
    private LocalDateTime journalDate; // metadata

    public JournalEntryV2(){
        // empty constructor
    }
    // constructor with arguments
    public JournalEntryV2(ObjectId journalId, String title, String content, LocalDateTime journalDate){
        this.journalId = journalId;
        this.title = title;
        this.content = content;
        this.journalDate = journalDate;
    }

    // concept of encapsulation
    public ObjectId getJournalId(){
        return journalId;
    }
    public void setJournalId(ObjectId journalId){
        this.journalId = journalId;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }



    public LocalDateTime getJournalDate() {
        return journalDate;
    }

    public void setJournalDate(LocalDateTime journalDate) {
        this.journalDate = journalDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
