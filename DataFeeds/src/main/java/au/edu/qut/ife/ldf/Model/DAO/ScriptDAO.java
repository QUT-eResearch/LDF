package au.edu.qut.ife.ldf.Model.DAO;

import java.util.List;

import au.edu.qut.ife.ldf.Model.Script;

public interface ScriptDAO {

	public Script showScript(Integer idScript);
	public List<Script> listScript();
	public void updateScript(Script script);
	public int removeScript(Integer idScript);
	public void addScript(Script script);
}
