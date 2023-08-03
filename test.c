int main() {
  int arr[2], sum = 0;
  int count = 0;
  while (count != 2) {
  	arr[count] = count;
    count = count + 1;
  }
  while (count != 2) {
    count = count - 1;
    sum = sum + arr[count];
  }
  return sum;
}
