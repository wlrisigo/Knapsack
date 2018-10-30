import java.lang.*;


public class Knapsack {
	private static int knapsack(int[][] M, int[] weight, int[] val, int w, int size){
		
		if(size<0)
			return 0;
	
		if(M[size][w] != -1) {
			return M[size][w];
		}
		//if we add this to the group, it will go over weight constraint, so move on 
		if(weight[size]>w) {
			M[size][w] = knapsack(M, weight, val, w, size -1);	
		}else{
			
		int included = val[size] + knapsack(M, weight, val, w - weight[size], size-1);
		
		int without = knapsack(M, weight, val, w, size -1);	
		
		//if include bigger create new root //else make child of previous optimal 
		M[size][w] = Math.max(included, without);
		}
		printKnap(M, weight, val, size, w);
		return M[size][w];
	}
	
	public static void printKnap(int[][]M, int [] W8, int val[], int size, int weight){
		int value = M[size][weight];
		int w = weight;
		System.out.println("Total Value: " + M[size][weight]);
		//I have a value of 40
		for (int i = size; i > 0 && value > 0; i--) { 
			//child M[i-1]
            if (value == M[i-1][w]) {
            	//do nothing
            	System.out.println("NOT|" + " NODE: " + (i) + " || " + "Weight: "+ W8[i]); 
            } 
         
            //root of opt val 
            else { 
            	//print out the  of prervious subset
            	System.out.print("INCLUDED|" + " NODE: " + (i) + " || " + "Weight: "+ W8[i]  + " || " + "Value: "+ val[i]); 
            	//
                value = value- val[i];
                //update weight
                w -= W8[i]; 
    
                System.out.println( " || Go back until zero " + value);
            } 
        } 
		
	}
	
	
	
	public static void main(String[] args){ 
		
			
			int W = 11;
			int val[] = {0, 1, 6, 18, 22, 28};
			int weight[] = {0, 1, 2, 5, 6, 7};
			int size = val.length;
			int[][] M = new int[size][W+1];
			
			for(int i = 0; i < W; i++){
				M[0][i]=0;
			}
			for(int j = 0; j < size; j++){
				M[j][0]=0;
			}
			
			for(int i = 1; i < M.length; i++){
				for(int j = 0; j < M[0].length; j++){
					M[i][j] = -1;
				}
			}
			System.out.println(" -------FOR 11------- ");
			
		 knapsack(M, weight, val, W, val.length-1);
		 System.out.println();
		 System.out.println("Final Result: ");
		 System.out.println();
		 printKnap(M, weight, val, val.length-1, W);
		 System.out.println();
		 
		 
		System.out.println(" -------FOR 10------- ");
		 System.out.println();
		 knapsack(M, weight, val, W-1, val.length-1);
		 System.out.println();
		 System.out.println("Final Result: ");
		 System.out.println();
		 printKnap(M, weight, val, val.length-1, W-1);
	
	 
	} 		
}
		
