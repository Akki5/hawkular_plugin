
/**
 * This plugin returns standard deviation of the data list.
 */
  package org.hawkular.plugins;
  
import java.util.Arrays;
import java.math.*;
public class StdDev implements StatisticalAlgo {

	int A[];
	
	public String getPluginName() {
		return "Standard Deviation";
	}

	public void setParameter (int param[]) {
		A = param;
	}

	public double getResult() {
		Average ob = new Average();
		ob.setParameter(A);
		double avg = ob.getResult();
		double sqsum = 0.0,res;
		int size = A.length;
		for(int i=0;i<size;i++)
			sqsum += (A[i]-avg)*(A[i]-avg);
		sqsum/=(size-1);
		res = Math.sqrt(sqsum);
		return res;
	}

	// yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}
}

