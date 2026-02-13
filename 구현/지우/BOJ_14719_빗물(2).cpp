#include <iostream>
#include <vector>

using namespace std;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);

    int N, M; cin >> N >> M;
    vector<int> arr(M, 0);
    int sum = 0;

    for(int i=0; i<M; i++) {
        cin >> arr[i];
    }

    int leftMax = arr[0];
    for(int i=1; i<M-1; i++) {

        int rightMax = 0;
        for(int j=i+1; j<M; j++) {
            rightMax = max(arr[j], rightMax);
        }

        int flag = min(leftMax, rightMax);
        if(flag - arr[i] >= 0) sum += flag - arr[i];

        leftMax = max(leftMax, arr[i]);
    }

    cout << sum;
    return 0;
}