package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.CipherUtils;
import java.io.PrintWriter;

/** ALLCaesar class, encapsulating all Caesar cipher functionality. */
public class AllCaesar {
  /**
   * Returns wether String str is lowercase.
   *
   * @param str message
   * @return boolean truth value
   */
  // checks that string is all lower case characters
  public static boolean myIsLower(String str) {
    for (char c : str.toCharArray()) {
      if (!Character.isLowerCase(c)) {
        return false;
      } // end if
    } // end for

    return true;
  } // end myIsLower()

  /**
   * Main method function.
   *
   * @param args String keyword arguments
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);

    if (args.length != 2) {
      System.err.println("Error: Incorrect number of parameters.");
      return;
    } // end if

    String arg0 = args[0];
    String arg1 = args[1];

    if (!myIsLower(arg1)) {
      System.err.println("Error: String contains characters other than lowercase letters.");
      pen.close();
      return;
    } // end if

    if (arg0.equals("encode")) {
      for (char ch = 'a'; ch <= 'z'; ch++) {
        pen.printf("n = %c: %s\n", ch, CipherUtils.caesarEncrypt(arg1, ch));
      } // end for
    } else if (arg0.equals("decode")) {
      for (char ch = 'a'; ch <= 'z'; ch++) {
        pen.printf("n = %c: %s\n", ch, CipherUtils.caesarDecrypt(arg1, ch));
      } // end for
    } else {
      System.err.println(
          "Error: Invalid option: " + arg0 + ". Valid options are \"encode\" or \"decode\".");
      pen.close();
      return;
    } // end else

    pen.close();
  } // end main()
} // end AllCaesar
