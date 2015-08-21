package org.hawkular;

import rx.Observable;

public interface StatisticalAlgo {

	// return the name of this plugin
	public String getPluginName();
	
	public void set_params(int size);

	public void compute(Observable<Integer> elements);

	// can be called to determine whether the plugin
	// aborted execution due to an error condition
	public boolean hasError();
}
