/**
 * This plugin returns standard deviation of the data list.
 */
 package org.hawkular.plugins;
 
import java.util.*;
import java.math.*;
import org.hawkular.*;
import org.hawkular.plugins.*;
public class StdDev implements StatisticalAlgo {

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
	
		int size = elements.size(),sum=0;
		if(size<window_size)
			return -1.0;
			
		double avg=0.0;
		for(int i=0;i<size;i++)
			sum+=elements.get(i);
		avg = sum/(size*1.0);
		
		double sqsum = 0.0,res;
		for(int i=0;i<size;i++)
			sqsum += (elements.get(i)-avg)*(elements.get(i)-avg);
		sqsum/=(size-1);
		res = Math.sqrt(sqsum);
		return res;
	}

	// yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}
}
