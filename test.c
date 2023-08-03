// ???  // ????
  				// ?????
/*

int main() {
  int arr[100], i = 0, sum = 0;
  while (getint()) {
  	arr[i] = getint();
    i = i + 1;
  }*/
int main() {
  int arr[100], sum = 0;
  int count = 10;
  while (count != 0) {
  	arr[count] = count;
    count = count - 1;
  }
  while (count != 10) {
    count = count + 1;
    sum = sum + arr[count];
  }
  return sum;
}