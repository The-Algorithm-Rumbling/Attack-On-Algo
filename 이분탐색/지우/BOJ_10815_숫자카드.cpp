#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    
    int N; cin >> N;
    vector<int> arr(N,0);
    for(int i=0; i<N; i++) {
        cin >> arr[i];
    }
    sort(arr.begin(), arr.end());
    
    int M; cin >> M;
    for(int i=0; i<M; i++) {
        int to_find; cin >> to_find;
        
        int left = 0; int right = N-1;
        int result = 0;
        while(left <= right) {
            int mid = (left + right)/2;

            if(to_find < arr[mid]) {
                right = mid -1;
            } else {
                left = mid + 1;
                result = arr[mid];
            }
        }
        if(result == to_find) cout << 1 << " ";
        else cout << 0 << " ";
    }
        
    return 0;
}