package com.spglobal.aws.report.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Ec2Instance")
public class Ec2Instance implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="instanceId")
	private String instanceId;
	private String instanceAmi;
	private String instanceType;
	private String instanceState;
	private String instanceMonitoring;
	private String tagName;
	
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getInstanceAmi() {
		return instanceAmi;
	}
	public void setInstanceAmi(String instanceAmi) {
		this.instanceAmi = instanceAmi;
	}
	public String getInstanceType() {
		return instanceType;
	}
	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}
	public String getInstanceState() {
		return instanceState;
	}
	public void setInstanceState(String instanceState) {
		this.instanceState = instanceState;
	}
	public String getInstanceMonitoring() {
		return instanceMonitoring;
	}
	public void setInstanceMonitoring(String instanceMonitoring) {
		this.instanceMonitoring = instanceMonitoring;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	@Override
	public String toString()
	{
		return "Tag Name: "+tagName;
	}
}
