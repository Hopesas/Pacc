
package Pacc;

import java.security.MessageDigest;

/**
 *
 * @author lenovo
 */
public class Crypt {
    private static MessageDigest md;
    
    public static String cryptWithMD5(String pass) {
        return Crypt.getHash(pass, "MD5");
    }
    
    public static String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
 
    /* Retorna un hash MD5 a partir de un texto */
    public static String md5(String txt) {
        return Crypt.getHash(txt, "MD5");
    }
}
