package com.testing.learning.OneToMany.dao;

import com.testing.learning.OneToMany.Entity.Courses;
import com.testing.learning.OneToMany.Entity.Instructor;
import com.testing.learning.OneToMany.Entity.Reviews;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class DaoService implements daoMethods{

    private final EntityManager entityManager;

    @Autowired
    public DaoService(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public String postInstructor(Instructor instructor) {
        entityManager.persist(instructor);
        return "Instructor added successfully";
    }


    @Override
    @Transactional
    public String postCource(Courses courses) {
        Instructor instructor=entityManager.find(Instructor.class,courses.getInstructor().getId());
        if(instructor!=null){
            courses.setInstructor(instructor);
            entityManager.persist(courses);

            return "Course added successfully";
        }
        else {
            return "instructor not found";
        }


    }

    @Override
    @Transactional
    public Instructor getInstructor(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public Courses getCourse(int id) {
        Courses courses=entityManager.find(Courses.class, id);
        Instructor instructor= courses.getInstructor();
        instructor.setCourses(null);
        courses.setInstructor(instructor);
        return courses;
    }



    @Override
    @Transactional
    public String deleteInstructor(int id) {
        // Find the instructor by id
        Instructor instructor = entityManager.find(Instructor.class, id);

        if (instructor != null) {
            // Get all courses associated with this instructor
            List<Courses> courses = getCoursesByInstructorID(instructor.getId());

            // Set the instructor of each course to null
            for (Courses tempCourse : courses) {
                tempCourse.setInstructor(null);
                entityManager.merge(tempCourse);  // Save the updated course
            }

            // Now that all courses have been detached, remove the instructor
            entityManager.remove(instructor);

            return "Instructor and associated instructor references removed successfully.";
        } else {
            return "Instructor not found.";
        }
    }


    @Override
    @Transactional
    public String deleteCourse(int id) {
        Courses course=entityManager.find(Courses.class,id);
        entityManager.remove(course);
        return "Done";
    }

    @Override
    @Transactional
    public List<Courses> getCoursesByInstructorID(int instructorID) {
        TypedQuery<Courses> courses = entityManager.createQuery(
                "from Courses where instructor.id = :data", Courses.class);  // Use 'instructor.id' to refer to the instructor's ID
        courses.setParameter("data", instructorID);

        return courses.getResultList();
    }

    @Override
    @Transactional
    public Instructor updateInstructor(Instructor instructor) {

        return entityManager.merge(instructor);

    }


//    @Override
//    @Transactional
//    public String updateCourse(Courses courses) {
//        Instructor instructor=getInstructor(courses.getInstructor().getId());
//        Reviews reviews= entityManager.find(Reviews.class,courses.getReviews());
//        System.out.println(reviews.toString());
//        if(instructor!=null && reviews.getComments()!=null){
//            courses.setInstructor(instructor);
//            courses.setReviews((List<Reviews>) reviews);
//            entityManager.merge(courses);
//            return "Instructor for this course: "+ instructor.getFirstName();
//        }
//        else {
//            return "Instructor not found or reviews not found";
//        }
//    }

    @Override
    @Transactional
    public String updateCourse(Courses courses) {
        // Find the instructor for the course
        Instructor instructor = getInstructor(courses.getInstructor().getId());

        // Check if the instructor exists
        if (instructor == null) {
            return "Instructor not found";
        }

        // Process the list of reviews
        List<Reviews> existingReviews = courses.getReviews();
        if (existingReviews == null || existingReviews.isEmpty()) {
            return "Reviews not found";
        }

        // Fetch the reviews from the database and verify each one
        List<Reviews> validReviews = new ArrayList<>();
        for (Reviews review : existingReviews) {
            Reviews foundReview = entityManager.find(Reviews.class, review.getId());
            if (foundReview != null && foundReview.getComments() != null) {
                validReviews.add(foundReview);  // Add valid reviews
            } else {
                return "One or more reviews not found";
            }
        }

        // Set the instructor and the valid reviews for the course
        courses.setInstructor(instructor);
        courses.setReviews(validReviews);

        // Update the course in the database
        entityManager.merge(courses);

        return "Instructor for this course: " + instructor.getFirstName();
    }


    @Override
    @Transactional
    public String postReview(Reviews reviews) {
        entityManager.persist(reviews);
        return "Done";
    }

}
