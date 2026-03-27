package com.efficiency.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.efficiency.entity.Schedule;
import com.efficiency.mapper.ScheduleMapper;
import com.efficiency.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleMapper scheduleMapper;

    public ScheduleServiceImpl(ScheduleMapper scheduleMapper) {
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    public List<Schedule> listAll() {
        return scheduleMapper.selectList(new LambdaQueryWrapper<Schedule>().orderByDesc(Schedule::getId));
    }

    @Override
    public void save(Schedule schedule) {
        schedule.setCreateTime(LocalDateTime.now());
        scheduleMapper.insert(schedule);
    }

    @Override
    public void update(Schedule schedule) {
        scheduleMapper.updateById(schedule);
    }

    @Override
    public void delete(Long id) {
        scheduleMapper.deleteById(id);
    }
}
