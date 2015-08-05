package org.hawkular;

import java.io.*;
import java.util.*;
import java.net.URLClassLoader;
import java.net.URL;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import rx.Observable;

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
		double A[] = new double[]{1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0,10.0,11.0};
		Integer[] testInts = {1,2,3,4,5,6,7,8,9,10,11,12};
		int window_size = 5;
		String plugin_choice;
		
		Observable<Integer> elements = Observable.from(testInts);
		
		PluginDemo demo = new PluginDemo(args);
		
		// load all classes in plugin directory via classloader class
		demo.getPlugins();
		
		// take input from user and use the concerned plugin
		for(;;)
		{
			System.out.println("Enter the plugin name you want to use\nAverage\nMaximum\nMinimum\nMode\nStandard Deviation\nQuit");
			plugin_choice = in.next();
			if(plugin_choice.equals("Quit"))
				break;
			demo.runPlugins(plugin_choice, A, window_size, elements);
		}
		
	}
	
	protected void getPlugins() throws Exception {
		File jarDir;
		ClassLoader cl;
		
		String[] Class_names = {"Average","Maximum","Minimum"};
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
	
	public void runPlugins(String name, double A[], int window_size, Observable elements) {
	
		StatisticalAlgo pf = ((StatisticalAlgo)plugins.get(name));
		pf.set_params(window_size);
		pf.compute(elements);
		/*
		IntStream.range(0, A.length)
		.mapToDouble(
					i -> {
						pf.pushPoint(A[i]);
						return pf.getResult();
					})
		.forEach( 
				i -> {
						if(i!=-1.0)
							System.out.println(i);
				});
				
		*/
	}
	
}
