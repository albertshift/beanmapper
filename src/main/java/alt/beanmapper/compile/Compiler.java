package alt.beanmapper.compile;

import java.lang.reflect.Method;

import org.objectweb.asm.Opcodes;

import alt.beanmapper.context.MapperClassDescription;
import alt.beanmapper.context.MetadataContext;

/**
 * 
 * @author Albert Shift
 *
 */

public final class Compiler implements Opcodes {

	private final MetadataContext metadataContext;

	public Compiler(MetadataContext metadataContext) {
		this.metadataContext = metadataContext;
	}

	public MetadataContext getMetadataContext() {
		return metadataContext;
	}

	public byte[] compileMapper(MapperClassDescription mapperClassDescription) {

		MapperClassCompiler mapperClassCompiler = new MapperClassCompiler(this, mapperClassDescription);

		mapperClassCompiler.defineClass();

		for (Method m : mapperClassDescription.getMapperInterface().getDeclaredMethods()) {

			mapperClassCompiler.implementMethod(m);

		}

		mapperClassCompiler.defineConstructor();

		mapperClassCompiler.defineInnerClasses();

		mapperClassCompiler.defineConverterFields();

		mapperClassCompiler.implementConverterSetters();

		return mapperClassCompiler.finish();

	}

}
