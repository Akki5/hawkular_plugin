package org.hawkular;

import java.io.*;
import java.util.*;
import java.net.URLClassLoader;
import java.net.URL;

  /**
   * In order to impose tight security restrictions on untrusted classes but
   * not on trusted system classes, we have to be able to distinguish between
   * those types of classes. This is done by keeping track of how the classes
   * are loaded into the system. By definition, any class that the interpreter
   * loads directly from the CLASSPATH is trusted. This means that we can't
   * load untrusted code in that way--we can't load it with Class.forName().
   * Instead, we create a ClassLoader subclass to load the untrusted code.
   * This one loads classes from a specified directory (which should not
   * be part of the CLASSPATH).
   */

public class PluginClassLoader extends ClassLoader {
    // This is the directory from which the classes will be loaded
    File jarDirectory;

    // Constructor to initialize the directory
    public PluginClassLoader (File dir) {
		jarDirectory = dir;
	}
    /**
     * This is one abstract method of ClassLoader that all subclasses must
     * define. It uses the concept of URLClassLoader to load classes 
	 * present in a package in the concerned .jar file.
     */
    public Class loadClass(String class_name) throws ClassNotFoundException {
      try {
        URL url = jarDirectory.toURI().toURL();
		URL[] urls = new URL[]{url};
		ClassLoader cl = new URLClassLoader(urls);
		Class cls = cl.loadClass("org.hawkular.plugins." + class_name);
		
        return cls;
      }
      // If anything goes wrong, throw a ClassNotFoundException error
      catch (Exception ex) { throw new ClassNotFoundException(ex.toString()); }
    }
}
