package au.edu.qut.ife.ldf.Service;

import java.util.List;

import au.edu.qut.ife.ldf.Model.Script;

public interface ScriptService {

	public Script showScript(Integer idScript);
	public List<Script> listScript();
	public void updateScript(Script script);
	public int removeScript(Integer idScript);
	public void addScript(Script script);
}
