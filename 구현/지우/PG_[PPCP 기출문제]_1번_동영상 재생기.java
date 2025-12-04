package 구현.지우;

import java.util.*;

class Solution {
    static int[] p;
    static int ps, lastSec, ops, ope;
    
    static int toSec(int m, int s) {
        return m*60 + s;
    }
    
    static void timeCal(int a) {
        
        if(ps >= ops && ps <= ope) ps = ope;
        ps = ps + a;
        if(ps < 0) ps = 0;
        if(ps > lastSec) ps = lastSec;
        if(ps >= ops && ps <= ope) ps = ope;
        
        p[0] = ps/60;
        p[1] = ps - (p[0]*60);
        
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        p = new int[2];
        String Pos[] = pos.split(":");
        p[0] = Integer.parseInt(Pos[0]); p[1] = Integer.parseInt(Pos[1]);
        ps = toSec(p[0], p[1]);
        
        String End[] = video_len.split(":");
        int m = Integer.parseInt(End[0]); int s = Integer.parseInt(End[1]);
        lastSec = toSec(m,s);
        
        String Op[] = op_start.split(":");
        m = Integer.parseInt(Op[0]); s = Integer.parseInt(Op[1]);
        ops = toSec(m,s);
        Op = op_end.split(":");
        m = Integer.parseInt(Op[0]); s = Integer.parseInt(Op[1]);
        ope = toSec(m,s);
        
        for(int i=0; i<commands.length; i++) {
            if(commands[i].equals("prev")) {
                timeCal(-10);
            }
            else timeCal(10);
        }
        
        String answer = String.format("%02d:%02d", p[0], p[1]);
        return answer;
    }
}