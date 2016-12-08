/**
 * Created by andreydelany on 08/12/2016.
 */

import java.math.BigInteger;

public class Main {

    static BigInteger firstPrimeNumber = new BigInteger("101");
    static BigInteger secondPrimeNumber = new BigInteger("103");
    static BigInteger basis = new BigInteger("500");
    static BigInteger exponent = new BigInteger("3000");

    public static void main(String [ ] args) {
        BigInteger n = firstPrimeNumber.multiply(secondPrimeNumber);
        BigInteger blah = basis.multiply(exponent);
        System.out.println(blah.mod(n));
        CRT test = new CRT(firstPrimeNumber,secondPrimeNumber);
        System.out.println(test.pow(basis,exponent));
    }
}