#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, K;
vector<int> dp;
queue<int> q; 

bool inRange(int c) {
    return c>=0 && c<=100000;
}

void bfs(int start) {
    dp[start] = 0;
    q.push(start);

    while(!q.empty()) {
        int curr = q.front(); q.pop();

        int nxt = curr*2; 
        if(inRange(nxt) && dp[nxt] > dp[curr]+1) {
            dp[nxt] = dp[curr]+1; 
            q.push(nxt);
        }

        for(int i=-1; i<=1; i+=2) {
            nxt = curr+i;
            if(inRange(nxt) && dp[nxt] > dp[curr]+1) {
                dp[nxt] = dp[curr]+1; 
                q.push(nxt);
            }
        }
    }
    
}

int main() {
    cin >> N >> K;
    dp.resize(100001, 98765432);
    bfs(N);
    cout << dp[K];
    
    return 0;
}