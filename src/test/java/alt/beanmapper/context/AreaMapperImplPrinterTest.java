package alt.beanmapper.context;

import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

public class AreaMapperImplPrinterTest {

	@Test
	public void test() throws IOException {

		ClassReader classReader = new ClassReader(AreaMapperImpl.class.getName());
		PrintWriter printWriter = new PrintWriter(System.out);
		TraceClassVisitor classVisitor = new TraceClassVisitor(printWriter);
		classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
	}

}
