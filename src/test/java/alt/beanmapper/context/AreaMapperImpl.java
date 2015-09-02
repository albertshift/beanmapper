package alt.beanmapper.context;

import alt.beanmapper.converter.LongToDoubleConverter;
import alt.beanmapper.converter.UtilDateToSqlDateConverter;
import alt.beanmapper.runtime.AutoboxingRuntime;
import alt.beanmapper.runtime.ConverterRuntime;
import alt.beanmapper.test.dto.AreaDto;
import alt.beanmapper.test.mappers.AreaMapper;
import alt.beanmapper.test.model.Area;

/**
 * 
 * @author Albert Shift
 *
 */

public final class AreaMapperImpl implements AreaMapper {

	private UtilDateToSqlDateConverter c1 = new UtilDateToSqlDateConverter();

	private LongToDoubleConverter c2 = new LongToDoubleConverter();

	public AreaMapperImpl() {
	}

	@Override
	public AreaDto toAreaDto(Area area) {
		AreaDto dto = new AreaDto();
		dto.setAreaId(area.getId());
		dto.setAreaName(area.getName());
		dto.setUpdateTime(area.getUpdateTime());
		dto.setPopulation(AutoboxingRuntime.unbox(ConverterRuntime.convert(area.getPopulation(), c2)));
		return dto;
	}

	@Override
	public Area fromAreaDto(AreaDto areaDto) {
		Area area = new Area();
		area.setId(AutoboxingRuntime.unbox(areaDto.getAreaId()));
		area.setName(areaDto.getAreaName());
		area.setUpdateTime(ConverterRuntime.convert(areaDto.getUpdateTime(), c1));
		return area;
	}

}
