import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        
        Set<Integer> set = new HashSet<>();
        
        PriorityQueue<Integer> max = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> min = new PriorityQueue<>();
        
        for(String query : operations){
            // 삽입
            if(query.charAt(0) == 'I'){
                int num = Integer.parseInt(query.substring(2));
                set.add(num);
                
                max.add(num);
                min.add(num);
            }
            // 최댓값 삭제
            else if(query.charAt(2) == '1'){
                while(!max.isEmpty()){
                    int num = max.poll();
                    if(set.contains(num)) {
                        set.remove(num);
                        break;
                    }
                }
            }
            // 최솟값 삭제
            else{
                while(!min.isEmpty()){
                    int num = min.poll();
                    if(set.contains(num)) {
                        set.remove(num);
                        break;
                    }
                }
            }
        }
        
        int[] answer = new int[2];
        
        while(!max.isEmpty()){
            int num = max.poll();
            if(set.contains(num)){
                answer[0] = num;
                break;
            }
        }
        
        while(!min.isEmpty()){
            int num = min.poll();
            if(set.contains(num)){
                answer[1] = num;
                break;
            }
        }
        
        return answer;
    }
}