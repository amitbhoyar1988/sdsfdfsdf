package com.ps.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.logging.Logger;

public class RegExValidationUtils {

  static Logger logger = Logger.getLogger(RegExValidationUtils.class);

  static String validEmailRegex = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
  static String validMobileRegex = "[1-9]{1}[0-9]{9}";
  static String validAdhaarRegex = "[0-9]{12}";
  static String validPanRegex = "[a-zA-Z]{3}[P]{1}[a-zA-Z]{1}[0-9]{4}[a-zA-Z]{1}";
  static String validUANRegex = "[0-9]{12}";
  static String validVoterIdRegex = "[a-zA-Z]{3}[0-9]{7}";
  static String validPRANRegex = "[0-9]{12}";
  static String validPassportRegex = "[a-zA-Z]{1}[0-9]{7}";
  static String validPinRegex = "[1-9]{1}[0-9]{5}";

  static String validWebSite = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";

  public static boolean isValidEmail(String email) {

    if (logger.isDebugEnabled())
      logger.debug("In isValidEmail utility method " + "validating email " + email);

    if (email != null) {
      Pattern pattern = Pattern.compile(validEmailRegex);
      Matcher matcher = pattern.matcher(email);

      if (logger.isDebugEnabled())
        logger.debug(email + " : " + matcher.matches());
      return matcher.matches();
    }
    return false;
  }

  public static boolean isValidMobile(String mobile) {

    if (logger.isDebugEnabled())
      logger.debug("In isValidMobile utility method " + "validating mobile " + mobile);
    if (mobile != null) {
      if (mobile.length() > 10) {
        String actualMobileNumber = mobile.substring(mobile.length() - 10);

        Pattern pattern = Pattern.compile(validMobileRegex);
        Matcher matcher = pattern.matcher(actualMobileNumber);

        if (logger.isDebugEnabled())
          logger.debug(mobile + " : " + matcher.matches());
        return matcher.matches();
      }
    }
    return false;
  }

  public static boolean isValidMobileNew(String mobile) {

    if (logger.isDebugEnabled())
      logger.debug("In isValidMobile utility method " + "validating mobile " + mobile);
    if (mobile != null) {

      if (mobile.length() == 10) {

        Pattern pattern = Pattern.compile(validMobileRegex);
        Matcher matcher = pattern.matcher(mobile);

        if (logger.isDebugEnabled())
          logger.debug(mobile + " : " + matcher.matches());
        return matcher.matches();
      }
    }
    return false;
  }

  public static boolean isValidAdhaar(String adhaar) {

    if (logger.isDebugEnabled())
      logger.debug("In isValidAdhaar utility method " + "validating adhaar " + adhaar);
    if (adhaar != null) {
      Pattern pattern = Pattern.compile(validAdhaarRegex);
      Matcher matcher = pattern.matcher(adhaar);

      if (logger.isDebugEnabled())
        logger.debug(adhaar + " : " + matcher.matches());
      return matcher.matches();
    }
    return false;
  }

  public static boolean isValidPan(String pan) {

    if (logger.isDebugEnabled())
      logger.debug("In isValidpan utility method " + "validating pan " + pan);
    if (pan != null) {
      Pattern pattern = Pattern.compile(validPanRegex);
      Matcher matcher = pattern.matcher(pan);

      if (logger.isDebugEnabled())
        logger.debug(pan + " : " + matcher.matches());
      return matcher.matches();
    }
    return false;
  }

  public static boolean isValidUAN(String UAN) {

    if (logger.isDebugEnabled())
      logger.debug("In isValidUAN utility method " + "validating UAN " + UAN);
    if (UAN != null) {
      Pattern pattern = Pattern.compile(validUANRegex);
      Matcher matcher = pattern.matcher(UAN);

      if (logger.isDebugEnabled())
        logger.debug(UAN + " : " + matcher.matches());
      return matcher.matches();
    }
    return false;
  }

  public static boolean isValidVoterId(String voterId) {

    if (logger.isDebugEnabled())
      logger.debug("In isValidvoterId utility method " + "validating voterId " + voterId);
    if (voterId != null) {
      Pattern pattern = Pattern.compile(validVoterIdRegex);
      Matcher matcher = pattern.matcher(voterId);

      if (logger.isDebugEnabled())
        logger.debug(voterId + " : " + matcher.matches());
      return matcher.matches();
    }
    return false;
  }

  public static boolean isValidPRAN(String PRAN) {
    if (logger.isDebugEnabled())
      logger.debug("In isValidPRAN utility method " + "validating PRAN " + PRAN);

    if (PRAN != null) {
      Pattern pattern = Pattern.compile(validPRANRegex);
      Matcher matcher = pattern.matcher(PRAN);

      if (logger.isDebugEnabled())
        logger.debug(PRAN + " : " + matcher.matches());
      return matcher.matches();
    }
    return false;
  }

  public static boolean isValidPassport(String passport) {

    if (logger.isDebugEnabled())
      logger.debug("In isValidpassport utility method " + "validating passport " + passport);
    if (passport != null) {
      Pattern pattern = Pattern.compile(validPassportRegex);
      Matcher matcher = pattern.matcher(passport);

      if (logger.isDebugEnabled())
        logger.debug(passport + " : " + matcher.matches());
      return matcher.matches();
    }
    return false;
  }

  public static boolean isValidPin(String pin) {

    if (logger.isDebugEnabled())
      logger.debug("In isValidpin utility method " + "validating pin " + pin);
    if (pin != null) {
      Pattern pattern = Pattern.compile(validPinRegex);
      Matcher matcher = pattern.matcher(pin);

      if (logger.isDebugEnabled())
        logger.debug(pin + " : " + matcher.matches());
      return matcher.matches();
    }
    return false;
  }
  
  //Below: Added by MayurG
  public static boolean isValidWebsite(String website) {
    if (logger.isDebugEnabled())
      logger.debug("In isValidWebsite utility method " + "validating Website " + website);
    if (website != null) {
      Pattern pattern = Pattern.compile(validWebSite);
      Matcher matcher = pattern.matcher(website);

      if (logger.isDebugEnabled())
        logger.debug(website + " : " + matcher.matches());
      return matcher.matches();
    }
    return false;
  }

}