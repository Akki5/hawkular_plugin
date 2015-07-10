/**
 * This plugin returns maximum of the data list.
 */
 package org.hawkular.plugins;
 
import java.util.Arrays;
import org.hawkular.*;
public class Maximum implements StatisticalAlgo {

	List elements;
	int window_size;
	
	Maximum(int window_size)
	{
		this.window_size = window_size;
		elements = new ArrayList();
	}
	
	public String getPluginName() {
		return "Maximum";
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
		Arrays.sort(elements);
		return elements[elements.length-1];
	}

	/* yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}*/
}

