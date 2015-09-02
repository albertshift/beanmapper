package alt.beanmapper.compile.array;

import java.io.IOException;

import org.junit.Test;

import alt.beanmapper.compile.AbstractCompilerTest;
import alt.beanmapper.mapping.MappingMapperClassDescription;

/**
 * 
 * @author Albert Shift
 *
 */

public class ArrayMapperTest extends AbstractCompilerTest {

	@Test
	public void test() throws IOException {

		// printImpl(ArrayReferenceMapperImpl.class, "box");

		byte[] byteCode = compiler.compileMapper(new MappingMapperClassDescription(ArrayMapper.class, "Impl"));
		assertEqualsImpl(ArrayReferenceMapperImpl.class, byteCode, "pmap");
		assertEqualsImpl(ArrayReferenceMapperImpl.class, byteCode, "wmap");
		assertEqualsImpl(ArrayReferenceMapperImpl.class, byteCode, "box");
		assertEqualsImpl(ArrayReferenceMapperImpl.class, byteCode, "unbox");
	}

}
