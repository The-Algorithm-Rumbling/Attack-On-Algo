#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
string arr;
vector<bool> used;

void dfs(int cnt, string s) {
    if(cnt == arr.size()) {
        cout << s << "\n";
        return;
    }

    char tmp = '\0';
    for(int i=0; i<arr.size(); i++) {
        if(!used[i] && tmp != arr[i]) {
            used[i] = true;
            dfs(cnt+1, s + arr[i]);
            used[i] = false;
            tmp = arr[i];
        }
    }
}

int main() {
    int T; cin >> T;
    while(T--) {
        cin >> arr;
        used.assign(arr.size(), false);
        sort(arr.begin(), arr.end());
        dfs(0, "");
    }
    return 0;
}