#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
vector<string> arr;

// s1 < s2 인지 판별한다!
bool cmp(string s1, string s2) {
    int maxLen = max(s1.size(), s2.size());

    for(int i=0; i<maxLen; i++) {
        if(i >= s1.size() || i >= s2.size()) break;
        
        // 알파벳 비교
        if(isalpha(s1[i]) || isalpha(s2[i]) && s1[i] != s2[i]) {
            // 1. 숫자열이 알파벳보다 앞에 온다.
            if(isdigit(s1[i]) || isdigit(s2[i])) {
                return isdigit(s1[i]);
            }

            // 2. 알파벳끼리는 A a B b 를 따른다.
            // A < B 인지 확인
            int tmp1 = (isupper(s1[i])) ? s1[i] - 'A' : s1[i] - 'a';
            int tmp2 = (isupper(s2[i])) ? s2[i] - 'A' : s2[i] - 'a';
            if(tmp1 != tmp2) {
                return tmp1 < tmp2;
            }

            // A < a 인지 확인
            if(s1[i] != s2[i]) {
                return isupper(s1[i]);
            }
        }
        // 숫자 비교
        else if(isdigit(s1[i]) && isdigit(s2[i])) {
            int start1 = i;
            int start2 = i;
            
            int zeroCnt1 = 0, zeroCnt2 = 0;
            // 의미 있는 숫자가 나올 때까지 0 지워주기 007 > 7(start1이 가리키는 숫자)
            while(start1 <s1.size() && s1[start1] == '0') {
                start1++;
                zeroCnt1++;
            }
            while(start2 <s2.size() && s2[start2] == '0') {
                start2++;
                zeroCnt2++;
            }

            // 각 숫자 자리 비교
            int end1 = start1; int end2 = start2;
            while(end1 < s1.size() && isdigit(s1[end1])) {
                end1++;
            }
            while(end2 < s2.size() && isdigit(s2[end2])) {
                end2++;
            }

            // 자릿수 더 작은게 더 먼저
            int len1 = end1 - start1; 
            int len2 = end2 - start2;
            if(len1 != len2) {
                return len1 < len2;
            }

            // 자릿수가 같다면, 각 자리의 숫자값을 비교한다.
            while(start1 < end1 && start2 < end2) {
                if(s1[start1] != s2[start2]) {
                    return s1[start1] < s2[start2];
                }
                start1++; 
                start2++;
            }
            
            // 그것도 아니라면 앞에 있던 0 개수 비교
            if(zeroCnt1 != zeroCnt2) {
                if(zeroCnt1 < zeroCnt2) return true;
                else return false;
            }

            // 여기까지 왔으면 end1이나 end2나 완전히 동일한 인덱스 위치 가리키고 있음.!
            i = end1 - 1;
        }
        
    }
    return s1.size() < s2.size();

    
}

int main() {
    cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
    int N; cin >> N;
    arr.resize(N, "");
    for(int i=0; i<N; i++) {
        cin >> arr[i];
    }

    sort(arr.begin(), arr.end(), cmp);
    for(string s : arr) {
        cout << s << "\n";
    }
    return 0;
}