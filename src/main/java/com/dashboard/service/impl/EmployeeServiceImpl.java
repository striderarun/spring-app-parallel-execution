package com.dashboard.service.impl;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dashboard.beans.CompensationDetailsBean;
import com.dashboard.beans.PersonalDetailsBean;
import com.dashboard.beans.RoleDetailsBean;
import com.dashboard.beans.SkillDetailsBean;
import com.dashboard.service.EmployeeService;


@Component
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	
	@Override
	public CompensationDetailsBean fetchCompensationDetails(Integer employeeId) {
		LOGGER.info("Fetching compensation details");
		simulateOneSecondDelay();
		return new CompensationDetailsBean();
	}

	@Override
	public PersonalDetailsBean fetchPersonalDetails(Integer employeeId) {
		LOGGER.info("Fetching personal details");
		simulateOneSecondDelay();
		return new PersonalDetailsBean();
	}

	@Override
	public RoleDetailsBean fetchRoleDetails(Integer employeeId) {
		LOGGER.info("Fetching role details");
		simulateOneSecondDelay();
		return new RoleDetailsBean();
	}

	@Override
	public SkillDetailsBean fetchSkillDetails(Integer employeeId) {
		LOGGER.info("Fetching skill details");
		simulateOneSecondDelay();
		return new SkillDetailsBean();
	}
	
	private void simulateOneSecondDelay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	

}
