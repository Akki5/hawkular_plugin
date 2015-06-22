package org.hawkular;

import java.io.*;
import java.util.*;
import java.net.URLClassLoader;
import java.net.URL;

public class PluginDemo {

	// the directory where we keep the plugin classes
	String jarDir, pluginsDir;

	// a list where we keep an initialized object of each plugin class
	List plugins;
	
	// constructor to initialize plugin directory and the list plugins
	PluginDemo (String args[]) {
		if (args.length > 0)
			pluginsDir = args[0];
		else
			pluginsDir = "org.hawkular.plugins";
		
		jarDir = "target\\hawkular_plugin-1.0-SNAPSHOT.jar";
		
		plugins = new ArrayList();

	}
	
	public void getPlugins() throws Exception {
		File dir  = new File(System.getProperty("user.dir")+ File.separator + jarDir);
		ClassLoader cl = new PluginClassLoader(dir);
		if (dir.exists()) {
			
			String[] Class_names = {"Average","Minimum","Maximum","Mode","StdDev"};
			for(int i=0;i<Class_names.length;i++)
			{
				Class c = cl.loadClass(Class_names[i]);
				Class[] intf = c.getInterfaces();
				for (int j=0; j<intf.length; j++) {
					if (intf[j].getName().equals("org.hawkular.StatisticalAlgo")) {
						// the following line assumes that StatisticalAlgo has a no-argument constructor
						StatisticalAlgo pf = (StatisticalAlgo) c.newInstance();
						plugins.add(pf);
						continue;
					}
				}
			}
		}

	}
	
	public int no_of_plugins(){
		return plugins.size();
	}
	
	public double runPlugins(int i, int A[]) {
	
			((StatisticalAlgo)plugins.get(i)).setParameter(A);
			return ((StatisticalAlgo)plugins.get(i)).getResult();
			
		}

}

