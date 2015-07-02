/**
 * This plugin returns standard deviation of the data list.
 */
 package org.hawkular.plugins;
 
import java.util.Arrays;
import java.math.*;
import org.hawkular.*;
import org.hawkular.plugins.*;
public class StdDev implements StatisticalAlgo {

	int A[];
	
	public String getPluginName() {
		return "Standard Deviation";
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
		
		double sqsum = 0.0,res;
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
