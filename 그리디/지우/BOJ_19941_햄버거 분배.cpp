#include <iostream>
#include <vector>

using namespace std;

int N, K;

bool inRange(int r) {
    return r>=0 && r<N;
}

int main() {
    cin >> N >> K;
    string s; cin >> s;

    int ans = 0;
    vector<bool> vis(s.length(), false);
    for(int i=0; i<s.length(); i++) {
        if(s[i] == 'P') {
            bool isS = false;
            for(int j = i-K; j<i; j++) {
                if(!inRange(j)) continue;
                if(s[j] == 'H' && !vis[j]) {
                    isS = true;
                    vis[j] = true; ans++;
                    break;
                }
            }

            if(!isS) {
                for(int j = i+1; j<=i+K; j++) {
                    if(!inRange(j)) break;
                    if(s[j] == 'H' && !vis[j]) {
                        vis[j] = true; ans++;
                        break;
                    }
                }
            }
        }
    }
    cout << ans;
    return 0;
}