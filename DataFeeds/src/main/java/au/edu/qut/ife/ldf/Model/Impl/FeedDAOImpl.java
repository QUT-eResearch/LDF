package au.edu.qut.ife.ldf.Model.Impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import au.edu.qut.ife.ldf.Model.Feed;
import au.edu.qut.ife.ldf.Model.DAO.FeedDAO;

@Repository
public class FeedDAOImpl implements FeedDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public void addFeed(Feed feed) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(feed);

	}

	public List<Feed> listFeeds(Integer projectId) {
		// TODO Auto-generated method stub
		if(null != projectId){
		   return sessionFactory.getCurrentSession().createQuery("from Feed f where f.idProject = "+ projectId).list();
		}
		return null;
	}

	public int removeFeed(Integer idProject, Integer idFeed) {
		// TODO Auto-generated method stub
		Feed feedPk = new Feed ();
		feedPk.setIdFeed(idFeed);
		feedPk.setIdProject(idProject);
		
		Feed feed = (Feed) sessionFactory.getCurrentSession().createQuery("from Feed f where f.idProject = "+ idProject+" and f.idFeed = "+ idFeed).uniqueResult();
		if(null != feed){
			if(sessionFactory.getCurrentSession().createQuery("from Chart f where f.idFeed = "+feed.getIdFeed()).list().isEmpty()){
			
				sessionFactory.getCurrentSession().delete(feed);
				return 1;
			}
			
		}
		return 0;
	}

	public Feed showFeed(Integer idProject, Integer idFeed) {
		// TODO Auto-generated method stub
		Feed feedPk = new Feed ();
		feedPk.setIdFeed(idFeed);
		feedPk.setIdProject(idProject);
		System.out.println(idProject+": "+idFeed);
		Feed feed = (Feed) sessionFactory.getCurrentSession().createQuery("from Feed f where f.idProject = "+ idProject+" and f.idFeed = "+ idFeed).uniqueResult();
		if(null != feed){
			return feed;
		}
		return null;	
	}

	public void updateFeed(Feed feed) {
		// TODO Auto-generated method stub

			sessionFactory.getCurrentSession().update(feed);
		
	}

}
