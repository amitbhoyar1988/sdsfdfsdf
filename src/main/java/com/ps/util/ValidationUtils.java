package com.ps.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.logging.Logger;

public class ValidationUtils {
	  static Logger logger = Logger.getLogger(ValidationUtils.class);

	  static String validBloodGroup = "(A|B|AB|O)[+-]";
	  static String validAccountNumber = "^[0-9]*$";
	  static String validEmployeeCode = "[a-zA-Z0-9][a-zA-Z0-9\\/_-]*$";
	  static String validName = "(?=.*[a-z])|(?=.*[A-Z])|(?=.*\\p{L})+[\\p{L}+A-Za-z\\-_.'äöüß ]+";

	  public static boolean isValidName(String name) {

	    if (logger.isDebugEnabled())
	      logger.debug("In isValidName utility method " + "validating name " + name);

	    if (name != null) {

	      Pattern pattern = Pattern.compile(validName);
	      Matcher matcher = pattern.matcher(name);
	      if (logger.isDebugEnabled())
	        logger.debug(name + " : " + matcher.matches());
	      return matcher.matches();
	    }
	    return false;
	  }

	  public static boolean isValidEmployeeCode(String employeeCode) {

	    if (logger.isDebugEnabled())
	      logger.debug("In isValidEmployeeCode utility method " + "validating employeeCode " + employeeCode);

	    if (employeeCode != null) {
	      Pattern pattern = Pattern.compile(validEmployeeCode);
	      Matcher matcher = pattern.matcher(employeeCode);

	      if (logger.isDebugEnabled())
	        logger.debug(employeeCode + " : " + matcher.matches());
	      return matcher.matches();
	    }
	    return false;
	  }

	  public static boolean isValidAccountNo(String accountNo) {

	    if (logger.isDebugEnabled())
	      logger.debug("In isValidAccountNo utility method " + "validating accountNo " + accountNo);

	    if (accountNo != null && accountNo.length()>0) {

	      Pattern pattern = Pattern.compile(validAccountNumber);
	      Matcher matcher = pattern.matcher(accountNo);

	      if (logger.isDebugEnabled())
	        logger.debug(accountNo + " : " + matcher.matches());
	      return matcher.matches();
	    }
	    return false;
	  }

	  public static boolean isValidBloodGroup(String bloodGroup) {

	    if (logger.isDebugEnabled())
	      logger.debug("In isValidBloodGroup utility method " + "validating bloodGroup " + bloodGroup);

	    if (bloodGroup != null) {
	      Pattern pattern = Pattern.compile(validBloodGroup);
	      Matcher matcher = pattern.matcher(bloodGroup);

	      if (logger.isDebugEnabled())
	        logger.debug(bloodGroup + " : " + matcher.matches());
	      return matcher.matches();
	    }
	    return false;
	  }

/*
 * 	For OtherMaster: Currently it does not required
 * 
 * 	  public static boolean isValidGender(String gender) {

	    if (logger.isDebugEnabled())
	      logger.debug("In isValidGender utility method " + "validating gender " + gender);

	    if (gender != null) {

	      for (GenderEnum genderEnum : GenderEnum.values()) {
	        if (gender.equalsIgnoreCase(genderEnum.name())) {
	          return true;
	        }
	      }

	    }
	    return false;
	  }*/

	/*
	 * For OtherMaster: Currently it does not required
	 *   
	 *   public static boolean isValidMaritalStatus(String status) {

	    if (logger.isDebugEnabled())
	      logger.debug("In isValidMaritalStatus utility method " + "validating Marital Status " + status);

	    if (status != null) {

	      for (MaritalStatusEnum maritalStatusEnum : MaritalStatusEnum.values()) {
	        if (status.equalsIgnoreCase(maritalStatusEnum.name())) {
	          return true;
	        }
	      }
	    }
	    return false;
	  }
*/

/*
 * For OtherMaster: Currently it does not required
 * 
 * 	  public static boolean isValidRelationship(String realtion) {

	    if (logger.isDebugEnabled())
	      logger.debug("In isValidMaritalStatus utility method " + "validating Marital Status " + realtion);

	    if (realtion != null) {

	      for (RelationshipwithEmployeeEnum relationEnum : RelationshipwithEmployeeEnum.values()) {
	        if (realtion.equalsIgnoreCase(relationEnum.name())) {
	          return true;
	        }
	      }
	    }
	    return false;
	  }
*/
	
/*
 * For OtherMaster: Currently it does not required
 * 
 * 	  public static boolean selectGender(FamilyMemberInfo familyMemberInfo) {
	    String realtion = familyMemberInfo.getRelation();
	    String gender = familyMemberInfo.getGender();
	    if (realtion != null && gender != null) {
	      switch (realtion.toUpperCase()) {
	      case "FATHER":
	        if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("trans"))
	          return true;
	        break;

	      case "MOTHER":
	        if (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("trans"))
	          return true;
	        break;

	      case "BROTHER":
	        if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("trans"))
	          return true;
	        break;

	      case "SISTER":
	        if (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("trans"))
	          return true;
	        break;

	      case "WIFE":
	        if (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("trans"))
	          return true;
	        break;

	      case "SON":
	        if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("trans"))
	          return true;
	        break;

	      case "DAUGHTER":
	        if (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("trans"))
	          return true;
	        break;

	      case "HUSBAND":
	        if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("trans"))
	          return true;
	        break;

	      case "MOTHERINLAW":
	        if (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("trans"))
	          return true;
	        break;

	      case "FATHERINLAW":
	        if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("trans"))
	          return true;
	        break;

	      default:
	        logger.error("Relation not found!");
	        return false;
	      }
	    }
	    return false;
	  }
*/
	
}