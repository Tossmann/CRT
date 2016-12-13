import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Traute on 08.12.2016.
 */
public class CRTForMulitplikation {

    BigInteger firstPrimeNumber;
    BigInteger secondPrimeNumber;
    BigInteger firstPlaceHolder;
    BigInteger secondPlaceHolder;
    static BigInteger z ;

    public CRTForMulitplikation(BigInteger firstPrimeNumber, BigInteger secondPrimeNumber){
        this.firstPrimeNumber= firstPrimeNumber;
        this.secondPrimeNumber = secondPrimeNumber;
        z = firstStep();
    }

    private BigInteger firstStep(){
        return EuclidianAlgorithm.calculateExtendedEuclidianAlgorithm(firstPrimeNumber,secondPrimeNumber);
    }

    public BigInteger multiplikate(BigInteger basis, BigInteger exponent) {
        prepareTwoOtherImportantValues(basis,exponent);
        BigInteger y = secondStep();
        return thirdStep(y, exponent);
    }

    public void prepareTwoOtherImportantValues(BigInteger basis, BigInteger exponent) {
        BigInteger basisFirst = basis.mod(firstPrimeNumber);
        BigInteger basisSecond = basis.mod(secondPrimeNumber);
        BigInteger exponentFirst = exponent.mod(firstPrimeNumber);
        BigInteger exponentSecond = exponent.mod(secondPrimeNumber);
        firstPlaceHolder = basisFirst.multiply(exponentFirst).mod(firstPrimeNumber);
        secondPlaceHolder= basisSecond.multiply(exponentSecond).mod(secondPrimeNumber);
    }

    private BigInteger secondStep(){
        return ((firstPlaceHolder.subtract(secondPlaceHolder)).multiply(z)).mod(firstPrimeNumber);
    }

    private BigInteger thirdStep(BigInteger inputValue, BigInteger exponent) {
        return secondPrimeNumber.multiply(inputValue).add(secondPlaceHolder);
    }


}
