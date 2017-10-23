package com.heig.amt_bootcamp_java.exceptions;

public class IntegrityConstraintViolation extends Exception {

   
   
   public IntegrityConstraintViolation(String message) {
      super(message);
   }
   
   public String getValue() {
      
      String message = this.getMessage();
      int x = message.indexOf("'");
      int y = message.indexOf("'", x + 1);
      String value = message.substring(x + 1, y);
      
      return value;
   }
}
