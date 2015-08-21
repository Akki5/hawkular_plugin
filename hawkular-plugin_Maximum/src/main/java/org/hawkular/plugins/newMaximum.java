/**
 * This plugin returns maximum of the data list.
 */
 package org.hawkular.plugins;
 
import java.util.*;
import org.hawkular.*;

import rx.Observable;
import rx.observables.MathObservable;

public class Maximum implements StatisticalAlgo {

	int window_size;
	
	public String getPluginName() {
		return "Maximum";
	}

	public void set_params(int size) {
		window_size = size;
	}
	
	public void compute(Observable<Integer> elements) {
		
		elements.window(window_size, window_size)
                .flatMap(MathObservable::max)
                .subscribe(i -> System.out.println(i));
	}
	
	// yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}
}

