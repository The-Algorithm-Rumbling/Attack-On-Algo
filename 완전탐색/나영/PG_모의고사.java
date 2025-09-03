import java.util.*;

class Solution {
    static int [] arr2 = {1, 3, 4, 5};
    static int [] arr3 = {3, 1, 2, 4, 5};
    static int a, b, c, id1=1, id2, id3;
    static List<Integer> list = new ArrayList<>();
    public int[] solution(int[] answers) {
        int[] answer = {};
        
        for (int i = 0; i < answers.length; i++) {
            if (id1 == answers[i]) a++;
            if (arr3[id3] == answers[i]) c++;
            
            if (i % 2 == 0) {
                if (answers[i] == 2) b++;
            } else {
                if (arr2[id2] == answers[i]) b++;
                id2 = (id2+1) % 4;
                id3 = (id3+1) % 5;
            }
            
            if (id1 == 5) id1 = 1;
            else id1++;
        }
        
        int max = Math.max(a, Math.max(b, c));
        
        if (max == a) list.add(1);
        if (max == b) list.add(2);
        if (max == c) list.add(3);
        
        answer = new int [list.size()];
        
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}