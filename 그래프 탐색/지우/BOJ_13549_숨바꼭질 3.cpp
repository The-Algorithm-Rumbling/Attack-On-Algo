#include <iostream>
#include <vector>
#include <deque>

using namespace std;

bool inRange(int r) {
    return r>=0 && r<=100000;
}

int main() {
    vector<bool> vis(100001, false);
    int N, K; cin >> N >> K;
    
    deque<pair<int,int>> dq;
    dq.push_back({0,N});
    vis[N] = true;


    while(!dq.empty()) {
        auto[time, curr] = dq.front(); dq.pop_front();
        if(curr == K) {
            cout << time;
            exit(0);
        }

        int nr = 2*curr;
        if(inRange(nr) && !vis[nr]) {
            vis[nr] = true;
            dq.push_front({time, nr});
        }

        nr = curr-1;
        if(inRange(nr) && !vis[nr]) {
            vis[nr] = true;
            dq.push_back({time+1, nr});
        }

        nr = curr+1;
        if(inRange(nr) && !vis[nr]) {
            vis[nr] = true;
            dq.push_back({time+1, nr});
        }
    }
    return 0;
}