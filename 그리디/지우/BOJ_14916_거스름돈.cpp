#include <iostream>

using namespace std;
int N;

int main() {
    cin >> N;

    int ans = -1;
    int fcnt = N/5;
    
    while(fcnt >= 0) {
        int left = N - (fcnt*5);
        if(left == 0) {
            ans = fcnt;
            break;
        }

        if(left % 2 == 0) {
            int tcnt = left/2;
            ans = fcnt + tcnt;
            break;
        }
        fcnt--;
    }
    cout << ans;
    
    return 0;
}