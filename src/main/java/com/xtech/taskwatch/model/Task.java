package com.xtech.taskwatch.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String taskName;

    private String taskDescription;

    @NonNull
    private Date taskEndDate;

    public Long getId() {
        return id;
    }
    

    public Task(String taskName, String taskDescription, Date taskEndDate) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskEndDate = taskEndDate;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getTaskEndDate() {
        return taskEndDate;
    }

    public void setTaskEndDate(Date taskEndDate) {
        this.taskEndDate = taskEndDate;
    }

    public Task() {
    }

    
}
