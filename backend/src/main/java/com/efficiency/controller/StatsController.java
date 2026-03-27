package com.efficiency.controller;

import com.efficiency.common.ApiResponse;
import com.efficiency.entity.Schedule;
import com.efficiency.entity.TodoTask;
import com.efficiency.service.ScheduleService;
import com.efficiency.service.TodoTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = "统计接口")
@RestController
@RequestMapping("/stats")
public class StatsController {

    private final TodoTaskService todoTaskService;
    private final ScheduleService scheduleService;

    public StatsController(TodoTaskService todoTaskService, ScheduleService scheduleService) {
        this.todoTaskService = todoTaskService;
        this.scheduleService = scheduleService;
    }

    @ApiOperation("首页图表统计")
    @GetMapping("/dashboard")
    public ApiResponse<Map<String, Object>> dashboard() {
        List<TodoTask> tasks = todoTaskService.listAll();
        List<Schedule> schedules = scheduleService.listAll();

        Map<String, Long> taskStatusMap = tasks.stream()
                .collect(Collectors.groupingBy(t -> t.getStatus() != null ? t.getStatus() : "未设置", Collectors.counting()));
        Map<String, Long> taskPriorityMap = tasks.stream()
                .collect(Collectors.groupingBy(t -> t.getPriority() != null ? t.getPriority() : "未设置", Collectors.counting()));

        Map<String, Object> data = new HashMap<>();
        data.put("todoTotal", tasks.size());
        data.put("scheduleTotal", schedules.size());
        data.put("taskStatusMap", taskStatusMap);
        data.put("taskPriorityMap", taskPriorityMap);
        return ApiResponse.success(data);
    }
}
