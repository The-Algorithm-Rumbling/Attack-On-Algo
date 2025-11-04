#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    int N; cin >> N;
    vector<int> arr(N, 0);
    for(int i=0; i<N; i++) {
        cin >> arr[i];
    }

    sort(arr.rbegin(), arr.rend());
    
    int maxW =  -1; int idx = 0;
    while(idx < N) {
        int nxtW = arr[idx]*(idx+1);
        if(maxW < nxtW) {
            maxW = nxtW;
        }
        idx++;
    }

    cout << maxW;
    
    return 0;
}