package com.knoldus.Validator

class MobileNumberValidator {


  def mobileNumberIsValid(mobileNo: Long): Boolean = {
    mobileNo.toString.matches("(0/91)?[7-9][0-9]{9}")

  }
}
