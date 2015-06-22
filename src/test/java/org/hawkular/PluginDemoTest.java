package org.hawkular;

import java.io.*;
import java.util.*;
import java.net.URLClassLoader;
import java.net.URL;

import org.junit.Test;
import static org.junit.Assert.*;

public class PluginDemoTest {

	@Test
	public void checkAllPlugins() throws Exception
	{
		
		//initializing an array for test purposes
		int A[] = new int[]{3,5,9,2,4,7};
		double result[] = new double[]{5.0,9.0,2.0,4.5,2.6076809620810595};
		
		PluginDemo demo = new PluginDemo(args);
		
		// load all classes in plugin directory via classloader class
		demo.getPlugins();
		
		// take input from user and use the concerned plugin
		for(i=0;i<demo.no_of_plugins();i++)
		{
			assertEquals(result[i],demo.runPlugins(i,A));
		}
	}

}

