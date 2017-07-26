package com.gs.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * ProcessEngine 流程引擎      所有的Activiti功能最终都要借用ProcessEngine来完成
 * ProcessEngineConfiguration   流程引擎配置类，主要是配置数据库
 * 
 * Activiti的表都以ACT_开头。 第二部分是表示表的用途的两个字母标识。 用途也和服务的API对应。

ACT_RE_*: 'RE'表示repository。 这个前缀的表包含了流程定义和流程静态资源 （图片，规则，等等）。
ACT_RU_*: 'RU'表示runtime。 这些运行时的表，包含流程实例，任务（Task），变量，异步任务，等运行中的数据。 Activiti只在流程实例执行过程中保存这些数据， 在流程结束时就会删除这些记录。 这样运行时表可以一直很小速度很快。
ACT_ID_*: 'ID'表示identity。 这些表包含身份信息，比如用户，组等等。
ACT_HI_*: 'HI'表示history。 这些表包含历史数据，比如历史流程实例， 变量，任务等等。
ACT_GE_*: 'GE'表示General。通用数据， 用于不同场景下。
 *
 */
public class ActivitiDB {
	
	@Test
	public void testActivitiDB(){  
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();  
        configuration.setJdbcDriver("com.mysql.jdbc.Driver");  
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf8&useSSL=false");  
        configuration.setJdbcUsername("root");  
        configuration.setJdbcPassword("123456");  
        /** 
         *    public static final String DB_SCHEMA_UPDATE_FALSE = "false";操作activiti25张表的时候，如果表不存在，就抛出异常，不能自动创建25张表 
   
              public static final String DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";每次操作，都会先删除表，再创建表 
             
              public static final String DB_SCHEMA_UPDATE_TRUE = "true";如果表不存在，就创建表，如果表存在，就直接操作 
         */  
        // 此时没有activiti的数据表，而是通过创建流程引擎的过程中创建数据表。设置创建数据表的策略
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);  
        // 创建流程引擎，在此过程中，ProcessEngine会自动在我们前面定义好的数据库中创建 activiti所需要的25张表
        ProcessEngine processEngine = configuration.buildProcessEngine();  
        System.out.println("processEngine："+processEngine);  
    }  
}
