import static org.junit.Assert.*;

import org.junit.Test;

import com.neet.jobsite.dal.DacType;
import com.neet.jobsite.dal.IDacFactory;

public class DacFactoryTest {

	@Test
	public void DacFactoryCreationTest() {
		IDacFactory dacfactory = new DacFactory();
		ICandidateManager candidateManager = (ICandidateManager)dacfactory.GetDac(DacType.CandidateManager);
		assertNotNull(candidateManager);		
	}
	
	//todo: due to time constraint have not added all the tests


}
