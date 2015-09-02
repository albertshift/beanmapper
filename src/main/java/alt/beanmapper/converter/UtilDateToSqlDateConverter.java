package alt.beanmapper.converter;

import java.sql.Date;

/**
 * 
 * @author Albert Shift
 *
 */

public class UtilDateToSqlDateConverter implements Converter<java.util.Date, java.sql.Date> {

	@Override
	public Date convert(java.util.Date src) {
		return new Date(src.getTime());
	}

}
