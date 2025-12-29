#include <iostream>
#include <vector>
#include <deque>

using namespace std;

int ans = 98765432;
int N;

int main() {
    string s; cin >> s;
    N = (int)s.length();

    int K = 0; // a 나열된 구간의 크기 
    for(int i=0; i<N; i++) {
        if(s[i] == 'a') K++;
    }
    if(K == 0) {
        cout << 0; 
        return 0;
    }

    for(int i=0; i<N; i++) {
        if(s[i] == 'a') {
            int k = K-1; int idx = i; int cnt = 0;
            while(k--) {
                idx = (idx+1)%N;
                if(s[idx] == 'b') cnt++;
            }
            ans = min(ans, cnt);
        }
    }
    
    cout << ans;
    return 0;
}