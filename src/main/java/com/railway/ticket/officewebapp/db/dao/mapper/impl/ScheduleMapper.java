package com.railway.ticket.officewebapp.db.dao.mapper.impl;

import com.railway.ticket.officewebapp.db.Fields;
import com.railway.ticket.officewebapp.db.dao.mapper.ObjectMapper;
import com.railway.ticket.officewebapp.model.Schedule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ScheduleMapper implements ObjectMapper<Schedule> {
    @Override
    public Schedule extractFromResultSet(ResultSet resultSet) throws SQLException {

        Schedule schedule = new Schedule();
        schedule.setId(resultSet.getInt(Fields.SCHEDULE_ID));
        schedule.setDate(resultSet.getDate(Fields.SCHEDULE_DATE));
        schedule.setTrainId(resultSet.getInt(Fields.SCHEDULE_TRAIN_ID));

        return schedule;
    }

    @Override
    public Schedule toUnique(Map<String, Schedule> cache, Schedule schedule) {
        return null;
    }
}
