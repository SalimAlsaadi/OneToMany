package com.testing.learning.OneToMany.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "comment")
    private String comments;

    public Reviews(){}

    public Reviews(String comments) {
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    @Override
    public String toString() {
        return "Reviews{" +
                "id=" + id +
                ", comments='" + comments + '\'' +
                '}';
    }
}
