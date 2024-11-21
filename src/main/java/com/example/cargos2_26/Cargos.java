package com.example.cargos2_26;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cargos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String content;
    private String sending_city;
    private String sending_date;
    private String arrival_city;
    private String arrival_date;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }

    public String getSending_city() { return sending_city; }

    public void setSending_city (String sending_city) { this.sending_city = sending_city; }

    public String getSending_date() { return sending_date; }

    public void setSending_date(String sending_date) { this.sending_date = sending_date; }

    public String getArrival_city() { return arrival_city; }

    public void setArrival_city (String arrival_city) { this.arrival_city = arrival_city; }

    public String getArrival_date() { return arrival_date; }

    public void setArrival_date(String arrival_date) { this.arrival_date = arrival_date; }


    @Override
    public String toString() {
        return "theatre [id=" + id + ", name=" + name + ", content=" + content +
                ", sending_city=" + sending_city + ", sending_date=" + sending_date + ", arrival_city" + arrival_city +
                ", arrival_date" + arrival_date + "]";
    }
}