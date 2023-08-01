/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.azrul.langkuik.loanorigsystem.bizlogic;

import com.azrul.langkuik.framework.dao.DataAccessObject;
import com.azrul.langkuik.framework.dao.params.FindRelationParameter;
import com.azrul.langkuik.framework.dao.query.FindRelationQuery;
import com.azrul.langkuik.framework.entity.Status;
import com.azrul.langkuik.framework.entity.WorkElement;
import com.azrul.langkuik.framework.factory.SpringBeanFactory;
import com.azrul.langkuik.framework.user.UserProfile;
import com.azrul.langkuik.loanorigsystem.model.Applicant;
import com.azrul.langkuik.loanorigsystem.model.Application;
import com.azrul.langkuik.loanorigsystem.model.Experian;
import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author azrul
 */
public class ExperianBureauLogic {

    public void run(UserProfile user, String tenant, Object oCurrentObject) {
        DataAccessObject dao = SpringBeanFactory.lookup(DataAccessObject.class);
        Application currentObject = (Application)oCurrentObject;
        FindRelationQuery query = new FindRelationQuery(
                new FindRelationParameter(
                        currentObject,
                        "applicants",
                        Optional.empty()
                )
        );
        Collection<Applicant> applicants = dao.runQuery(query, Optional.of(tenant), Optional.empty());

        for (Applicant applicant : applicants) {
            
            //Call bureau for each applicant
            //Each call return 3 matches as per below
            //We simulate 'ambiguity' in the match by appending _X to the forename
            Optional<Experian> experian1 = dao.createAndSave(Experian.class,
                    Optional.of(tenant),
                    Optional.of(applicant.getEnumPath()),
                    Optional.empty(),
                    Optional.empty(),
                    Status.DONE,
                    user.getUserIdentifier());
            experian1.ifPresent(e -> {
                e.setParentId(applicant.getId());
                e.setBureauScore(50);
                e.setBureauResult(applicant.getForename() + "_1 " + applicant.getSurname());
                dao.save(e);
            });

            Optional<Experian> experian2 = dao.createAndSave(Experian.class,
                    Optional.of(tenant),
                    Optional.of(applicant.getEnumPath()),
                    Optional.empty(),
                    Optional.empty(),
                    Status.DONE,
                    user.getUserIdentifier());

            experian2.ifPresent(e -> {
                e.setParentId(applicant.getId());
                e.setBureauScore(50);
                e.setBureauResult(applicant.getForename() + "_2 " + applicant.getSurname());
                dao.save(e);
            });

            Optional<Experian> experian3 = dao.createAndSave(Experian.class,
                    Optional.of(tenant),
                    Optional.of(applicant.getEnumPath()),
                    Optional.empty(),
                    Optional.empty(),
                    Status.DONE,
                    user.getUserIdentifier());

            experian3.ifPresent(e -> {
                e.setParentId(applicant.getId());
                e.setBureauScore(50);
                e.setBureauResult(applicant.getForename() + "_3 " + applicant.getSurname());;
                dao.save(e);
            });
        }
    }
}
