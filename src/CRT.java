import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Traute on 08.12.2016.
 */
public class CRT {

    BigInteger x;
    BigInteger y;
    BigInteger n;

    public CRT(BigInteger x, BigInteger y){
        this.x = x;
        this.y = y;
        this.n = x.multiply(y);
    }
    public BigInteger pow(BigInteger basis, BigInteger exponent){
        Random prime = new Random();
        
    }
}
