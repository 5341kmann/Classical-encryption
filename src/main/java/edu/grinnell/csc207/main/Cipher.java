package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.CipherUtils;
import java.io.PrintWriter;

/** Cipher class, enabling both caesar and vigenere cipher functionality. */
public class Cipher {
  /** Maximum number of CML arguments. */
  private static final int MAX_SIZE = 4;
  /**
   * Returns truth value of wether String argument is valid.
   *
   * @param arg keyword string argument
   * @return boolean truth value
   */
  public static boolean isValidArg(String arg) {
    if (arg != "" && arg != null) {
      if (arg.charAt(0) == '-') {
        return AllCaesar.myIsLower(arg.substring(1));
      } else {
        return AllCaesar.myIsLower(arg);
      } // end else
    } // end if
    return false;
  } // end isValidArg()

  /**
   * Main class.
   *
   * @param args keyword argument array
   */
  public static void main(String[] args) {
    // PenWriter object for printing to console.
    PrintWriter pen = new PrintWriter(System.out, true);

    if (args.length != MAX_SIZE) {
      System.err.println("Error: Incorrect number of parameters.");
      return;
    } // end if

    // boolean defining the state as either -caesar and -vigenere.
    boolean isCaesar = false;
    // boolean defining the state as either -encode and -decode.
    boolean isEncode = false;
    // cipher message
    String msg = null;
    // cipher key
    String key = null;

    for (String arg : args) {
      if (isValidArg(arg)) {
        if (arg.charAt(0) == '-') {
          if (arg.equals("-encode")) {
            isEncode = true;
          } else if (arg.equals("-decode")) {
            isEncode = false;
          } else if (arg.equals("-vigenere")) {
            isCaesar = false;
          } else if (arg.equals("-caesar")) {
            isCaesar = true;
          } else {
            System.err.println("Error: Unknown option:" + arg);
            pen.close();
            return;
          } // end if
        } else if (msg == null) {
          msg = arg;
        } else {
          key = arg;
        } // end else
      } else {
        System.err.println("Error: Invalid argument: " + arg);
        pen.close();
        return;
      } // end else
    } // end for

    // function calling post argument variable fills
    if (isCaesar) {
      if (key.length() != 1) {
        System.err.println(
            "Error: Invalid Input: Require single character " + "key for Caesar cipher");
        return;
      } else if (isEncode) {
        pen.printf(CipherUtils.caesarEncrypt(msg, key.charAt(0)));

      } else {
        pen.printf(CipherUtils.caesarDecrypt(msg, key.charAt(0)));
      } // end else
    } else {
      if (isEncode) {
        pen.printf(CipherUtils.vigenereEncrypt(msg, key));
      } else {
        pen.printf(CipherUtils.vigenereDecrypt(msg, key));
      } // end else
    } // end if

    // Clearing the pen to remove chance of memory leaks: redundant
    pen.close();
    return;
  } // end main()
} // end
