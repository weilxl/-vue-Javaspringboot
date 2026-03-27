package com.efficiency.service;

import com.efficiency.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> listAll();
    void save(Schedule schedule);
    void update(Schedule schedule);
    void delete(Long id);
}
