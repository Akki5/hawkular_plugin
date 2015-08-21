
/**
 * This plugin returns average of the data list.
 */
 package org.hawkular.plugins;
 
import java.util.*;
import org.hawkular.*;

import rx.Observable;
import rx.observables.MathObservable;

public class StdDev implements StatisticalAlgo {

	int window_size;
	
	public String getPluginName() {
		return "StdDev";
	}

	public void set_params(int size) {
		window_size = size;
	}

	public void compute(Observable<Integer> elements) {
		
		// TODO : use Average.java to compute the average of the data set
		// then compute the standard deviation using mathematical operators
		
	}
	
	// yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}
}

