package org.hawkular;

import java.io.*;
import java.util.*;
import java.net.URLClassLoader;
import java.net.URL;

import org.junit.Test;
import static org.junit.Assert.*;

public class PluginDemoTest {

	@Test
	public void checkResult() throws Exception
	{
		Scanner in = new Scanner(System.in);
		
		//initializing a static array for test purposes
		int A[] = new int[]{3,5,9,2,4,7};
		int choice=0;
		double result[] = new double[]{5.0,2.0,9.0,4.5,2.6076809620810595};
		
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
			assertEquals(result[choice-1],demo.runPlugins(choice-1,A));
		}
	}

}

