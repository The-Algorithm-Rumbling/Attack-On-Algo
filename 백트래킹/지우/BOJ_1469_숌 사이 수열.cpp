#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N;
vector<int> X;
vector<int> picks;
vector<bool> used;

void dfs1(int pos);

bool dfs2(int num, int pos) {
    int nPos = pos + num + 1;
    
    if(nPos >= 2*N) return false;
    if(picks[nPos] != -1) return false;

    picks[nPos] = num;
    dfs1(pos+1);
    return true;
}

void dfs1(int pos) {
    if(pos == 2*N) {
        for(int pick : picks) {
            cout << pick << " ";
        }
        exit(0);
        return;
    }
    
    if(picks[pos] == -1) {
        for(int num : X) {
            if(!used[num]) {
                used[num] = true;
                picks[pos] = num;
                
                if(dfs2(num, pos)) picks[pos+num+1] = -1;
                
                used[num] = false;
                picks[pos] = -1;
            }
        }
    } else {
        dfs1(pos+1);
    }

    
}

int main() {
    cin >> N;
    used.resize(17, false);
    picks.resize(2*N, -1);
    
    for(int i=0; i<N; i++) {
        int a; cin >> a;
        X.push_back(a);
    }
    sort(X.begin(), X.end());
    dfs1(0);
    cout << -1;
    return 0;
}