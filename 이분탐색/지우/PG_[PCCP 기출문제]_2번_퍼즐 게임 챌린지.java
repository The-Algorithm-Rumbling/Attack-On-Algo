package 이분탐색.지우;

class Solution {
    int N;
    int[] diffs, times;
    long limit; 
    
    boolean Play(int level) {
        long time = times[0];
        
        for(int i=1; i<N; i++) {
            int diff = diffs[i];
            if(diff <= level) time += times[i];
            else {
                int cnt = diff-level;
                time += (times[i]+times[i-1])*cnt + times[i];
            }
            if(time > limit) return false;
        }
        
        return time <= limit;
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        N = diffs.length;
        this.diffs = diffs;
        this.times = times;
        this.limit = limit;
        
        int maxDiff = -1;
        for(int i=0; i<N; i++) {
            maxDiff = Math.max(maxDiff, diffs[i]);
        }
        
        int answer = 0;
        int left = 1; int right = maxDiff;
        while(left <= right) {
            int mid = (left + right)/2;
            if(Play(mid)) {
                answer = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        
        return answer;
    }
}