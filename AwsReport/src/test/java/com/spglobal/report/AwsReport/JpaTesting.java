package com.spglobal.report.AwsReport;

import com.spglobal.aws.report.dao.Ec2InstanceDao;
import com.spglobal.aws.report.dao.Ec2InstanceDaoImpl;
import com.spglobal.aws.report.model.Ec2Instance;

public class JpaTesting {

	public static void main(String[] args) {
		Ec2InstanceDao ec2InstanceDAO = new Ec2InstanceDaoImpl();
		
		Ec2Instance ec2Instance = new Ec2Instance();
		ec2Instance.setInstanceId("i-03d3da4573dac434d");
		ec2Instance.setInstanceAmi("ami-6f68cf0f");
		ec2Instance.setInstanceType("t2.micro");
		ec2Instance.setInstanceMonitoring("disabled");
		ec2Instance.setInstanceState("stopped");
		ec2Instance.setTagName("JPATest");
		
		ec2InstanceDAO.addInstance(ec2Instance);
		
	}

}
