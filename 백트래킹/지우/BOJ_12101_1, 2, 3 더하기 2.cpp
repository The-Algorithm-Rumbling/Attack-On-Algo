#include <iostream>
#include <vector>
#include <string>

using namespace std;

int n, k;
int cnt = 0;

void dfs(int sum, string s) {

    if(sum == n) {
        cnt++;
        if(cnt == k) {
            cout << s;
            exit(0);
        }
        return;
    }
    if(sum > n) return;

    for(int i=1; i<=3; i++) {
        if(s.length() == 0) dfs(sum+i, to_string(i));   
        else dfs(sum+i, s + "+" + to_string(i));
    }
}

int main() {
    cin >> n >> k;
    dfs(0, "");
    cout << -1;
    return 0;
}