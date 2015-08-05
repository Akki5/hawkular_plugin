/**
 * This plugin returns maximum of the data list.
 */
 package org.hawkular.plugins;
 
import java.util.*;
import org.hawkular.*;

import rx.Observable;
import rx.observables.MathObservable;

public class Minimum implements StatisticalAlgo {

	int window_size;
	
	public String getPluginName() {
		return "Minimum";
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
		Collections.sort(elements);
		return elements.get(window_size-1);
	}

	Observable<Observable<T>> slice_wind(Observable<T> elements) {
		return elements.window(window_size);
	}
	
	Observable<T> compute(Observable<Observable<T>> wind_list) {
		
		Iterator<Observable<T>> iterator = wind_list.toBlocking().toIterable().iterator();
		Observable<T> result = Observable.create();
		while(iterator.hasNext())
		{
			result.onNext((iterator.next()).max());
		}
		return result;
	}
	
*/
	
	public void compute(Observable<Integer> elements) {
		
		elements.window(window_size, window_size)
                .flatMap(MathObservable::min)
                .subscribe(i -> System.out.println(i));
	}
	
	/* yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}*/
}

