package deu.team.jsp.notification.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

  public static byte[] sha256(String msg) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(msg.getBytes());
    return md.digest();
  }

  public static String bytesToHex1(byte[] bytes) {
    StringBuilder builder = new StringBuilder();
    for (byte b : bytes) {
      builder.append(String.format("%02x", b));
    }
    return builder.toString();
  }

  public static String stringToSha256HexString(String string) throws NoSuchAlgorithmException {
    return bytesToHex1(sha256(string)).substring(0, 32);
  }
}
