package org.hawkular;

import java.io.*;
import java.util.*;
import java.net.URLClassLoader;
import java.net.URL;

public class PluginDemo {

	// the directory where we keep the plugin classes
	String pluginsDir;
	
	// a list where we keep an initialized object of each plugin class
	Map<String, StatisticalAlgo> plugins;
	
	// constructor to initialize plugin directory and the list plugins
	PluginDemo (String args[]) {
		if (args.length > 0)
			pluginsDir = args[0];
		else
			pluginsDir = "org.hawkular.plugins";
		
		plugins = new HashMap<String, StatisticalAlgo>();

	}
	
	public static void main (String args[]) throws Exception {
		
		Scanner in = new Scanner(System.in);
		
		//initializing a static array for test purposes
		int A[] = new int[]{3,5,9,2,4,7};
		String plugin_choice;
		
		PluginDemo demo = new PluginDemo(args);
		
		// load all classes in plugin directory via classloader class
		demo.getPlugins();
		
		// take input from user and use the concerned plugin
		for(;;)
		{
			System.out.println("Enter the plugin name you want to use\nAverage\nMaximum\nMinimum\nMode\nStandard Deviation\nQuit");
			plugin_choice = in.next();
			if(plugin_choice=="Quit")
				break;
			System.out.println(demo.runPlugins(plugin_choice,A));
		}
		
	}
	
	protected void getPlugins() throws Exception {
		File jarDir;
		ClassLoader cl;
		
		String[] Class_names = {"Average","Maximum","Minimum","Mode","StdDev"};
		for(int i=0;i<Class_names.length;i++)
		{
			jarDir = new File(System.getProperty("user.dir")+ File.separator + "target" + File.separator + "hawkular-plugin_"+ Class_names[i] + "-1.0-SNAPSHOT.jar");
			cl = new PluginClassLoader(jarDir);
			Class c = cl.loadClass(Class_names[i]);
			Class[] intf = c.getInterfaces();
			for (int j=0; j<intf.length; j++) {
				if (intf[j].getName().equals("org.hawkular.StatisticalAlgo")) {
					// the following line assumes that StatisticalAlgo has a no-argument constructor
					StatisticalAlgo pf = (StatisticalAlgo) c.newInstance();
					plugins.put(Class_names[i], pf);
					continue;
				}
			}
		}

	}
	
	public int no_of_plugins(){
		return plugins.size();
	}
	
	public double runPlugins(String name, int A[]) {
	
			((StatisticalAlgo)plugins.get(name)).setParameter(A);
			return ((StatisticalAlgo)plugins.get(name)).getResult();
			
	}
	
}
