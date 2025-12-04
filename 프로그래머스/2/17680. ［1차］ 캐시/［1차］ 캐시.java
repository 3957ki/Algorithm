import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        
        Map<String, Integer> cache = new HashMap<>();
        
        int N = cities.length;
        int answer = 0;
        
        if(cacheSize == 0) return 5 * N;
        
        for(int i = 0; i < N; i++){
            String city = cities[i].toLowerCase();
            
            // hit
            if(cache.containsKey(city)) answer++;
            
            // miss
            else {
                answer += 5;
                
                if(cache.size() >= cacheSize){
                    int min = i;
                    String minKey = null;
                    for(String key : cache.keySet()){
                        int val = cache.get(key);
                        if(min > val){
                            min = val;
                            minKey = key;
                        }
                    }
                    
                    cache.remove(minKey);
                }
            }
            
            cache.put(city, i);
        }
        
        return answer;
    }
}