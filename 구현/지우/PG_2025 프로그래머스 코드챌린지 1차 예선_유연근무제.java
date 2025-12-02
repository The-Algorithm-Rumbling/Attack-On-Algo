package 구현.지우;

class Solution {
    int N; 
    int[][] timelogs;
    
    boolean compare(int a, int b) {
        int h1 = a / 100; int m1 = a % 100;
        int h2 = b / 100; int m2 = b % 100;
        
        if(h1 != h2) return h1 >= h2;
        return m1 >= m2;
    }
    
    boolean check(int person, int limit, int startday) {
        int idx = 0; int day = startday;
        
        while(idx < timelogs[person].length) {
            if(day == 6 || day == 7) {
                day++; if(day==8) day = 1;
            }
            else if(compare(limit, timelogs[person][idx])) {
                day++; if(day==8) day = 1;
            }
            else {
                return false;
            }
            
            idx++;
        }
        
        return true;
    }
    
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        this.timelogs= timelogs;
        
        N = schedules.length;
        for(int i=0; i<N; i++) {
            int h = schedules[i] / 100; 
            int m = schedules[i] % 100;
            m += 10;
            if(m >= 60) {
                h++; m -= 60;
            }
            
            if(check(i, h*100+m, startday)) answer++;
        }
        
        return answer;
    }
}