package au.edu.qut.ife.ldf.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.edu.qut.ife.ldf.Model.Feed;
import au.edu.qut.ife.ldf.Model.DAO.FeedDAO;
import au.edu.qut.ife.ldf.Service.FeedService;

@Service
public class FeedServiceImpl implements FeedService{

	@Autowired
	private FeedDAO feedDAO;
	
	@Transactional
	public void addFeed(Feed feed) {
		// TODO Auto-generated method stub
		feedDAO.addFeed(feed);
	}

	@Transactional
	public List<Feed> listFeeds(Integer projectId) {
		// TODO Auto-generated method stub
		return feedDAO.listFeeds(projectId);
	}

	@Transactional
	public int removeFeed(Integer projectId, Integer feedId) {
		// TODO Auto-generated method stub
		return feedDAO.removeFeed(projectId, feedId);
	}

	@Transactional
	public Feed showFeed(Integer idProject, Integer idFeed) {
		// TODO Auto-generated method stub
		return feedDAO.showFeed(idProject, idFeed);
	}

	@Transactional
	public void updateFeed(Feed feed) {
		// TODO Auto-generated method stub
		feedDAO.updateFeed( feed);
	}

}
