import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Integer.parseInt(st.nextToken());
        Integer[] arr = new Integer[N];
        
        int low = 0;
        int high = 0;
        int mid = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        	high = Math.max(high, arr[i]);
        }
        long sum = 0;
        while(low <= high) {
        	mid = (low+high)/2;
        	sum = 0;
        	for(Integer num : arr) {
        		if(num > mid) {
        			sum += num-mid;
        		}
        	}
        	if(sum < M) {
        		high = mid - 1;
        	}
        	else {
        		low = mid + 1;
        	}
        	
        }
        System.out.println(high);
    }
}