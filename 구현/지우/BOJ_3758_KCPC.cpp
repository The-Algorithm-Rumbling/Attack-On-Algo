#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> maxScore;
vector<pair<int,int>> teamLog;
vector<int> total;

int main() {
    int T; cin >> T;
    while(T--) {
        int n, k, me, m; cin >> n >> k >> me >> m;
        maxScore.assign(n+1, vector<int>(k+1, 0));
        teamLog.assign(n+1, {0,0});
        total.assign(n+1, 0);

        for(int i=0; i<m; i++) {
            int id, num, s;
            cin >> id >> num >> s;
            maxScore[id][num] = max(maxScore[id][num], s);
            auto[cnt, lastTime] = teamLog[id];
            teamLog[id] = {cnt+1, i};
        }

        for(int id=1; id<=n; id++) {
            int sum = 0;
            for(int c=1; c<=k; c++) {
                sum += maxScore[id][c];
            }
            total[id] = sum;
        }

        int rank = 1;
        auto[meCnt, meLastTime] = teamLog[me];
        
        for(int id=1; id<=n; id++) {
            if(id == me) continue;
            if(total[id] > total[me]) rank++;
            else if(total[id] == total[me]) {
                auto[cnt, lastTime] = teamLog[id];

                if(cnt < meCnt) {
                    rank++;
                }
                else if(cnt == meCnt) {
                    if(lastTime < meLastTime) {
                        rank++;
                    }
                }
            }
        }
        cout << rank << "\n";
    }
    
    return 0;
}