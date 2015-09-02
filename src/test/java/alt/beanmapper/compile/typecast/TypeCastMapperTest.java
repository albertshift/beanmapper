package alt.beanmapper.compile.typecast;

import java.io.IOException;

import org.junit.Test;

import alt.beanmapper.compile.AbstractCompilerTest;
import alt.beanmapper.mapping.MappingMapperClassDescription;

/**
 * 
 * @author Albert Shift
 *
 */

public class TypeCastMapperTest extends AbstractCompilerTest {

	@Test
	public void test() throws IOException {

		// printImpl(TypeCastReferenceMapperImpl.class, "mapShort");

		byte[] byteCode = compiler
				.compileMapper(new MappingMapperClassDescription(TypeCastMapper.class, "Impl"));

		assertEqualsImpl(TypeCastReferenceMapperImpl.class, byteCode, "mapBoolean");
		assertEqualsImpl(TypeCastReferenceMapperImpl.class, byteCode, "mapByte");
		assertEqualsImpl(TypeCastReferenceMapperImpl.class, byteCode, "mapChar");
		assertEqualsImpl(TypeCastReferenceMapperImpl.class, byteCode, "mapShort");
		assertEqualsImpl(TypeCastReferenceMapperImpl.class, byteCode, "mapInt");
		assertEqualsImpl(TypeCastReferenceMapperImpl.class, byteCode, "mapLong");
		assertEqualsImpl(TypeCastReferenceMapperImpl.class, byteCode, "mapFloat");
		assertEqualsImpl(TypeCastReferenceMapperImpl.class, byteCode, "mapDouble");
	}

}
