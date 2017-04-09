import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		int lightCount=scanner.nextInt();
		int distance=scanner.nextInt();
		int[] lights=new int[lightCount];
		for(int i=0;i<lightCount;i++){
			lights[i]=scanner.nextInt();
		}
		scanner.close();
		Arrays.sort(lights);
		int[] maxDistance=new int[lightCount+1];
		for(int i=1;i<lightCount;i++){
			maxDistance[i]=lights[i]-lights[i-1];
		}
		maxDistance[0]=lights[0]>0?lights[0]:0;
		double max=0;
		for(int i=1;i<maxDistance.length-1;i++){
			if(maxDistance[i]/2.0>=max){
				max=maxDistance[i]/2.0;
			}
		}
		
		if(lights[0]>=max){
			isLorF=true;
			max=lights[0];
		}
		if(distance-lights[lightCount-1]>=max){
			isLorF=true;
			max=distance-lights[lightCount-1];
		}
		System.out.printf("%.2f",max);
	}
}