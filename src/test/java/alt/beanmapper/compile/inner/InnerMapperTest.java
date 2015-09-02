package alt.beanmapper.compile.inner;

import java.io.IOException;

import org.junit.Test;

import alt.beanmapper.compile.AbstractCompilerTest;
import alt.beanmapper.mapping.Mapper;
import alt.beanmapper.mapping.MapperClass;
import alt.beanmapper.mapping.MappingMapperClassDescription;
import alt.beanmapper.mapping.Property;

/**
 * 
 * @author Albert Shift
 *
 */

public class InnerMapperTest extends AbstractCompilerTest {

	@Test
	public void test() throws IOException {

		// printImpl(InnerMapperReferenceImpl.class);

		byte[] byteCode = compiler.compileMapper(new MappingMapperClassDescription(InnerMapper.class, "Impl"));

		// printImpl(byteCode);

		assertEqualsImpl(InnerMapperReferenceImpl.class, byteCode, "map");
	}

	@MapperClass(impl = "InnerMapperReferenceImpl", jsr330 = true)
	public interface InnerMapper {

		@Mapper({ @Property(src = "intValue") })
		Destination map(Source src);

	}

	public static class Destination {

		private int intValue;

		public int getIntValue() {
			return intValue;
		}

		public void setIntValue(int intValue) {
			this.intValue = intValue;
		}

	}

	protected static class Source {

		private int intValue;

		public int getIntValue() {
			return intValue;
		}

		public void setIntValue(int intValue) {
			this.intValue = intValue;
		}

	}

}
