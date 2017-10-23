package com.heig.amt_bootcamp_java.exceptions;

/**
 * This exception is for integrity constraint violation
 * 
 * @author Mathieu Monteverde, Sathiya Kirushnapillai
 */
public class IntegrityConstraintViolation extends Exception {

   /**
    * Message fill in by MYSQL JDBC Driver
    * @param message 
    */
   public IntegrityConstraintViolation(String message) {
      super(message);
   }
   
   /**
    * Return the value that cause the exception
    * @return 
    */
   public String getValue() {
      
      String message = this.getMessage();
      int x = message.indexOf("'");
      int y = message.indexOf("'", x + 1);
      String value = message.substring(x + 1, y);
      
      return value;
   }
}
