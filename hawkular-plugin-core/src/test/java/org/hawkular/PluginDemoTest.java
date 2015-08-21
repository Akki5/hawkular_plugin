package org.hawkular;

import java.io.*;
import java.util.*;
import java.net.URLClassLoader;
import java.net.URL;

import org.junit.Test;
import static org.junit.Assert.*;

public class PluginDemoTest {

	//initializing an array for test purposes
	int A[];
	String Class_names[];
	
	// storing pre-defined results
	Map<String, Double> result;
	
	PluginDemo demo;
	
	//execute only once, in the starting 
	@BeforeClass
	public static void beforeClass() 
	{
		A = new int[]{3,5,9,2,4,7};
		Class_names = new String[]{"Average","Minimum","Maximum","Mode","StdDev"};
		
		result = new HashMap<String, Double>();
		
		// Add results of statistical operations.
			result.put("Average", 5.0);
			result.put("Maximum", 9.0);
			result.put("Minimum", 2.0);
			result.put("Mode", 4.5);
			result.put("StdDev", 2.6076809620810595);
		
		// load all classes in plugin directory via classloader class
		demo = new PluginDemo(args);
		demo.getPlugins();

	}

	@Test
	public void checkAllPlugins() throws Exception
	{	
		
		for(i=0;i<demo.no_of_plugins();i++)
		{
			assertEquals(result.get(ClassNames[i]),demo.runPlugins(ClassNames[i],A));
		}
	}
	
	@Test
	public void checkPluginAverage() throws Exception
	{
		
		String pluginName = "Average";
		
		assertEquals(result.get(pluginName),demo.runPlugins(pluginName,A));
		
	}
	
	@Test
	public void checkPluginMaximum() throws Exception
	{
		
		String pluginName = "Maximum";
		
		assertEquals(result.get(pluginName),demo.runPlugins(pluginName,A));
	}
	
	@Test
	public void checkPluginMinimum() throws Exception
	{
		
		String pluginName = "Minimum";
		
		assertEquals(result.get(pluginName),demo.runPlugins(pluginName,A));
	}
	
	@Test
	public void checkPluginMode() throws Exception
	{
		
		String pluginName = "Mode";
		
		assertEquals(result.get(pluginName),demo.runPlugins(pluginName,A));
	}
	
	@Test
	public void checkPluginStdDev() throws Exception
	{
		
		String pluginName = "StdDev";
		
		assertEquals(result.get(pluginName),demo.runPlugins(pluginName,A));
	}
	
}
