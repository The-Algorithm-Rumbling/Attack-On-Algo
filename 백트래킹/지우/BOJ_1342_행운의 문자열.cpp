#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <set>

using namespace std;

string input;
int ans;
vector<char> arr;
vector<bool> vis;

bool isLucky(string str) {
    for(int i=1; i<str.length(); i++) {
        if(str[i-1] == str[i]) return false;
    }
    return true;
}

void dfs(int cnt, string str) {
    if(cnt == arr.size()) {
        if(isLucky(str)) {
            ans++;
        }
        return;
    }

    char tmp = '\0';
    for(int i=0; i<arr.size(); i++) {
        if(!vis[i] && tmp != arr[i]) {
            string tmpStr = str + arr[i];
            
            vis[i] = true;
            dfs(cnt+1, tmpStr);
            vis[i] = false;

            tmp = arr[i];
        }
    }
}

int main() {
    cin >> input;
    vis.resize(input.length(), false);
    arr.resize(input.length(), '\0');

    set<char> sets;
    for(int i=0; i<input.length(); i++) {
        arr[i] = input[i];
        sets.insert(arr[i]);
    }
    
    if(sets.size() == arr.size()) {
        int result = 1;
        for(int i=1; i<=sets.size(); i++) {
            result *= i;
        }
        cout << result;
        return 0;
    } 
    
    sort(arr.begin(), arr.end());
    dfs(0, "");
    cout << ans;
    return 0;
}