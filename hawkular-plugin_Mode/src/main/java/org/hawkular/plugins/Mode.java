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
		return "Mode";
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
		double res,temp,max=0;
		temp=1;
		for(int i=1;i<size;i++)
		{
			if(elements.get(i)==elements.get(i-1))
			{
				temp++;
				continue;
			}
			
			if(temp>max)
			{
				max=temp;
				temp=1;
				res=elements.get(i-1);
			}
		}
		
		return res;
	}

	// yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}
}
