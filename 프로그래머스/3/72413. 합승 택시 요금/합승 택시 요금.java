import java.util.*;

class Solution {
    private final int INF = Integer.MAX_VALUE;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        int[][] edges = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            Arrays.fill(edges[i], INF);
        }
        
        for(int[] f : fares){
            edges[f[0]][f[1]] = f[2];
            edges[f[1]][f[0]] = f[2];
        }
        
        for(int k = 1; k <= n; k++){
            for(int i = 1; i <= n; i++){
                for(int j = 1; j <= n; j++){
                    if(i == j) continue;
                    if(edges[i][k] == INF || edges[k][j] == INF) continue;
                    edges[i][j] = Math.min(edges[i][j], edges[i][k] + edges[k][j]);
                }
            }
        }
        
        int answer = INF;
        for(int i = 1; i <= n; i++){
            edges[i][i] = 0;
            answer = Math.min(answer, edges[s][i] + edges[i][a] + edges[i][b]);
        }
        
        return answer;
    }
    
    private class Edge {
        int num, cost;
        
        Edge(int num, int cost){
            this.num = num;
            this.cost = cost;
        }
    }
}