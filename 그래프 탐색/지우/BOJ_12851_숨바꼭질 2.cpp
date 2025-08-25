#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int INF = 999999;
int ans = INF; int N, K; int cnt;
vector<int> dp;
queue<pair<int,int>> q;

void bfs() {
    dp[N] = 0;
    q.push({N,0});

    while(!q.empty()) {
        auto[pos, time] = q.front(); q.pop();
        
        if(pos == K) {
            if(ans >= time) {
                ans = time;
                cnt++;
                continue;
            } else {
                return;
            }
        }

        int npos = pos*2;
        if(npos <= 100000 && dp[npos] >= time+1) {
            dp[npos] = time+1;
            q.push({npos, time+1});
        }

        npos = pos+1;
        if(npos <= 100000 && dp[npos] >= time+1) {
            dp[npos] = time+1;
            q.push({npos, time+1});
        }

        npos = pos-1;
        if(npos >= 0 && dp[npos] >= time+1) {
            dp[npos] = time+1;
            q.push({npos, time+1});
        }
    }
}

int main() {
    dp.resize(100001, INF);
    cin >> N >> K;
    bfs();
    cout << ans << "\n" << cnt;
    return 0;
}