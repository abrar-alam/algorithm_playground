import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuickSort {
    public static int total_comparison = 0;

    public static void main (String[] args){
//        System.out.println("Hello");
//        int[] test = new int[]{1,2,0,-12, -6,8, 19};
//        quicksort(test, 0, test.length-1);
//        for (int i=0; i<=test.length-1;i++){
//            System.out.println(test[i]);
//        }
//        System.out.println("\nTot. num of comparison: "+ total_comparison);

        int[] arr = new int[10000];
        try {
            File myObj = new File("C:\\Users\\abrar\\OneDrive\\Documents\\Extra learning + Online courses\\algorithm_edx\\projects\\quicksort\\src\\QuickSort.txt");
            Scanner myReader = new Scanner(myObj);
            int i = 0;
            while (myReader.hasNextLine()) {
                int data = myReader.nextInt();
                //System.out.println(data);
                arr[i]=data;
                i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        quicksort(arr, 0, 9999);
        System.out.println(total_comparison);
        System.out.println("\n\nSorted array below");
        for (int i=0; i<10000; i++){
            System.out.println(arr[i]);
        }

//        int[] temp_arr = new int[]{8,2,4,5,7,1};
//        System.out.println(choosepivot(arr, 0, 9999));

    }
    public static void quicksort(int[] arr, int l, int r){
        if (l >= r){
            return;
        }
        int i = choosepivot(arr, l, r);// p is the index of the pivot element
        int temp = arr[l];
        arr[l] = arr[i];
        arr[i] = temp;
        int j = partition(arr, l, r);
        quicksort(arr, l, j-1);
        quicksort(arr, j+1, r);
    }

    private static int choosepivot(int[] arr, int l, int r){
        int arr_size=r-l+1;
        int median_index;
        if (arr_size%2 == 0){
            median_index = ((arr_size/2)-1)+l;
        }
        else{
            median_index = (arr_size/2);
        }
        int[] temp = new int[]{arr[l], arr[median_index], arr[r]};
        int minimum;
        int min_index;
        for (int i=0; i < temp.length-1; i++){
            min_index = i;
            for (int j = i+1; j <=temp.length-1 ; j++){
                if (temp[j] < temp[min_index]){
//                    total_comparison+=1;
                    min_index = j;
                }
            }
            int temp_var = temp[min_index];
            temp[min_index] = temp[i];
            temp[i] = temp_var;
        }
        if (temp[1] == arr[l]){
            return l;
        }
        else if (temp[1] == arr[r]){
            return r;
        }
        else {
            return median_index;
        }
    }

    private static int partition(int[] a, int l, int r){
        int p = a[l];
        int i=l+1;
        total_comparison += (r-i)+1;
        for (int j=l+1; j<=r; j++){
            if (a[j]<p){
                int temp = a[j];
                a[j] = a[i];
                a[i] = temp;
                i++;
            }
        }
        int temp = a[l];
        a[l]=a[i-1];
        a[i-1]=temp;
        return i-1;
    }
}
