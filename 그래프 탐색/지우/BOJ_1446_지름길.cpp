#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
#include <algorithm>

using namespace std;

int N, D;
vector<tuple<int,int,int>> arr;
int ans  = 98765432;
queue<pair<int,int>> q;
vector<bool> vis;

void bfs() {
    q.push({0, 0});

    while(!q.empty()) {
        auto[curr, dist] = q.front(); q.pop();
        if(curr > D) continue;
        ans = min(dist+D-curr, ans);

        for(int i=0; i<N; i++) {
            auto[a, b, w] = arr[i];
            int add = 0;
            if(a >= curr) {
                add = a-curr;
                q.push({b, dist+add+w});
            }
        }
    }
}

int main() {
    cin >> N >> D;
    vis.resize(N, false);

    for(int i=0; i<N; i++) {
        int a, b, w; 
        cin >> a >> b >> w;
        arr.push_back({a, b, w});
    }
    sort(arr.begin(), arr.end());

    bfs();
    cout << ans;
    
    return 0;
}