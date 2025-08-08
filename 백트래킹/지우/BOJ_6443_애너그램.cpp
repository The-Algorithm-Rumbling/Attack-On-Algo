#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N;
vector<char> words;
vector<bool> vis;

void dfs(int sIdx, string pick) {
    if(sIdx == words.size()) {
        cout << pick <<"\n";
        return;
    }        

    char pre = '\0';
    
    for(int i=0; i<words.size(); i++) {
        if(!vis[i] && pre != words[i]) {
            
            vis[i] = true;
            pick.push_back(words[i]);
            dfs(sIdx+1, pick);
            vis[i] = false;
            pick.pop_back();
            
            pre = words[i];
        }
    }
}

int main() {  
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    for(int i=0; i<N; i++) {
        string s; cin >> s;
        words.assign(s.length(),'\0');
        vis.assign(s.length(), false);
        
        for(int j=0; j<s.length(); j++) {
            words[j] = s[j];
        }
        sort(words.begin(), words.end());
        dfs(0, "");
    }
    
    return 0;
}