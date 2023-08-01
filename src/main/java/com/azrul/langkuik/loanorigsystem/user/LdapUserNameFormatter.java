/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azrul.langkuik.loanorigsystem.user;

import com.azrul.langkuik.framework.user.UserNameFormatter;
import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 *
 * @author azrul
 */
@Service
@Primary
public class LdapUserNameFormatter implements UserNameFormatter {

    @Override
    public String format(String userIdentifier) {
        if (userIdentifier==null){
            return "";
        }
        String s = userIdentifier;
        try {
            LdapName ln = new LdapName(userIdentifier);
            
            for (Rdn rdn : ln.getRdns()) {
                if (rdn.getType().equalsIgnoreCase("CN")) {
                    s =  (String) rdn.getValue();
                }
            }
        } catch (InvalidNameException ex) {
            //if exception occurs, the userIdentifierPassed is not LDAP compatible
        }
        return s;
    }

}
