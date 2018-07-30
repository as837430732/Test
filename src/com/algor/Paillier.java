package com.algor;

/**
 * Created by 高浩然 on 2018/3/7.
 */


import java.math.*;
import java.util.*;


public class Paillier {

    /**
     * p and q are two large primes. lambda = lcm(p-1, q-1) =
     * (p-1)*(q-1)/gcd(p-1, q-1).
     */
    private BigInteger p, q, lambda;//p和q是两个大素数，lambda是p-1和q-1的最小公倍数
    /**
     * n = p*q, where p and q are two large primes.
     */
    public BigInteger n;//n=p*q
    /**
     * nsquare = n*n
     */
    public BigInteger nsquare;//nsquare = n*n
    /**
     * a random integer in Z*_{n^2} where gcd (L(g^lambda mod n^2), n) = 1.
     */
    private BigInteger g;
    /**
     * number of bits of modulus
     */
    private int bitLength;

    /**
     * Constructs an instance of the Paillier cryptosystem.
     *
     * @param bitLengthVal
     *            number of bits of modulus 模的位数
     * @param certainty
     *            The probability that the new BigInteger represents a prime
     *            number will exceed (1 - 2^(-certainty)). The execution time of
     *            this constructor is proportional to the value of this
     *            parameter.
     */
    public Paillier(int bitLengthVal, int certainty) {
        KeyGeneration(bitLengthVal, certainty);//密钥产生
    }

    /**
     * Constructs an instance of the Paillier cryptosystem with 512 bits of
     * modulus and at least 1-2^(-64) certainty of primes generation.
     */
    public Paillier() {
        KeyGeneration(8, 64);
    }

