#include <iostream>
#include <vector>
#include <set>

using namespace std;
int N;
int minCost = 98765432;
vector<int> limits; // 단 지 탄 비
vector<vector<int>> inform;
vector<int> picks;
set<pair<int,vector<int>>> sets;

void dfs(int food, int cost, int d, int j, int t, int b) {
    if(d >= limits[0] && j >= limits[1] && t >= limits[2] && b >= limits[3]) {
        minCost = min(minCost, cost);
        sets.insert({cost, picks});
        return;
    }
    if(food >= N+1) return;
    if(minCost < cost) return;

    picks.push_back(food);
    dfs(food+1, cost+inform[food][4], d+inform[food][0], j+inform[food][1], t+inform[food][2], b+inform[food][3]);
    picks.pop_back();
    dfs(food+1, cost, d, j, t, b);
}

int main() {
    cin >> N;
    limits.resize(4,0);
    inform.resize(N+1, vector<int>(5,0));
    for(int i=0; i<4; i++) {
        cin >> limits[i];
    }
    for(int f=1; f<=N; f++) {
        for(int i=0; i<5; i++) {
            cin >> inform[f][i];
        }
    }

    dfs(1,0,0,0,0,0);
    
    if(sets.size() == 0) {
        cout << -1;
    } else {
        for(auto s : sets) {
            auto[cost, arr] = s;
            cout << cost << "\n";
            for(int food : arr) {
                cout << food << " ";
            }
            break;
        }
    }
    return 0;
}