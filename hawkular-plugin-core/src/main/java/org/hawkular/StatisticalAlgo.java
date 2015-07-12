package org.hawkular;

public interface StatisticalAlgo {

	// return the name of this plugin
	public String getPluginName();
	
	public void set_params(int size);

	public void pushPoint (double value);

	// retrieve a result from the plugin
	public double getResult();

	// can be called to determine whether the plugin
	// aborted execution due to an error condition
	//public boolean hasError();
}
