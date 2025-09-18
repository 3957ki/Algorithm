class Solution {
    public int solution(int n, int[] stations, int w) {
        
        int prev = 0;
        int cover = w * 2 + 1;
        int answer = 0;
        
        for(int now : stations){
            int amount = now - w - prev - 1;
            
            if(amount > 0){
                answer += (amount + cover - 1) / cover;
            }
            
            prev = now + w;
        }

        int amount = n - prev;
        if(amount > 0){
            answer += (amount + cover - 1) / cover;
        }

        return answer;
    }
}