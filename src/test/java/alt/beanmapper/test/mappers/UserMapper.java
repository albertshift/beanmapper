package alt.beanmapper.test.mappers;

import alt.beanmapper.mapping.Mapper;
import alt.beanmapper.mapping.Property;
import alt.beanmapper.test.converter.DateToLongConverter;
import alt.beanmapper.test.converter.LongToDateConverter;
import alt.beanmapper.test.dto.UserDto;
import alt.beanmapper.test.model.User;

/**
 * 
 * @author Albert Shift
 *
 */

public interface UserMapper {

	@Mapper({ @Property(src = "firstName", dest = "firstNameDto"),
			@Property(src = "lastName", dest = "lastNameDto"),
			@Property(src = "registrationDate", dest = "registrationDate", converter = DateToLongConverter.class),
			@Property(src = "area", dest = "areaDto"),
			@Property(src = "citizenship.countries{0}", dest = "primaryCitizenship"),
			@Property(src = "citizenship.countries{1:}", dest = "restCitizenships") })
	UserDto toUserDto(User user);

	@Mapper({ @Property(src = "firstNameDto", dest = "firstName"),
			@Property(src = "lastNameDto", dest = "lastName"),
			@Property(src = "registrationDate", dest = "registrationDate", converter = LongToDateConverter.class),
			@Property(src = "areaDto", dest = "area"),
			@Property(src = "primaryCitizenship", dest = "citizenship.countries{0}"),
			@Property(src = "restCitizenships", dest = "citizenship.countries{1:}") })
	User fromUserDto(UserDto userDto);

}
