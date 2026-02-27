#include <iostream>
#include <vector>

using namespace std;

int N, d, k, c;
vector<int> sushis;
vector<int> check;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> d >> k >> c;
    sushis.resize(N); check.resize(d+1, 0);
    for(int i=0; i<N; i++) {
        cin >> sushis[i];
    }

    int cnt = 1;
    check[c] = 1;

    for(int i=0; i<k; i++) {
        if(check[sushis[i]] == 0) cnt++;
        check[sushis[i]]++; 
    }
    int ans = cnt;

    for(int pre=0; pre<N; pre++) {
        check[sushis[pre]]--;
        if(check[sushis[pre]] == 0) cnt--;
        
        int nxt = (pre+k)%N;
        if(check[sushis[nxt]] == 0) cnt++;
        check[sushis[nxt]]++;
        
        ans = max(ans, cnt);
    }
    cout << ans;
    return 0;
}