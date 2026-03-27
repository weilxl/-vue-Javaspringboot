package com.efficiency.service;

import com.efficiency.entity.TodoTask;

import java.util.List;

public interface TodoTaskService {
    List<TodoTask> listAll();
    void save(TodoTask todoTask);
    void update(TodoTask todoTask);
    void delete(Long id);
}
