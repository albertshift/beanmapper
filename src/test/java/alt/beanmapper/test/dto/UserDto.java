package alt.beanmapper.test.dto;

import java.util.List;

/**
 * 
 * @author Albert Shift
 *
 */

public class UserDto {

	private String firstNameDto;
	private String lastNameDto;
	private Long registrationDate;

	private AreaDto areaDto;

	private String primaryCitizenship;
	private List<String> restCitizenships;

	public String getFirstNameDto() {
		return firstNameDto;
	}

	public void setFirstNameDto(String firstNameDto) {
		this.firstNameDto = firstNameDto;
	}

	public String getLastNameDto() {
		return lastNameDto;
	}

	public void setLastNameDto(String lastNameDto) {
		this.lastNameDto = lastNameDto;
	}

	public Long getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Long registrationDate) {
		this.registrationDate = registrationDate;
	}

	public AreaDto getAreaDto() {
		return areaDto;
	}

	public void setAreaDto(AreaDto areaDto) {
		this.areaDto = areaDto;
	}

	public String getPrimaryCitizenship() {
		return primaryCitizenship;
	}

	public void setPrimaryCitizenship(String primaryCitizenship) {
		this.primaryCitizenship = primaryCitizenship;
	}

	public List<String> getRestCitizenships() {
		return restCitizenships;
	}

	public void setRestCitizenships(List<String> restCitizenships) {
		this.restCitizenships = restCitizenships;
	}

}
