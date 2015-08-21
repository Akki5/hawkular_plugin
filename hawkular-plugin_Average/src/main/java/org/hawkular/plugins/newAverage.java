
/**
 * This plugin returns average of the data list.
 */
 package org.hawkular.plugins;
 
import java.util.*;
import org.hawkular.*;

import rx.Observable;
import rx.observables.MathObservable;

public class newAverage implements newStatisticalAlgo {

	int window_size;
	
	public String getPluginName() {
		return "newAverage";
	}

	public void set_params(int size) {
		window_size = size;
	}

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

