package com.example.appengine.springboot.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private ITaskService taskService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Task getTaskById(@PathVariable("id") Long taskId) {		
		return taskService.findTaskById(taskId);
	}
	
	@RequestMapping(value = "/name/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String getTaskName(@PathVariable("id") Long taskId) {
		return taskService.findTaskNameById(taskId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Task> getAllTask() {
		return taskService.findAllTasks();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addTask(@RequestBody Task task) {
		taskService.addTask(task.getId(), task.getName());
		return new ResponseEntity<>("Successfully added!", HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateTaskName(@RequestBody Task task) {
		taskService.updateTaskName(task.getId(), task.getName());
		return new ResponseEntity<>("Successfully updated!", HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTaskById(@PathVariable("id") Long taskId) {
		taskService.deleteTaskById(taskId);
		return new ResponseEntity<>("Successfully deleted!", HttpStatus.OK);
	}

}
