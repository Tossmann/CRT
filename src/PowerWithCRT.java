import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by andreydelany on 08/12/2016.
 */
public class PowerWithCRT {
    BigInteger x = new BigInteger("0");
    BigInteger y = new BigInteger("0");
    BigInteger multiplikationsCounter = new BigInteger("0");
    BigInteger currentValue = new BigInteger("0");
    BigInteger currenExponent = new BigInteger("0");
    ArrayList<BigInteger> savedExponents = new ArrayList<>();
    ArrayList<BigInteger> correspondingValues = new ArrayList<>();
    BigInteger nextExponent = new BigInteger("0");
    BigInteger missingExponents = new BigInteger("0");
    CRT crt;

    public PowerWithCRT(CRT crt) {
        this.crt = crt;
    }

    public BigInteger calculate(BigInteger basis, BigInteger exponent) {
        initializeValues(basis, exponent);
        calculateOptimizedPower();
        return currentValue;
    }

    private void initializeValues(BigInteger basis, BigInteger exponent) {
        this.x = basis;
        this.y = exponent;
        currentValue = x;
        multiplikationsCounter = BigInteger.ZERO;
        currenExponent = BigInteger.ONE;
        nextExponent = BigInteger.ONE;
        correspondingValues.add(currentValue);
        correspondingValues.add(currenExponent);
    }

    private void calculateOptimizedPower() {
        calculateLargestPossibleExponentWichFits();
        if (isThereSomethingMissingFromTheExponent())
            fillExponentUpWithSavedValues();
    }

    private void calculateLargestPossibleExponentWichFits() {
        while (isNextExponentFitting()) {
            multiplicateCurrentValueWithItSelf();
            calculateHowMuchIsMissing();
        }
    }

    private boolean isThereSomethingMissingFromTheExponent() {
        return (missingExponents.compareTo(BigInteger.ZERO) == 1);
    }

    private void fillExponentUpWithSavedValues() {
        reverseLists();
        goThroughListsAndMultiplikateFittingExponents();
    }

    private boolean isNextExponentFitting() {
        return (nextExponent.compareTo(y) <= 0);
    }

    private void multiplicateCurrentValueWithItSelf() {
        multiplikationsCounter = multiplikationsCounter.add(BigInteger.ONE);
        currenExponent = currenExponent.add(currenExponent);
        nextExponent = currenExponent.add(currenExponent);
        currentValue = crt.multiplikate(currentValue,currentValue);
        updateLists();
    }

    private void calculateHowMuchIsMissing() {
        missingExponents = y.subtract(currenExponent);
    }

    private BigInteger getNextExponent() {
        return currenExponent.multiply(currenExponent);
    }

    private void updateLists(){
        savedExponents.add(currenExponent);
        correspondingValues.add(currentValue);
    }

    private void reverseLists() {
        Collections.reverse(correspondingValues);
        Collections.reverse(savedExponents);
    }

    private void goThroughListsAndMultiplikateFittingExponents() {
        for(BigInteger exponent:savedExponents) {
            if(isExponentFittingInTheAmountOfMissingsExponetes(exponent)) {
                int index = savedExponents.indexOf(exponent);
                multiplikateExponent(index);
                if (! isThereSomethingMissingFromTheExponent())
                        return;
            }
        }
    }

    private boolean isExponentFittingInTheAmountOfMissingsExponetes(BigInteger exponent) {
        return(exponent.compareTo(missingExponents) <= 0);
    }

    private void multiplikateExponent (int index) {
        multiplikationsCounter = multiplikationsCounter.add(BigInteger.ONE);
        missingExponents.subtract(savedExponents.get(index));
        currentValue = crt.multiplikate(currentValue,correspondingValues.get(index));
    }
}
