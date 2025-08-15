#include <iostream>
#include <vector>
#include <unordered_set>
#include <algorithm>

using namespace std;

int N, M; // 기타 수, 곡 수
vector<vector<int>> g_songs;
vector<int> gList;
int maxSong = -1;
int minG = 11;

void dfs(int gIdx) {
    if(gList.size() > 0) {
        unordered_set<int> picks;
        for(int g : gList) {
            for(int s : g_songs[g]) {
                picks.insert(s);
            }
        }

        int sCnt = picks.size();
        int gCnt = gList.size();
        if(sCnt > maxSong) {
            maxSong = picks.size();
            minG = gCnt;
        } else if(sCnt == maxSong) {
            minG = min(minG, gCnt);
        }
    }
    if(gIdx == N) return;

    gList.push_back(gIdx);
    dfs(gIdx+1);
    
    gList.pop_back();
    dfs(gIdx+1);
}


int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    g_songs.resize(N, vector<int>(0));
    gList.clear();
    
    for(int g=0; g<N; g++) {
        string name; string yesno;
        cin >> name >> yesno;
        for(int i=0; i<M; i++) {
            if(yesno[i] == 'Y') {
                g_songs[g].push_back(i+1); // 기타 - 연주 가능 곡 번호 넣어주기
            }
        }
    }

    dfs(0);
    
    if(maxSong == 0) minG = -1;
    cout << minG;
    
    return 0;
}