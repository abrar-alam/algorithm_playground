
//import org.apache.commons.lang.StringUtils;
import java.math.BigInteger;
public class KaratsubaMultiplier {
    public static void main (String[] args){
        BigInteger m = new BigInteger("3141592653589793238462643383279502884197169399375105820974944592");
        BigInteger n = new BigInteger("2718281828459045235360287471352662497757247093699959574966967627");
        BigInteger o = m.multiply(n);
        System.out.println("Normal multiplication:"+o.toString());
        String p = karatsuba("3141592653589793238462643383279502884197169399375105820974944592",
                "2718281828459045235360287471352662497757247093699959574966967627");
        System.out.println("Karatsuba multiplication:"+p);
    }

    public static String karatsuba(String x, String y){


        /* Base case*/
        /* Here we assumed x and y have the same number of digits.
         so we only check for the number of digits in x */
        if (x.length() == 1){
            return String.valueOf(Integer.parseInt(x) * Integer.parseInt(y));
        }
        else{
            //Left half portion of x
            String a = x.substring(0,x.length()/2);
            //Right half portion of x
            String b = x.substring(x.length()/2);
            //Left half portion of y
            String c = y.substring(0,y.length()/2);
            //Right half portion of y
            String d = y.substring(y.length()/2);

            BigInteger big_int_a = new BigInteger(a);
            BigInteger big_int_b = new BigInteger(b);
            BigInteger big_int_c = new BigInteger(c);
            BigInteger big_int_d = new BigInteger(d);

            String ac = karatsuba(a,c);
            String bd = karatsuba(b,d);

            String ad = karatsuba(a,d);
            String bc = karatsuba(b,c);
            BigInteger ad_plus_bc = new BigInteger(ad).add(new BigInteger(bc));

            String n_zeros = "";
            String n_by_2_zeros = "";
            int n = x.length();
            for (int i = 0; i < n; i++){
                n_zeros += 0;
                if (i < (n/2)){
                    n_by_2_zeros += 0;
                }
            }
            String first_result_seg = ac+n_zeros;
            String second_result_seg = ad_plus_bc.toString()+n_by_2_zeros;
            String result =
                    (new BigInteger(first_result_seg).add(new BigInteger(second_result_seg)).add(new BigInteger(bd))).toString();

            return result;
        }

    }
}
