#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
vector<long long> desc;

void dfs(int pre, string s_num) {
    if(s_num != "") {
        long long num = 0;
        for(char s : s_num) {
            num = num * 10 + (s-'0');
        }
        desc.push_back(num);
    }

    for(int i=9; i>=0; i--) {
        if(pre > i) {
            dfs(i, s_num + to_string(i));
        }
    }
}

int main() {
    int N; cin >> N;
    dfs(10, "");
    sort(desc.begin(), desc.end());

    if(N >= desc.size()) {
        cout << -1;
    } else {
        cout << desc[N];
    }
    return 0;
}