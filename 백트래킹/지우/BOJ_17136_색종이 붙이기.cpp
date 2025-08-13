#include <iostream>
#include <vector>

using namespace std;

int ans = 100;
vector<vector<int>> maps;
int paper[] = {0, 5, 5, 5, 5, 5};

bool inRange(int r, int c) {
    return r>=0 && r<10 && c>=0 && c<10;
}

void check(int sr, int sc, int i, int b) {
    for(int r=sr; r<sr+i; r++) {
        for(int c=sc; c<sc+i; c++) {
            maps[r][c] = b;
        }
    }
    return;
}

bool canGlue(int sr, int sc, int i) {
    for(int r=sr; r<sr+i; r++) {
        for(int c=sc; c<sc+i; c++) {
            if(!inRange(r,c) || maps[r][c] == 0) return false;
        }
    }
    return true;
}

void dfs(int idx, int cnt, int cntOne) {
    if(cntOne == 0) {
        ans = min(ans, cnt);
        return;
    }
    if(idx == 100) return;
    
    int r = idx/10;
    int c = idx%10;

    if(maps[r][c] != 1) dfs(idx+1, cnt, cntOne);

    for(int i=1; i<=5; i++) {
        if(paper[i] > 0 && canGlue(r,c,i)) {
            check(r,c,i, 0);
            paper[i]--;
            
            dfs(idx+1, cnt+1, cntOne - i*i);
            check(r,c,i, 1);
            paper[i]++;
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    maps.resize(10, vector<int>(10,0));
    int cnt1 = 0;

    for(int r=0; r<10; r++) {
        for(int c=0; c<10; c++) {
            cin >> maps[r][c];
            if(maps[r][c] == 1) {
                cnt1++;
            }
        }
    }

    dfs(0,0, cnt1);
    if(ans == 100) ans = -1;
    cout << ans;

    return 0;
}