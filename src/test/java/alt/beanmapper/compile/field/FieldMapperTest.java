package alt.beanmapper.compile.field;

import java.io.IOException;

import org.junit.Test;

import alt.beanmapper.compile.AbstractCompilerTest;
import alt.beanmapper.mapping.MappingMapperClassDescription;

/**
 * 
 * @author Albert Shift
 *
 */

public class FieldMapperTest extends AbstractCompilerTest {

	@Test
	public void test() throws IOException {

		byte[] byteCode = compiler.compileMapper(new MappingMapperClassDescription(FieldMapper.class, "Impl"));
		assertEqualsImpl(FieldReferenceMapperImpl.class, byteCode, "map");
	}

}
