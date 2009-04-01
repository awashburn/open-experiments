package org.sakaiproject.kernel2.osgi.jpaexample;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.sakaiproject.kernel2.osgi.jpaprovider.UserManagerFactory;
import org.sakaiproject.kernel2.osgi.jpaprovider.model.SystemUser;

import javax.persistence.EntityManager;

public class JpaExample implements BundleActivator {

  public void start(BundleContext arg0) throws Exception {
    System.err.println("Doing some JPA");
    EntityManager em = UserManagerFactory.getUserManager();
    System.err.println("EM: " + em);
    SystemUser u = new SystemUser();
    u.setName("Some user");
    em.getTransaction().begin();
    em.persist(u);
    em.getTransaction().commit();

    // g should be written to database now.
    // Read it from db (no transaction context needed for em.find method)
    SystemUser u2 = em.find(SystemUser.class, u.getId());
    System.out.println("User " + u.getId() + " from db: " + u2);

  }

  public void stop(BundleContext arg0) throws Exception {
  }

}