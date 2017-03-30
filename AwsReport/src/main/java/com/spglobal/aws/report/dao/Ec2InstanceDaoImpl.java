package com.spglobal.aws.report.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.spglobal.aws.report.model.Ec2Instance;


public class Ec2InstanceDaoImpl implements Ec2InstanceDao {

	
	private static final Logger logger = LoggerFactory.getLogger(Ec2InstanceDaoImpl.class);
	private static final String PERSISTENCE_UNIT_NAME = "AWSReport";
    private static EntityManagerFactory factory;
    EntityManager em;
	
    
    public Ec2InstanceDaoImpl()
    {
    	factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
    }
	
	@Override
	public void addInstance(Ec2Instance ec2Instance) {
		
        em.getTransaction().begin();
        em.persist(ec2Instance);
        em.getTransaction().commit();
        em.close();
		logger.info("Instance saved successfully, Instance Details="+ec2Instance);
	}

	@Override
	public void updateInstance(Ec2Instance ec2Instance) {
		
		Session session = em.unwrap(Session.class);
		session.update(ec2Instance);
		
		logger.info("Instance saved successfully, Instance Details="+ec2Instance);
	}

	@Override
	public List<Ec2Instance> listInstance() {
		Query query = em.createQuery("select ec2 from Ec2Instance ec2");
		List<Ec2Instance> lstEc2Instance = query.getResultList();
		lstEc2Instance.forEach(ec2Instance -> logger.info(ec2Instance.toString()));
		return lstEc2Instance;
	}

	@Override
	public Ec2Instance getInstanceById(String id) {
		Ec2Instance ec2Instance = (Ec2Instance)em.find(Ec2Instance.class, id);
		logger.info("EC2 Instance: "+ec2Instance);
		return ec2Instance;
	}

	@Override
	public void removeInstance(String id) {
		
		Ec2Instance ec2Instance = (Ec2Instance)em.find(Ec2Instance.class, id);
		if(null != ec2Instance)
			em.remove(ec2Instance);
		
		logger.info("Instance Deleted: "+ec2Instance);
	}

}
