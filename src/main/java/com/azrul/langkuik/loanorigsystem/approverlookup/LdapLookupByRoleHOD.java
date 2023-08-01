/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azrul.langkuik.loanorigsystem.approverlookup;

import com.azrul.langkuik.custom.approval.ApproverLookup;
import com.azrul.langkuik.framework.entity.Element;
import com.azrul.langkuik.framework.user.UserProfile;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author azrul
 */
@Service
@Qualifier("HOD")
public class LdapLookupByRoleHOD<T extends Element> implements ApproverLookup<T> {

    @Autowired
    LdapTemplate ldapTemplate;

    //This is a sample implementation of Head of Department (HOD) look up. We can even lookup through LDAP or AD if we want
    @Override
    public Optional<UserProfile> lookupApprover(T work, String userIdentifier) {
        //return  Optional.of((String)ldapTemplate.lookup(userIdentifier));
        DirContextAdapter dca = (DirContextAdapter) ldapTemplate.lookup(userIdentifier);
        String managerDN = dca.getStringAttribute("manager");
        if (managerDN != null) {
            DirContextAdapter dca2 = (DirContextAdapter) ldapTemplate.lookup(managerDN);
            String hodDN = dca2.getStringAttribute("manager");
            if (hodDN != null) {
                DirContextAdapter dca3 = (DirContextAdapter) ldapTemplate.lookup(hodDN);
                UserProfile user = new UserProfile();
                user.setLoginName(dca3.getStringAttribute("uid"));
                user.setUserIdentifier(hodDN);
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

}
