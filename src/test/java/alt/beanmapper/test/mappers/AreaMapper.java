package alt.beanmapper.test.mappers;

import alt.beanmapper.converter.DoubleToLongConverter;
import alt.beanmapper.converter.LongToDoubleConverter;
import alt.beanmapper.converter.UtilDateToSqlDateConverter;
import alt.beanmapper.mapping.Mapper;
import alt.beanmapper.mapping.Property;
import alt.beanmapper.mapping.TypeCastStrategy;
import alt.beanmapper.test.dto.AreaDto;
import alt.beanmapper.test.model.Area;

/**
 * 
 * @author Albert Shift
 *
 */

public interface AreaMapper {

	@Mapper(value = { @Property(src = "id", dest = "areaId"), @Property(src = "name", dest = "areaName"),
			@Property(src = "population", dest = "population", converter = LongToDoubleConverter.class) }, typeCast = TypeCastStrategy.NO_BOOLEAN)
	AreaDto toAreaDto(Area area);

	@Mapper(value = { @Property(src = "areaId", dest = "id"), @Property(src = "areaName", dest = "name"),
			@Property(src = "updateTime", dest = "updateTime", converter = UtilDateToSqlDateConverter.class),
			@Property(src = "population", dest = "population", converter = DoubleToLongConverter.class) }, typeCast = TypeCastStrategy.NO_BOOLEAN)
	Area fromAreaDto(AreaDto areaDto);

}
