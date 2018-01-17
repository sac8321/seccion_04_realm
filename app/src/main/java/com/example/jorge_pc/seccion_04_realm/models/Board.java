package com.example.jorge_pc.seccion_04_realm.models;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by JORGE-PC on 16/01/2018.
 */

public class Board extends RealmObject {

    @PrimaryKey
    private int id;
    @Required
    private String title;
    @Required
    private Date createdAt;

    //Esto es para crear la relacion entre tablas para la DB
    private RealmList<Note> notes;

    public Board(String title){
        this.id=0;
        this.title=title;
        this.createdAt=new Date();
        this.notes=new RealmList<Note>();

    }

    public int getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public RealmList<Note> getNotes() {
        return notes;
    }

    
}