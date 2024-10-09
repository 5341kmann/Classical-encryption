package edu.grinnell.csc207.util;

/**
 * Utility Class: Providing core functionality of Vigenere and Caesar Cipher encryption and
 * decryption schemes.
 * Written for Prof. Samuel Rebelsky's CSC-207 Fall 2024.
 * @author Grant Sackmann
 */
public class CipherUtils {
  /** ASCII base-ing value. */
  private static final int BASE = 97;

  /** English alphabet length. */
  private static final int ALPHABET_LEN = 26;

  /**
   * Returns equivalent ASCII value of given char letter.
   *
   * @param letter some lower case ASCII letter
   * @return int corresponding ASCII integer [0, 25]
   */
  private static int letter2int(char letter) {
    int charVal = letter - BASE;
    return charVal;
  } // end letter2int()

  /**
   * Returns matching ASCII character of given int number.
   *
   * @param i integer [0, 25]
   * @return char ASCII character
   */
  private static char int2letter(int i) {
    char letter = (char) (i + BASE);
    return letter;
  } // end int2letter()

  /**
   * Returns the String Caesar encryption of the given String message and char letter shift key.
   *
   * @param str String message
   * @param letter char letter shift
   * @return String Caesar encrypted String
   */
  public static String caesarEncrypt(String str, char letter) {
    StringBuilder stringBuilder = new StringBuilder();

    int keyVal = letter2int(letter);
    for (int i = 0; i < str.length(); i++) {
      int newLetterAsInt = (letter2int(str.charAt(i)) + keyVal) % ALPHABET_LEN;
      stringBuilder.append(int2letter(newLetterAsInt));
    } // end for
    
    String newString = stringBuilder.toString();
    // clearing stringBuilder
    stringBuilder.setLength(0);
    return newString;
  } // end caesarEncrypt()

  /**
   * Returns the String Caesar .decryption of the given String char letter shift key.
   *
   * @param str String message
   * @param letter char letter shift
   * @return String String of the decrypted message
   */
  public static String caesarDecrypt(String str, char letter) {
    StringBuilder stringBuilder = new StringBuilder();

    int keyVal = letter2int(letter);
    for (int i = 0; i < str.length(); i++) {
      int newLetterAsInt = (letter2int(str.charAt(i)) - keyVal) % ALPHABET_LEN;
      if (newLetterAsInt < 0) {
        newLetterAsInt += ALPHABET_LEN;
      } // end if
      stringBuilder.append(int2letter(newLetterAsInt));
    } // end for

    String newString = stringBuilder.toString();
    // clearing stringBuilder
    stringBuilder.setLength(0);
    return newString;
  } // end caesarDecrypt()

  /**
   * Returns Vigenere encryption of message using the pairwise key to shift characters.
   *
   * @param str String message of lowercase ASCII characters
   * @param key String of lowercase ASCII characters
   * @return String result of encrypted message
   */
  public static String vigenereEncrypt(String str, String key) {
    String vigenereString = key.repeat((str.length() / key.length()) + 1);
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < str.length(); i++) {
      int newLetterAsInt =
          ((letter2int(str.charAt(i)) + letter2int(vigenereString.charAt(i))) % ALPHABET_LEN);
      stringBuilder.append(int2letter(newLetterAsInt));
    } // end for

    String newString = stringBuilder.toString();
    stringBuilder.setLength(0);

    return newString;
  } // end vigenereString()

  /**
   * Returns Vigenere decryption of message using the pairwise key to shift characters.
   *
   * @param str String message of lowercase ASCII characters
   * @param key String of lowercase ASCII characters
   * @return String result of decrypted message
   */
  public static String vigenereDecrypt(String str, String key) {
    String vigenereString = key.repeat((str.length() / key.length()) + 1);
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < str.length(); i++) {
      int newLetterAsInt =
          ((letter2int(str.charAt(i)) - letter2int(vigenereString.charAt(i))) % ALPHABET_LEN);
      if (newLetterAsInt < 0) {
        newLetterAsInt += ALPHABET_LEN;
      } // end if
      stringBuilder.append(int2letter(newLetterAsInt));
    } // end for

    String newString = stringBuilder.toString();
    stringBuilder.setLength(0);

    return newString;
  } // end vigenereDecrypt()
} // end main()
