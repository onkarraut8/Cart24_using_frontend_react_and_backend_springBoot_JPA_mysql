package com.OnlineShopping.cart24.utility;

public class Helper {
	
	public static String getAlphaNumericOrderId()
    {
  
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";
  
        StringBuilder sb = new StringBuilder(10);
  
        for (int i = 0; i < 10; i++) {
  
            int index
                = (int)(AlphaNumericString.length()
                        * Math.random());
  
            sb.append(AlphaNumericString
                          .charAt(index));
        }
  
        return sb.toString().toUpperCase();
    }
	
	public static String getNumericOtp()
    {
  
        String NumericString ="1234567890";
  
        StringBuilder sb = new StringBuilder(6);
  
        for (int i = 0; i < 6; i++) {
  
            int index
                = (int)(NumericString.length()
                        * Math.random());
  
            sb.append(NumericString
                          .charAt(index));
        }
  
        return sb.toString();
    }

}
