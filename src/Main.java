/**
 * Created by andreydelany on 08/12/2016.
 */

import java.math.BigInteger;
import java.util.Random;

public class Main {

    static BigInteger firstPrimeNumber;
    static BigInteger secondPrimeNumber;
    static BigInteger basis;
    static BigInteger exponent;
    static BigInteger[] basisAndExponents;

    public static void main(String [ ] args) {
       basisAndExponents= generateManyBasisAndExponents(100);
       // calculateOwnImplentation();
       // runTestThatCRTWorksCorrect();
        //taskA();
        //taskB();
        taskC();
    }

    private static void calculateOwnImplentation() {
        System.out.println("RunTestThatCRTWorksCorrect:");
        basis = new BigInteger("10");
        exponent = new BigInteger("50");
        firstPrimeNumber = new BigInteger("101");
        secondPrimeNumber = new BigInteger("103");
        System.out.println("basis: " + basis + " exponent: " + exponent + " firstPrimeNumber: " + firstPrimeNumber + " secondPrimeNumber: " + secondPrimeNumber);
        CRTForPower crt = new CRTForPower(firstPrimeNumber,secondPrimeNumber);
        System.out.println(" Result is: " + crt.pow(basis,exponent));
        System.out.println(" From Implemented Version: " + basis.modPow(exponent,firstPrimeNumber.multiply(secondPrimeNumber)));
        System.out.println("");
    }

    private static void runTestThatCRTWorksCorrect() {
        System.out.println("RunTestThatCRTWorksCorrect:");
        generateRandomBasisAndExponent();
        generateRandomPrimes();
        BigInteger n = firstPrimeNumber.multiply(secondPrimeNumber);
        CRTForPower crt = new CRTForPower(firstPrimeNumber,secondPrimeNumber);
        System.out.println(" Own implementation: " + crt.pow(basis,exponent));
        System.out.println(" Implemented modPow: " + basis.modPow(exponent,n));
    }

    private static void taskA() {
        generateRandomPrimes();
        CRTForPower crt = new CRTForPower(firstPrimeNumber,secondPrimeNumber);
        BigInteger n = firstPrimeNumber.multiply(secondPrimeNumber);

        int amount = basisAndExponents.length/2;

        System.out.println(amount + " Iterations: ");

        TimeTracker first = new TimeTracker();
        first.start();
        for (int i = 0; i < amount; i+= 2) {
            basisAndExponents[i].modPow(basisAndExponents[i+1],n);
        }
        first.end();
        System.out.println("Implemented needed: " + first.get() + " ms");

        TimeTracker second = new TimeTracker();
        second.start();
        for (int i = 0; i < amount; i+= 2) {
            crt.pow(basisAndExponents[i],basisAndExponents[i+1]);
        }
        second.end();
        System.out.println("Self Implemented needed: " + second.get() + " ms");

        System.out.println("Verhälnis: " + (double)(second.get()) / (double)first.get());
    }

    private static void taskB() {
        generateRandomBasisAndExponent();

        for (int i = 20; i < 2000; i += 100) {
            firstPrimeNumber= generateRandomPrimeNumber(i);
            secondPrimeNumber= generateRandomPrimeNumber(i);
            BigInteger n = firstPrimeNumber.multiply(secondPrimeNumber);
            CRTForPower crt = new CRTForPower(firstPrimeNumber,secondPrimeNumber);
            TimeTracker first = new TimeTracker();
            first.start();
            for(int j = 0; j < 10; j ++)
                crt.pow(basis,exponent);
            first.end();
            TimeTracker second = new TimeTracker();
            second.start();
            for(int j = 0; j < 10; j ++)
                basis.modPow(exponent,n);
            second.end();

            System.out.println("Amount of Bits: " + i + " -> " + first.get() + " ms  " + second.get() + " ms  " + (double)first.get()/(double)second.get());
        }
    }

    private static void taskC() {
        generateRandomPrimes();
        CRTForPower crt = new CRTForPower(firstPrimeNumber,secondPrimeNumber);
        BigInteger n = firstPrimeNumber.multiply(secondPrimeNumber);

        int amount = basisAndExponents.length;

        System.out.println(100 * amount + " Iterations: ");

        TimeTracker first = new TimeTracker();
        first.start();
        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < amount; i++) {
                basisAndExponents[i].modPow(new BigInteger("3"),n);
            }
        }
        first.end();
        System.out.println("Implemented needed: " + first.get() + " ms");

        TimeTracker second = new TimeTracker();
        second.start();
        for (int j = 0; j < 100; j++) {
            for (int i = 0; i < amount; i++) {
                crt.pow(basisAndExponents[i],new BigInteger("3"));
            }
        }
        second.end();
        System.out.println("Own needed: " + second.get() + " ms");

        System.out.println("Verhälnis: " + (double)(second.get()) / (double)first.get());
    }

    private static BigInteger[] generateManyBasisAndExponents(int amount) {
        BigInteger [] result = new BigInteger[amount];
        for (int i = 0; i < amount; i ++) {
            result[i] = generateRandom1024Number();
        }
        return result;
    }

    private static void generateRandomPrimes() {
        firstPrimeNumber = generateRandom1024BitPrimeNumber();
        secondPrimeNumber = generateRandom1024BitPrimeNumber();
    }

    private static void generateRandomBasisAndExponent(){
        basis = generateRandom1024Number();
        exponent = generateRandom1024Number();
    }

    private static BigInteger generateRandom1024BitPrimeNumber() {
        Random rnd = new Random();
        BigInteger bigInteger;
        do {
        bigInteger = new BigInteger(1024,Integer.MAX_VALUE, rnd);
        } while(! bigInteger.isProbablePrime(100));
        return bigInteger;
    }

    private static BigInteger generateRandomPrimeNumber(int amountOfBits) {
        Random rnd = new Random();
        BigInteger bigInteger;
        do {
            bigInteger = new BigInteger(amountOfBits,Integer.MAX_VALUE, rnd);
        } while(! bigInteger.isProbablePrime(100));
        return bigInteger;
    }

    private static BigInteger generateRandom1024Number() {
        Random rnd = new Random();
        BigInteger bigInteger = new BigInteger(1024,0, rnd);
        return bigInteger;
    }
}