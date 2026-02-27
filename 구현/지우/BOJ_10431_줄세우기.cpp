#include <iostream>
#include <vector>

using namespace std;

vector<int> arr;

int main() {
    int T = 0; cin >>T;
    while(T--) {
        int tc = 0; cin >> tc;
        int ans = 0;

        arr.assign(20,0);
        
        for(int i=0; i<20; i++) {
            cin >> arr[i];
        }

        for(int i=0; i<20; i++) {
            for(int j=i+1; j<20; j++) {
                if(arr[i] > arr[j]) {
                    ans++;
                    int tmp = arr[j]; 
                    arr[j] = arr[i];
                    arr[i] = tmp;
                }
            }
        }
        cout << tc << " " << ans << "\n";
    }
    return 0;
}