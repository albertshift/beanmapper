package alt.beanmapper.compile.unboxing;

import java.io.IOException;

import org.junit.Test;

import alt.beanmapper.compile.AbstractCompilerTest;
import alt.beanmapper.mapping.MappingMapperClassDescription;

/**
 * 
 * @author Albert Shift
 *
 */

public class UnboxingMapperTest extends AbstractCompilerTest {

	@Test
	public void test() throws IOException {

		byte[] byteCode = compiler.compileMapper(new MappingMapperClassDescription(UnboxingMapper.class, "Impl"));
		assertEqualsImpl(UnboxingReferenceMapperImpl.class, byteCode, "map");
	}

}
