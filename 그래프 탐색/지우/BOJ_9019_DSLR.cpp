#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int in;
int target;
vector<bool> vis;

string bfs() {
    queue<pair<int,string>> q;
    vis[in] = true;
    q.push({in, ""});
    
    while(!q.empty()) {
        auto [num, cmd] = q.front(); q.pop();

        if(num == target) return cmd;

        // D
        int tmp = (num * 2) % 10000;
        if(!vis[tmp]) {
            vis[tmp] = true;
            q.push({tmp, cmd + "D"});
        }

        // S
        tmp = num-1;
        if(num == 0) tmp = 9999;
        if(!vis[tmp]) {
            vis[tmp] = true;
            q.push({tmp, cmd + "S"});
        }

        // L
        tmp = (num%1000) * 10 + (num/1000);
        if(!vis[tmp]) {
            vis[tmp] = true;
            q.push({tmp, cmd + "L"});
        }

        // R
        tmp = (num/10) + (num%10)*1000;
        if(!vis[tmp]) {
            vis[tmp] = true;
            q.push({tmp, cmd + "R"});
        }
    }

    return "";
    
}

int main() {
    int T; cin >> T;
    while(T--) {
        cin >> in;
        cin >> target;
        vis.assign(10000, false);
        
        cout << bfs() << "\n";
    }
    
    return 0;
}