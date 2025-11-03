#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int N;
long long result = 9999999999;
vector<long long> arr;
vector<long long> ans;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    arr.resize(N, 0);
    ans.resize(3,0);
    for(int i=0; i<N; i++) {
        cin >> arr[i];
    }

    sort(arr.begin(), arr.end());

    for(int i=0; i<N-2; i++) {
        int left = i + 1;
        int right = N-1;

        while(left < right) {
            long long sum = arr[i] + arr[left] + arr[right];

            if(abs(sum) < abs(result)) { // 0에 더 가까운 값이면 갱신!
                result = sum;
                ans[0] = arr[i]; ans[1] = arr[left]; ans[2] = arr[right];
            }

            if(sum < 0) left++;
            else if(sum > 0) right--;
            else if(sum == 0) break;
        }
    }

    sort(ans.begin(), ans.end());
    for(auto a : ans) {
        cout << a << " ";
    }
    
    return 0;
}