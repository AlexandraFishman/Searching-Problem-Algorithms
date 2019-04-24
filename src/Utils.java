
public class Utils {

	public static Integer[][] deepCopy(Integer [][] orig){
		Integer[][] copy = new Integer[orig.length][orig[0].length];
		for(int i=0; i<copy.length; i++){
			for(int j=0; j<copy[0].length; j++){
				copy[i][j] = orig[i][j];
			}
		}
		return copy;
	}
	
}
