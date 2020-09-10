package com.xtech.taskwatch.model;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {
    Optional<Task> findByTaskName(String taskName);
    Iterable<Task> findByTaskDescriptionContainsAndOwner(String taskDescription, User owner);
    Iterable<Task> findByOwner(User owner);
}
