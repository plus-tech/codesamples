package com.example._20_tasklet;

import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example._60_dao.UsersDao;

@Configuration
public class DbToDbBatchTasklet {
	
	@Autowired
	private UsersDao usersDao;
	
	@Qualifier("bcp")
	@Bean(defaultCandidate = false)
	public MethodInvokingTaskletAdapter dtdTruncateStepTasklet() {
		MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
		adapter.setTargetObject(usersDao);
		adapter.setTargetMethod("truncateBcpUsers");
		
		return adapter;
	}

}
