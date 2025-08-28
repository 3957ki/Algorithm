class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int N = key.length;  // key 크기
        int M = lock.length; // lock 크기
        
        // 4가지 방향 Key Bit
        int[][] arr = new int[N][4];
        
        // 0도 (원본)
        for(int i = 0; i < N; i++){
            int bit = 0;
            for(int j = 0; j < N; j++){
                bit = bit << 1 | key[i][j];
            }
            arr[i][0] = bit;
        }
        
        // 90도 회전 (시계방향)
        for(int j = 0; j < N; j++){
            int bit = 0;
            for(int i = N-1; i >= 0; i--){
                bit = bit << 1 | key[i][j];
            }
            arr[j][1] = bit;
        }
        
        // 180도 회전
        for(int i = N-1; i >= 0; i--){
            int bit = 0;
            for(int j = N-1; j >= 0; j--){
                bit = bit << 1 | key[i][j];
            }
            arr[N-1-i][2] = bit;
        }
        
        // 270도 회전 (시계방향)
        for(int j = N-1; j >= 0; j--){
            int bit = 0;
            for(int i = 0; i < N; i++){
                bit = bit << 1 | key[i][j];
            }
            arr[N-1-j][3] = bit;
        }
        
        // Lock을 비트로 변환
        int[] frame = new int[M];
        for(int i = 0; i < M; i++){
            int bit = 0;
            for(int j = 0; j < M; j++){
                bit = bit << 1 | lock[i][j];
            }
            frame[i] = bit;
        }
        
        int B = (1 << M) - 1; // 모든 비트가 1인 마스크
        
        // 모든 위치에서 시도
        for(int i = -(N-1); i < M; i++){          // key의 세로 이동
            for(int j = -(N-1); j < M; j++){      // key의 가로 이동 (수정)
                A: for(int d = 0; d < 4; d++){    // 4가지 회전
                    for(int k = 0; k < M; k++){   // lock의 각 행
                        int keyRow = k - i;       // 해당하는 key의 행
                        
                        if(keyRow >= N || keyRow < 0){
                            // key가 이 lock 행에 영향을 주지 않음
                            // lock의 이 행이 모두 채워져야 함
                            if(frame[k] != B) continue A;
                        }
                        else{
                            // key가 이 lock 행에 영향을 줌
                            int keyPart;
                            if(j >= 0){
                                // 오른쪽으로 이동: 오른쪽 시프트
                                keyPart = (arr[keyRow][d] >> j) & B;
                            }
                            else{
                                // 왼쪽으로 이동: 왼쪽 시프트
                                keyPart = (arr[keyRow][d] << (-j)) & B;
                            }
                            
                            // 충돌 체크: key의 돌기와 lock의 돌기가 만나면 안됨
                            if((frame[k] & keyPart) != 0) continue A;
                            
                            // 완성 체크: 합쳤을 때 모든 홈이 채워져야 함
                            if((frame[k] | keyPart) != B) continue A;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}