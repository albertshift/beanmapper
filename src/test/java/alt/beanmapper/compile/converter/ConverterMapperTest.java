package alt.beanmapper.compile.converter;

import java.io.IOException;

import org.junit.Test;

import alt.beanmapper.compile.AbstractCompilerTest;
import alt.beanmapper.mapping.MappingMapperClassDescription;

/**
 * 
 * @author Albert Shift
 *
 */

public class ConverterMapperTest extends AbstractCompilerTest {

	@Test
	public void test() throws IOException {

		// printImpl(ConverterReferenceMapperImpl.class, "setIntToLongConverter");
		// printImpl(ConverterReferenceMapperImpl.class);

		byte[] byteCode = compiler.compileMapper(new MappingMapperClassDescription(ConverterMapper.class, "Impl"));
		assertEqualsImpl(ConverterReferenceMapperImpl.class, byteCode, "map");
		assertEqualsImpl(ConverterReferenceMapperImpl.class, byteCode, "setIntToLongConverter");
	}

}
