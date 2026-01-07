class Solution {
    public int solution(String name) {
        int answer = 0;
        int N = name.length();
        int[] tmp = new int[N];
        
        int cnt = 0;
        for(int i=0; i<N; i++) {
            char ch = name.charAt(i);
            int a = ch-'A';
            int z = 'Z'-ch+1;
            tmp[i] = Math.min(a,z);
            cnt += tmp[i];
        }
        
        int move = Integer.MAX_VALUE;
        for(int i=0; i<N; i++) {
            int j = i+1;
            while(j<N && tmp[j]==0) {
                j++;
            }
            
            move = Math.min(move, 2*i+N-j);
            move = Math.min(move, 2*(N-j) +i);
        }
        answer = cnt + move;
        
        return answer;
    }
}