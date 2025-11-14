import java.io.*;
import java.lang.*;
import java.util.*;

class Solution {
    static int op_s, op_e;
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        int p = clock(pos);
        int v_len = clock(video_len);
        op_s = clock(op_start);
        op_e = clock(op_end);
        for (String s : commands) { 
            p = opening(p);
            if (s.equals("next")) { 
                p += 10;
                if (p > v_len) p = v_len;
            } else {
                p -= 10;
                if (p < 0) p = 0;
            }
            p = opening(p);
        }
        
        answer = re(p/60) + ":" + re(p%60);
        return answer;
    }
    
    static String re(int n){
        if (n / 10 == 0) return "0" + n;
        return n+"";
    }
    
    static int clock (String s) { 
        return Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(3, 5));
    }
    
    static int opening(int p) {
        if (p >= op_s && p <= op_e) p = op_e; 
            
        return p;
    }
}