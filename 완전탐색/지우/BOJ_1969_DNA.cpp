#include <iostream>
#include <vector>

using namespace std;

int N, M;
vector<string> maps;
string DNA[] = {"A", "C", "G", "T"};
string ans = "";

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    maps.resize(N, "");

    for(int r=0; r<N; r++) {
        string s; cin >> s;
        maps[r] = s;
    }

    for(int c=0; c<M; c++) {
        vector<int> cnts(4,0);
        
        for(int r=0; r<N; r++) {
            if(maps[r][c] == 'A') {
                cnts[0]++;
            } 
            else if(maps[r][c] == 'C') {
                cnts[1]++;
            } 
            else if(maps[r][c] == 'G') {
                cnts[2]++;
            } 
            else if(maps[r][c] == 'T') {
                cnts[3]++;
            }
        }

        int maxIdx = 0; int maxCnt = 0;
        for(int i=0; i<4; i++) {
            if(maxCnt < cnts[i]) {
                maxIdx = i;
                maxCnt = cnts[i];
            }
        }

        ans += DNA[maxIdx];
    }
    int cnt = 0;
    for(int r=0; r<N; r++) {

        for(int c=0; c<M; c++) {
            if(ans[c] != maps[r][c]) cnt++;
        }
    }
    
    cout << ans << "\n";
    cout << cnt;
}
