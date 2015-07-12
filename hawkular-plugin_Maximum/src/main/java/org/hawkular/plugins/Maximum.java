/**
 * This plugin returns maximum of the data list.
 */
 package org.hawkular.plugins;
 
import java.util.*;
import org.hawkular.*;
public class Maximum implements StatisticalAlgo {

	List<Double> elements;
	int window_size;
	
	public String getPluginName() {
		return "Maximum";
	}

	public void set_params(int size) {
		elements = new ArrayList<Double>();
		window_size = size;
	}

	public void pushPoint (double value) {
		if(elements.size()<window_size)
			elements.add(value*1.0);
		else
		{
			for(int i=0;i<window_size-1;i++)
			{
				elements.set(i,elements.get(i+1));
			}
			elements.set(window_size-1, value*1.0);
		}
	}
	
	public double getResult() {
	
		int size = elements.size();
		if(size<window_size)
			return -1.0;
			
		Collections.sort(elements);
		return elements.get(window_size-1);
	}

	/* yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}*/
}

