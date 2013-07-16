package au.edu.qut.ife.ldf.Model.Impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.edu.qut.ife.ldf.Model.Chart;
import au.edu.qut.ife.ldf.Model.Feed;
import au.edu.qut.ife.ldf.Model.DAO.ChartDAO;

@Repository
public class ChartDAOImpl implements ChartDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addChart(Chart chart) {
		// TODO Auto-generated method stub
		
		try {
			System.out.println(chart.getIdChart());
			sessionFactory.getCurrentSession().save(chart);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Chart> listCharts(Integer idFeed) {
		// TODO Auto-generated method stub
		if(null != idFeed){
			   return sessionFactory.getCurrentSession().createQuery("from Chart c where c.idFeed = "+ idFeed).list();
			}
			return null;
	}

	public void removeChart(Integer idFeed, Integer idChart) {
		// TODO Auto-generated method stub
		Chart chartPk = new Chart ();
		chartPk.setIdFeed(idFeed);
		chartPk.setIdChart(idChart);
		
		Chart chart = (Chart) sessionFactory.getCurrentSession().createQuery("from Chart where idChart="+idChart+" and idFeed="+idFeed).uniqueResult();
		if(null != chart){
			sessionFactory.getCurrentSession().delete(chart);
		}
	}

	public Chart showChart(Integer idFeed, Integer idChart) {
		// TODO Auto-generated method stub
		Chart chartPk = new Chart ();
		chartPk.setIdFeed(idFeed);
		chartPk.setIdChart(idChart);
		
		//Chart chart = (Chart) sessionFactory.getCurrentSession().load(Chart.class, (Serializable) chartPk);
		Chart chart = (Chart) sessionFactory.getCurrentSession().createQuery("from Chart where idChart="+idChart+" and idFeed="+idFeed).uniqueResult();
		if(null != chart){
			return chart;
		}
		return null;
	}

	public void updateChart(Chart chart) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(chart);

	}
	

}
