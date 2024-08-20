import java.util.Scanner;

class Solution
{
    public static int answer;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            answer = Integer.MAX_VALUE;
			int n = sc.nextInt();
            int h = sc.nextInt();
            int[] arr = new int[n];
            for(int i = 0; i < n; i++){
                arr[i] = sc.nextInt();
            }
            
			for(int r = 1; r <= n; r++){
                DFS(arr, new boolean[n], 0, n, r, h);
            }
			System.out.println("#"+test_case+" "+answer);
		}
	}
    
    public static void DFS(int[] arr, boolean[] visited, int start, int n, int r, int h){
    	if (r == 0){
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (visited[i]) {
                    sum += arr[i];
                }
            }
            if(sum-h >= 0){
                answer = Math.min(answer, sum-h);
            }
            return;
    	}
        
    	else{
        	for (int i = start; i < n; i++) {
            	visited[i] = true;
            	DFS(arr, visited, i + 1, n, r - 1, h);
            	visited[i] = false;
        	}
    	}
    }
}