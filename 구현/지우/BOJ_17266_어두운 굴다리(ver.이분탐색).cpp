#include <iostream>
#include <vector>

using namespace std;

int N, M;
vector<int> arr;

bool check(int h) {
    for(int i=1; i<M; i++) {
        if(arr[i-1]+h < arr[i]-h) return false;
    }
    return true;
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N >> M;
    
    for(int i=0; i<M; i++) {
        int num; cin >> num;
        arr.push_back(num);
    }

    int ans = 0;
    int left = 0; int right = N;
    while(left <= right) {
        int mid = (left + right)/2;

        if(check(mid)) {
            right = mid - 1;
            ans = mid;
        } else {
            left = mid + 1;
        }
    }
    
    if(0+ans < arr[0]) {
        ans = arr[0] - 0;
    }
    if(arr[M-1]+ans < N) {
        ans = N - (arr[M-1]+ans) + ans;
    }
    
    cout << ans;
    return 0;
}