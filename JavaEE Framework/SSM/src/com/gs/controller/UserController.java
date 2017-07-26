package com.gs.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gs.bean.User;
import com.gs.service.UserService;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private UserService userService;
	
	@Resource
	private RepositoryService repositoryService;
	
	@Resource
	private RuntimeService runtimeService;
	
	@Resource 
	private TaskService taskService;
	
	@ResponseBody
	@RequestMapping("all")
	public List<User> queryAll() {
		logger.info("查询所有用户");
		Subject subject = SecurityUtils.getSubject();
		subject.login(new UsernamePasswordToken("bcd@qq.com", "123"));
		System.out.println(subject.getPrincipal());
		return userService.queryAll();
	}
	
	@ResponseBody
	@RequestMapping("add") 
	public User add() {
		User u = new User();
		u.setName("test");
		u.setEmail("test@126.com");
		u.setPwd("123");
		userService.add(u);
		return u;
	}
	
	@RequestMapping("login")
	public ModelAndView login(User user, HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		subject.login(new UsernamePasswordToken(user.getEmail(), user.getPwd()));
		session.setAttribute("user", user);
		ModelAndView mav = new ModelAndView();
		if (subject.hasRole("admin") || subject.hasRole("account")) {
			// List<Task> tasks = taskService.createTaskQuery().taskAssignee(user.getEmail()).list();
			//List<Task> tasks = taskService.createTaskQuery().taskCandidateUser(user.getEmail()).list(); // 根据候选人来获取任务
			List<Task> tasks = null;
			if (subject.hasRole("admin")) {
				tasks = taskService.createTaskQuery().taskCandidateGroup("admin").list();
			} else if (subject.hasRole("account")) {
				tasks = taskService.createTaskQuery().taskCandidateGroup("account").list();
			}
			
			mav.setViewName("admin_home");
			if (tasks != null && tasks.size() > 0) {
				Task currentTask = tasks.get(0);
				String str = (String) taskService.getVariable(currentTask.getId(), "des");
				mav.addObject("tasks", currentTask.getName());
				mav.addObject("taskId", currentTask.getId());
				mav.addObject("des", str);
			}
		} else if (subject.hasRole("customer")) {
			mav.setViewName("home");
		} else {
			mav.setViewName("error");
		}
		return mav;
	}
	
	@RequestMapping("deploy") 
	public void deploy(String procName) {
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("activiti_diagrams/" + procName +".bpmn").deploy();
		System.out.println(deployment.getId());
	}
	
	@RequestMapping("del") 
	public void del() {
		// runtimeService.deleteProcessInstance("50005", "delete"); // 删除流程的任务
		repositoryService.deleteDeployment("50001");
	}
	
	@RequestMapping("leave") 
	public void leave(HttpSession session) {
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("leave_process");
		System.out.println(pi.getId());
		List<Task> tasks = taskService.createTaskQuery().list();
		Task currentTask = tasks.get(0);
		User currentUser = (User) session.getAttribute("user");
		taskService.setAssignee(currentTask.getId(), currentUser.getEmail());
		Map<String, Object> variables = new HashMap<String ,Object>();
		variables.put("des", "我要请假");
		taskService.complete(currentTask.getId(), variables);
		// 到了下一个任务， 应该在此处指派任务由谁来处理
		// 重新获取当前任务
		// taskService.setAssignee(taskService.createTaskQuery().list().get(0).getId(), "abc@126.com");
	}
	
	@RequestMapping("check") 
	public void check(HttpSession session) {
		Task task = taskService.createTaskQuery().list().get(0);
		taskService.complete(task.getId());
	}
	
	@RequestMapping("apply")
	public void apply(HttpSession session) {
		Subject subject = SecurityUtils.getSubject();
		if (subject.hasRole("customer")) {
			runtimeService.startProcessInstanceByKey("goods_apply_process");
			Task task = taskService.createTaskQuery().list().get(0);
			Map<String, Object> vars = new HashMap<String, Object>();
			vars.put("money", 600);
			vars.put("des", "买一个篮球");
			taskService.complete(task.getId(), vars);
			System.out.println("aaaa");
		}
	}
	
	@RequestMapping("check_apply")
	public void checkApply() {
		Task task = taskService.createTaskQuery().list().get(0);
		taskService.complete(task.getId());
	}
	
	@RequestMapping("leave_1")
	public void leave1() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.hasRole("customer") || subject.hasRole("acc")) {
			runtimeService.startProcessInstanceByKey("leave_proc");
			Task task = taskService.createTaskQuery().list().get(0);
			Map<String, Object> vars = new HashMap<String, Object>();
			vars.put("days", 4);
			vars.put("des", "我要请假");
			taskService.complete(task.getId(), vars);
		}
	}
	
	@RequestMapping("leave1_check")
	public void checkLeave1() {
		Task task = taskService.createTaskQuery().list().get(0);
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("days", 4);
		taskService.complete(task.getId(), vars);
	}
	
	@RequestMapping("view_proc")
	public void viewProc(HttpServletResponse resp) throws IOException {
		List<String> names = repositoryService.getDeploymentResourceNames("30039");
		  String imageName = null;
		  for (String name : names) {
		   if(name.indexOf(".png")>=0){
		    imageName = name;
		   }
		  }
		  System.out.println("=====================================" + imageName);
		 InputStream  in = repositoryService.getResourceAsStream("30039", imageName);
		 /**
		 ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                 .processDefinitionKey("expense")
                 .singleResult();

		String diagramResourceName = processDefinition.getDiagramResourceName();
		InputStream imageStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), diagramResourceName);
		 */
		 OutputStream out = resp.getOutputStream();
         byte[] b = new byte[1024];
         for (int len = -1; (len= in.read(b))!=-1; ) {
             out.write(b, 0, len);
         }
         out.close();
         in.close();
	}
	
	@RequestMapping("view_proc1")
	public void viewProc1(HttpServletResponse resp) throws IOException {
		List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().processDefinitionKey("leave_proc").list();
        //得到流程执行对象
        List<Execution> executions = runtimeService.createExecutionQuery().processInstanceId(processInstances.get(0).getId()).list();
        //得到正在执行的Activity的Id
        List<String> activityIds = new ArrayList<String>();
        for (Execution exe : executions) {
            List<String> ids = runtimeService.getActiveActivityIds(exe.getId()); // 获取正在执行的任务
            activityIds.addAll(ids);
        }
        
        InputStream in = new DefaultProcessDiagramGenerator().generateDiagram(repositoryService.getBpmnModel("leave_proc:1:30042"), "png",
                activityIds);
        OutputStream out = resp.getOutputStream();
        byte[] b = new byte[1024];
        for (int len = -1; (len= in.read(b))!=-1;) {
            out.write(b, 0, len);
        }
        out.close();
        in.close();
	}

 }
