
/**
 * This plugin returns average of the data list.
 */
 package org.hawkular.plugins;
 
import java.util.*;
import org.hawkular.*;

import rx.Observable;

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
/*
	public void pushPoint (double value) {
		if(elements.size()>=window_size)
			elements.remove(0);
		elements.add(value);
	}
	
	public double getResult() {
	
		int size = elements.size();
		double sum=0.0,avg=0.0;
		for(int i=0;i<size;i++)
			sum+=elements.get(i);
		avg = sum/(size*1.0);
		return avg;
	}
*/
	Observable<Observable<T>> slice_wind(Observable<T> elements) {
		return elements.window(window_size);
	}
	
	Observable<T> compute(Observable<Observable<T>> wind_list) {
		
		Iterator<Observable<T>> iterator = wind_list.toBlocking().toIterable().iterator();
		Observable<T> result = Observable.create();
		while(iterator.hasNext())
		{
			result.onNext((iterator.next()).averageDouble());
		}
		return result;
	}
	
	// yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}
}

