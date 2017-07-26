package com.gs.activiti;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ActivitiDemo {
	
	@Test
	public void testActiviti() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-activiti.xml");
		ProcessEngine processEngine = (ProcessEngine) context.getBean("processEngine");
		RepositoryService repositoryService = (RepositoryService) context.getBean("repositoryService");
		RuntimeService runtimeService = (RuntimeService) context.getBean("runtimeService");
		TaskService taskService = (TaskService) context.getBean("taskService");
		IdentityService identityServcie = (IdentityService) context.getBean("identityService");
		HistoryService historyService = (HistoryService) context.getBean("historyService");
	}
	
	@Test
	public void testDeploy() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-activiti.xml");
		// 第一步：部署流程
		RepositoryService repositoryService = (RepositoryService) context.getBean("repositoryService");
		repositoryService.createDeployment().addClasspathResource("diagrams/first_process.bpmn").deploy();
	}
	
	@Test
	public void testStartProc() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-activiti.xml");
		ProcessEngine processEngine = (ProcessEngine) context.getBean("processEngine");
		// 第二步：启动流程
		RuntimeService runtimeService = (RuntimeService) context.getBean("runtimeService");
		// 通过流程bpmn文件中指定的id获取到流程
		String procId = runtimeService.startProcessInstanceByKey("myProcess").getId(); 
	}
	
	@Test
	public void testUserTask() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-activiti.xml");
		// 第三步：获取流程定义中的任务
		TaskService taskService = (TaskService) context.getBean("taskService");
		List<Task> tasks = taskService.createTaskQuery().list();
		for (Task task : tasks) {
			System.out.println(task.getId() + task.getName());
			taskService.claim(task.getId(), "aa"); // 某个人要做某个任务
		}
	}
	
	@Test
	public void teqstTask1() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-activiti.xml");
		TaskService taskService = (TaskService) context.getBean("taskService");
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("aa").list(); // 获取指定用户的任务
		for (Task task : tasks) {
			System.out.println(task.getAssignee() + task.getId() + task.getName());
		}
	}
	
	@Test
	public void testCompeleteUserTask() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-activiti.xml");
		// 第四步：执行任务
		TaskService taskService = (TaskService) context.getBean("taskService");
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("aa").list(); // 获取指定用户的任务
		for (Task task : tasks) {
			System.out.println(task.getAssignee() + task.getId() + task.getName());
			taskService.complete(task.getId()); // 执行指定编号的任务
		}
	}
	
	@Test
	public void testManagerTask() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-activiti.xml");
		// 第三步：获取流程定义中的任务
		TaskService taskService = (TaskService) context.getBean("taskService");
		List<Task> tasks = taskService.createTaskQuery().list();
		for (Task task : tasks) {
			System.out.println(task.getId() + task.getName());
			taskService.claim(task.getId(), "bb"); // 某个人要做某个任务
		}
	}
	
	@Test
	public void testCompeleteManagerTask() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-activiti.xml");
		// 第四步：执行任务
		TaskService taskService = (TaskService) context.getBean("taskService");
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("bb").list(); // 获取指定用户的任务
		for (Task task : tasks) {
			System.out.println(task.getAssignee() + task.getId() + task.getName());
			taskService.complete(task.getId()); // 执行指定编号的任务
		}
	}

}
