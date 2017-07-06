package com.dashboard.service.impl;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dashboard.beans.EmployeeDashboardBean;
import com.dashboard.service.DashboardService;
import com.dashboard.service.EmployeeService;
import com.dashboard.service.InsuranceService;
import com.dashboard.service.UnitService;

@Component
public class DashboardServiceImpl implements DashboardService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DashboardServiceImpl.class);

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private InsuranceService insuranceService;
	
	@Autowired
	private UnitService unitService;
	
	@Override
	public EmployeeDashboardBean fetchDashboardDetails(Integer employeeId) {
		LOGGER.info("------STARTING SERIAL EXECUTION-----");
		long startTime = System.nanoTime();
		EmployeeDashboardBean employeeDashboardBean = new EmployeeDashboardBean();
		employeeDashboardBean.setCompensationDetails(employeeService.fetchCompensationDetails(employeeId));
		employeeDashboardBean.setPersonalDetailsBean(employeeService.fetchPersonalDetails(employeeId));
		employeeDashboardBean.setSkillDetailsBean(employeeService.fetchSkillDetails(employeeId));
		employeeDashboardBean.setRoleDetailsBean(employeeService.fetchRoleDetails(employeeId));
		employeeDashboardBean.setInsuranceDetailsBean(insuranceService.fetchInsuranceDetails(employeeId));
		employeeDashboardBean.setUnitDetailsBean(unitService.fetchUnitDetails(employeeId));
		long executionTime = (System.nanoTime() - startTime) / 1000000;
		LOGGER.info("Total elapsed time is: " + executionTime);
		return employeeDashboardBean;
	}
	
	@Override
	public <T> EmployeeDashboardBean fetchDashboardDetailsParallel(Integer employeeId) {
		LOGGER.info("------STARTING PARALLEL EXECUTION-----");
		long startTime = System.nanoTime();
		EmployeeDashboardBean employeeDashboardBean = new EmployeeDashboardBean();
		List<Object> result = Stream.<Callable>of(
				() -> employeeService.fetchCompensationDetails(employeeId),
				() -> employeeService.fetchPersonalDetails(employeeId),
				() -> employeeService.fetchSkillDetails(employeeId),
				() -> employeeService.fetchRoleDetails(employeeId),
				() -> insuranceService.fetchInsuranceDetails(employeeId),
				() -> unitService.fetchUnitDetails(employeeId)
		).parallel()
			.map(callable -> {
				Object call = null;
				try {
					call = callable.call();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return call;
			})
			.collect(Collectors.toList());
		long executionTime = (System.nanoTime() - startTime) / 1000000;
		LOGGER.info("Total elapsed time is: " + executionTime);
		return employeeDashboardBean;
	}

}
