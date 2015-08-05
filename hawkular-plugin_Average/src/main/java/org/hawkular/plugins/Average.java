
/**
 * This plugin returns average of the data list.
 */
 package org.hawkular.plugins;
 
import java.util.*;
import org.hawkular.*;

import rx.Observable;
import rx.observables.MathObservable;

public class Average implements StatisticalAlgo {

	int window_size;
	
	public String getPluginName() {
		return "Average";
	}

	public void set_params(int size) {
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

	Observable<Observable<T>> sliding_wind(Observable<T> elements) {
		return elements.window(window_size);
	}
	
	Observable<T> compute(Observable<Observable<T>> wind_list) {
		
		return wind_list.map(i -> i.averageDouble());
	}
	
*/

	public void compute(Observable<Integer> elements) {
		
		elements.window(window_size, window_size)
                .flatMap(MathObservable::averageInteger)
                .subscribe(i -> System.out.println(i));
	}
	
	// yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}
}

