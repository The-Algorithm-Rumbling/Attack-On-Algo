#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N; int off;
int root;
vector<vector<int>> childs;

int bfs(int v) {
    int leaf = 0;
    queue<int> q; 
    q.push(v);

    while(!q.empty()) {
        int curr = q.front(); q.pop();

        bool hasChild = false;
        for(int c : childs[curr]) {
            if(c == off) continue;
            hasChild = true;
            q.push(c);
        }

        if(!hasChild) leaf++;
    }

    return leaf;
}

int main() {
    cin >> N;
    childs.resize(N);
    for(int i=0; i< N; i++) {
        int parent = 0; cin >> parent;
        if(parent == -1) {
            root = i;
        } else {
            childs[parent].push_back(i);
        }
    }
    cin >> off;
    if(root == off) {
        cout << 0;
        return 0;
    }
    
    childs[off].clear();
    cout << bfs(root);
    return 0;
}