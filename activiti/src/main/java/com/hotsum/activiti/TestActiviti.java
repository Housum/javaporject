package com.hotsum.activiti;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.logging.Log;
import org.junit.Before;
import org.junit.Test;

public class TestActiviti {
	
	ProcessEngine processEngine=null;

	@Before
	public void init(){
		processEngine=ProcessEngines.getDefaultProcessEngine();
	}
	
	@Test
	public void mainClass(){
		RuntimeService runtimeService = processEngine.getRuntimeService();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		TaskService taskService = processEngine.getTaskService();
		ManagementService managementService = processEngine.getManagementService();
		IdentityService identityService = processEngine.getIdentityService();
		HistoryService historyService = processEngine.getHistoryService();
		FormService formService = processEngine.getFormService();
	}
	
	@Test
	public void deployed(){
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment()
		  .addClasspathResource("act/MyProcess.bpmn")
		  .deploy();
		System.out.println("Number of process definitions: " + repositoryService.createProcessDefinitionQuery().count());
	}
	
	@Test
	public void startProcess(){
		
		deployed();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("username", "hotusm");
		variables.put("startDate",new Date());
		variables.put("endDate",new Date());
		variables.put("reason", "father is tired");

		RuntimeService runtimeService = processEngine.getRuntimeService();
		//启动实例
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess", variables);
		System.out.println("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());
	}
	
	@Test
	public void completeTask(){
		startProcess();
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("departmentManager").list();
		for (Task task : tasks) {
		  System.out.println("Task available: " + task.getName());
		  String description=task.getDescription();
		  //这里是document中的描述信息   一般都会包含一些的
		  System.out.println("description: " + description);
		 // Task task = tasks.get(0);

		  Map<String, Object> taskVariables = new HashMap<String, Object>();
		  taskVariables.put("vacationApproved", "false");
		  taskVariables.put("managerMotivation", "We have a tight deadline!");
		  taskService.complete(task.getId(), taskVariables);
		}

		 //上面是第一个task的  下面是第二个task
		List<Task> secondTasks = taskService.createTaskQuery().taskCandidateGroup("manager1").list();
		for(Task task:secondTasks){
			System.out.println(task.getName());
		}
		
	}
//	
//	public static void main(String[] args) {
//		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//		RepositoryService repositoryService = processEngine.getRepositoryService();
//		repositoryService.createDeployment().addClasspathResource("act/financialReport.bpmn").deploy();
//		RuntimeService runtimeService = processEngine.getRuntimeService();
//		ProcessInstance instance = runtimeService.startProcessInstanceByKey("financialReport");
//		System.out.println("启动流程: proId:"+instance.getActivityId());
//		TaskService taskService = processEngine.getTaskService();
//		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
//		
//		for(Task task:tasks){
//			System.out.println("任务: "+task.getName());
//			//通过这个进行分配任务
//			taskService.claim(task.getId(), "hotusm");
//		}
//		tasks = taskService.createTaskQuery().taskAssignee("hotusm").list();
//	    for (Task task : tasks) {
//	    	//因为在上面就只分配了一个任务
//	      System.out.println("hotusm的任务: " + task.getName());
//	      //hotusm完任务
//	     taskService.complete(task.getId());
//	    }
//	    System.out.println("hotusm任务: "
//	            + taskService.createTaskQuery().taskAssignee("hotusm").count());
//
//	    tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
//	    for (Task task : tasks) {
//	      System.out.println("Following task is available for management group: " + task.getName());
//	    //  taskService.claim(task.getId(), "kermit");
//	    }
//
//	    for (Task task : tasks) {
//	      taskService.complete(task.getId());
//	    }
//	}
	
	public static class Node<E> extends ArrayList<E> implements Comparable<E>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public int compareTo(E o) {
			
			return 0;
		}
	}
	
	public static void main(String[] args) {
		Node<String> node=new Node<String>();
	}
}
