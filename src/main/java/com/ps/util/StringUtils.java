	package com.ps.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.jboss.logging.Logger;

import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.RESTful.response.enums.ErrorCode;

public class StringUtils {

  static Logger logger = Logger.getLogger(StringUtils.class);

  public static boolean isValidString(String stringToValidate) {
    if (stringToValidate != null && !stringToValidate.isEmpty() && stringToValidate.length()>0 && stringToValidate!="") {
      if (logger.isDebugEnabled())
        logger.debug("In isValidString utility method: String is Valid:");
      return true;
    }

    if (logger.isDebugEnabled())
      logger.debug("In isValidString utility method: String is Invalid:" + stringToValidate);

    return false;
  }

  public static Date stringToDate(String simpleString) {
    if (logger.isDebugEnabled())
      logger.debug("In stringToDate utility method " + "converting string: " + simpleString);
    if (simpleString == null) {
      return null;
    }
    Date date = null;
    if (!simpleString.isEmpty()) {
      DateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

      try {
        date = format.parse(simpleString);

      } catch (ParseException error) {
        logger.error(error.getMessage());
        throw new InvalidRequestException(ErrorCode.INVALID_PARAMETER, simpleString+": Date formate is wrong");
      }
      if (logger.isDebugEnabled())
        logger.debug("converted date:" + date);

    }
    return date;
  }

  public static String dateToString(Date date) {
    if (logger.isDebugEnabled())
      logger.debug("In dateToString utility method " + "converting date: " + date);
    String strDate = null;
    if (date != null) {
      DateFormat format = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
      strDate = format.format(date);

      if (logger.isDebugEnabled())
        logger.debug("converted date:" + strDate);

    }
    return strDate;
  }
  
  
  public static Date currentDate() {
	    String currDate =  null;
	    Date dtCurrDate = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
	                        
	    try{
	      currDate = formatter.format(dtCurrDate);
	      dtCurrDate = StringUtils.stringToDate(currDate);
	      }
	     catch (Exception e) {
	      logger.error("Exception occured while fetching current Date "+e.getMessage(),e);      
	      e.printStackTrace();
	    } 
	    return dtCurrDate;
	  }
	  
	  public static Date convertToddMMMyyyy(Date date) {
	    String currDate =  null;
	    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
	    
	    try{
	     currDate = formatter.format(date);
	     date = StringUtils.stringToDate(currDate);
	       }
	     catch (Exception e) {
	      logger.error("Exception occured while converting date to dd-MMMM-yyyy formate "+e.getMessage(),e);      
	      e.printStackTrace();
	    } 
	    return date;
	  }
  
  

}