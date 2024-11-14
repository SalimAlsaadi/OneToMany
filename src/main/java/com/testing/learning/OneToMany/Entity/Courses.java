package com.testing.learning.OneToMany.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;

@Entity
   @Table(name = "courses")
   //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")//This annotation allows bidirectional relationships to be serialized without causing infinite recursion
   public class Courses {

       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "id")
       private int id;

       @Column(name = "course_name")
       private String courseName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Reviews> reviews;

       @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
                             CascadeType.PERSIST, CascadeType.REFRESH})
       @JoinColumn(name = "instructor_id")
       private Instructor instructor;


       public Courses(){}

       public Courses(int id, String courseName) {
           this.id = id;
           this.courseName = courseName;
       }

       public int getId() {
           return id;
       }

       public void setId(int id) {
           this.id = id;
       }

       public String getCourseName() {
           return courseName;
       }

       public void setCourseName(String courseName) {
           this.courseName = courseName;
       }
    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

       public Instructor getInstructor() {
           return instructor;
       }

       public void setInstructor(Instructor instructor) {
           this.instructor = instructor;
       }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", reviews=" + reviews +
                ", instructor=" + instructor +
                '}';
    }
}


