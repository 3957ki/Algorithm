import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        
        Map<String, Node> map = new HashMap<>();
        
        int L = genres.length;
        
        for(int i = 0; i < L; i++){
            
            if(!map.containsKey(genres[i])){
                map.put(genres[i], new Node());
            }
            
            Node now = map.get(genres[i]);
            now.sum += plays[i];
            now.list.add(new Song(i, plays[i]));
        }
        
        String[] arr = map.keySet().toArray(new String[0]);
        Arrays.sort(arr, (o1, o2) -> map.get(o2).sum - map.get(o1).sum);
        
        List<Integer> answerList = new ArrayList<>();
        
        for(String str : arr){
            Node now = map.get(str);
            Collections.sort(now.list, (o1, o2) -> {
                if(o2.play == o1.play){
                    return o1.num - o2.num;
                }
                return o2.play - o1.play;
            });
            
            int temp = 0;
            int tempL = now.list.size();
            while(tempL > temp && temp < 2){
                answerList.add(now.list.get(temp++).num);
            }
        }
        
        int aL = answerList.size();
        int[] answer = new int[aL];
        for(int i = 0; i < aL; i++){
            answer[i] = answerList.get(i);
        }
        return answer;
    }
    
    static class Node{
        int sum = 0;
        List<Song> list = new ArrayList<>();
    }
    
    static class Song{
        int num;
        int play;
        
        Song(int num, int play){
            this.num = num;
            this.play = play;
        }
    }
}