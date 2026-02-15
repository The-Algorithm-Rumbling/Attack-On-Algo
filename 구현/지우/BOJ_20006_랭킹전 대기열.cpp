#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct cmp {
    bool operator()(const pair<int,string> &a, const pair<int,string> &b) {
        return a.second < b.second;
    }
};

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    int p, m; cin >> p >> m;

    vector<pair<int, int>> rooms;
    vector<vector<pair<int,string>>> players;

    for(int i=0; i<p; i++) {
        int level; string name;
        cin >> level >> name;
        
        bool isS = false;
        for(int r=0; r<rooms.size(); r++) {
            auto[currCnt, standard] = rooms[r];
            if(currCnt == m) continue;
            if(standard-10 <= level && level <= standard+10) {
                isS = true;
                rooms[r] = {currCnt+1, standard};
                players[r].push_back({level, name});
                break;
            }
        }

        if(!isS) {
            rooms.push_back({1, level}); 
            players.push_back({{level, name}});
        }
    }

    for(int i=0; i<rooms.size(); i++) {
        auto[currCnt, standard] = rooms[i];
        if(currCnt == m) cout << "Started!" << "\n";
        else cout << "Waiting!" << "\n";

        sort(players[i].begin(), players[i].end(), cmp());
        for(auto player : players[i]) {
            cout << player.first << " " << player.second << "\n";
        }
    }
    return 0;
}