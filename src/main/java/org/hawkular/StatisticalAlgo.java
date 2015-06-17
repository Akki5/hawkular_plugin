package org.hawkular;

public interface StatisticalAlgo {

	// return the name of this plugin
	public String getPluginName();
		
	// let the application pass in a parameter
	public void setParameter (int param[]);

	// retrieve a result from the plugin
	public double getResult();

	// can be called to determine whether the plugin
	// aborted execution due to an error condition
	//public boolean hasError();
}
