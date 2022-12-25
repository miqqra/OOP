package ru.nsu.krasnikov;

import java.util.List;

public class JsonNotebookInteraction {
    private int notesNumber;

    public JsonNotebookInteraction(){

    }

    public boolean add(String time, String topic, String content){
        return false;
    }

    public boolean add(String topic, String content){
        return false;
    }

    public boolean remove(String topic){
        return false;
    }

    public boolean remove(String time, String topic){
        return false;
    }

    public List<String> show(){
        return null;
    }

    public List<String> show(String dateFrom, String dateTo, String[] args){
        return null;
    }
}
