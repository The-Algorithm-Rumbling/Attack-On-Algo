package 구현.지우;

class Solution {
    double[] degree(int h, int m, int s) {
        double[] arr = new double[3];
        arr[0] = 30*(h%12) + 0.5*m + 1/120.0*s;
        arr[1] = 6*m + 0.1*s;
        arr[2] = 6*s;
        return arr;
    }
    
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        
        int h = h1; int m = m1; int s = s1;
        double[] curr = degree(h, m, s);
        if(curr[0] == curr[1] && s == 0) answer++;
        
        while(true) {
            double[] pre = degree(h, m, s);
            s++;
            if(s == 60) {
                m++; s = 0;
                if(m == 60) {
                    h++; m = 0;
                    if(h == 24) h = 0;
                }
            }
            
            double[] nxt = degree(h, m, s);
            
            boolean hCross = false;
            if(pre[2] < pre[0] && nxt[2] >= nxt[0]) {
                hCross = true;
            } else if(pre[2] > nxt[2]) { // 0도 통과
                if(pre[2] < pre[0] || nxt[2] >= nxt[0]) hCross = true; // (초 증가 각도가 더 크니까 당연히..) 354 < 357 || 2 >= 0 
            }
            if(hCross) answer++;
        
            
            boolean mCross = false;
            if(pre[2] < pre[1] && nxt[2] >= nxt[1]) {
                mCross = true;
            } else if(pre[2] > nxt[2]) { // 0도 통과
                if(pre[2] < pre[1] || nxt[2] >= nxt[1]) mCross = true; // (초 증가 각도가 더 크니까 당연히..) 354 < 357 || 2 >= 0 
            }
            if(mCross) answer++;
            
            if(hCross && mCross && nxt[0] == nxt[1]) answer--;
            if(h == h2 && m == m2 && s == s2) break;
        }
        
        
        return answer;
    }
}