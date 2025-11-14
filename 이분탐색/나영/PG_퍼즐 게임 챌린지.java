import java.io.*;
import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 1;
        int left = 1;
        int right = 0;
        
        for (int diff : diffs) right = Math.max(right, diff);
        
        while (left <= right) {
            boolean isS = true;
            int mid = (left + right) / 2;
            long sum = times[0];   
            for (int i = 1; i < diffs.length; i++) {
                sum += times[i];
                if (mid < diffs[i]) {
                    sum += (long) (times[i-1] + times[i]) * (diffs[i] - mid);
                }
                
                if (sum > limit) {
                    left = mid + 1;
                    isS = false;
                    break;
                }
            }
            
            if (isS) {
                answer = mid;
                right = mid - 1;
            }
        }
        
        return answer;
    }
}