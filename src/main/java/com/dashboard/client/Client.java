package com.dashboard.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dashboard.beans.EmployeeDashboardBean;
import com.dashboard.config.AppConfig;
import com.dashboard.service.impl.DashboardServiceImpl;


public class Client {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		DashboardServiceImpl dashboardServiceImpl = ctx.getBean(DashboardServiceImpl.class);

		//Serial execution
		dashboardServiceImpl.fetchDashboardDetails(100);

		//Parallel execution
		dashboardServiceImpl.fetchDashboardDetailsParallel(100);
		
	}

}
