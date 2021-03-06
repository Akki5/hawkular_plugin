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
		if(elements.size()>=window_size)
			elements.remove(0);
		elements.add(value);
	}
	
	public double getResult() {
	
		int size = elements.size();
		Collections.sort(elements);
		return elements.get(window_size-1);
	}
	
	// yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}
}

