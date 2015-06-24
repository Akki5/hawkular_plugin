
/**
 * This plugin returns average of the data list.
 */
 package org.hawkular.plugins;
 
import java.util.Arrays;
import org.hawkular.*;
public class Average implements StatisticalAlgo {

	int A[];
	
	public String getPluginName() {
		return "Average";
	}

	public void setParameter (int param[]) {
		A = param;
	}

	public double getResult() {
		int size = A.length,sum=0;
		double avg=0.0;
		for(int i=0;i<size;i++)
			sum+=A[i];
		avg = sum/(size*1.0);
		return avg;
	}

	// yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}
}

