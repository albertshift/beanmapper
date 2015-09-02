package alt.beanmapper.compile.converter;

/**
 * 
 * @author Albert Shift
 *
 */

public class ConverterSource {

	private int primitiveInt;
	private Integer wrapperedInt;

	private int boxingInt;
	private Integer unboxingInt;

	public int getPrimitiveInt() {
		return primitiveInt;
	}

	public void setPrimitiveInt(int primitiveInt) {
		this.primitiveInt = primitiveInt;
	}

	public Integer getWrapperedInt() {
		return wrapperedInt;
	}

	public void setWrapperedInt(Integer wrapperedInt) {
		this.wrapperedInt = wrapperedInt;
	}

	public int getBoxingInt() {
		return boxingInt;
	}

	public void setBoxingInt(int boxingInt) {
		this.boxingInt = boxingInt;
	}

	public Integer getUnboxingInt() {
		return unboxingInt;
	}

	public void setUnboxingInt(Integer unboxingInt) {
		this.unboxingInt = unboxingInt;
	}
}
