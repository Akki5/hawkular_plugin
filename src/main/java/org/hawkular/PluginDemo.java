package org.hawkular;

import java.io.*;
import java.util.*;
import java.net.URLClassLoader;
import java.net.URL;

public class PluginDemo {

	// the directory where we keep the plugin classes
	String jarDir, pluginsDir;

	// a list where we keep an initialized object of each plugin class
	static List plugins;
	
	// constructor to initialize plugin directory and the list plugins
	PluginDemo (String args[]) {
		if (args.length > 0)
			pluginsDir = args[0];
		else
			pluginsDir = "org.hawkular.plugins";
		
		jarDir = "target" + File.separator + "hawkular_plugin-1.0-SNAPSHOT.jar";
		
		plugins = new ArrayList();

	}
	
	public static void main (String args[]) throws Exception {
		
		Scanner in = new Scanner(System.in);
		
		//initializing a static array for test purposes
		int A[] = new int[]{3,5,9,2,4,7};
		int choice=0;
		
		PluginDemo demo = new PluginDemo(args);
		
		// load all classes in plugin directory via classloader class
		demo.getPlugins();
		
		// take input from user and use the concerned plugin
		for(;;)
		{
			System.out.println("Enter the choice number corresponding to the required mathematical operation.");
			for(int i=0;i<plugins.size();i++)
				System.out.println("'" + (i+1) + "' for " + ((StatisticalAlgo)plugins.get(i)).getPluginName());
			System.out.println("'0' to quit");
			choice = in.nextInt();
			if(choice==0)
				break;
			demo.runPlugins(choice-1,A);
		}
			
	}
	
	protected void getPlugins() throws Exception {
		File dir  = new File(System.getProperty("user.dir")+ File.separator + jarDir);
		ClassLoader cl = new PluginClassLoader(dir);
		if (dir.exists()) {
			
			String[] Class_names = {"Average","Maximum","Minimum","Mode","StdDev"};
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
	
	protected void runPlugins(int i, int A[]) {
	
			((StatisticalAlgo)plugins.get(i)).setParameter(A);
			System.out.println(((StatisticalAlgo)plugins.get(i)).getResult());
			
		}

}

