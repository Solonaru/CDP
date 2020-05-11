package com.example.appengine.springboot.task;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TaskRowMapper implements RowMapper<Task> {

	@Override
	public Task mapRow(ResultSet rs, int rowNum) throws SQLException {

		Task task = new Task();
		task.setId(rs.getLong("ID"));
		task.setName(rs.getString("TASK_NAME"));

		return task;

	}
}
