
import java.io.File;
import java.util.*;

public class PluginDemo {

	/* will bw used later
	// the directory where we keep the plugin classes
	String pluginsDir;

	// a list where we keep an initialized object of each plugin class
	List plugins;
	*/

	public static void main (String args[]) {
		int A[] = new int[]{3,5,9,2,4,7};
		
		StatisticalAlgo ob=new StdDev();
		ob.setParameter(A);
		System.out.println(ob.getResult());
		
	}

}

