/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azrul.langkuik.loanorigsystem.user;

import com.azrul.langkuik.framework.user.UserProfile;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;
import com.azrul.langkuik.framework.user.UserIdentifierLookup;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.springframework.context.annotation.Primary;
import org.springframework.ldap.core.DirContextAdapter;

/**
 *
 * @author azrul
 */

@Service
@Primary
public class LdapUserIdentifierLookup  implements UserIdentifierLookup{

    public Optional<String> lookup(Map keycloakUserMap) {
        Map attributes = (Map)keycloakUserMap.get("attributes");
        if (attributes!=null){
            List ldapEntryList = (List)attributes.get("LDAP_ENTRY_DN");
            if (ldapEntryList!=null || !ldapEntryList.isEmpty()){
                return Optional.ofNullable((String)ldapEntryList.get(0));
            }
        }
        return Optional.empty();
    }
    
    public Optional<String> lookup(UserProfile user) {
        return Optional.of(user.getUserIdentifier());
    }

    
}
