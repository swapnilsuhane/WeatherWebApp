package weather.junit;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/*TestSuite for running all Unit Tests together
 * Remove @Ignore to run all*/

@Ignore
@RunWith(Suite.class)
@SuiteClasses({ JsonParserServiceTest.class,
		WeatherServiceTest.class, WeatherValidatorTest.class,
		ZipCodeFoundTest.class })
public class AllTests {

}
