#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, K;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> K;
    vector<int> arr(N, 0);
    vector<int> cnts(100001, 0);
    
    for(int i=0; i<N; i++) {
        cin >> arr[i];
    }

    int ans = 0;
    int st = 0; int end = 0;
    while(st < N && end < N) {
        if(cnts[arr[end]] < K) {
            cnts[arr[end]]++;
            end++;
            ans = max(ans, end-st);
        } else {
            cnts[arr[st]]--;
            st++;
        }
    }
    
    cout << ans;
    return 0;
}