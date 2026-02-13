#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N;
vector<int> arr;
int best = 2100000000;
pair<int, int> ans;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    arr.resize(N, 0);
    for(int i=0; i<N; i++) {
        cin >> arr[i];
    }

    int left = 0; int right = N-1;
    while(left < right) {
        int sum = arr[left] + arr[right];

        if(abs(sum) < abs(best)) {
            best = sum;
            ans = {arr[left], arr[right]};
        }

        if(sum < 0) {
            left = left+1; 
        } else if(sum > 0) {
            right = right -1;
        } else {
            break;
        }
    }
    cout << ans.first << " " << ans.second;
        
    return 0;
}