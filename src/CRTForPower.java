import java.math.BigInteger;

/**
 * Created by andreydelany on 12/12/2016.
 */
public class CRTForPower {

    BigInteger firstPrimeNumber;
    BigInteger secondPrimeNumber;
    BigInteger firstPlaceHolder;
    BigInteger secondPlaceHolder;
    BigInteger z ;

    public CRTForPower(BigInteger firstPrimeNumber, BigInteger secondPrimeNumber){
        this.firstPrimeNumber= firstPrimeNumber;
        this.secondPrimeNumber = secondPrimeNumber;
        z = firstStep();
    }

    private BigInteger firstStep(){
        return EuclidianAlgorithm.calculateExtendedEuclidianAlgorithm(secondPrimeNumber,firstPrimeNumber);
    }

    public BigInteger pow(BigInteger basis, BigInteger exponent) {

        prepareTwoOtherImportantValues(basis,exponent);
        BigInteger y = secondStep();
        return thirdStep(y);
    }

    public void prepareTwoOtherImportantValues(BigInteger basis, BigInteger exponent) {
        BigInteger preparedBasisWithFirstPrime = basis.mod(firstPrimeNumber);
        BigInteger preparedBasisWithSecondPrime = basis.mod(secondPrimeNumber);

        BigInteger prepareFirstExponent = exponent.mod(firstPrimeNumber.subtract(BigInteger.ONE));
        BigInteger prepareSecondExponent = exponent.mod(secondPrimeNumber.subtract(BigInteger.ONE));

        firstPlaceHolder = preparedBasisWithFirstPrime.modPow(prepareFirstExponent,firstPrimeNumber);
        secondPlaceHolder = preparedBasisWithSecondPrime.modPow(prepareSecondExponent,secondPrimeNumber);
    }

    private BigInteger secondStep(){
        return ((firstPlaceHolder.subtract(secondPlaceHolder)).multiply(z)).mod(firstPrimeNumber);
    }

    private BigInteger thirdStep(BigInteger inputValue) {
        return inputValue.multiply(secondPrimeNumber).add(secondPlaceHolder);
    }


}
