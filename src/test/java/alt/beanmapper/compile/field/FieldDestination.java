package alt.beanmapper.compile.field;

/**
 * 
 * @author Albert Shift
 *
 */

public class FieldDestination {

	public int primitiveInt;

	protected Integer wrapperInt;
	public Integer boxingInt;
	protected int unboxingInt;

	public int openAccessInt;
	private int closeAccessInt;

	public Integer openAccessIntBoxing;
	private int closeAccessIntUnboxing;

	public int getCloseAccessInt() {
		return closeAccessInt;
	}

	public void setCloseAccessInt(int closeAccessInt) {
		this.closeAccessInt = closeAccessInt;
	}

	public int getCloseAccessIntUnboxing() {
		return closeAccessIntUnboxing;
	}

	public void setCloseAccessIntUnboxing(int closeAccessIntUnboxing) {
		this.closeAccessIntUnboxing = closeAccessIntUnboxing;
	}

}
