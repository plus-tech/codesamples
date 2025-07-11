package com.example._20_tasklet;

import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example._60_dao.UsersDao;

@Configuration
public class OrganizedBatchTasklet {
	
	@Autowired
	private UsersDao usersDao;
	
	@Bean(name="obTruncateStepTasklet")
	public MethodInvokingTaskletAdapter obTruncateStepTasklet() {
		MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
		adapter.setTargetObject(usersDao);
		adapter.setTargetMethod("truncateUsers");
		
		return adapter;
	}

}
