import java.util.*;

class Solution {
    
    private int[] dx = {0, 0, 1, -1};
    private int[] dy = {1, -1, 0, 0};
    
    public int solution(String[] storage, String[] requests) {
        int N = storage.length;
        int M = storage[0].length();
        
        char[][] map = new char[N+2][M+2];
        Map<Character, List<int[]>> location = new HashMap<>();
        
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i+1][j+1] = storage[i].charAt(j);
                location.putIfAbsent(map[i+1][j+1], new ArrayList<>());
                location.get(map[i+1][j+1]).add(new int[]{i+1, j+1});
            }
        }
        
        for(int i = 0; i <= N+1; i++){
            map[i][0] = '0';
            map[i][M+1] = '0';
        }
        for(int j = 0; j <= M+1; j++){
            map[0][j] = '0';
            map[N+1][j] = '0';
        }
        
        int answer = N * M;
        
        for(String request : requests){
            if(request.length() == 2){
                if(!location.containsKey(request.charAt(0))) continue;
                List<int[]> list = location.get(request.charAt(0));
                
                for(int[] now : list){
                    if(map[now[0]][now[1]] == '0') continue;
                    else{
                        map[now[0]][now[1]] = '0';
                        answer--;
                    }
                }
            }
            else{
                char ch = request.charAt(0);
                
                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[]{0, 0});
                boolean[][] visited = new boolean[N+2][M+2];
                visited[0][0] = true;
                
                while(!queue.isEmpty()){
                    int[] now = queue.poll();
                    
                    for(int d = 0; d < 4; d++){
                        int y = now[0] + dy[d];
                        int x = now[1] + dx[d];
                        
                        if(y < 0 || y > N+1 || x < 0 || x > M+1 || visited[y][x]) continue;
                        visited[y][x] = true;
                        
                        if(map[y][x] == ch) {
                            map[y][x] = '0';
                            answer--;
                        }
                        else if(map[y][x] == '0') queue.add(new int[]{y, x});
                    }
                }
            }
        }
        
        return answer;
    }
}