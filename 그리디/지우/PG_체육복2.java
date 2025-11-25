package 그리디.지우;

class Solution {
    int n;
    boolean[] lostVis;
    boolean[] reserveVis;
    int answer = 0;
    
    public int solution(int n, int[] lost, int[] reserve) {
        this.n = n;
        lostVis = new boolean[n+1];
        reserveVis = new boolean[n+1];
        
        int lostCnt = lost.length;
        for(int i=0; i<lost.length ;i++) {
            lostVis[lost[i]] = true;
        }
        for(int i=0; i<reserve.length ;i++) {
            reserveVis[reserve[i]] = true;
        }
        
        for(int i=1; i<=n; i++) {
            if(reserveVis[i] && lostVis[i]) {
                lostCnt--;
                reserveVis[i] = false;
                lostVis[i] = false;
            }
        }
        
        answer = n-lostCnt;
        for(int i=1; i<=n; i++) {
            if(!lostVis[i]) continue;
            if(i > 1 &&  reserveVis[i-1]) {
                reserveVis[i-1] = false;
                answer++;
            }
            else if(i<n && reserveVis[i+1]) {
                reserveVis[i+1] = false;
                answer++;
            }
        }
        
        return answer;
    }
}