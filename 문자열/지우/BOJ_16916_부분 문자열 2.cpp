#include <iostream>
#include <vector>

using namespace std;

vector<int> makeTable(string p) { // 접두사, 접미사 최대 길이를 포함하는 테이블
    int plen = p.length();
    vector<int> table(plen,0); // mismatch 발견 시, 다음번에 비교해야 할 p상의 위치를 알려주는 함수 
    
    int j=0;
    for(int i=1; i<plen; i++) {
        while(j>0 && p[i] != p[j]) { // j번째랑 i번째랑 일치하지 않으면 j는 그 전으로 돌아가 비교하게 된다.
            j = table[j-1];
        }
        if(p[i] == p[j]) {
            table[i] = j+1;
            j++;
        }
    }
    return table;
}

int kmp(string s, string p) {
    vector<int> table = makeTable(p);
    int sSize = s.size();
    int pSize = p.size();

    // 안 맞는 부분이 생기면 거기서 -1을 뺀 만큼의 위치로 돌아가서 table[j-1]까지의 인덱스까지는 일치를 했음을 알 수 있다.
    // 그럼 p의 그 다음 인덱스부터 s의 다음 문자와 맞는지 비교하여 쭉쭉 넘어간다.
    
    int j=0; 
    for(int i=0; i<sSize; i++) {
        while(j>0 && s[i] != p[j]) {
            j = table[j-1];
        }
        if(s[i] == p[j]) {
            if(j== pSize-1) {
                // 모든게 다 일치!!
                return 1;
            } else {
                j++;
            }
        }
    }

    return 0;
}

int main() {
    string s, p;
    cin >> s >> p;

    cout << kmp(s,p);

    return 0;
}