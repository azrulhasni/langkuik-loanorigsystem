/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azrul.langkuik.loanorigsystem.model;

import com.azrul.langkuik.framework.entity.Element;
import com.azrul.langkuik.framework.entity.WebEntity;
import com.azrul.langkuik.framework.entity.WebEntityType;
import com.azrul.langkuik.framework.field.WebField;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

/**
 *
 * @author azrul
 */
@Indexed
@Entity
@Where(clause = "status<>3")
@WebEntity(name = "Checklist",
        type = WebEntityType.NOMINAL
)
public class Checklist extends Element {

    @Column(name = "CONTRACT_SIGNED")
    @Audited
    @WebField(
            displayName = "Contract-signed",
            order = 1000,
            visibleInForm = "true",
            visibleInTable = "false")
    @NotNull(message = "Contract must be signed before proceeding")
    private Boolean contractSigned;

    @Column(name = "CONTRACT_COUNT")
    @Audited
    @WebField(
            displayName = "Number of contracts",
            order = 1100,
            visibleInForm = "true",
            visibleInTable = "false")
    @NotNull(message = "Number of contract")
    @Min(value = 1, message = "At least 1 contract")
    private Integer contractCount;

    @OneToOne(fetch = FetchType.LAZY)
    private Application application;

    /**
     * @return the contractSigned
     */
    public Boolean getContractSigned() {
        return contractSigned;
    }

    /**
     * @param contractSigned the contractSigned to set
     */
    public void setContractSigned(Boolean contractSigned) {
        this.contractSigned = contractSigned;
    }

    /**
     * @return the contractCount
     */
    public Integer getContractCount() {
        return contractCount;
    }

    /**
     * @param contractCount the contractCount to set
     */
    public void setContractCount(Integer contractCount) {
        this.contractCount = contractCount;
    }

    /**
     * @return the application
     */
    public Application getApplication() {
        return application;
    }

    /**
     * @param application the application to set
     */
    public void setApplication(Application application) {
        this.application = application;
    }

}
