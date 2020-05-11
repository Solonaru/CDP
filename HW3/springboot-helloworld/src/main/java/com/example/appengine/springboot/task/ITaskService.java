package com.example.appengine.springboot.task;

import java.util.List;

public interface ITaskService {

	public String findTaskNameById(Long taskId);

	public Task findTaskById(Long taskId);

	public List<Task> findAllTasks();

	public void addTask(Long taskId, String taskName);

	public void updateTaskName(Long taskId, String taskName);

	public void deleteTaskById(Long taskId);

}
