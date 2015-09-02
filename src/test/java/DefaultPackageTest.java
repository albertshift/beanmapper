import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author Albert Shift
 *
 */

public class DefaultPackageTest {

	@Test
	public void test() {

		Package p = DefaultPackageTest.class.getPackage();
		Assert.assertNull(p);

	}

}
