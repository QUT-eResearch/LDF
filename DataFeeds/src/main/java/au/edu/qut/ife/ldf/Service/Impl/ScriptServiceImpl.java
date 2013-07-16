package au.edu.qut.ife.ldf.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.edu.qut.ife.ldf.Model.Script;
import au.edu.qut.ife.ldf.Model.DAO.ScriptDAO;
import au.edu.qut.ife.ldf.Service.ScriptService;

@Service
public class ScriptServiceImpl implements ScriptService{
	@Autowired
	private ScriptDAO scriptDAO;
	
	@Transactional
	public Script showScript(Integer idScript){
		return scriptDAO.showScript(idScript);
	}

	@Transactional
	public List<Script> listScript() {
		// TODO Auto-generated method stub
		return scriptDAO.listScript();
	}

	@Transactional
	public void updateScript(Script script) {
		// TODO Auto-generated method stub
		scriptDAO.updateScript(script);
	}

	@Transactional
	public int removeScript(Integer idScript) {
		// TODO Auto-generated method stub
		return scriptDAO.removeScript(idScript);
	}

	@Transactional
	public void addScript(Script script) {
		// TODO Auto-generated method stub
		scriptDAO.addScript(script);
	}
}
