package org.hawkular;

import java.io.*;
import java.util.*;

public class PluginDemo {

	// the directory where we keep the plugin classes
	String pluginsDir;

	// a list where we keep an initialized object of each plugin class
	List plugins;
	
	// constructor to initialize plugin directory and the list plugins
	PluginDemo (String args[]) {
		if (args.length > 0)
			pluginsDir = args[0];
		else
			pluginsDir = "plugins";

		plugins = new ArrayList();

	}
	
	public static void main (String args[]) {
		
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
			System.out.println("Enter the choice number corresponding to the required mathematical operation.\n'1' for Average\n'2' for Maximum\n'3' for Minimum\n'4' for Mode\n'5' for Standard Deviation\n'0' to quit");
			choice = in.nextInt();
			if(choice==0)
				break;
			demo.runPlugins(choice-1,A);
		}
			
	}
	
	protected void getPlugins() {
		File dir = new File("D:/PROG/current_projects/gsoc/New folder/hawkular_plugin/target/classes/org/hawkular/" + pluginsDir);
		ClassLoader cl = new PluginClassLoader(dir);
		if (dir.exists() && dir.isDirectory()) {
			// we'll only load classes directly in this directory -
			// no subdirectories, and no classes in packages are recognized
			String[] files = dir.list();
			for (int i=0; i<files.length; i++) {
				try {
					// only consider files ending in ".class"
					if (! files[i].endsWith(".class"))
						continue;

					Class c = cl.loadClass(files[i].substring(0, files[i].indexOf(".")));
					Class[] intf = c.getInterfaces();
					for (int j=0; j<intf.length; j++) {
						if (intf[j].getName().equals("StatisticalAlgo")) {
							// the following line assumes that StatisticalAlgo has a no-argument constructor
							StatisticalAlgo pf = (StatisticalAlgo) c.newInstance();
							plugins.add(pf);
							continue;
						}
					}
				} catch (Exception ex) {
					System.err.println("File " + files[i] + " does not contain a valid PluginFunction class.");
				}
			}
		}
	}
	
	protected void runPlugins(int i, int A[]) {
			
			((StatisticalAlgo)plugins.get(i)).setParameter(A);
			System.out.println(((StatisticalAlgo)plugins.get(i)).getResult());
			
		}

}

