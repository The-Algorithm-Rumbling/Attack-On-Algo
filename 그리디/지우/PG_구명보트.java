package 그리디.지우;

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        boolean[] vis = new boolean[people.length];
        Arrays.sort(people);
        
        int idx=0;
        for(int i=people.length-1; i>=0; i--) {
            if(vis[i]) continue;
            
            int a = people[i]; int b = people[idx];
            if(a+b <= limit) {
                vis[i] = true; vis[idx] = true;
                answer++;
                idx++;
            }
        }
        
        for(int i=0; i<people.length; i++) {
            if(!vis[i]) answer++;
        }
        return answer;
    }
}