package alt.beanmapper.context;

import org.junit.Assert;
import org.junit.Test;

import alt.beanmapper.context.BeanMapperContext;
import alt.beanmapper.context.BeanMapperContextBuilder;
import alt.beanmapper.test.dto.AreaDto;
import alt.beanmapper.test.mappers.AreaMapper;
import alt.beanmapper.test.model.Area;

public class AreaMapperTest {

	@Test
	public void test() {

		BeanMapperContext context = new BeanMapperContextBuilder().instantiateConverters()
				.addMapperInterface(AreaMapper.class).build();

		AreaMapper areaMapper = context.getMapperService(AreaMapper.class);

		Assert.assertNotNull(areaMapper);

		Area area = new Area();
		area.setId(123L);
		area.setName("SFBay");

		AreaDto dto = areaMapper.toAreaDto(area);

		Assert.assertNotNull(dto);
		Assert.assertEquals(Long.valueOf(area.getId()), dto.getAreaId());
		Assert.assertEquals(area.getName(), dto.getAreaName());

		System.out.println("dto = " + dto);

	}
}
