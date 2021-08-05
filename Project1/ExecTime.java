package project1;

public class ExecTime {

    public static void main(String[] args) {   
   
      long startTime, endTime, execTime;
      int N = 1000000;
      String ans;
               
      startTime = System.nanoTime();
               
      for (int i=0; i<N; i++) {
    	  d2x(65535);
      }
      
      ans = d2x(65535);
        
      System.out.println(ans);
               
      endTime = System.nanoTime();
               
      execTime = endTime-startTime;
            
      System.out.println("Execution Time in nano seconds = " + (double)(execTime/N));

   }
   
   static String d2x(int n) {
		
	   String s = Integer.toHexString(n);
	   
	   String hex = "";
	    
	   for(int i=0; i<s.length(); i++) {
		   hex = hex + s.charAt(i) + " ";
	   }
	   
	    return hex;
   }
   
}