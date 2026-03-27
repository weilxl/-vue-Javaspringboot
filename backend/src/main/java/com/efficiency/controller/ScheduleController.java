package com.efficiency.controller;

import com.efficiency.common.ApiResponse;
import com.efficiency.entity.Schedule;
import com.efficiency.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "日程安排管理")
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @ApiOperation("查询日程安排列表")
    @GetMapping("/list")
    public ApiResponse<List<Schedule>> list() {
        return ApiResponse.success(scheduleService.listAll());
    }

    @ApiOperation("新增日程安排")
    @PostMapping("/add")
    public ApiResponse<Object> add(@RequestBody Schedule schedule) {
        scheduleService.save(schedule);
        return ApiResponse.success("新增成功", null);
    }

    @ApiOperation("编辑日程安排")
    @PutMapping("/update")
    public ApiResponse<Object> update(@RequestBody Schedule schedule) {
        scheduleService.update(schedule);
        return ApiResponse.success("更新成功", null);
    }

    @ApiOperation("删除日程安排")
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Object> delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return ApiResponse.success("删除成功", null);
    }
}
