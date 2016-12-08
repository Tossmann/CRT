/**
 * Created by andreydelany on 08/12/2016.
 */

import java.math.BigInteger;
import java.util.Random;

public class Main {

    static BigInteger firstPrimeNumber = new BigInteger("101");
    static BigInteger secondPrimeNumber = new BigInteger("103");
    static BigInteger basis = new BigInteger("500");
    static BigInteger exponent = new BigInteger("3000");

    public static void main(String [ ] args) {
        //generateRandomNumbers();

        BigInteger n = firstPrimeNumber.multiply(secondPrimeNumber);

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1; i++)
            basis.modPow(exponent,n);
        long endTime = System.currentTimeMillis();
        System.out.println(basis.modPow(exponent,n));
        System.out.println("-> " + (endTime - startTime));


        CRT crt = new CRT(firstPrimeNumber,secondPrimeNumber);
        startTime = System.currentTimeMillis();
        PowerWithCRT power = new PowerWithCRT(crt);
        for (int i = 0; i < 1; i++) {
            power.calculate(basis,exponent);
        }
        System.out.println(power.calculate(basis,exponent));
        endTime = System.currentTimeMillis();
        System.out.println("-> " + (endTime - startTime));
    }

    private static void generateRandomNumbers() {
        firstPrimeNumber = generateRandom1024BitPrimeNumber();
        secondPrimeNumber = generateRandom1024BitPrimeNumber();
        basis = generateRandom1024Number();
        exponent = generateRandom1024Number();
    }

    private static BigInteger generateRandom1024BitPrimeNumber() {
        Random rnd = new Random();
        BigInteger bigInteger;
        do {
            bigInteger = new BigInteger(1024,rnd);
        } while (!bigInteger.isProbablePrime(10));
        return bigInteger;
    }

    private static BigInteger generateRandom1024Number() {
        Random rnd = new Random();
        BigInteger bigInteger = new BigInteger(1024,rnd);
        return bigInteger;
    }
}