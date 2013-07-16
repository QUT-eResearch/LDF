package au.edu.qut.ife.ldf.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.edu.qut.ife.ldf.Model.Chart;
import au.edu.qut.ife.ldf.Model.DAO.ChartDAO;
import au.edu.qut.ife.ldf.Service.ChartService;

@Service
public class ChartServiceImpl implements ChartService{

	@Autowired
	private ChartDAO chartDAO;
	
	@Transactional
	public void addChart(Chart chart) {
		// TODO Auto-generated method stub
		chartDAO.addChart(chart);
	}

	@Transactional
	public List<Chart> listCharts(Integer chartId) {
		// TODO Auto-generated method stub
		return chartDAO.listCharts(chartId);
	}

	@Transactional
	public void removeChart(Integer idFeed, Integer idChart) {
		// TODO Auto-generated method stub
		chartDAO.removeChart(idFeed, idChart);
	}

	@Transactional
	public Chart showChart(Integer idFeed, Integer idChart) {
		// TODO Auto-generated method stub
		return chartDAO.showChart(idFeed, idChart);
	}

	@Transactional
	public void updateChart(Chart chart) {
		// TODO Auto-generated method stub
		chartDAO.updateChart(chart);
	}

}
