package com.engineeringdigest.journalApp.entity;

public class JournalEntry {

    private long journalId;
    private String title;
    private String content;
    private String journalDate; // metadata

    // concept of encapsulation
    public long getJournalId(){
        return journalId;
    }
    public void setJournalId(long journalId){
        this.journalId = journalId;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }



    public String getJournalDate() {
        return journalDate;
    }

    public void setJournalDate(String journalDate) {
        this.journalDate = journalDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
