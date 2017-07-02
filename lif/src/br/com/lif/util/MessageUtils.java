package br.com.lif.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageUtils {


private static ResourceBundle bundle;
   
   private static ResourceBundle getBundle() {
      if (bundle == null) {
         bundle = ResourceBundle.getBundle(Constants.RESOURCES_MESSAGES, Locale.getDefault());
         if (bundle == null) {
            bundle = ResourceBundle.getBundle(Constants.RESOURCES_MESSAGES_PT_BR);
         }
      }
      return bundle;
   }
   
   public static String getMessage(String key, Object... parameters){	   
	   return MessageFormat.format(getBundle().getString(key), parameters);
   }
}
