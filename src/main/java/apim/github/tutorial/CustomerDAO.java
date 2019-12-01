package apim.github.tutorial;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAO {

	private SessionFactory sessionFactory;

	private HibernateTemplate hibernateTemplate;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Transactional
	public void addCustomer(Customer c) {
		hibernateTemplate.save(c);
	}

	@Transactional
	public List<Customer> getAllCustomers() {
		return hibernateTemplate.loadAll(Customer.class);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Customer> findCustomerByBalance(int amt) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		criteria.add(Restrictions.gt("balance", amt));
		return (List<Customer>) hibernateTemplate.findByCriteria(criteria);
	}

}
