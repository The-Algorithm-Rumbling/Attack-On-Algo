#include <iostream>
#include <vector>
#include <climits>
#include <tuple>

using namespace std;
int N, M;
int INF = 987654321;

vector<long long> dp;
vector<tuple<int,int,int>> edges;

bool bellmanFord(int start) {
    dp[start] = 0;

    for(int i=0; i<=N; i++) { // 최대 N-1번째 떨어진 곳까지 닿을 수 있음 // 이후에 갱신되는 애들은 사이클 존재
        for(int j=0; j<M; j++) {
            auto[curr, nxt, time] = edges[j];

            if(dp[curr] != INF && dp[nxt] > dp[curr]+time) {
                dp[nxt] = dp[curr] + time;
                if(i == N) { // 여기서 갱신되는 애들은 사이클!!
                    return false;
                }
            }
        }
    }
    return true;
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    dp.resize(N+1, INF);
    edges.resize(M);
    
    for(int i=0; i<M; i++) {
        int start; int end; int time;
        cin >> start >> end >> time;
        edges[i] = {start, end, time};
    }

    if(bellmanFord(1)) {
        for(int i=2; i<=N; i++) {
            if(dp[i] == INF) cout << -1 << "\n";
            else cout << dp[i] << "\n";
        }
    } else {
        cout << -1;
    }
    
    return 0;
}