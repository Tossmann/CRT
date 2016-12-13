import java.math.BigInteger;

/**
 * Created by andreydelany on 08/12/2016.
 */
public class EuclidianAlgorithm {

    public static BigInteger calculateExtendedEuclidianAlgorithm(BigInteger firstPrimeNumber, BigInteger secondPrimeNumber) {
        BigInteger uc = new BigInteger("1");
        BigInteger vc = new BigInteger("0");
        BigInteger ud = new BigInteger("0");
        BigInteger vd = new BigInteger("1");

        BigInteger c = firstPrimeNumber.abs();
        BigInteger d = secondPrimeNumber.abs();
        BigInteger q;

        while (c.compareTo(BigInteger.ZERO) != 0) {
            q = d.divide(c);
            BigInteger tempCopyC = d.subtract(q.multiply(c));
            d = c;
            c = tempCopyC;

            BigInteger tempCopyUC = uc;
            BigInteger tempCopyVC = vc;
            BigInteger tempCopyUd = ud;
            BigInteger tempCopyVd = vd;

            uc = tempCopyUd.subtract(q.multiply(tempCopyUC));
            vc = tempCopyVd.subtract(q.multiply(tempCopyVC));
            ud = tempCopyUC;
            vd = tempCopyVC;
        }
        return ud;
    }
}


