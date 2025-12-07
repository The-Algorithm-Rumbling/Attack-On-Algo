#include <iostream>
#include <vector>

using namespace std;

int N;
vector<int> arr;
vector<int> picks;
vector<bool> vis;

bool check() {
    int curr = 1;
    while(curr <= N) {
        int cnt = 0;
        for(int p : picks) {
            if(curr == p) {
                if(arr[curr-1] != cnt) return false;
            }
            else if(curr < p) cnt++;
        }
        curr++;
    }
    return true;
}

void dfs(int pidx) {
    if(pidx == N) {
        if(check()) {
            for(int p : picks) cout << p << " ";
            exit(0);
        }
        return;
    }
    
    for(int i=1; i<=N; i++) {
        if(!vis[i]) {
            vis[i] = true; picks.push_back(i);
            dfs(pidx+1);
            vis[i] = false; picks.pop_back();
        }         
    }
}


int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    arr.resize(N, 0);
    vis.resize(N+1, false);

    for(int i=0; i<N; i++) {
        cin >> arr[i];
    }

    dfs(0);
    return 0;
}