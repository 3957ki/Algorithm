class Solution {
    static int answer = 0;
    
    public int solution(int n) {

        DFS(0, 0, n);
        return answer;
    }
    
    static void DFS(int left, int right, int n){
        if(right == n) {
            answer++;
            return;
        }
        
        if(left == n){
            DFS(left, right + 1, n);
            return;
        }
        
        if(left > right){
            DFS(left, right + 1, n);
        }
        DFS(left + 1, right, n);
    }
}