package com.efficiency.controller;

import com.efficiency.common.ApiResponse;
import com.efficiency.entity.TodoTask;
import com.efficiency.service.TodoTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "待办任务管理")
@RestController
@RequestMapping("/todo-task")
public class TodoTaskController {

    private final TodoTaskService todoTaskService;

    public TodoTaskController(TodoTaskService todoTaskService) {
        this.todoTaskService = todoTaskService;
    }

    @ApiOperation("查询待办任务列表")
    @GetMapping("/list")
    public ApiResponse<List<TodoTask>> list() {
        return ApiResponse.success(todoTaskService.listAll());
    }

    @ApiOperation("新增待办任务")
    @PostMapping("/add")
    public ApiResponse<Object> add(@RequestBody TodoTask todoTask) {
        todoTaskService.save(todoTask);
        return ApiResponse.success("新增成功", null);
    }

    @ApiOperation("编辑待办任务")
    @PutMapping("/update")
    public ApiResponse<Object> update(@RequestBody TodoTask todoTask) {
        todoTaskService.update(todoTask);
        return ApiResponse.success("更新成功", null);
    }

    @ApiOperation("删除待办任务")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Object> delete(@PathVariable Long id) {
        todoTaskService.delete(id);
        return ApiResponse.success("删除成功", null);
    }
}
