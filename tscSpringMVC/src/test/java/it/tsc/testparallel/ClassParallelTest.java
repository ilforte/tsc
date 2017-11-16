package it.tsc.testparallel;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;

public class ClassParallelTest {

  @Test
  public void test() throws ClassNotFoundException, IOException {
    @SuppressWarnings("rawtypes")
    Class[] cls = getClasses("it.tsc.test.dao");

    for (Class<?> clazz : cls) {
      // Parallel among classes
      JUnitCore.runClasses(ParallelComputer.classes(), clazz);
      System.out.println("----------------------------");
      // Parallel among methods in a class
      JUnitCore.runClasses(ParallelComputer.methods(), clazz);
      System.out.println("----------------------------");
      // Parallel all methods in all classes
      JUnitCore.runClasses(new ParallelComputer(true, true), clazz);
    }
  }


  /**
   * Scans all classes accessible from the context class loader which belong to the given package
   * and subpackages.
   *
   * @param packageName The base package
   * @return The classes
   * @throws ClassNotFoundException
   * @throws IOException
   */
  private Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    assert classLoader != null;
    String path = packageName.replace('.', '/');
    Enumeration<URL> resources = classLoader.getResources(path);
    List<File> dirs = new ArrayList<File>();
    while (resources.hasMoreElements()) {
      URL resource = resources.nextElement();
      dirs.add(new File(resource.getFile()));
    }
    ArrayList<Class> classes = new ArrayList<Class>();
    for (File directory : dirs) {
      classes.addAll(findClasses(directory, packageName));
    }
    return classes.toArray(new Class[classes.size()]);
  }


  /**
   * Recursive method used to find all classes in a given directory and subdirs.
   *
   * @param directory The base directory
   * @param packageName The package name for classes found inside the base directory
   * @return The classes
   * @throws ClassNotFoundException
   */
  private List<Class> findClasses(File directory, String packageName)
      throws ClassNotFoundException {
    List<Class> classes = new ArrayList<Class>();
    if (!directory.exists()) {
      return classes;
    }
    File[] files = directory.listFiles();
    for (File file : files) {
      if (file.isDirectory()) {
        assert !file.getName().contains(".");
        classes.addAll(findClasses(file, packageName + "." + file.getName()));
      } else if (file.getName().endsWith(".class")) {
        Class clazz = Class
            .forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6));
        if (getMethodsAnnotatedWith(clazz, Test.class, Ignore.class) != null
            && getMethodsAnnotatedWith(clazz, Test.class, Ignore.class).size() != 0) {
          classes.add(Class.forName(
              packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
        }
      }
    }
    return classes;
  }

  private static List<Method> getMethodsAnnotatedWith(final Class<?> type,
      final Class<? extends Annotation> annotation, final Class<? extends Annotation> ignore) {
    final List<Method> methods = new ArrayList<Method>();
    Class<?> klass = type;
    while (klass != Object.class) { // need to iterated thought hierarchy in order to retrieve
                                    // methods from above the current instance
      // iterate though the list of methods declared in the class represented by klass variable, and
      // add those annotated with the specified annotation
      final List<Method> allMethods =
          new ArrayList<Method>(Arrays.asList(klass.getDeclaredMethods()));
      for (final Method method : allMethods) {
        if (ignore != null) {
          if (method.isAnnotationPresent(annotation) && !method.isAnnotationPresent(ignore)) {
            Annotation annotInstance = method.getAnnotation(annotation);
            // TODO process annotInstance
            methods.add(method);
          }
        } else {
          if (method.isAnnotationPresent(annotation)) {
            Annotation annotInstance = method.getAnnotation(annotation);
            // TODO process annotInstance
            methods.add(method);
          }
        }
      }
      // move to the upper class in the hierarchy in search for more methods
      klass = klass.getSuperclass();
    }
    return methods;
  }
}
