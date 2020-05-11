package com.example.appengine.springboot.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService implements ITaskService {

	@Autowired
	TaskDAO taskDAO;

	public String findTaskNameById(Long taskId) {
		return taskDAO.getTaskNameById(taskId);
	}

	public Task findTaskById(Long taskId) {
		return taskDAO.getTaskById(taskId);
	}

	public List<Task> findAllTasks() {
		return taskDAO.getTasks();
	}

	public void addTask(Long taskId, String taskName) {
		taskDAO.insertTask(taskId, taskName);
	}

	public void updateTaskName(Long taskId, String taskName) {
		taskDAO.updateTaskName(taskId, taskName);
	}

	public void deleteTaskById(Long taskId) {
		taskDAO.deleteTaskById(taskId);
	}

}
