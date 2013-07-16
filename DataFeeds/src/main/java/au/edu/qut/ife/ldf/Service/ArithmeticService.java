package au.edu.qut.ife.ldf.Service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Service enables the class to be used as a Spring service
 * @Transactional enables transaction support for this class
 */
@Service("springService")
@Transactional
public class ArithmeticService {
	
	protected static Logger logger = Logger.getLogger("service");
	
	/**
	 * Adds two numbers
	 */
	public Integer add(Integer operand1, Integer operand2) {
		logger.debug("Adding two numbers");
		// A simple arithmetic addition
		return operand1 + operand2;
	}
	
}
