package alt.beanmapper.compile.field;

/**
 * 
 * @author Albert Shift
 *
 */

public class FieldSource {

	protected int primitiveInt;

	public Integer wrapperInt;
	protected int boxingInt;
	public Integer unboxingInt;

	private int openAccessInt;
	public int closeAccessInt;

	private int openAccessIntBoxing;
	public Integer closeAccessIntUnboxing;

	public int getOpenAccessInt() {
		return openAccessInt;
	}

	public void setOpenAccessInt(int i) {
		this.openAccessInt = i;
	}

	public int getOpenAccessIntBoxing() {
		return openAccessIntBoxing;
	}

	public void setOpenAccessIntBoxing(int openAccessIntBoxing) {
		this.openAccessIntBoxing = openAccessIntBoxing;
	}

}
