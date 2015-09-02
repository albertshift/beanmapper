package alt.beanmapper.compile.converter;

/**
 * 
 * @author Albert Shift
 *
 */

public class ConverterDestination {

	private long primitiveLong;
	private Long wrapperedLong;
	private Long boxingLong;
	private long unboxingLong;

	public long getPrimitiveLong() {
		return primitiveLong;
	}

	public void setPrimitiveLong(long primitiveLong) {
		this.primitiveLong = primitiveLong;
	}

	public Long getWrapperedLong() {
		return wrapperedLong;
	}

	public void setWrapperedLong(Long wrapperedLong) {
		this.wrapperedLong = wrapperedLong;
	}

	public Long getBoxingLong() {
		return boxingLong;
	}

	public void setBoxingLong(Long boxingLong) {
		this.boxingLong = boxingLong;
	}

	public long getUnboxingLong() {
		return unboxingLong;
	}

	public void setUnboxingLong(long unboxingLong) {
		this.unboxingLong = unboxingLong;
	}

}
