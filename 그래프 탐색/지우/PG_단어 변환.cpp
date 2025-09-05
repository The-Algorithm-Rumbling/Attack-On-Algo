#include <string>
#include <vector>
#include <queue>

using namespace std;
queue<pair<string,int>> q;
vector<bool> vis;
vector<string> wrds;
string t;

bool Diff1(string a, string b) {
    int cnt = 0;
    for(int i=0; i<a.length(); i++) {
        if(a[i] != b[i]) cnt++;
    }
    
    if(cnt==1) return true;
    return false;
}

int bfs(string begin) {
    q.push({begin, 0});
    
    while(!q.empty()) {
        auto [curr, cnt] = q.front(); q.pop();
        
        if(curr == t) return cnt;
        
        for(int i=0; i<wrds.size(); i++) {
            if(!vis[i] && Diff1(wrds[i], curr)) {
                vis[i] = true;
                q.push({wrds[i], cnt+1});
             }
        }
    }
    
    return 0;
}

int solution(string begin, string target, vector<string> words) {
    vis.resize(words.size(), false);
    wrds = words;
    t = target;
    
    return bfs(begin);
}