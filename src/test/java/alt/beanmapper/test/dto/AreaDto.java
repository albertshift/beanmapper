package alt.beanmapper.test.dto;

import java.util.Date;

/**
 * 
 * @author Albert Shift
 *
 */

public class AreaDto {

	private Long areaId;
	private String areaName;
	private Date updateTime;
	private double population;

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public double getPopulation() {
		return population;
	}

	public void setPopulation(double population) {
		this.population = population;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "AreaDto [areaId=" + areaId + ", areaName=" + areaName + "]";
	}

}
