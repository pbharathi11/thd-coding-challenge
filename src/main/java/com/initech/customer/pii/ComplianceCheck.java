package com.initech.customer.pii;

import com.initech.customer.pii.*;
/**
 *
 */
public class ComplianceCheck {


    /**
     *
     * @param S
     * @return
     */
    public String maskPII(String S) throws Exception {
        if(S.indexOf("@")>0)
            return maskEmail(S);
        else
            return maskPhoneNumber(S);
    }

    private String maskPhoneNumber(String S) {
        StringBuilder builder = new StringBuilder();
        int length = S.length();
            int count = 0;
            for (int i = length - 1; i >= 0; i--) {
                char c = S.charAt(i);
                if (c >= '0' && c <= '9') {
                    if (count < 4 || count > 7) {
                        builder.insert(0, c);

                    } else if (count == 7) {
                        builder.insert(0, "-");
                        builder.insert(0, c);
                    } else {
                        if (count == 4) {
                            builder.insert(0, "-");
                        }
                        builder.insert(0, "*");
                    }
                    count++;
                }
            }
            if (count > 10) {
                builder.insert(0, "+");
            }
        return builder.toString();
    }


    /**
     *
     * @param S
     * @return
     */
    String maskEmail(String S) {
        // define :
        // s.length ≥ 80
        // All email addresses are guaranteed to be valid and in the format of "name1@name2.name3".

        // all names must be converted to lowercase and
        // all letters between the first and last letter of the first name must be replaced by 5 asterisks '*'

            String[] emailTokens = S.split("@");
            String firstName = emailTokens[0];
            StringBuffer sb = new StringBuffer();
            sb.append(firstName.charAt(0));
            sb.append("*****");
            sb.append(firstName.charAt(firstName.length() - 1));
            sb.append("@").append(emailTokens[1]);

            return sb.toString().toLowerCase();
    }

}