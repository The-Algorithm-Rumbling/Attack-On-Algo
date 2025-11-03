#include <iostream>
#include <vector>
#include <set>
#include <algorithm>

using namespace std;

int n, k;
vector<string> arr;
vector<int> vis;
set<string> sets;

void dfs(int cnt, string s) {
    if(cnt == k) {
        sets.insert(s);
        return;
    }

    for(int i=0; i<n; i++) {
        if(vis[i]) continue;

        vis[i] = true;
        dfs(cnt+1, s+arr[i]);
        vis[i] = false;
    }
}

int main() {
    cin >> n >> k;
    arr.resize(n,"");
    vis.resize(n, false);

    for(int i=0; i<n; i++) {
        int a; cin >> a;
        arr[i] = to_string(a);
    }
    sort(arr.begin(), arr.end());

    dfs(0,"");
    cout << sets.size();
    return 0;
}