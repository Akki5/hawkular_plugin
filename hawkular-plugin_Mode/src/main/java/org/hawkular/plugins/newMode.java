
/**
 * This plugin returns average of the data list.
 */
 package org.hawkular.plugins;
 
import java.util.*;
import org.hawkular.*;

import rx.Observable;
import rx.observables.MathObservable;

public class Mode implements StatisticalAlgo {

	int window_size;
	
	public String getPluginName() {
		return "Mode";
	}

	public void set_params(int size) {
		window_size = size;
	}

	public void compute(Observable<Integer> elements) {
		
		// TODO : use Observable.window() and Observable.toSortedList()
		// then find the data value with the highest frequency in the data set to compute mode
		
	}
	
	// yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}
}

