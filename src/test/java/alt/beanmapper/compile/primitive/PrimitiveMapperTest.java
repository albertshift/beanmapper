package alt.beanmapper.compile.primitive;

import java.io.IOException;

import org.junit.Test;

import alt.beanmapper.compile.AbstractCompilerTest;
import alt.beanmapper.mapping.MappingMapperClassDescription;

/**
 * 
 * @author Albert Shift
 *
 */

public class PrimitiveMapperTest extends AbstractCompilerTest {

	@Test
	public void test() throws IOException {

		byte[] byteCode = compiler.compileMapper(new MappingMapperClassDescription(PrimitiveMapper.class, "Impl"));
		assertEqualsImpl(PrimitiveReferenceMapperImpl.class, byteCode, "map");
	}

}
