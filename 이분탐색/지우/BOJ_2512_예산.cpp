#include <iostream>
#include <vector>

using namespace std;

int N;
long long M;
vector<int> arr;

bool check(int b) {
    long long sum = 0;
    for(int i=0; i<N; i++) {
        if(arr[i] >= b) sum += b;
        else sum += arr[i];
    }
    return sum <= M;
}

int main() {
    cin >> N;
    int left = 0; int right = 0;
    arr.resize(N, 0);
    for(int i=0; i<N; i++) {
        cin >> arr[i];
        right = max(right, arr[i]);
    }
    cin >> M;
    
    int ans = 0;
    while(left <= right) {
        int mid = (left+right)/2;
        if(check(mid)) {
            left = mid+1;
            ans = mid;
        } else {
            right = mid-1;
        }
    }

    cout << ans;
    return 0;
}