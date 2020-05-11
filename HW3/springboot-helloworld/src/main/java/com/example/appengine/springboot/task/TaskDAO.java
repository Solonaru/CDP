package com.example.appengine.springboot.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;

	public String getTaskNameById(Long taskId) {
		return jdbcTemplate.queryForObject("SELECT TASK_NAME FROM TASK WHERE ID = ?", new Object[] { taskId },
				String.class);
	}

	public Task getTaskById(Long taskId) {
		return jdbcTemplate.queryForObject("SELECT * FROM TASK WHERE ID = ?", new Object[] { taskId },
				new TaskRowMapper());
	}

	public List<Task> getTasks() {
		return jdbcTemplate.query("SELECT * FROM TASK", new TaskRowMapper());
	}

	public void insertTask(Long taskId, String taskName) {
		jdbcTemplate.update("INSERT INTO TASK (ID, TASK_NAME) VALUES (?, ?)", taskId, taskName);
	}

	public void updateTaskName(Long taskId, String taskName) {
		jdbcTemplate.update("UPDATE TASK SET TASK_NAME = ? WHERE ID = ?", taskId, taskName);
	}

	public void deleteTaskById(Long taskId) {
		jdbcTemplate.update("DELETE FROM TASK WHERE ID = ?", taskId);
	}
}
