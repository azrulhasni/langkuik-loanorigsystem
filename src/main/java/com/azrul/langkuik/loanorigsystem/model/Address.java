/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azrul.langkuik.loanorigsystem.model;

import com.azrul.langkuik.framework.entity.WebEntity;
import com.azrul.langkuik.framework.field.WebField;
import com.azrul.langkuik.custom.lookupchoice.Lookup;
import com.azrul.langkuik.framework.format.Format;
import com.azrul.langkuik.framework.format.FormatType;
import com.azrul.langkuik.framework.rights.FieldAccess;
import com.azrul.langkuik.framework.rights.FieldRights;
import com.azrul.langkuik.framework.entity.Element;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Where;
import org.hibernate.envers.Audited;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.engine.backend.types.TermVector;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

/*@Entity
@Indexed
@WebEntity(name = "Address")
public class Address extends Element implements Serializable {
    //other fields
    @KeywordField(name="City",  searchable=Searchable.YES)
    @GenericField(sortable = Sortable.YES, searchable=Searchable.YES)
    @WebField(displayName = "City", order = 500)
    @Lookup(entity = DistrictStateCountry.class, field = "district", filterBy = "state")
    private String city;

    //getters and setters
}*/
/**
 *
 * @author azrulm
 */
@Entity
@Indexed
@WebEntity(name = "Address")
public class Address extends Element implements Serializable {

    /**
     * @return the collateral
     */
    public Collateral getCollateral() {
        return collateral;
    }

    /**
     * @param collateral the collateral to set
     */
    public void setCollateral(Collateral collateral) {
        this.collateral = collateral;
    }

    /**
     * @return the applicantFromCurrent
     */
    public Applicant getApplicantFromCurrent() {
        return applicantFromCurrent;
    }

    /**
     * @param applicantFromCurrent the applicantFromCurrent to set
     */
    public void setApplicantFromCurrent(Applicant applicantFromCurrent) {
        this.applicantFromCurrent = applicantFromCurrent;
    }

    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }

    @Audited
    @FullTextField(analyzer="english",name="Street-address", searchable=Searchable.YES, termVector = TermVector.YES)
    //@GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable=Searchable.YES) //no need as we dont display in tale
    @Size(max = 100)
    @Column(name = "STREET_ADDRESS")
    @WebField(displayName = "Street-address", order = 200, visibleInForm = "true", visibleInTable = "false",
              rights = {
                @FieldAccess(atWorklist = "S3.VALUATION",currentOwner=FieldRights.CAN_EDIT)
            })
    @NotEmpty(message = "Street address cannot be empty")
    @Size(min = 5, max = 255, message = "Street address must be more than characters")
    private String streetAddress;

    @Audited
     @FullTextField(analyzer="english",name="Country",  searchable=Searchable.YES)
    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable=Searchable.YES)
    @Size(max = 50)
    @Column(name = "COUNTRY")
    @WebField(displayName = "Country", order = 300,  rights = {
                @FieldAccess(atWorklist = "S3.VALUATION",currentOwner = FieldRights.CAN_EDIT)
            })
    @Size(min = 2, max = 255, message = "Country must be more than characters")
    @NotEmpty(message = "Country cannot be empty")
    @Lookup(entity = DistrictStateCountry.class, field = "country")
    private String country;

    @Audited
    @FullTextField(analyzer="english",name="State",  searchable=Searchable.YES)
    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable=Searchable.YES)
    @Size(max = 50)
    @Column(name = "STATE")
    @WebField(displayName = "State", order = 400,   rights = {
                @FieldAccess(atWorklist = "S3.VALUATION",currentOwner = FieldRights.CAN_EDIT)
            })
    @Size(min = 2, max = 255, message = "State must be more than characters")
    @NotEmpty(message = "Country cannot be empty")
    @Lookup(entity = DistrictStateCountry.class, field = "state", filterBy = "country")
    private String state;

    @Audited
    @FullTextField(analyzer="english",name="City",  searchable=Searchable.YES)
    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable=Searchable.YES)
    @Size(max = 50)
    @Column(name = "CITY")
    @WebField(displayName = "City", order = 500,  rights = {
                @FieldAccess(atWorklist = "S3.VALUATION",currentOwner = FieldRights.CAN_EDIT)
            })
    @Size(min = 2, max = 255, message = "City must be more than characters")
    @NotEmpty(message = "City cannot be empty")
    @Lookup(entity = DistrictStateCountry.class, field = "district", filterBy = "state")
    private String city;

    @Audited
    @KeywordField(name="Postal-code", searchable=Searchable.YES)
    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable=Searchable.YES)
    @Size(max = 50)
    @Column(name = "POSTALCODE")
    @WebField(displayName = "Postal-code", order = 600,   rights = {
                @FieldAccess(atWorklist = "S3.VALUATION",currentOwner = FieldRights.CAN_EDIT)
            }
            ,visibleInForm = "true")
    @Size(min = 5, max = 5, message = "Postal code must be 5 character long")
    @NotEmpty(message = "Postal code cannot be empty")
    private String postalcode;

    @Audited
    @KeywordField(name="Phone-number",  searchable=Searchable.YES)
    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable=Searchable.YES)
    @Size(max = 50)
    @Column(name = "Phone_number")
    @WebField(displayName = "Phone-number", order = 700, format = @Format(
            blocks = {2, 4, 4},
            delimiters = {"-","-"},
            showPrefixImmediately = true,
            type = FormatType.NUMERIC
    ),  rights = {
                @FieldAccess(atWorklist = "S3.VALUATION",currentOwner = FieldRights.CAN_EDIT)
            })
    private String phoneNumber;

    @OneToOne(fetch = FetchType.LAZY)
    private Collateral collateral;

    @OneToOne(fetch = FetchType.LAZY)
    private Applicant applicantFromCurrent;

    /**
     * @return the streetAddress
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * @param streetAddress the streetAddress to set
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the postalcode
     */
    public String getPostalcode() {
        return postalcode;
    }

    /**
     * @param postalcode the postalcode to set
     */
    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

     @Override
    public String toString() {
        return "streetAddress=" + streetAddress + ':';
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Address)) {
            return false;
        }
        Address other = (Address) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

     @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

}
