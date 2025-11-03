#include <iostream>
#include <vector>
#include <set>

using namespace std;

int dr[] = {  0, -1, -1, -1, 0, 1, 1, 1};
int dc[] = { -1, -1,  0,  1, 1, 1, 0, -1};

int cr[] = {-1, -1, 1, 1};
int cc[] = {-1, 1, 1, -1};

int N, M;
vector<vector<int>> maps;
vector<pair<int,int>> clouds;

bool inRange(int r, int c) {
    return r>=0 && r<N && c>=0 && c<N;
}

void moveCloud(int d, int s) {
    set<pair<int,int>> preCloud;
    
    for(int i=0; i<clouds.size(); i++) {
        auto [r,c] = clouds[i];
        r = (r + dr[d]*s)%N ; c = (c + dc[d]*s)%N;
        if(r < 0) r += N;
        if(c < 0) c += N;
        maps[r][c]++;
        
        clouds[i] = {r,c};
        preCloud.insert({r,c});
    }

    for(int i=0; i<clouds.size(); i++) {
        auto [r,c] = clouds[i];
        int cnt = 0;
        
        for(int dir=0; dir<4; dir++) {
            int nr = r + cr[dir]; int nc = c + cc[dir];
            if(inRange(nr,nc)) {
                if(maps[nr][nc] > 0) cnt++;
            }
        }

        maps[r][c] += cnt;
    }

    clouds.clear();
    
    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            if(!preCloud.count({r,c}) && maps[r][c] >= 2) {
                maps[r][c] -= 2;
                clouds.push_back({r,c});
            }
        }
    }
    

}

int main() {
    cin >> N >> M;
    maps.resize(N, vector<int>(N, 0));

    clouds = {{N-1,0}, {N-1,1}, {N-2, 0}, {N-2, 1}};

    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            cin >> maps[r][c];
        }
    }

    for(int i=0; i<M; i++) {
        int d; int s;
        cin >> d >> s;
        d--; 
        moveCloud(d,s);
    }

    int ans = 0;
    for(int r=0; r<N; r++) {
        for(int c=0; c<N; c++) {
            ans += maps[r][c];
        }
    }
    cout << ans;    
    return 0;
}