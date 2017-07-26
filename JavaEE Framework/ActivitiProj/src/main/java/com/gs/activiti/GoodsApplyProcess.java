package com.gs.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gs.bean.User;

public class GoodsApplyProcess {
	
	public void deployGoodsApplyProcess() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-activiti.xml");
		// 第一步：部署流程
		RepositoryService repositoryService = (RepositoryService) context.getBean("repositoryService");
		repositoryService.createDeployment().addClasspathResource("diagrams/goods_apply.bpmn").deploy();
	}
	
	public void startGoodsApply(User user) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-activiti.xml");
		RuntimeService runtimeService = (RuntimeService) context.getBean("runtimeService");
		runtimeService.startProcessInstanceByKey("goodsApplyProcess");
		TaskService taskService = (TaskService) context.getBean("taskService");
		List<Task> tasks = taskService.createTaskQuery().list();
		for (Task task : tasks) {
			System.out.println("当前任务：" + task.getName());
			// taskService.claim(task.getId(), user.getName());// 不能继续指派给其他用户，如果指派给其他用户，则抛异常
			taskService.setAssignee(task.getId(), user.getName()); // 可以继续指派给其他用户
			taskService.complete(task.getId()); // 提交任务
		}
	}
	
	public void checkApply(User user) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-activiti.xml");
		TaskService taskService = (TaskService) context.getBean("taskService");
		List<Task> tasks = taskService.createTaskQuery().list();
		for (Task task : tasks) {
			System.out.println("当前任务：" + task.getName());
			taskService.setAssignee(task.getId(), user.getName());
			// 通过自己的service去获取当前用户提交的申请表，如果申请表数量小于10，则通过，否则不通过
			int count = 20;
			if (count <= 10) {
				taskService.complete(task.getId()); // 同意申请
			} else {
				// 不同意申请
				System.out.println("test");
				Map<String, Object> variables = new HashMap<String, Object>(0);
				variables.put("aa", "test");
				taskService.complete(task.getParentTaskId(), variables);
			}
		}
	}
	
	@Test
	public void testProcess() {
		deployGoodsApplyProcess();
	}
	
	@Test
	public void testApply() {
		User user = new User();
		user.setName("test@126.com");
		startGoodsApply(user);
	}
	
	@Test
	public void testCheck() {
		User user = new User();
		user.setName("后勤主任");
		checkApply(user);
	}
}
