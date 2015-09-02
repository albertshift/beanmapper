package alt.beanmapper.compile;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.inject.Named;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import alt.beanmapper.compile.options.MapperServiceOptions;
import alt.beanmapper.context.MapperClassDescription;
import alt.beanmapper.context.MetadataContext;
import alt.beanmapper.mapping.MapperClass;
import alt.beanmapper.util.BeanUtil;
import alt.beanmapper.util.StringUtil;

/**
 * 
 * @author Albert Shift
 *
 */

public final class MapperClassCompiler implements Opcodes {

	public static final String JSR330_NAMED_DESC = Type.getDescriptor(Named.class);
	public static final String JSR330_INJECT_DESC = Type.getDescriptor(Inject.class);

	private final Compiler compiler;
	private final MapperClassDescription mapperClassDescription;

	private final Type mapperInterfaceType;
	private final String implClassInternalName;

	private final MapperServiceOptions mapperServiceOptions;

	private final ClassWriter cv;

	private final Map<String, Class<?>> converterFields = new HashMap<String, Class<?>>();
	private final Set<InnerClassDefinition> innerClasses = new TreeSet<InnerClassDefinition>();

	public MapperClassCompiler(Compiler compiler, MapperClassDescription mapperClassDescription) {
		this.compiler = compiler;
		this.mapperClassDescription = mapperClassDescription;

		this.mapperInterfaceType = Type.getType(mapperClassDescription.getMapperInterface());

		MapperClass mc = mapperClassDescription.getMapperInterface().getAnnotation(MapperClass.class);
		this.mapperServiceOptions = new MapperServiceOptions(mc);

		this.implClassInternalName = mapperClassDescription.getImplClassName().replace('.', '/');

		this.cv = new ClassWriter(ClassWriter.COMPUTE_MAXS);

		addInnerClassIfNeeded(mapperClassDescription.getMapperInterface());

	}

	public Compiler getCompiler() {
		return compiler;
	}

	public MapperServiceOptions getMapperServiceOptions() {
		return mapperServiceOptions;
	}

	public String getImplClassInternalName() {
		return implClassInternalName;
	}

	public String getImplPackageName() {
		return mapperClassDescription.getImplPackageName();
	}

	public Type getMapperInterfaceType() {
		return mapperInterfaceType;
	}

	public MetadataContext getMetadataContext() {
		return compiler.getMetadataContext();
	}

	public void defineClass() {

		cv.visit(V1_6, ACC_PUBLIC + ACC_SUPER, implClassInternalName, null, "java/lang/Object",
				new String[] { mapperInterfaceType.getDescriptor() });

		if (mapperServiceOptions.useJsr330()) {
			cv.visitAnnotation(JSR330_NAMED_DESC, true);
		}

		cv.visitEnd();

	}

	public void defineConstructor() {
		MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
		mv.visitCode();
		mv.visitVarInsn(ALOAD, 0);
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
		mv.visitInsn(RETURN);
		mv.visitMaxs(1, 1);
		mv.visitEnd();
	}

	public byte[] finish() {
		return cv.toByteArray();
	}

	public void implementMethod(Method m) {

		MapperCompiler mapperCompiler = new MapperCompiler(this, m);

		MethodVisitor mv = mapperCompiler.startImplementation(cv);

		mapperCompiler.copyProperties(mv);

		mapperCompiler.endImplementation(mv);

	}

	public void defineInnerClasses() {

		for (InnerClassDefinition def : innerClasses) {
			def.defineInnerClass(cv);
		}

	}

	public void defineConverterFields() {

		for (Map.Entry<String, Class<?>> entry : converterFields.entrySet()) {

			Type conveterType = Type.getType(entry.getValue());

			FieldVisitor fv = cv.visitField(ACC_PRIVATE, entry.getKey(), conveterType.getDescriptor(), null, null);

			if (mapperServiceOptions.useJsr330()) {
				fv.visitAnnotation(JSR330_INJECT_DESC, true);
			}

		}

	}

	public void implementConverterSetters() {

		for (Map.Entry<String, Class<?>> entry : converterFields.entrySet()) {

			Type conveterType = Type.getType(entry.getValue());

			MethodVisitor mv = cv.visitMethod(ACC_PUBLIC, BeanUtil.getSetterName(entry.getKey()), "("
					+ conveterType.getDescriptor() + ")V", null, null);
			mv.visitCode();

			mv.visitVarInsn(ALOAD, 0);
			mv.visitVarInsn(ALOAD, 1);

			mv.visitFieldInsn(PUTFIELD, implClassInternalName, entry.getKey(), conveterType.getDescriptor());

			mv.visitInsn(RETURN);
			mv.visitMaxs(2, 2);
			mv.visitEnd();
		}

	}

	public void addInnerClassIfNeeded(Class<?> currentClass) {

		Class<?> topClass = currentClass.getEnclosingClass();

		if (topClass != null) {

			innerClasses.add(new InnerClassDefinition(currentClass, topClass));

		}

	}

	public String getConverterFieldName(Class<?> converterClass) {

		String fieldName = getDefaultConverterFieldName(converterClass);

		return tryRegisterConverterFieldName(fieldName, converterClass);

	}

	private String tryRegisterConverterFieldName(String fieldName, Class<?> converterClass) {

		Class<?> existingConverterClass = converterFields.get(fieldName);

		if (existingConverterClass == null) {
			converterFields.put(fieldName, converterClass);
			return fieldName;
		}

		if (existingConverterClass == converterClass) {
			return fieldName;
		}

		return tryRegisterConverterFieldName(fieldName + "N", converterClass);

	}

	private String getDefaultConverterFieldName(Class<?> converterClass) {
		return StringUtil.uncapitalize(converterClass.getSimpleName());
	}

}
