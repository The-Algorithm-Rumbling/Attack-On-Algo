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

    long long sum1 = 0;
    sort(arr.begin(), arr.end());
    for(int i=0; i<N; i++) {
        long long tip = arr[i] - i;
        if(tip < 0) break;
        sum1 += tip;
    }

    long long sum2 = 0;
    sort(arr.rbegin(), arr.rend());
    for(int i=0; i<N; i++) {
        long long tip = arr[i] - i;
        if(tip < 0) break;
        sum2 += tip;
    }

    cout << max(sum1, sum2);
    
    return 0;
}