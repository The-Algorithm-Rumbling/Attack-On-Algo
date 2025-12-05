#include <iostream>
#include <vector>

using namespace std;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

    vector<int> arr(1001, 0);
    
    int N; cin >> N;
    for(int i=0; i<N; i++) {
        int pos, h; cin >> pos >> h;
        arr[pos] = h;
    }

    int ans = 0;
    int Lmax = 0;
    for(int i=0; i<=1000; i++) {
        int curr = arr[i];
        int Rmax = 0;
        for(int j=i+1; j<=1000; j++) {
            Rmax = max(Rmax, arr[j]);
        }

        int b = min(Lmax, Rmax);
        if(b > curr) {
            ans += b;
        } else {
            ans += curr;
            Lmax = curr;
        }
    }
    cout << ans;
    return 0;
}