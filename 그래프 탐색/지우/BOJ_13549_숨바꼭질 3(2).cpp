#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, K;
queue<int> q;
vector<int> dp(100001, 98765432);

bool inRange(int x) {
    return x>=0 && x<=100000;
}

int bfs() {
    dp[N] = 0;
    q.push(N);

    while(!q.empty()) {
        int curr = q.front(); q.pop();

        int nxt = 2*curr;
        if(inRange(nxt) && dp[nxt] > dp[curr]) {
            dp[nxt] = dp[curr];
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

    return dp[K];
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> K;
    cout << bfs();
    return 0;
}