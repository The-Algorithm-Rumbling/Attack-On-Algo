#include <iostream>
#include <vector>

using namespace std;

int N;
vector<int> arr;
vector<int> seats;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N;
    arr.resize(N+1, 0);
    seats.resize(N+1, 0);

    for(int i=1; i<=N; i++) {
        cin >> arr[i];
    }

    for(int curr=1; curr<=N; curr++) {
        int bigger = -1;
        for(int s=1; s<=N; s++) {
            if(!seats[s]) {
                bigger++;
                if(arr[curr] == bigger) {
                    seats[s] = curr;
                    break;
                }
            }
        }
    }

    for(int i=1; i<=N; i++) cout << seats[i] << " ";
    return 0;
}