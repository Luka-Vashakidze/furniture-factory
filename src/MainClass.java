public class MainClass {
    public static void main(String[] args) {
        int[] arr = {13,21,25,4,9};
        int n = arr.length;


        for(int i = 1;i < n; i++ ){
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key){
                arr[j+1] = arr[j];
                j = j - 1;

            }
            arr[j+1] = key;
        }
        for (int j : arr) {
            System.out.println(j);
        }
    }
}
