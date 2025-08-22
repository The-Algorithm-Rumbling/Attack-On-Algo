#include <iostream>
#include <vector>
#include <set>

using namespace std;

vector<int> vis1R; vector<int> vis1C; vector<vector<int>> vis1D;
vector<int> vis2R; vector<int> vis2C; vector<vector<int>> vis2D;
set<string> sets;
string s = ".........";

bool check() {
    for(int i=0; i<3; i++) {
        if(vis1R[i] == 3 || vis1C[i] == 3 || vis2R[i] == 3 || vis2C[i] == 3) return true; 
    }
    if(vis1D[0][2] == 3 || vis1D[1][2] == 3 || vis2D[0][2] == 3 || vis2D[1][2] == 3) return true;
    return false;
}

void dfs(int cnt, char turn) {
    
    if(check() || cnt == 9) {
        sets.insert(s);
        return;
    } 
    if(cnt > 9) return;


    for(int i=0; i<9; i++) {
        if(s[i] != '.') continue;

        int r = i/3; int c = i%3;
        if(turn == 'X') {
            vis1R[r]++; vis1C[c]++; vis1D[0][r-c+2]++; vis1D[1][r+c]++;
            s[i] = 'X';
            dfs(cnt+1, 'O');
            vis1R[r]--; vis1C[c]--; vis1D[0][r-c+2]--; vis1D[1][r+c]--;
            s[i] = '.';
        }
        else {
            vis2R[r]++; vis2C[c]++; vis2D[0][r-c+2]++; vis2D[1][r+c]++;
            s[i] = 'O';
            dfs(cnt+1, 'X');
            vis2R[r]--; vis2C[c]--; vis2D[0][r-c+2]--; vis2D[1][r+c]--;
            s[i] = '.';
        }
        
    }

}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    vis1R.resize(3,0); vis1C.resize(3,0); vis1D.resize(2, vector<int>(5,0));
    vis2R.resize(3,0); vis2C.resize(3,0); vis2D.resize(2, vector<int>(5,0));
    dfs(0, 'X');

    while(1) {
        string s; cin >> s;
        if(s == "end") break;
        if(sets.count(s)) cout << "valid" << "\n";
        else cout << "invalid" << "\n";
    }
    
    return 0;
}