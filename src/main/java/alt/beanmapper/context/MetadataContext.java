package alt.beanmapper.context;

public interface MetadataContext {

	PropertyDescription[] getProperties(Class<?> beanClass);

	public void clear();

}
