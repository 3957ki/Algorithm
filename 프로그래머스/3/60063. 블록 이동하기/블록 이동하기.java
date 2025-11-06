import java.util.*;

class Solution {
    private int[] dx = {0, 0, 1, -1};
    private int[] dy = {1, -1, 0, 0};
    private int N;
    
    public int solution(int[][] board) {
        
        N = board.length;
        
        Set<Integer> visited = new HashSet<>();
        visited.add(1);
        visited.add(10000);
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 0, 0, 1, true));
        
        int answer = 0;
        while(!queue.isEmpty()){
            int L = queue.size();
            
            while(L-- > 0){
                Node now = queue.poll();
                
                if((now.y1 == N-1 && now.x1 == N-1) || (now.y2 == N-1 && now.x2 == N-1)) return answer;
                
                for(int d = 0; d < 4; d++){
                    int y1 = now.y1 + dy[d];
                    int x1 = now.x1 + dx[d];
                    int y2 = now.y2 + dy[d];
                    int x2 = now.x2 + dx[d];
                    
                    if(!isValid(y1, x1, y2, x2, visited, board)) continue;
                    visited.add(calc(y1, x1, y2, x2));
                    visited.add(calc(y2, x2, y1, x1));
                    
                    queue.add(new Node(y1, x1, y2, x2, now.isRow));
                }
                
                if(now.isRow){
                    for(int d = 0; d < 2; d++){
                        int y1 = now.y1 + dy[d];
                        int x1 = now.x1 + dx[d];
                        int y2 = now.y2 + dy[d];
                        int x2 = now.x2 + dx[d];
                        
                        if(!isValid2(y1, x1, y2, x2, board)) continue;
                        if(!visited.contains(calc(now.y1, now.x1, y1, x1))){
                            visited.add(calc(now.y1, now.x1, y1, x1));
                            visited.add(calc(y1, x1, now.y1, now.x1));
                            queue.add(new Node(now.y1, now.x1, y1, x1, false));
                        }
                        if(!visited.contains(calc(now.y2, now.x2, y2, x2))){
                            visited.add(calc(now.y2, now.x2, y2, x2));
                            visited.add(calc(y2, x2, now.y2, now.x2));
                            queue.add(new Node(now.y2, now.x2, y2, x2, false));
                        }
                    }
                }
                
                else{
                    for(int d = 2; d < 4; d++){
                        int y1 = now.y1 + dy[d];
                        int x1 = now.x1 + dx[d];
                        int y2 = now.y2 + dy[d];
                        int x2 = now.x2 + dx[d];
                        
                        if(!isValid2(y1, x1, y2, x2, board)) continue;
                        if(!visited.contains(calc(now.y1, now.x1, y1, x1))){
                            visited.add(calc(now.y1, now.x1, y1, x1));
                            visited.add(calc(y1, x1, now.y1, now.x1));
                            queue.add(new Node(now.y1, now.x1, y1, x1, true));
                        }
                        if(!visited.contains(calc(now.y2, now.x2, y2, x2))){
                            visited.add(calc(now.y2, now.x2, y2, x2));
                            visited.add(calc(y2, x2, now.y2, now.x2));
                            queue.add(new Node(now.y2, now.x2, y2, x2, true));
                        }
                    }
                }
            }
            
            answer++;
        }
        
        return answer;
    }
    
    private boolean isValid(int y1, int x1, int y2, int x2, Set<Integer> visited, int[][] board){
        if(y1 < 0 || y1 >= N || x1 < 0 || x1 >= N || y2 < 0 || y2 >= N || x2 < 0 || x2 >= N || board[y1][x1] == 1 || board[y2][x2] == 1 || visited.contains(calc(y1, x1, y2, x2))) return false;
        return true;
    }
    
    private boolean isValid2(int y1, int x1, int y2, int x2, int[][] board){
        if(y1 < 0 || y1 >= N || x1 < 0 || x1 >= N || y2 < 0 || y2 >= N || x2 < 0 || x2 >= N || board[y1][x1] == 1 || board[y2][x2] == 1) return false;
        return true;
    }
    
    private int calc(int y1, int x1, int y2, int x2){
        return y1*1000000 + x1*10000 + y2*100 + x2;
    }
    
    private class Node{
        int y1, x1, y2, x2;
        boolean isRow;
        
        Node(int y1, int x1, int y2, int x2, boolean isRow){
            this.y1 = y1;
            this.x1 = x1;
            this.y2 = y2;
            this.x2 = x2;
            this.isRow = isRow;
        }
    }
}