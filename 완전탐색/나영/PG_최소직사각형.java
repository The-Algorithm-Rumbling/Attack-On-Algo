class Solution {
    static int maxR, maxC;
    public int solution(int[][] sizes) {
        int answer = 0;
        
        for (int i = 0; i < sizes.length; i++) {
            int a = sizes[i][0];
            int b = sizes[i][1];
            
            if (a < b) {
                sizes[i][0] = b;
                sizes[i][1] = a;
            }
            
            maxR = Math.max(maxR, sizes[i][0]);
            maxC = Math.max(maxC, sizes[i][1]);
        }
        
        return maxR * maxC;
    }
}