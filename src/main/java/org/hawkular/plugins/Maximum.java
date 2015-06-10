
/**
 * This plugin returns maximum of the data list.
 */
  package org.hawkular.plugins;
  
import java.util.Arrays;
public class Maximum implements StatisticalAlgo {

	int A[];
	
	public String getPluginName() {
		return "Maximum";
	}

	public void setParameter (int param[]) {
		A = param;
	}

	public double getResult() {
		Arrays.sort(A);
		return A[A.length-1];
	}

	/* yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}*/
}

