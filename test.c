int c;
int main() {
    int a = 5, b = 4;
    if (a >= b) {
        c = a;
    } else {
        c = b;
    }
    int d = c;
    while(d > 0) {
        d = d - 1;
        c = c + 1;
    }
    return c;
}