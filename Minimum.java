
/**
 * This plugin returns minimum of the data list.
 */
 
import java.util.Arrays;
public class Minimum implements StatisticalAlgo {
	
	int A[];

	public String getPluginName() {
		return "Minimum";
	}
	
	public void setParameter (int param[]) {
			A = param;
	}

	public int getResult() {
		Arrays.sort(A);
		return A[0];
		
	}

	/* yes, ths operation can fail, but we are going to ignore this here
	public boolean hasError() {
		return false;
	}*/
}