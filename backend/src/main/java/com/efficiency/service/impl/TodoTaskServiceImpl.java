package com.efficiency.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.efficiency.entity.TodoTask;
import com.efficiency.mapper.TodoTaskMapper;
import com.efficiency.service.TodoTaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoTaskServiceImpl implements TodoTaskService {

    private final TodoTaskMapper todoTaskMapper;

    public TodoTaskServiceImpl(TodoTaskMapper todoTaskMapper) {
        this.todoTaskMapper = todoTaskMapper;
    }

    @Override
    public List<TodoTask> listAll() {
        return todoTaskMapper.selectList(new LambdaQueryWrapper<TodoTask>().orderByDesc(TodoTask::getId));
    }

    @Override
    public void save(TodoTask todoTask) {
        todoTask.setCreateTime(LocalDateTime.now());
        todoTaskMapper.insert(todoTask);
    }

    @Override
    public void update(TodoTask todoTask) {
        todoTaskMapper.updateById(todoTask);
    }

    @Override
    public void delete(Long id) {
        todoTaskMapper.deleteById(id);
    }
}
