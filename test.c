const int delta = 1;
int main() {
    int a = 1;
    int b = 2;
    int c = 3;
    if (a > b) {
        if (b > c) {
          c = c * 2;
        }
    }else if (a == b) {
        c = c + a + b;
    }else {
        c = a - b;
    }
    return c;
}