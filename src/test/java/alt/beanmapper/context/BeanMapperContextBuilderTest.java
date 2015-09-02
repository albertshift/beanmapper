package alt.beanmapper.context;

import org.junit.Test;

import alt.beanmapper.context.BeanMapperContext;
import alt.beanmapper.context.BeanMapperContextBuilder;
import alt.beanmapper.test.mappers.AreaMapper;
import alt.beanmapper.test.mappers.UserMapper;

/**
 * 
 * @author Albert Shift
 *
 */

public class BeanMapperContextBuilderTest {

	@Test
	public void test() {

		BeanMapperContext context = new BeanMapperContextBuilder().addMapperInterface(UserMapper.class)
				.addMapperInterface(AreaMapper.class).build();

	}

	@Test
	public void testPackage() throws ClassNotFoundException {

		BeanMapperContext context = new BeanMapperContextBuilder().addPackage("beanmapper.test.mappers").build();

	}

}
