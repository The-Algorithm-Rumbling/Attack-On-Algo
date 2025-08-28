#include <iostream>
#include <cstring>

using namespace std;

int main() {
    string s, p;
    cin >> s >> p;

    if(strstr(s.c_str(), p.c_str()) != NULL) {
        cout << 1;
    } else {
        cout << 0;
    }

    return 0;
}