/**Author: Abrar*/

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class InversionCounter {
    public static Object[] sort_and_countinversions(int[] a, int start_idx, int end_idx){
        if (start_idx==end_idx ){
            // Instead of checking for length attribute we could have relied
            // on the midpoint
            Object[] vals = new Object[2];
            vals[0] = new int[]{a[start_idx]};
            vals[1]= 0L;
            return vals;
        }
        else{
            int middle_idx=(start_idx+end_idx)/2;
            Object[] temp = sort_and_countinversions(a, start_idx, middle_idx);
            //Possible source of error. Solution seems to be casting as 'Integer'
            int[] C = (int[])temp[0];
            long left_inv=(long)temp[1];
            temp = sort_and_countinversions(a, middle_idx+1, end_idx);
            int[] D = (int[])temp[0];
            //Possible source of error. Solution seems to be casting as 'Integer'
            long right_inv=(long)temp[1];

            //Output from the 'merge_and_count_split_inversion()'
            temp = merge_and_count_split_inversion(C,D);
            int[] B = (int[])temp[0];
            long split_inv = (long)temp[1];

            return new Object[]{B,(left_inv + right_inv + split_inv)};

        }
    }
    public static Object[] merge_and_count_split_inversion(int[] left, int[] right){
        int left_array_size= left.length;
        int right_array_size=right.length;
        int left_array_tracker = 0;
        int right_array_tracker=0;
        long split_inversions = 0;
        int[] merged_and_sorted=new int[left_array_size+right_array_size];
        int i = 0;
        while((left_array_tracker < left_array_size) && (right_array_tracker < right_array_size)){
            if (left[left_array_tracker] > right[right_array_tracker]){
                split_inversions+=left_array_size-left_array_tracker;
                merged_and_sorted[i] = right[right_array_tracker];
                right_array_tracker +=1;
                i++;

                if ((right_array_tracker==right_array_size)&&(i<=merged_and_sorted.length-1)){
                    while (i <= merged_and_sorted.length - 1) {
                        merged_and_sorted[i]=left[left_array_tracker];
                        left_array_tracker++;
                        i++;
                    }
                }
            }
            else{
                merged_and_sorted[i] = left[left_array_tracker];
                left_array_tracker++;
                i++;
                if ((left_array_tracker==left_array_size)&&(i<=merged_and_sorted.length-1)){
                    while (i <= merged_and_sorted.length - 1) {
                        merged_and_sorted[i]=right[right_array_tracker];
                        right_array_tracker++;
                        i++;
                    }
                }
            }
        }
        return new Object[]{merged_and_sorted, split_inversions};

    }

    public static void main (String[] args){
//        int[] demo = new int[] {55,40,90,0,1,30,10,-23,2};
//        Object[] demo_result=sort_and_countinversions(demo,0,8);
//        System.out.println(demo_result[1].toString());
//        int[] sorted_array = (int[])demo_result[0];
//        for (int j : sorted_array) {
//            System.out.println(j);
//        }
//        Since we have 100,000 number of ints in total
        int[] arr = new int[100000];
        try {
            File myObj = new File("C:\\Users\\abrar\\OneDrive\\Documents\\Extra learning + Online courses\\algorithm_coursera\\projects\\counting_num_of_inversions\\src\\IntegerArray.txt");
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

//        for (int e:arr){
//            System.out.println(e);
//        }
        Object[] result = sort_and_countinversions(arr, 0, arr.length-1);
        System.out.println("Number of inv: "+result[1].toString());
        int[] sorted_array = (int[])result[0];
        for (int j : sorted_array) {
            System.out.println(j);
        }
    }
}

