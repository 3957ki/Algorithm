import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        int N = plans.length;
        
        Node[] arr = new Node[N];
        for(int i = 0; i < N; i++){
            String name = plans[i][0];
            int h = Integer.parseInt(plans[i][1].substring(0, 2));
            int m = Integer.parseInt(plans[i][1].substring(3, 5));
            int start = h*60 + m;
            int remain = Integer.parseInt(plans[i][2]);
            
            arr[i] = new Node(name, start, remain);
        }
        
        Arrays.sort(arr, (o1, o2) -> o1.start - o2.start);
        
        Queue<Node> queue = new ArrayDeque<>();
        ArrayDeque<Node> stack = new ArrayDeque<>();
        
        for(Node now : arr){
            queue.add(now);
        }
        
        stack.addLast(queue.poll());
        String[] answer = new String[N];
        
        int idx = 0;
        int time = stack.peekLast().start;
        
        while(!stack.isEmpty()){
            Node now = stack.peekLast();
            Node next = queue.peek();
            
            if(!queue.isEmpty() && time + now.remain > next.start){
                now.remain -= next.start - time;
                time = next.start;
                stack.addLast(queue.poll());
            }
            
            else{
                time += now.remain;
                answer[idx++] = now.name;
                stack.pollLast();
                
                if(stack.isEmpty() && !queue.isEmpty()){
                    time = queue.peek().start;
                    stack.addLast(queue.poll());
                }
            }
        }
        
        return answer;
    }
    
    private class Node{
        String name;
        int start, remain;
        
        Node(String name, int start, int remain){
            this.name = name;
            this.start = start;
            this.remain = remain;
        }
    }
}