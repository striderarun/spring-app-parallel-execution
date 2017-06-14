package com.dashboard.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dashboard.beans.CompensationDetailsBean;
import com.dashboard.beans.EmployeeDashboardBean;
import com.dashboard.beans.InsuranceDetailsBean;
import com.dashboard.beans.PersonalDetailsBean;
import com.dashboard.beans.RoleDetailsBean;
import com.dashboard.beans.SkillDetailsBean;
import com.dashboard.beans.UnitDetailsBean;
import com.dashboard.parallel.ParallelProcessor;
import com.dashboard.parallel.Signature;
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
		LOGGER.info("STARTING SERIAL EXECUTION");
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
		LOGGER.info("STARTING PARALLEL EXECUTION");
		long startTime = System.nanoTime();
		EmployeeDashboardBean employeeDashboardBean = new EmployeeDashboardBean();
		
		Map<Object, List<Signature>> executionMap = new HashMap<>();
		List<Signature> employeeServiceSignatures = new ArrayList<>();
		List<Signature> insuranceServiceSignatures = new ArrayList<>();
		List<Signature> unitServiceSignatures = new ArrayList<>();

		employeeServiceSignatures.add(Signature.method("fetchCompensationDetails")
				.returnType(CompensationDetailsBean.class)
				.argsList(Arrays.asList(100))
				.argTypes(Arrays.asList(Integer.class))
				.build());
		
		employeeServiceSignatures.add(Signature.method("fetchPersonalDetails")
				.returnType(PersonalDetailsBean.class)
				.argsList(Arrays.asList(100))
				.argTypes(Arrays.asList(Integer.class))
				.build());
		
		employeeServiceSignatures.add(Signature.method("fetchSkillDetails")
				.returnType(SkillDetailsBean.class)
				.argsList(Arrays.asList(100))
				.argTypes(Arrays.asList(Integer.class))
				.build());
		
		employeeServiceSignatures.add(Signature.method("fetchRoleDetails")
				.returnType(RoleDetailsBean.class)
				.argsList(Arrays.asList(100))
				.argTypes(Arrays.asList(Integer.class))
				.build());
		
		insuranceServiceSignatures.add(Signature.method("fetchInsuranceDetails")
				.returnType(InsuranceDetailsBean.class)
				.argsList(Arrays.asList(100))
				.argTypes(Arrays.asList(Integer.class))
				.build());
		
		unitServiceSignatures.add(Signature.method("fetchUnitDetails")
				.returnType(UnitDetailsBean.class)
				.argsList(Arrays.asList(100))
				.argTypes(Arrays.asList(Integer.class))
				.build());
		
		executionMap.put(employeeService, employeeServiceSignatures);
		executionMap.put(insuranceService, insuranceServiceSignatures);
		executionMap.put(unitService, unitServiceSignatures);
		
		List<T> results = ParallelProcessor.genericParallelExecutor(executionMap);
		long executionTime = (System.nanoTime() - startTime) / 1000000;
		LOGGER.info("Total elapsed time is: " + executionTime);
		return employeeDashboardBean;
	}

}
