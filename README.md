# thd-coding-challenge
THD Coding Challenge

## Assumptions
* Supports only US phone numbers with or without +1 as prefix
* The masked email/phone number value returned by the below masking logic is saved in the database
* isValidEmail - Validates the email format and returns false if not satisfied. Example: abc@gmail.com
* isValidPhoneNumber - Validates for a standard US phone number and returns false if not satisfied. Example: (470)258-0816

## Pseudocode

```
// Let the class name be ComplianceCheck

function maskPII(String str)
{
BEGIN

CONSTANT MASK = "******"
CONSTANT AT_THE_RATE = "@"
CONSTANT ASTERISK = "*"
CONSTANT HYPHEN = "-"
CONSTANT PLUS = "+"

// Email Id masking flow
IF str.indexOf(AT_THE_RATE) > 0

IF str length > 80
  THROW INVALIDEMAILADDRESSEXCEPTION -> EXITS LENGTH
ELSE IF str -> isValidEmail -> returns false
  THROW INVALIDEMAILADDRESSEXCEPTION -> NOT VALID EMAIL FORMAT
ELSE
  IF str length<= 80
    Initialize StringBuffer sb
    SET String[] emailTokens = str.split(AT_THE_RATE);
    SET name = emailTokens[0]
    APPEND to stringBuffer ->  name.charAt(0)
    APPEND to stringBuffer -> MASK
    APPEND to stringBuffer -> name.charAt(name.length-1)
    APPEND to stringBuffer -> AT_THE_RATE
    APPEND emailTokens[0]
  END IF
    RETURN sb.toString toLowerCase 
    
ELSE
  // Phone number masking flow

  IF str -> isValidPhoneNumber -> returns false
    THROW INVALIDPHONENUMBEREXCEPTION -> NOT VALID PHONE NUMBER
  ELSE
    Initialize StringBuffer sb
    SET length = str.length
    SET count =0
    FOR EACH char i in the str
      SET c = str.charAt(i)
      IF count is < 4 OR count > 7
        INSERT to stringBuffer -> sb.insert(0, c);
      ELSE IF count ==7
        INSERT to stringBuffer -> sb.insert(0, HYPHEN);
        INSERT to stringBuffer -> sb.insert(0, c);
      ELSE
        IF count==4
          INSERT to buffer -> sb.insert(0, ASTERISK);
        END IF
        INSERT to buffer -> sb.insert(0, ASTERISK);
      END IF
      INCREMENT count by 1
    END FOR
    IF count > 10
          INSERT to buffer -> sb.insert(0, PLUS);
    END IF
  RETURN sb.toString
END IF
END
}

```

### Usage Documentation

```

import com.initech.customer.pii.ComplianceCheck; 

String email = test@gmail.com
String result = new ComplianceCheck().maskPII(email);


String phoneNumber = (470)258-0816
String result = new ComplianceCheck().maskPII(phoneNumber);

```

## Unit tests

#### Scenario 1 : testValidEmailAddressInLowerCase() - Valid email address in lowercase ####
```
Given -  
      String input = bharupal@gmail.com
      String output = b*****l@gmail.com
      given(new ComplianceCheck().maskPII(input)).willReturn(output);
When -
      result = new ComplianceCheck().maskPII(input)

Then -
    assertThat(result).equals(output);


```
#### Scenario 2 : testValidEmailAddressInUpperCase() - Valid email address with uppercase ####
```
Given -  
      String input = BP@gmail.com
      String output = b*****p@gmail.com
      given(new ComplianceCheck().maskPII(input)).willReturn(output);
When -
      result = new ComplianceCheck().maskPII(input)
Then -
      assertThat(result).equals(output);

```   
#### Scenario 3 : testValidPhoneNumber() - Valid US phone Number ####
```
Given -  
      String input = (470)258-0816
      String output = 470-***-0816
      given(new ComplianceCheck().maskPII(input)).willReturn(output);
When -
      result = new ComplianceCheck().maskPII(input)

Then -
      assertThat(result).equals(output);

```
#### Scenario 4 : testValidPhoneNumberWithCountryCode() - Valid US Number with country code ####
```
    Given -  
        String input = 470-***-0816
        String output = +1470-***-0816
        given(new ComplianceCheck().maskPII(input)).willReturn(output);
When -
        result = new ComplianceCheck().maskPII(input)

Then -
      assertThat(result).equals(output);
      
```      
#### Scenario 5 : testInvalidEmailAddress() - invalid email address ####
```
Given -  
    String input = BPgmail.com
    given(new ComplianceCheck().maskPII(input)).willReturn(new InvalidEmailAddressException());
When -
    result = new ComplianceCheck().maskPII(input)
Then -
    Expect InvalidEmailAddressException

```
#### Scenario 6 : testInvalidEmailAddress_Null() - invalid email address which is null ####
```
Given -  
        String input = null
        given(new ComplianceCheck().maskPII(input)).willReturn(new NullPointerException());
When -
        result = new ComplianceCheck().maskPII(input)
Then -
        Expect NullPointerException

```
#### Scenario 7 : testInvalidPhoneNumber() - invalid phone number ####
```
  Given -  
    String input = +12334345435456546
    given(new ComplianceCheck().maskPII(input)).willReturn(new InvalidPhoneNumberException());
  When -
        result = new ComplianceCheck().maskPII(input)
  Then -
        Expect InvalidPhoneNumberException

