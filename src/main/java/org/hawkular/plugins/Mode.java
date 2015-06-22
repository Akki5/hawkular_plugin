
/**
 * This plugin returns mode of the data list.
 */
 package org.hawkular.plugins;
 
import java.util.Arrays;
import org.hawkular.*;
public class Mode implements StatisticalAlgo {

	int A[];
	
	public String getPluginName() {
		return "Mode";
	}

	public void setParameter (int param[]) {
		A = param;
	}

	public double getResult() {
		Arrays.sort(A);
		int size = A.length;
		double res;
		if(size%2!=0)
			res = A[size/2];
		else
			res = (A[size/2-1] + A[size/2])/2.0;
		return res;
	}

	/* yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}*/
}

