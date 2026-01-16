#include <iostream>
#include <vector>
#include <stack>

using namespace std;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

    int N; cin >> N;
    stack<pair<int,int>> st;
    vector<int> ans(N, 0);

    for(int i=1; i<=N; i++) {
        int h; cin >> h;

        while(!st.empty() && st.top().first < h) { // 나보다 작으면 다 없애기
            st.pop();
        }

        if(st.empty()) cout << 0 << " ";
        else cout << st.top().second << " ";

        st.push({h, i});
    }
    
    return 0;
}