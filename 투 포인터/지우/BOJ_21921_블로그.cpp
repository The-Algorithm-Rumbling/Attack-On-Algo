#include <iostream>
#include <vector>

using namespace std;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    int N, X; cin >> N >> X;

    vector<int> arr(N, 0);
    for(int i=0; i<N; i++) {
        cin >> arr[i];
    }

    int p1 = 0, p2 = 0;
    int sum = 0; int ans = -1; int cnt = 0;
    while(p1 < N && p2 < N) {
        
        sum += arr[p2];
        if(p2-X+1 == p1) {
            if(ans < sum) {
                cnt = 1;
                ans = sum;
            }
            else if(ans == sum) {
                cnt++;
            }
            sum -= arr[p1];
            p1++;
        } 
            
        p2++;
    }

    if(ans == 0) {
        cout << "SAD";
    } else {
        cout << ans << "\n" << cnt;
    }

    return 0;
}