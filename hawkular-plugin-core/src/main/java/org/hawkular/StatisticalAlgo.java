package org.hawkular;

import rx.Observable;

public interface StatisticalAlgo {

	// return the name of this plugin
	public String getPluginName();
	
	public void set_params(int size);

	//public void pushPoint (double value);

	// retrieve a result from the plugin
	//public double getResult();
	
	Observable<Observable<T>> slice_wind(Observable<T> elements);
	
	Observable<T> compute(Observable<Observable<T>> wind_list);

	// can be called to determine whether the plugin
	// aborted execution due to an error condition
	//public boolean hasError();
}
