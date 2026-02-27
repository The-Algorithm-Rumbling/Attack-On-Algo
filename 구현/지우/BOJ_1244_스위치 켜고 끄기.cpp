#include <iostream>
#include <vector>

using namespace std;

int N;
vector<int> arr;

void boy(int num) {
    for(int i=num; i<=N; i+=num) {
        arr[i] = (arr[i]+1)%2;
    }
}

void girl(int num) {
    int left = num; int right = num;

    while(1) {
        if((left <= 0 || right > N) || arr[left] != arr[right]) {
            left++; right--; 
            break;
        } else {
            left--; right++;
        }
    }

    for(int i=left; i<=right; i++) {
        arr[i] = (arr[i]+1)%2;
    }
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    cin >> N; 
    arr.resize(N+1, 0);

    for(int i=1; i<=N; i++) {
        cin >> arr[i];
    }

    int K; cin >> K;
    for(int i=0; i<K; i++) {
        int gender, num; cin >> gender >> num;
        if(gender == 1) {
            boy(num);
        } else {
            girl(num);
        }
    }

    for(int i=1; i<=N; i++) {
        cout << arr[i] << " ";

        if((i)%20 == 0) {
            cout << "\n";
        }
    }
    
    
    return 0;
}