/**
 * This plugin returns mode of the data list.
 */
 package org.hawkular.plugins;
 
import java.util.*;
import org.hawkular.*;
public class Mode implements StatisticalAlgo {

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
		if(elements.size()>=window_size)
			elements.remove(0);
		elements.add(value);
	}

	public double getResult() {
		
		int size = elements.size();
		if(size<window_size)
			return -1.0;
			
		Collections.sort(elements);
		double res;
		if(size%2!=0)
			res = (elements.get(size/2));
		else
			res = (elements.get(size/2-1) + elements.get(size/2))/2.0;
		
		return res;
	}

	/* yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}*/
}
