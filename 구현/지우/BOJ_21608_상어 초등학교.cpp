#include <iostream>
#include <vector>
#include <unordered_set>

using namespace std;

int N;
vector<vector<int>> maps;
vector<unordered_set<int>> likes;
vector<vector<int>> dp;
vector<vector<int>> dp2;

int dr[] = {1,0,-1,0};
int dc[] = {0,-1,0,1};
int w[] = {0,1,10,100,1000};

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<N;
}

void Seat(int student) {
    int maxLike = -1; 
    dp.assign(N, vector<int>(N, -1));
    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            if(maps[r][c] != -1) continue;
            
            int cnt = 0;
            for(int d=0; d<4; d++) {
                int nr = r+ dr[d]; int nc = c + dc[d];
                if(inRange(nr,nc) && maps[nr][nc] != -1 && likes[student].count(maps[nr][nc])) cnt++;
            }
            dp[r][c] = cnt;
            maxLike = max(cnt, maxLike);
        }
    }

    int maxBlank = -1;
    dp2.assign(N, vector<int>(N, -1));
    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            if(maxLike == dp[r][c]) {
                int cnt = 0;
                for(int d=0; d<4; d++) {
                    int nr = r+ dr[d]; int nc = c + dc[d];
                    if(inRange(nr,nc) && maps[nr][nc] == -1) cnt++;
                }
                dp2[r][c] = cnt;
                maxBlank = max(cnt, maxBlank);
            }
        }
    }

    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            if(maxBlank == dp2[r][c] && maps[r][c] == -1) {
                maps[r][c] = student;
                return;
            }
        }
    }
}

int main() {
    cin >> N;
    maps.resize(N, vector<int>(N, -1));
    likes.resize(N*N+1);

    for(int i=0; i<N*N; i++) {
        int student; cin >> student;
        for(int j=0; j<4; j++) {
            int f; cin >> f;
            likes[student].insert(f);
        }
        Seat(student);
    }

    int ans = 0;
    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            int student = maps[r][c];
            int cnt = 0;
            for(int d=0; d<4; d++) {
                int nr = r+ dr[d]; int nc = c + dc[d];
                if(inRange(nr,nc) && likes[student].count(maps[nr][nc])) cnt++;
            }
            ans += w[cnt];
        }
    }
    cout << ans;
    return 0;
}