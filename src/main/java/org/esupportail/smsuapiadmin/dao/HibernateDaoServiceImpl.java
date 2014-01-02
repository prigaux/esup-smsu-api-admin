/**
 * ESUP-Portail esup-smsu-api-admin - Copyright (c) 2006-2014 ESUP-Portail consortium
 * http://sourcesup.cru.fr/projects/esup-smsu-api-admin
 */
package org.esupportail.smsuapiadmin.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.esupportail.commons.services.logging.Logger;
import org.esupportail.commons.services.logging.LoggerImpl;
import org.esupportail.smsuapiadmin.dao.beans.Account;
import org.esupportail.smsuapiadmin.dao.beans.Application;
import org.esupportail.smsuapiadmin.dao.beans.Institution;
import org.esupportail.smsuapiadmin.dao.beans.Role;
import org.esupportail.smsuapiadmin.dao.beans.Sms;
import org.esupportail.smsuapiadmin.dao.beans.Statistic;
import org.esupportail.smsuapiadmin.dao.beans.UserBoSmsu;
import org.hibernate.classic.Session;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.InitializingBean;

/**
 * The Hibernate implementation of the DAO service.
 */
@SuppressWarnings({"unchecked"})
public class HibernateDaoServiceImpl extends
HibernateDaoSupport implements DaoService,
InitializingBean {

	/**
	 * Log4j logger.
	 */
	private final Logger logger = new LoggerImpl(getClass());

	/**
	 * The serialization id.
	 */
	private static final long serialVersionUID = 3152554337896617315L;

	/**
	 * The name of the 'id' attribute.
	 */
	@SuppressWarnings("unused")
	private static final String ID_ATTRIBUTE = "id";

	/**
	 * Bean constructor.
	 */
	public HibernateDaoServiceImpl() {
		super();
	}

	// ////////////////////////////////////////////////////////////
	// User
	// ////////////////////////////////////////////////////////////



	public UserBoSmsu getUserById(final Integer id) {
		String queryString = "FROM UserBoSmsu user " + "WHERE user.Id="
		+ id + "";

		List<UserBoSmsu> users = getHibernateTemplate().find(queryString);
		if (users.size() == 1) {
			return users.get(0);
		}
		return null;
	}


	public UserBoSmsu getUserByLogin(final String login) {
		String queryString = "FROM UserBoSmsu user " + "WHERE user.Login='"
		+ login + "'";

		List<UserBoSmsu> users = getHibernateTemplate().find(queryString);
		if (users.size() == 1) {
			return (UserBoSmsu) users.get(0);
		}
		return null;
	}

	/**
	 * @see org.esupportail.smsuapiadmin.dao.DaoService#getUsers()
	 */
	public List<UserBoSmsu> getUsers() {
		return getHibernateTemplate().loadAll(UserBoSmsu.class);
	}

	/**
	 * 
	 */
	public void addUser(final UserBoSmsu user) {
		addObject(user);
	}

	public void deleteUser(final UserBoSmsu user) {
		deleteObject(user);
	}

	public void updateUser(final UserBoSmsu user) {
		updateObject(user);
	}

	// ////////////////////////////////////////////////////////////
	// Application
	// ////////////////////////////////////////////////////////////


	public void addApplication(final Application app) {
		addObject(app);
	}


	public void deleteApplication(final Application app) {
		deleteObject(app);
	}


	public List<Application> getApplications() {
		return getHibernateTemplate().loadAll(Application.class);
	}


	public void updateApplication(final Application app) {
		updateObject(app);
	}


	public Application getApplicationById(final int id) {
		String queryString = "FROM Application app " + "WHERE app.Id=" + id;

		List<Application> apps = getHibernateTemplate().find(queryString);
		if (apps.size() == 1) {
			return apps.get(0);
		}
		return null;
	}

	public Application getApplicationByName(String name) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Application.class);
		criteria.add(Restrictions.eq(Application.PROP_NAME, name));
		List<Application> l = getHibernateTemplate().findByCriteria(criteria); 
		return l.size() != 0 ? l.get(0) : null;
	}

	// ////////////////////////////////////////////////////////////
	// Account
	// ////////////////////////////////////////////////////////////


	public Account getAccountByName(final String name) {
		String queryString = "FROM Account account 	" + "WHERE account.Label='"
		+ name + "'";

		List<Account> accounts = getHibernateTemplate().find(queryString);
		if (accounts.size() == 1) {
			return accounts.get(0);
		}
		return null;
	}

	public void addAccount(final Account account) {
		addObject(account);
	}

	// ////////////////////////////////////////////////////////////
	// Institution
	// ////////////////////////////////////////////////////////////

	public Institution getInstitutionByName(final String name) {
		String queryString = "FROM Institution institution 	"
			+ "WHERE institution.Label='" + name + "'";

		List<Institution> institutions = getHibernateTemplate().find(queryString);
		if (institutions.size() == 1) {
			return institutions.get(0);
		}
		return null;
	}


	public void addInstitution(final Institution institution) {
		addObject(institution);
	}

	/**
	 * Retrieves the current session.
	 * 
	 * @return
	 */
	private Session getCurrentSession() {
		return getHibernateTemplate().getSessionFactory().getCurrentSession();
	}


	public List<Sms> getSMSByApplication(final Application app) {
		List<Sms> result = null;
		String queryString = "FROM Sms sms " + "WHERE sms.App.Id="
		+ app.getId();

		result = getHibernateTemplate().find(queryString);

		return result;
	}


	public List<Statistic> getStatisticByApplication(final Application app) {
		List<Statistic> result = null;
		String queryString = "FROM Statistic statistic "
			+ "WHERE statistic.Id.App.Id=" + app.getId();

		result = getHibernateTemplate().find(queryString);

		return result;
	}


	public List<Account> getAccounts() {
		return getHibernateTemplate().loadAll(Account.class);
	}


	public void updateAccount(final Account account) {
		updateObject(account);
	}


	public List<Institution> getInstitutions() {
		return getHibernateTemplate().loadAll(Institution.class);
	}


	public List<Statistic> getStatistics() {
		return getHibernateTemplate().loadAll(Statistic.class);
	}


	public Account getAccountById(final int id) {
		String queryString = "FROM Account acc " + "WHERE acc.Id=" + id;

		List<Account> accs = getHibernateTemplate().find(queryString);
		if (accs.size() == 1) {
			return accs.get(0);
		}
		return null;
	}


	public Institution getInstitutionById(final int id) {
		String queryString = "FROM Institution inst " + "WHERE inst.Id=" + id;

		List<Institution> institutions = getHibernateTemplate().find(queryString);
		if (institutions.size() == 1) {
			return institutions.get(0);
		}
		return null;
	}


	public List<Statistic> getStatisticsSorted() {
		String select = "SELECT stat FROM Statistic stat";

		String orderBy = " ORDER BY stat.Id.App.Institution.Label,  "
			+ "stat.Id.App.Name, stat.Id.Acc.Label, " + "stat.Id.Month";

		return getHibernateTemplate().find(select + orderBy);
	}


	public List<Sms> getSms() {
		return getHibernateTemplate().loadAll(Sms.class);
	}

	public Sms getSmsById(Integer id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Sms.class);
		criteria.add(Restrictions.eq(Sms.PROP_ID, id));
		List<Sms> l = getHibernateTemplate().findByCriteria(criteria); 
		return l.size() != 0 ? l.get(0) : null;
	}

	public List<Map<String,?>> getSmsAccountsAndApplications() {

		Criteria criteria = getCurrentSession().createCriteria(Sms.class);

		criteria.setProjection(Projections.projectionList()
				.add( Projections.distinct(Projections.projectionList()
						.add(Projections.property(Sms.PROP_APP), Sms.PROP_APP)
						.add(Projections.property(Sms.PROP_ACC), Sms.PROP_ACC))));

		criteria.setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP);

		return criteria.list();
	}

	public List<List<?>> searchGroupSmsWithInitialId(final Institution inst,
			final Account acc, final Application app, final Date startDate,
			final Date endDate, int maxResults) {

		Criteria criteria = criteriaSearchSms(inst, acc, app, startDate, endDate, maxResults);

		criteria.add(Restrictions.isNotNull(Sms.PROP_INITIAL_ID));

		criteria.setProjection(Projections.projectionList()
				.add( Projections.distinct(Projections.projectionList()
						.add(Projections.property(Sms.PROP_APP))
						.add(Projections.property(Sms.PROP_INITIAL_ID)))));

		criteria.setResultTransformer(Transformers.TO_LIST);
		return criteria.list();
	}

	public List<Sms> searchGroupSmsWithNullInitialId(final Institution inst,
			final Account acc, final Application app, final Date startDate,
			final Date endDate, int maxResults) {

		Criteria criteria = criteriaSearchSms(inst, acc, app, startDate, endDate, maxResults);

		criteria.add(Restrictions.isNull(Sms.PROP_INITIAL_ID));

		return (List<Sms>) criteria.list();
	}

	private Criteria criteriaSearchSms(final Institution inst,
			final Account acc, final Application app, final Date startDate,
			final Date endDate, int maxResults) {

		Criteria criteria = getCurrentSession().createCriteria(Sms.class);
		
		if (inst != null) {
			criteria.createCriteria(Sms.PROP_APP).add(Restrictions.eq(Application.PROP_INS, inst));
		}

		if (app != null) {
			criteria.add(Restrictions.eq(Sms.PROP_APP, app));
		}

		if (acc != null) {
			criteria.add(Restrictions.eq(Sms.PROP_ACC, acc));
		}

		if (startDate != null) {
			long startDateLong = startDate.getTime();
			java.sql.Date startDateSQL = new java.sql.Date(startDateLong);
			criteria.add(Restrictions.ge(Sms.PROP_DATE, startDateSQL));
		}

		if (endDate != null) {
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(endDate);
			gregorianCalendar.add(Calendar.DAY_OF_YEAR,1); 
			Date newDate = gregorianCalendar.getTime();

			long endDateLong = newDate.getTime();
			java.sql.Date endDateSQL = new java.sql.Date(endDateLong);

			criteria.add(Restrictions.lt(Sms.PROP_DATE, endDateSQL));
		}

		criteria.addOrder(Order.desc(Sms.PROP_DATE));
		if (maxResults > 0) criteria.setMaxResults(maxResults);
		
		return criteria;
	}

	public Role getRoleByName(final String name) {
		String queryString = "FROM Role role " + "WHERE role.Name='" + name + "'";

		List<Role> roles = getHibernateTemplate().find(queryString);
		if (roles.size() == 1) {
			return roles.get(0);
		}
		return null;
	}


	public List<Role> getRoles() {
		return getHibernateTemplate().loadAll(Role.class);
	}

	public List<Sms> getSmsByApplicationAndInitialId(final Application application,
			final Integer smsInitialId) {

		DetachedCriteria criteria = DetachedCriteria.forClass(Sms.class);
		criteria.add(Restrictions.eq(Sms.PROP_APP, application));
		if (smsInitialId != null) {
			criteria.add(Restrictions.eq(Sms.PROP_INITIAL_ID, smsInitialId));
		} else {
			criteria.add(Restrictions.isNull(Sms.PROP_INITIAL_ID));
		}
		return getHibernateTemplate().findByCriteria(criteria);
	}



	protected void addObject(final Object object) {
		if (logger.isDebugEnabled()) {
			logger.debug("adding " + object + "...");
		}
		getCurrentSession().beginTransaction();
		getHibernateTemplate().save(object);
		getCurrentSession().getTransaction().commit();
		if (logger.isDebugEnabled()) {
			logger.debug("done.");
		}
	}

	/**
	 * Update an object in the database.
	 * @param object
	 */
	protected void updateObject(final Object object) {
		if (logger.isDebugEnabled()) {
			logger.debug("merging " + object + "...");
		}
		getCurrentSession().beginTransaction();
		Object merged = getHibernateTemplate().merge(object);
		if (logger.isDebugEnabled()) {
			logger.debug("done, updating " + merged + "...");
		}
		getHibernateTemplate().update(merged);
		getCurrentSession().getTransaction().commit();
		if (logger.isDebugEnabled()) {
			logger.debug("done.");
		}
	}

	/**
	 * Delete an object from the database.
	 * @param object
	 */
	protected void deleteObject(final Object object) {
		if (logger.isDebugEnabled()) {
			logger.debug("merging " + object + "...");
		}
		getCurrentSession().beginTransaction();
		Object merged = getHibernateTemplate().merge(object);
		if (logger.isDebugEnabled()) {
			logger.debug("done, deleting " + merged + "...");
		}
		getHibernateTemplate().delete(merged);
                getCurrentSession().getTransaction().commit();
		if (logger.isDebugEnabled()) {
			logger.debug("done.");
		}
	}

}