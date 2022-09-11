package com.app.APFinal.aplication.util;

public class RegExpressionUtils {

  public static final String ALPHANUMERIC_CHARACTERS_WITHOUT_BLANK_SPACES =
      "^[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|´']*$";
  public static final String URL =
      "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

  private RegExpressionUtils() {

  }

}
