package alt.beanmapper.mapping;

/**
 * 
 * @author Albert Shift
 *
 */

public interface CustomMapper<S, D> {

	void map(S src, D dest);

}
