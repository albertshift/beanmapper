package alt.beanmapper.compile.boxing;

import java.io.IOException;

import org.junit.Test;

import alt.beanmapper.compile.AbstractCompilerTest;
import alt.beanmapper.mapping.MappingMapperClassDescription;

/**
 * 
 * @author Albert Shift
 *
 */

public class BoxingMapperTest extends AbstractCompilerTest {

	@Test
	public void test() throws IOException {

		byte[] byteCode = compiler.compileMapper(new MappingMapperClassDescription(BoxingMapper.class, "Impl"));
		assertEqualsImpl(BoxingReferenceMapperImpl.class, byteCode, "map");
	}

}
