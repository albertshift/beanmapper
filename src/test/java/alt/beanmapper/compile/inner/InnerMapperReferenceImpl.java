package alt.beanmapper.compile.inner;

import alt.beanmapper.compile.inner.InnerMapperTest.Destination;
import alt.beanmapper.compile.inner.InnerMapperTest.InnerMapper;
import alt.beanmapper.compile.inner.InnerMapperTest.Source;

/**
 * 
 * @author Albert Shift
 *
 */

public class InnerMapperReferenceImpl implements InnerMapper {

	@Override
	public Destination map(Source src) {
		Destination dest = new Destination();
		dest.setIntValue(src.getIntValue());
		return dest;
	}

}