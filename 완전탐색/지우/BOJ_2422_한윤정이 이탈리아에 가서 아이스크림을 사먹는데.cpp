#include <iostream>
#include <vector>
using namespace std;

int N, M;
vector<vector<int>> adj;
vector<int> picks;
int ans = 0;

bool check() {
    for(int i=0; i<3; i++) {
        for(auto nope : adj[picks[i]]) {
            if(nope == picks[0] || nope == picks[1] || nope == picks[2])
                return false;
        }
    }
    return true;
}

void dfs(int start, int cnt) {
    if(cnt == 3) {
        if(check()) ans++;
        return;
    }

    for(int i=start; i<=N; i++) {
        picks.push_back(i);
        dfs(i+1, cnt+1);
        picks.pop_back();
    }
}

int main() {
    cin >> N >> M;
    adj.resize(N+1);

    for(int i=0; i<M; i++) {
        int a, b;
        cin >> a >> b;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    dfs(1, 0);
    cout << ans;
    return 0;
}