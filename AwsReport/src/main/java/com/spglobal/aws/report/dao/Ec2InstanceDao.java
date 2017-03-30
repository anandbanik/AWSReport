package com.spglobal.aws.report.dao;

import java.util.List;

import com.spglobal.aws.report.model.Ec2Instance;



public interface Ec2InstanceDao 
{
	public void addInstance(Ec2Instance ec2Instance);
	public void updateInstance(Ec2Instance ec2Instance);
	public List<Ec2Instance> listInstance();
	public Ec2Instance getInstanceById(String id);
	public void removeInstance(String id);
}
