#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N, M;
vector<vector<pair<int,int>>> list;
vector<int> dp;
priority_queue<pair<int,int>, vector<pair<int,int>>, greater<pair<int,int>>> q;

int bfs() {
    dp[1] = 0;
    q.push({0, 1});

    while(!q.empty()) {
        auto [dist, curr] = q.top(); q.pop();
        if(curr == N) return dist;

        for(auto nxt : list[curr]) {
            int nxtNode = nxt.first;
            int w = nxt.second;

            if(dp[nxtNode] > dp[curr] + w) {
                dp[nxtNode] = dp[curr] + w;
                q.push({dist+w, nxtNode});
            }
        }
    }
    return 0;
}


int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    list.resize(N+1);
    dp.resize(N+1, 98765432);

    for(int i=0; i<M; i++) {
        int a, b, w; cin >> a >> b >> w;

        list[a].push_back({b, w});
        list[b].push_back({a, w});
    }

    cout << bfs();
    return 0;
}