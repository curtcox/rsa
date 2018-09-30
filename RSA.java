import java.math.BigInteger;
import java.security.SecureRandom;

final class RSA {

   private final static BigInteger one      = new BigInteger("1");
   private final static SecureRandom random = new SecureRandom();

   private final BigInteger privateKey;
   private final BigInteger publicKey;
   private final BigInteger modulus;

   // generate an N-bit (roughly) public and private key
   RSA(int N) {
      BigInteger p = BigInteger.probablePrime(N/2, random);
      BigInteger q = BigInteger.probablePrime(N/2, random);
      BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

      modulus    = p.multiply(q);                                  
      publicKey  = new BigInteger("65537");     // common value in practice = 2^16 + 1
      privateKey = publicKey.modInverse(phi);
   }

   BigInteger encrypt(BigInteger message) {
      return message.modPow(publicKey, modulus);
   }

   BigInteger decrypt(BigInteger encrypted) {
      return encrypted.modPow(privateKey, modulus);
   }

   public String toString() {
      return
          "public  = " + publicKey  + "\n" +
          "private = " + privateKey + "\n" +
          "modulus = " + modulus;
   }
 
}
