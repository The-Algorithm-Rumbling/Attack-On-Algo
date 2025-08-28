#include <iostream>
#include <vector>

using namespace std;

int main() {
    vector<string> walls(5);
    for(int i=0; i<5; i++) {
        cin >> walls[i];
    }

    for(int i=0; i<15; i++) {
        for(int j=0; j<5; j++) {
            if(i > walls[j].length()-1) continue;
            cout << walls[j][i];
        }
    }
    return 0;
}