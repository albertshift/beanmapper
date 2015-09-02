package alt.beanmapper.test.model;

import java.sql.Date;

/**
 * 
 * @author Albert Shift
 *
 */

public class Area {

	private long id;
	private String name;
	private String unusedField;
	private Date updateTime;
	private long population;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnusedField() {
		return unusedField;
	}

	public void setUnusedField(String unusedField) {
		this.unusedField = unusedField;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public long getPopulation() {
		return population;
	}

	public void setPopulation(long population) {
		this.population = population;
	}

	@Override
	public String toString() {
		return "Area [id=" + id + ", name=" + name + ", unusedField=" + unusedField + "]";
	}

}
