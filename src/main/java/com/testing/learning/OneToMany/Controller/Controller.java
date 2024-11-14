package com.testing.learning.OneToMany.Controller;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.testing.learning.OneToMany.Entity.Courses;
import com.testing.learning.OneToMany.Entity.Instructor;
import com.testing.learning.OneToMany.Entity.Reviews;
import com.testing.learning.OneToMany.dao.DaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructorWithCourcese")
public class Controller {

    public DaoService daoService;

    public Controller(){}

    @Autowired
    public Controller(DaoService daoService){
        this.daoService=daoService;
    }

   @PostMapping("/addInctructor")
    public String postInstructor(@RequestBody Instructor instructor){
            return daoService.postInstructor(instructor);

   }

   @PostMapping("/addCourse")
    public String postCourse(@RequestBody Courses courses){
       return daoService.postCource(courses);
   }

   @PostMapping("/postReview")
   public String postReview(@RequestBody Reviews reviews){
        return daoService.postReview(reviews);
   }

   @GetMapping("/getInstructorWithCourses/{id}")
    public Instructor getInstructor(@PathVariable int id){
        return daoService.getInstructor(id);
   }

   @GetMapping("/getCourses/{id}")
    public Courses getCourse(@PathVariable int id){
       return daoService.getCourse(id);
   }

   @GetMapping("/getCoursesByInstructorID/{instructorID}")
   public List<Courses> getCoursesByInstructorID(@PathVariable int instructorID){
        return daoService.getCoursesByInstructorID(instructorID);
   }

   @DeleteMapping("/deleteInstructor/{id}")
    public String deleteInstructor(@PathVariable int id){
        return daoService.deleteInstructor(id);
   }


   @DeleteMapping("/deleteCourse/{id}")
    public String deleteCourse(@PathVariable int id){
        return daoService.deleteCourse(id);
   }

   @PutMapping("/updateInstructor")
    public Instructor updateInstructor(@RequestBody Instructor instructor){
        return daoService.updateInstructor(instructor);
   }

   @PutMapping("/updateCourse")
    public String updateCourse(@RequestBody Courses courses){
       return daoService.updateCourse(courses);
   }
}
