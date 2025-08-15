#include <iostream>
#include <vector>
#include <tuple>

using namespace std;

int N,M;
vector<vector<int>> talks; // talks[개구리][대화주제] 
vector<vector<int>> likes; // likes[연꽃] {희망개구리1, 희망개구리2 ,,}
vector<tuple<int,int,int>> connects; // <a,b,t> ...

vector<bool> fVis;
vector<int> lotusDp;


bool check() {
    for(int i=0; i<M; i++) {
        auto[a, b, t] = connects[i];
        int af = lotusDp[a]; int bf = lotusDp[b];
        if(talks[af][t] != talks[bf][t]) return false;
    }
    return true;
}
 
void dfs(int lotusIdx) {
    if(lotusIdx == N+1) {
        if(check()) {
            cout << "YES" << "\n";
            for(int i=1; i<=N; i++) {
                cout << lotusDp[i] << " ";
            }
            cout << "\n";
            exit(0);
        }
        else {
            return;
        }
    }

    for(auto f : likes[lotusIdx]) {
        if(!fVis[f]) {
            fVis[f] = true;
            lotusDp[lotusIdx] = f;
            
            dfs(lotusIdx+1);
            
            fVis[f] = false;
            lotusDp[lotusIdx] = 0;
        }
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    fVis.resize(N+1, 0);
    lotusDp.resize(N+1, 0);
    
    talks.resize(N+1, vector<int>(5,0));    
    for(int f=1; f<=N; f++) {
        for(int i=1; i<=4; i++) {
            cin >> talks[f][i];
        }
    }

    likes.resize(N+1, vector<int>(0));
    for(int f=1; f<=N; f++) {
        int a, b;
        cin >> a >> b;
        
        if(a != b) likes[b].push_back(f);
        likes[a].push_back(f);
    }

    connects.clear();
    for(int i=0; i<M; i++) {
        int a, b, t;
        cin >> a >> b >> t;
        connects.push_back({a,b,t});
    }

    dfs(1);
    cout << "NO";
    
    return 0;
}