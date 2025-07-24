import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int N = words.length;
        int M = begin.length();
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(begin, 0));
        
        int answer = 0;
        while(!queue.isEmpty()){
            answer++;
            int L = queue.size();
            while(L-- > 0){
                Node now = queue.poll();
                
                A: for(int i = 0; i < N; i++){
                    if((now.visited & (1 << i)) > 0) continue;
                    
                    boolean flag = false;
                    for(int j = 0; j < M; j++){
                        char c = now.str.charAt(j);
                        if(words[i].charAt(j) != c){
                            if(!flag) flag = true;
                            else continue A;
                        }
                    }
                    
                    if(target.equals(words[i])) return answer;
                    
                    queue.add(new Node(words[i], now.visited | (1 << i)));
                    
                }
            }
        }
        
        return 0;
    }
    
    static class Node{
        String str;
        long visited;
        
        Node(String str, long visited){
            this.str = str;
            this.visited = visited;
        }
    }
}