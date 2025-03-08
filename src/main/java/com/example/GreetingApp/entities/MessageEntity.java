package com.example.GreetingApp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "greetings")
public class MessageEntity {

    String message;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public MessageEntity(){
    }

    public MessageEntity(String message) {
        this.message = message;
        id = null;
    }

    public String getMessage() {
        return message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}