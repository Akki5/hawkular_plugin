
/**
 * This plugin returns average of the data list.
 */
 package org.hawkular.plugins;
 
import java.util.*;
import org.hawkular.*;
public class Average implements StatisticalAlgo {

	List<Double> elements;
	int window_size;
	
	public String getPluginName() {
		return "Average";
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
			
		double sum=0.0,avg=0.0;
		for(int i=0;i<size;i++)
			sum+=elements.get(i);
		avg = sum/(size*1.0);
		return avg;
	}

	// yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}
}