    /**
     * Sets up the public key and private key.
     *
     * @param bitLengthVal
     *            number of bits of modulus.
     * @param certainty
     *            The probability that the new BigInteger represents a prime
     *            number will exceed (1 - 2^(-certainty)). The execution time of
     *            this constructor is proportional to the value of this
     *            parameter.
     */
    //certainty越大精确度越高  素数的范围是0-2^(bitLength/2)
    public void KeyGeneration(int bitLengthVal, int certainty) {
        bitLength = bitLengthVal;
        /*
         * Constructs two randomly generated positive BigIntegers that are
         * probably prime, with the specified bitLength and certainty.
         */
        p = new BigInteger(bitLength / 2, certainty, new Random());
        //此构造函数用于构造一个随机生成正BigInteger的可能是以指定的bitLength的素数
        q = new BigInteger(bitLength / 2, certainty, new Random());
        System.out.println("p= "+p);
        System.out.println("q= "+q);

        n = p.multiply(q);
        nsquare = n.multiply(n);

        g = new BigInteger("2");
        //此构造函数用于将BigInteger的十进制字符串表示形式转换成一个BigInteger值。
        lambda = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE))
                .divide(p.subtract(BigInteger.ONE).gcd(q.subtract(BigInteger.ONE)));
        /* check whether g is good. */
        //g的选取有条件
        if (g.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).gcd(n).intValue() != 1) {
            System.out.println("g is not good. Choose g again.");
            System.exit(1);
        }
    }

    /**
     * Encrypts plaintext m. ciphertext c = g^m * r^n mod n^2. This function
     * explicitly requires random input r to help with encryption.
     *
     * @param m
     *            plaintext as a BigInteger
     * @param r
     *            random plaintext to help with encryption
     * @return ciphertext as a BigInteger
     */
    public BigInteger Encryption(BigInteger m, BigInteger r) {
        return g.modPow(m, nsquare).multiply(r.modPow(n, nsquare)).mod(nsquare);
    }

    /**
     *
     * Encrypts plaintext m. ciphertext c = g^m * r^n mod n^2. This function
     * automatically generates random input r (to help with encryption).
     *
     * @param m
     *            plaintext as a BigInteger
     * @return ciphertext as a BigInteger
     */
    public BigInteger Encryption(BigInteger m) {
        BigInteger r = new BigInteger(bitLength, new Random());
        //此构造函数用于构造一个随机生成的BigInteger，均匀分布在范围0到 (2的bitLength次方 - 1)
        return g.modPow(m, nsquare).multiply(r.modPow(n, nsquare)).mod(nsquare);

    }

    /**
     * Decrypts ciphertext c. plaintext m = L(c^lambda mod n^2) * u mod n, where
     * u = (L(g^lambda mod n^2))^(-1) mod n.
     *
     * @param c
     *            ciphertext as a BigInteger
     * @return plaintext as a BigInteger
     */
    public BigInteger Decryption(BigInteger c) {
        BigInteger u = g.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).modInverse(n);
        return c.modPow(lambda, nsquare).subtract(BigInteger.ONE).divide(n).multiply(u).mod(n);
    }

    /**
     * sum of (cipher) em1 and em2
     *
     * @param em1
     * @param em2
     * @return
     */
    public BigInteger cipher_add(BigInteger em1, BigInteger em2) {
        return em1.multiply(em2).mod(nsquare);
    }

    /**
     * main function
     *
     * @param str
     *            intput string
     */
    public static void main(String[] str) {
        /* instantiating an object of Paillier cryptosystem */
        long startTime = System.currentTimeMillis();//获取当前时间

        Paillier paillier = new Paillier();
        /* instantiating two plaintext msgs */
        BigInteger m1 = new BigInteger("20");
        BigInteger m2 = new BigInteger("60");
        /* encryption */
        BigInteger em1 = paillier.Encryption(m1);
        BigInteger em2 = paillier.Encryption(m2);
        /* printout encrypted text */
        System.out.println("em1= "+em1);
        System.out.println("em2= "+em2);
        /* printout decrypted text */
        System.out.println("D(em1)= "+paillier.Decryption(em1).toString());
        System.out.println("D(em2)= "+paillier.Decryption(em2).toString());

        /*
         * test homomorphic properties -> D(E(m1)*E(m2) mod n^2) = (m1 + m2) mod
         * n
         */
        // m1+m2,求明文数值的和
        BigInteger sum_m1m2 = m1.add(m2).mod(paillier.n);
        System.out.println("m1+m2= " + sum_m1m2.toString());
        // em1+em2，求密文数值的乘
        BigInteger product_em1em2 = em1.multiply(em2).mod(paillier.nsquare);
        System.out.println("em1*em2= " + product_em1em2.toString());
        System.out.println("D(em1*em2)= " + paillier.Decryption(product_em1em2).toString());
        //密文乘的结果解密后与明文加的结果相同

        /* test homomorphic properties -> D(E(m1)^m2 mod n^2) = (m1*m2) mod n */
        // m1*m2,求明文数值的乘
        BigInteger prod_m1m2 = m1.multiply(m2).mod(paillier.n);
        System.out.println("m1*m2= " + prod_m1m2.toString());
        // em1的m2次方，再mod paillier.nsquare
        BigInteger expo_em1m2 = em1.modPow(m2, paillier.nsquare);
        System.out.println("em1^m2= " + expo_em1m2.toString());
        System.out.println("D(em1^m2)= " + paillier.Decryption(expo_em1m2).toString());

        //sum test
        System.out.println("--------------------------------");
        Paillier p = new Paillier();
        BigInteger t1 = new BigInteger("21");System.out.println(t1.toString());
        BigInteger t2 = new BigInteger("50");System.out.println(t2.toString());
        BigInteger t3 = new BigInteger("50");System.out.println(t3.toString());
        BigInteger et1 = p.Encryption(t1);System.out.println(et1.toString());
        BigInteger et2 = p.Encryption(t2);System.out.println(et2.toString());
        BigInteger et3 = p.Encryption(t3);System.out.println(et3.toString());
        BigInteger sum = new BigInteger("1");
        sum = p.cipher_add(sum, et1);
        sum = p.cipher_add(sum, et2);
        sum = p.cipher_add(sum, et3);
        System.out.println("sum: "+sum.toString());
        System.out.println("decrypted sum: "+p.Decryption(sum).toString());
        System.out.println("--------------------------------");

        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间："+(endTime-startTime)+"ms");
    }
}
