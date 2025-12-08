#include <iostream>
#include <list>

using namespace std;

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    list<char> L;
    string str; cin >> str;
    for(int s : str) {
        L.push_back(s);
    }
    auto cursor = L.end();

    int M; cin >> M;
    while(M--) {
        char cmd; cin >> cmd;
        if(cmd == 'L') {
            if(cursor != L.begin()) cursor--;
        }
        else if(cmd == 'D') {
            if(cursor != L.end()) cursor++;
        }
        else if(cmd == 'B') {
            if(cursor != L.begin()) {
                cursor--; // 커서는 오른쪽으로 가리키고 있으므로 왼쪽 아이로 넘어가기
                cursor = L.erase(cursor); // 일정 요소 삭제 후, 커서는 삭제한 애 '다음'을 자동으로 가리킴
            }
        }
        else if(cmd == 'P') {
            char x; cin >> x;
            L.insert(cursor, x); // insert는 어차피 cursor의 '앞'에 넣는다.
            // 커서는 오른쪽으로 가리키고 있으므로 그냥 그대로 유지해도 ㅇㅋ
        }
    }
    
    for(char i : L) cout << i;
    return 0;
}