/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siess1.jsf.controladores.util;

import java.security.*;
/**
 *
 * @author Aprendiz
 */
public class AlgoritmoMD5 {

    public static String cifrarMD5(String strContrasena) {

        String strContrasenaMD5 = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] tmp = strContrasena.getBytes();
            md5.update(tmp);
            strContrasenaMD5 = byteArrToString(md5.digest());
        } catch (NoSuchAlgorithmException ex) {
            strContrasenaMD5 = null;
        }
        return strContrasenaMD5;
    }

    private static String byteArrToString(byte[] b) {
        String res = null;
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int j = b[i] & 0xff;
            if (j < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(j));
        }
        res = sb.toString();
        return res.toUpperCase();
    }

}
