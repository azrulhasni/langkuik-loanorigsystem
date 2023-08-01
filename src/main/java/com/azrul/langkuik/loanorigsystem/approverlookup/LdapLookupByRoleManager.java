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


@Service
@Qualifier("Manager")
public class LdapLookupByRoleManager<T extends Element> implements ApproverLookup<T> {

    @Autowired
    LdapTemplate ldapTemplate;

    @Override
    public Optional<UserProfile> lookupApprover(T work, String userIdentifier) {
        DirContextAdapter dca = (DirContextAdapter)ldapTemplate.lookup(userIdentifier);
             String managerDN =  dca.getStringAttribute("manager");
             if (managerDN!=null){
                DirContextAdapter dca2 = (DirContextAdapter) ldapTemplate.lookup(managerDN);
                UserProfile user = new UserProfile();
                user.setLoginName(dca2.getStringAttribute("uid"));
                user.setUserIdentifier(managerDN);
                return Optional.of(user);
             }
         return Optional.empty();
    }
}
