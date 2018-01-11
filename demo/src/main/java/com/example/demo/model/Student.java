package com.example.demo.model;

import org.springframework.data.annotation.Id;

public class Student {

  @Id
  private String id;
  private String name;
  private String age;
  private String university;
  private String course;

  public String getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = String.valueOf(id);
  }

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }
  public void setAge(String age) {
    this.age = age;
  }

  public String getUniversity() {
    return university;
  }
  public void setUniversity(String university) {
    this.university = university;
  }
  public String getCourse() {
    return course;
  }
  public void setCourse(String course) {
    this.course = course;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Id: " + id);
    builder.append("Name: " + name);
    builder.append("Age: " + age);
    builder.append("University: " + university);
    builder.append("Course: " + course);
    
    return builder.toString();
  }
}
