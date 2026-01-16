package 그리디.지우;

class Solution {
    public int solution(String name) {
        int answer = 0;
        int n = name.length();
        char[] arr = name.toCharArray();
        int[] tmp = new int[n];
        
        // 상-하 움직임 최소
        int leftRight = 0;
        for(int i=0; i<n; i++) {
            char a = arr[i];
            tmp[i] = Math.min(a-'A', 'Z'-a+1);
            leftRight += tmp[i];
        }
        
        // 좌-우 움직임 최소
        int upDown = n-1;
        for(int i=0; i<n; i++) {
            // 만약 이거 다음에 AAAA 구간 존재! - 안 건너가는게 좋을 수 있지.
            int idx = i+1;
            while(idx < n && tmp[idx] == 0) {
                idx++;
            }
            
            upDown = Math.min(upDown, i*2 + (n-idx)); // i까지 왔다가 - 되돌아가서 - 뒤에서부터 훑
            upDown = Math.min(upDown, i + (n-idx)*2); // 바로 뒤돌아가서 왕복 - 처음부터 i까지 훑
        }
        answer = leftRight + upDown;
        return answer;
    }
}