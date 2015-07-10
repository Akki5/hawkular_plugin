
/**
 * This plugin returns average of the data list.
 */
 package org.hawkular.plugins;
 
import java.util.Arrays;
import org.hawkular.*;
public class Average implements StatisticalAlgo {

	List elements;
	int window_size;

	Average(int window_size)
	{
		this.window_size = window_size;
		elements = new ArrayList();
	}
	
	public String getPluginName() {
		return "Average";
	}

	public void setPoints (List values) {
		elements = values;
	}
	
	public void pushPoint (int value) {
		if(elements.size()<window_size)
			elements.add(value);
		else
		{
			for(int i=0;i<window_size;i++)
				elements[i] = elements[i+1];
			elements[window_size-1] = value;
		}
	}

	public double getResult() {
		int size = elements.length,sum=0;
		double avg=0.0;
		for(int i=0;i<size;i++)
			sum+=elements[i];
		avg = sum/(size*1.0);
		System.out.println(avg);
	}

	// yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}
}
