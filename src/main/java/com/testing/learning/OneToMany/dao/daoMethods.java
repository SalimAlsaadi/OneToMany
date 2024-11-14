package com.testing.learning.OneToMany.dao;

import com.testing.learning.OneToMany.Entity.Courses;
import com.testing.learning.OneToMany.Entity.Instructor;
import com.testing.learning.OneToMany.Entity.Reviews;

import java.util.List;

public interface daoMethods {

    String postInstructor(Instructor instructor);
    String postCource(Courses courses);
    Instructor getInstructor(int id);
    Courses getCourse(int id);
    String deleteInstructor(int id);
    String deleteCourse(int id);
    List<Courses> getCoursesByInstructorID(int instructorID);
    Instructor updateInstructor(Instructor instructor);
    String updateCourse(Courses courses);
    String postReview(Reviews reviews);


}
