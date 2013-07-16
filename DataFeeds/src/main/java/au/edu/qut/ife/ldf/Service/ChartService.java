package au.edu.qut.ife.ldf.Service;

import java.util.List;

import au.edu.qut.ife.ldf.Model.Chart;

public interface ChartService {

	public void addChart(Chart chart);
	public List<Chart> listCharts(Integer chartId);
	public void removeChart(Integer idFeed, Integer idChart);
	public Chart showChart(Integer idFeed, Integer idChart);
	public void updateChart(Chart chart);
}
