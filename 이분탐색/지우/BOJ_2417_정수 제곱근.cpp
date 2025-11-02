#include <iostream>
#include <vector>
#include <cmath>

using namespace std;
using ll = long long;

int main() {
    long long N; cin >> N;

    long long left = 0; long long right = N;
    long long ans;
    
    while(left <= right) {
        long long mid = (left+right)/2;

        __int128 square = (__int128)mid * mid;  // mid*mid로 쓰면 long long도 터져서 음수값이 나옴
        
        if(square>= N) {
            ans = mid;
            right = mid-1;
        } else {
            left = mid+1;
        }
    }

    cout << ans; 
    
    return 0;
}