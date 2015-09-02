package alt.beanmapper.util;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import alt.beanmapper.support.PackageException;

/**
 * 
 * @author Albert Shift
 *
 */

public final class PackageUtil {

	public static final String JAR_URL_SEPARATOR = "!/";

	private PackageUtil() {
	}

	private static void doFetchInPath(Set<Class<?>> classes, File directory, String packageName,
			ClassLoader classLoader) throws ClassNotFoundException {
		File[] dirContents = directory.listFiles();
		if (dirContents == null) {
			throw new PackageException("invalid directory " + directory.getAbsolutePath());
		}
		for (File file : dirContents) {
			String fileName = file.getName();
			if (file.isDirectory()) {
				doFetchInPath(classes, file, packageName + "." + fileName, classLoader);
			} else if (fileName.endsWith(".class")) {
				classes.add(classLoader.loadClass(packageName + '.' + fileName.substring(0, fileName.length() - 6)));
			}
		}
	}

	public static Set<Class<?>> discoveryPackage(String packagePath, ClassLoader classLoader)
			throws ClassNotFoundException {

		Set<Class<?>> classes = new HashSet<Class<?>>();

		Enumeration<URL> resources = null;
		try {
			resources = classLoader.getResources(packagePath.replace('.', '/'));
		} catch (IOException e) {
			throw new PackageException("invalid package " + packagePath, e);
		}

		while (resources.hasMoreElements()) {

			URL url = resources.nextElement();
			if (url == null) {
				throw new PackageException(packagePath + " - package not found");
			}

			String dirPath = StringUtil.replaceAll(url.getFile(), "%20", " ");
			int jarSeparator = dirPath.indexOf(JAR_URL_SEPARATOR);
			if (jarSeparator == -1) {
				File directory = new File(dirPath);
				if (!directory.exists()) {
					throw new PackageException(packagePath + " - invalid package");
				}
				doFetchInPath(classes, directory, packagePath, classLoader);

			} else {

				String rootEntry = dirPath.substring(jarSeparator + JAR_URL_SEPARATOR.length());
				if (!"".equals(rootEntry) && !rootEntry.endsWith("/")) {
					rootEntry = rootEntry + "/";
				}

				try {
					JarFile jarFile = null;
					URLConnection con = url.openConnection();
					if (con instanceof JarURLConnection) {
						JarURLConnection jarCon = (JarURLConnection) con;
						jarCon.setUseCaches(false);
						jarFile = jarCon.getJarFile();
					} else {
						String jarName = dirPath.substring(0, jarSeparator);
						jarName = StringUtil.replaceAll(jarName, " ", "%20");
						jarFile = new JarFile(jarName);
					}

					for (Enumeration<JarEntry> entries = jarFile.entries(); entries.hasMoreElements();) {
						JarEntry entry = entries.nextElement();
						String fileName = entry.getName();
						if (fileName.startsWith(rootEntry) && fileName.endsWith(".class")) {
							fileName = fileName.replace('/', '.');
							classes.add(classLoader.loadClass(fileName.substring(0, fileName.length() - 6)));
						}
					}
				} catch (IOException e) {
					throw new PackageException("invalid jar file", e);
				}
			}
		}
		return classes;
	}
}
