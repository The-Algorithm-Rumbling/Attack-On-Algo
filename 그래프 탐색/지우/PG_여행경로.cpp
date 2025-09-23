#include <string>
#include <vector>
#include <algorithm>

using namespace std;
vector<string> answer;
int n; bool found = false;
vector<vector<string>> tickets2;
vector<string> picks;
vector<bool> vis;

void dfs(int cnt, string curr) {
    if(found) return;
    if(cnt == n) {
        answer = picks;
        found = true;
        return;
    }
    
    for(int i=0; i<n; i++) {
        if(!vis[i] && tickets2[i][0] == curr) {
            string nxt = tickets2[i][1];
            vis[i] = true; picks.push_back(nxt);
            dfs(cnt+1, nxt);
            vis[i] = false; picks.pop_back();
        }
    }
}

vector<string> solution(vector<vector<string>> tickets) {
    sort(tickets.begin(), tickets.end());
    tickets2 = tickets; n = tickets.size();
    vis.resize(n, false);
    
    picks.push_back("ICN");
    dfs(0, "ICN");
    
    return answer;
}