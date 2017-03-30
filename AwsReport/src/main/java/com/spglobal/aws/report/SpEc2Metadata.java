package com.spglobal.aws.report;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.spglobal.aws.report.model.Ec2Instance;

public class SpEc2Metadata {

	
	private static final Logger logger = LoggerFactory.getLogger(SpEc2Metadata.class);
	
	public static void main(String[] args) {
	
		
		SpEc2Metadata spEc2Metadata = new SpEc2Metadata();
        
		spEc2Metadata.getAllInstanceDetails(Regions.US_WEST_2, "anandbanik", "Name");
	}
	
	public List<Ec2Instance> getAllInstanceDetails(Regions regions, String profileName, String tagKey )
	{
		List<Ec2Instance> lstEc2Instance = new ArrayList<Ec2Instance>();
		AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard().withRegion(regions).withCredentials(new ProfileCredentialsProvider(profileName)).build();
        boolean done = false;
        while(!done) {
            DescribeInstancesRequest request = new DescribeInstancesRequest();
            DescribeInstancesResult response = ec2.describeInstances(request);

            for(Reservation reservation : response.getReservations()) {
                for(Instance instance : reservation.getInstances()) {
                	Ec2Instance ec2Instance = new Ec2Instance();
                	ec2Instance.setInstanceId(instance.getInstanceId());
                	ec2Instance.setInstanceAmi(instance.getImageId());
                	ec2Instance.setInstanceType(instance.getInstanceType());
                	ec2Instance.setInstanceState(instance.getState().getName());
                	ec2Instance.setInstanceMonitoring(instance.getMonitoring().getState());
                	ec2Instance.setTagName(instance.getTags().stream().filter(key -> tagKey.equals(key.getKey())).collect(Collectors.toList()).get(0).toString());
                	logger.info("ID: "+instance.getInstanceId());
                	logger.info("AMI: "+instance.getImageId());
                	logger.info("Instance Type: "+instance.getInstanceType());
                	logger.info("State: "+instance.getState().getName());
                	logger.info("Monitoring State: "+instance.getMonitoring().getState());
                	instance.getTags().stream().filter(key -> "Name".equals(key.getKey())).collect(Collectors.toList()).forEach(tag -> logger.info("Tag Name: "+tag.getValue()));
                	lstEc2Instance.add(ec2Instance);
                }
            }

            request.setNextToken(response.getNextToken());

            if(response.getNextToken() == null) {
                done = true;
            }
        }
		return lstEc2Instance;
	}

}
