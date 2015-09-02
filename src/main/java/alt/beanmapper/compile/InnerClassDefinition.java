package alt.beanmapper.compile;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;

/**
 * 
 * @author Albert Shift
 *
 */

public final class InnerClassDefinition implements Comparable<InnerClassDefinition> {

	private final String name;
	private final String outerName;
	private final String innerName;
	private final int access;

	public InnerClassDefinition(String name, String outerName, String innerName, int access) {
		this.name = name;
		this.outerName = outerName;
		this.innerName = innerName;
		this.access = access;
	}

	public InnerClassDefinition(Class<?> currentClass, Class<?> topClass) {
		this.name = Type.getInternalName(currentClass);
		this.outerName = Type.getInternalName(topClass);
		this.innerName = currentClass.getSimpleName();
		this.access = currentClass.getModifiers();
	}

	public void defineInnerClass(ClassWriter cv) {
		cv.visitInnerClass(name, outerName, innerName, access);
	}

	public String getName() {
		return name;
	}

	public String getOuterName() {
		return outerName;
	}

	public String getInnerName() {
		return innerName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InnerClassDefinition other = (InnerClassDefinition) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(InnerClassDefinition o) {
		if (name == null) {
			return -1;
		}
		if (o.name == null) {
			return 1;
		}
		return name.compareTo(o.name);
	}

	@Override
	public String toString() {
		return "InnerClassDefinition [name=" + name + ", outerName=" + outerName + ", innerName=" + innerName
				+ ", access=" + access + "]";
	}

}
