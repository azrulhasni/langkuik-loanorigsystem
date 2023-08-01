/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.azrul.langkuik.loanorigsystem.model;

import com.azrul.langkuik.custom.money.Money;
import com.azrul.langkuik.framework.entity.WebEntity;
import com.azrul.langkuik.framework.field.WebField;
import com.azrul.langkuik.framework.relationship.WebRelation;
import com.azrul.langkuik.framework.rights.FieldAccess;
import com.azrul.langkuik.framework.rights.FieldRights;
import com.azrul.langkuik.framework.rights.RelationAccess;
import com.azrul.langkuik.framework.rights.RelationRights;
import com.azrul.langkuik.framework.entity.Element;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;

/**
 *
 * @author azrulm
 */
@Indexed
@Entity
@WebEntity(name="Collateral")
public class Collateral extends Element implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @IndexedEmbedded(name = "Address")
    @Audited
    @WebRelation(name = "Address",order = 200,  rights = {
        @RelationAccess(atWorklist = "S3.VALUATION",currentOwner = RelationRights.CAN_ADD_AND_DELETE_OWN_ITEMS)
    })
    @OneToOne(fetch=FetchType.LAZY, mappedBy="collateral")
    private Address addressCollection;

    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable=Searchable.YES)
    @GenericField(name="Value",searchable = Searchable.YES)
    @Column(name = "VALUE",precision = 18, scale=2)
    @Money(currency="MYR")
    @WebField(displayName = "Value", order = 300, 
            rights = {
                @FieldAccess(atWorklist = "S3.VALUATION",currentOwner = FieldRights.CAN_EDIT),
            })
    @DecimalMin(value="0", inclusive=false, message="The value must be bigger than 0 ")
    private BigDecimal value;
    
    @WebField(displayName = "Require appraisal", order = 400,  rights = {
                @FieldAccess(atWorklist = "S3.VALUATION",currentOwner = FieldRights.CAN_EDIT)
            })
    @Column(name = "REQUIRE_APPRAISAL")
    private Boolean requireAppraisal;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Application application;
    

    public Collateral() {
    }

    public Collateral(Long id) {
        this.id = id;
    }

//    public Long getCollateralId() {
//        return collateralId;
//    }
//
//    public void setCollateralId(Long collateralId) {
//        this.collateralId = collateralId;
//    }

  

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Boolean getRequireAppraisal() {
        return requireAppraisal;
    }

    public void setRequireAppraisal(Boolean requireAppraisal) {
        this.requireAppraisal = requireAppraisal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Collateral)) {
            return false;
        }
        Collateral other = (Collateral) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (addressCollection!=null){
            return "at "+addressCollection;
        }else{
            return "["+id+"]";
        }
    }

    /**
     * @return the addressCollection
     */
    public Address getAddressCollection() {
        return addressCollection;
    }

    /**
     * @param addressCollection the addressCollection to set
     */
    public void setAddressCollection(Address addressCollection) {
        this.addressCollection = addressCollection;
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
