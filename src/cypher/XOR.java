/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cypher;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

public class XOR {
  public static void main(String[] args) throws Exception {
        XOR xor = new XOR();

        String text = "Mr. and Mrs. Dursley, of number four Privet Drive, were proud to say that they were perfectly normal, thank you very much.";
        String[] pieces = xor.encrypt(text, 5);
        System.out.println(pieces[0]);
        System.out.println(pieces[1]);
        System.out.println(pieces[2]);
        System.out.println(pieces[3]);
        System.out.println(pieces[4]);
        String test = xor.decrypt(pieces);
        System.out.println(test);

    }

    public String[] encrypt(String s, int n) throws Exception {
        int length = s.length();
        String[] pieces = new String[n];

        for (int i = 0; i < n - 1; i++) {
            pieces[i] = keyGen(length);
        }

        String nth = s;
        for (int i = 0; i < n - 1; i++) {
            nth = doXOR(pieces[i], nth);
        }

        pieces[n - 1] = nth;
        return pieces;
    }

    public String decrypt(String[] pieces) {

        String decrypted = new String(new char[pieces[0].length()]).replace("\0", Character.toString((char) 0));
        for (String piece : pieces) {
            decrypted = doXOR(piece, decrypted);
        }
        return decrypted;
    }

    public String toBinary(String s) throws Exception {
        byte[] bytes = s.getBytes("ASCII");
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return binary.toString();
    }


    public String keyGen(int length) throws UnsupportedEncodingException {
        SecureRandom srand = new SecureRandom();
        byte[] bytes = new byte[length];
        srand.nextBytes(bytes);
        return new String(bytes, "ASCII");
    }

    public String doXOR(String s1, String s2) throws IllegalArgumentException {

        String ret;
        if (s1.length() != s2.length()) {
            throw new IllegalArgumentException("The bit strings must have equal length!");
        } else {
            ret = "";
            for (int i = 0; i < s1.length(); i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(i);
                int i1 = (int) c1;
                int i2 = (int) c2;
                int i3 = i1 ^ i2;
                char c3 = (char) i3;
                ret += c3;
            }
        }
        return ret;
    }   
}
