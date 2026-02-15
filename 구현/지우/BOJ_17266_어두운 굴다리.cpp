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
    cin >> N >> M;
    
    for(int i=0; i<M; i++) {
        int num; cin >> num;
        arr.push_back(num);
    }

    int ans = 0;
    for(int h=0; h<=N; h++) {
        if(check(h)) {
            ans = h;
            break;
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