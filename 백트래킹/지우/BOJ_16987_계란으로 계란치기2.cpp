#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int N;
int maxC = 0;
vector<pair<int,int>> eggs;

void dfs(int idx) {
    if(idx == N) {
        int cnt =0;
        for(int i=0; i<N; i++) {
            if(eggs[i].first <= 0) cnt++;
        }
        maxC = max(maxC, cnt);
        return;
    }

    auto[d, w] = eggs[idx];
    if(d <= 0) {
        dfs(idx+1);
        return;
    }

    bool isS = false;
    for(int negg = 0 ; negg < N; negg++) {
        if(idx == negg) continue;

        auto [nd, nw] = eggs[negg];
        if(nd > 0) {
            isS = true;
            eggs[idx] = {d-nw, w};
            eggs[negg] = {nd-w, nw};

            dfs(idx+1);

            eggs[idx] = {d, w};
            eggs[negg] = {nd, nw};
        }
    }
    if(!isS) dfs(idx+1);
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N; eggs.resize(N);
    for(int i=0; i<N; i++) {
        int d, w; cin >> d >> w;
        eggs[i] = {d,w};
    }

    dfs(0);
    cout << maxC;
       
    return 0;
}