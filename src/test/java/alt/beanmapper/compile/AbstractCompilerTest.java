package alt.beanmapper.compile;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

import alt.beanmapper.compile.Compiler;
import alt.beanmapper.context.MetadataContext;
import alt.beanmapper.mapping.MappingMetadataContext;

/**
 * 
 * @author Albert Shift
 *
 */

public abstract class AbstractCompilerTest {

	protected Compiler compiler;

	@Before
	public void setup() {
		MetadataContext metadataContext = new MappingMetadataContext();
		compiler = new Compiler(metadataContext);
	}

	public void assertEqualsImpl(Class<?> beanMapperImplClass, byte[] byteCode, String methodName)
			throws IOException {
		ClassReader expectedClassReader = new ClassReader(beanMapperImplClass.getName());
		ClassReader actualClassReader = new ClassReader(byteCode);

		List<String> expectedCode = getMethodImpl(expectedClassReader, methodName);
		List<String> actualCode = getMethodImpl(actualClassReader, methodName);

		if (!Arrays.equals(expectedCode.toArray(), actualCode.toArray())) {
			printDiff(expectedCode, actualCode);
		}

		Assert.assertEquals(expectedCode, actualCode);
	}

	private void printDiff(List<String> expectedCode, List<String> actualCode) {
		int max = Math.max(expectedCode.size(), actualCode.size());

		for (int i = 0; i != max; ++i) {
			String expectedLine = i < expectedCode.size() ? expectedCode.get(i) : "";
			String actualLine = i < actualCode.size() ? actualCode.get(i) : "";

			if (expectedLine.equals(actualLine)) {
				System.out.println(expectedLine + " == " + actualLine);
			} else {
				System.err.println(expectedLine + " != " + actualLine);
			}
		}
	}

	public List<String> getMethodImpl(ClassReader classReader, String methodName) {

		StringWriter stringWriter = new StringWriter();
		PrintWriter printWriter = new PrintWriter(stringWriter);
		TraceClassVisitor classVisitor = new TraceClassVisitor(null, new MethodSourceCodeTextifier(), printWriter);
		MethodSelectorVisitor methodSelectorVisitor = new MethodSelectorVisitor(classVisitor, methodName);
		classReader.accept(methodSelectorVisitor, ClassReader.SKIP_DEBUG);

		List<String> result = new ArrayList<String>();

		for (String line : stringWriter.toString().split("\n")) {
			line = line.trim();
			if (line.length() > 0) {
				result.add(line);
			}
		}

		return result;

	}

	public void printImpl(byte[] byteCode) throws IOException {

		ClassReader classReader = new ClassReader(byteCode);
		PrintWriter printWriter = new PrintWriter(System.out);
		TraceClassVisitor classVisitor = new TraceClassVisitor(printWriter);
		classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
	}

	public void printImpl(Class<?> beanMapperImplClass) throws IOException {

		ClassReader classReader = new ClassReader(beanMapperImplClass.getName());
		PrintWriter printWriter = new PrintWriter(System.out);
		TraceClassVisitor classVisitor = new TraceClassVisitor(printWriter);
		classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
	}

	public void printImpl(Class<?> beanMapperImplClass, String methodName) throws IOException {

		ClassReader classReader = new ClassReader(beanMapperImplClass.getName());
		PrintWriter printWriter = new PrintWriter(System.out);
		TraceClassVisitor classVisitor = new TraceClassVisitor(null, new MethodSourceCodeTextifier(), printWriter);
		MethodSelectorVisitor methodSelectorVisitor = new MethodSelectorVisitor(classVisitor, methodName);
		classReader.accept(methodSelectorVisitor, ClassReader.SKIP_DEBUG);

	}

}
