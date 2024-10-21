import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        
        List<Node> list = new ArrayList<>();
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            list.add(new Node(Math.min(start, end), Math.max(start, end)));
        }
        
        int L = Integer.parseInt(br.readLine());
        
        Collections.sort(list, (o1, o2) -> {
        	if((int)o1.end == (int)o2.end) {
        		return (int)o1.start-(int)o2.start;
        	}
        	return(int)o1.end - (int)o2.end;
        });
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o1 - o2);
        
        int max = 0;
        
        for(Node now : list) {
//          길이보다 긴 선분은 패스
        	if(now.end - now.start > L) continue;
        	
        	pq.add(now.start);
        	
        	while(!pq.isEmpty()) {
//        		포함되면 break;
        		if(now.end - pq.peek() <= L) break;
        		pq.poll();
        	}
        	max = Math.max(max, pq.size());
        }
        
        System.out.println(max);
    }

    static class Node{
        int start, end;

        public Node(int start, int end) {
            super();
            this.start = start;
            this.end = end;
        }
        
    }
}