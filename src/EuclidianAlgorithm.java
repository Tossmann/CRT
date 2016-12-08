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

        BigInteger c = firstPrimeNumber;
        BigInteger d = secondPrimeNumber;
        BigInteger q;

        while (c.intValue() != 0) {
            q = d.divide(c);
            BigInteger tempCopyC = c;
            c = d.subtract(q.multiply(c));
            d = tempCopyC;
            BigInteger tempCopyUC = uc;
            BigInteger tempCopyVC = vc;
            uc = ud.subtract(q.multiply(uc));
            vc = vd.subtract(q.multiply(vc));
            ud = tempCopyUC;
            vd = tempCopyVC;
        }
        return ud;
    }
}
