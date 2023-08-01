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
import com.azrul.langkuik.framework.format.Format;
import com.azrul.langkuik.framework.format.FormatType;
import com.azrul.langkuik.framework.entity.Element;
import com.azrul.langkuik.framework.entity.WebEntityType;
import com.azrul.langkuik.framework.entity.WorkElement;
import com.azrul.langkuik.framework.field.FieldVisibility;
import com.azrul.langkuik.framework.rights.RelationAccess;
import com.azrul.langkuik.framework.rights.RelationRights;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.hibernate.envers.Audited;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Where;


/*@Indexed
@Entity
@WebEntity(name = "Applicant", type=WebEntityType.NOMINAL)
public class Applicant extends Element implements Serializable {
    //other fields

    @IndexedEmbedded(name = "Current-address")
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "applicantFromCurrent")
    @WebRelation(name = "Current-address", order = 1000)
    private Address currentAddressCollection;

    //getters and setters
}*/
/**
 *
 * @author azrulm
 */
@Indexed
@Entity
@WebEntity(name = "Applicant", type=WebEntityType.NOMINAL,
        fieldVisibility={
            @FieldVisibility(fieldName="priority", visibleInForm = "true", visibleInTable = "true"),
            @FieldVisibility(fieldName="creator", visibleInForm = "true", visibleInTable = "true"),
            @FieldVisibility(fieldName="workflowInfo", visibleInTable = "true")})
public class Applicant extends Element implements Serializable {

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

    //-----------Personal info---------------------------------------------//
    @GenericField( sortable = Sortable.YES, projectable = Projectable.YES, searchable=Searchable.YES)
    @FullTextField(analyzer="english",name="Forename", searchable = Searchable.YES )
    @Audited
    @WebField(displayName = "Forename", order = 200)
    @NotEmpty(message = "Forename cannot be empty")
    @Size(min = 3, max = 50, message = "Forename must be at least 3 characters long")
    private String forename;

    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable=Searchable.YES)
    @FullTextField(analyzer="english",name="Surname", searchable = Searchable.YES)
    @Audited
    @WebField(displayName = "Surname", order = 300)
    @NotEmpty(message = "Surname cannot be empty")
    @Size(min = 3, max = 50, message = "Surname must be at least 3 characters long")
    private String surname;

    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable=Searchable.YES)
    @GenericField(name="Age", searchable = Searchable.YES)
    @Audited
    @Column(name = "AGE_OF_APPLICANT")
    @WebField(displayName = "Age", order = 400)
    @DecimalMin(value = "18", inclusive = true, message = "Age must be at least 18")
    private Integer ageOfApplicant;

    @GenericField(name="Date-of-birth", searchable = Searchable.YES)
    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable = Searchable.NO)
    @WebField(displayName = "Date-of-birth", order = 500)
    @Audited
    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;

    @Size(max = 1)
    @Audited
    private String gender;

    @KeywordField(name="Email", searchable = Searchable.YES)
    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable = Searchable.NO)
    @Audited
    @Size(max = 200)
    @Column(name = "EMAIL_ADDRESS")
    @WebField(displayName = "Email", order = 600)
    @Email(message = "The email format must be <name>@<host>.<extension>")
    private String emailAddress;

    @Size(max = 1)
    @Column(name = "MARITAL_STATUS")
    @Audited
    private String maritalStatus;

    @Column(name = "TIME_AT_CURRENT_ADDRESS_MONTHS")
    @Audited
    private Integer timeAtCurrentAddressMonths;

    @IndexedEmbedded(name = "Current-address")
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "applicantFromCurrent")
    @Audited
    @WebRelation(name = "Current-address", order = 1000)
    private Address currentAddressCollection;

    @ManyToOne(fetch = FetchType.LAZY)
    private Application application;

    @IndexedEmbedded(name = "Experian")
    @OneToOne(fetch = FetchType.LAZY, mappedBy="applicant")
    @WebRelation(name = "Experian",
            order = 1100,
            rights = {
                @RelationAccess(atWorklist = "S4.UNDERWRITING",currentOwner = RelationRights.CAN_ADD_AND_DELETE_OWN_ITEMS ),
                @RelationAccess(atWorklist = "S0.MY",currentOwner = RelationRights.CAN_ONLY_READ ),
                @RelationAccess(atWorklist = "S0.PH",currentOwner = RelationRights.CAN_ONLY_READ ),
            })
    private Experian experian;


    //-----------Employer info---------------------------------------------//
    @Size(max = 30)
    @Column(name = "EMPLOYERNAME")
    private String employername;


    //-----------Financial info---------------------------------------------//
    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable=Searchable.YES)
    @GenericField(name="Annual-salary",searchable=Searchable.YES)
    @Column(name = "ANNUAL_SALARY",precision = 18, scale=2)
    @Money(currency = "MYR")
    @WebField(displayName = "Annual-salary", order = 700)
    private BigDecimal annualSalary;

    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable=Searchable.YES)
    @KeywordField(name="Mobile-number",searchable=Searchable.YES)
    @Size(max = 50)
    @Column(name = "MOBILE_NUMBER")
    @WebField(displayName = "Mobile-number", order = 800, format = @Format(
            blocks = {2, 4, 4},
            delimiters = {"-", "-"},
            prefix = "+60",
            showPrefixImmediately = true,
            type = FormatType.NUMERIC
    ))
    private String mobileNumber;

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Applicant)) {
            return false;
        }
        Applicant other = (Applicant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return forename + " " + surname;
    }

    /**
     * @return the currentAddressCollection
     */
    public Address getCurrentAddressCollection() {
        return currentAddressCollection;
    }

    /**
     * @param currentAddressCollection the currentAddressCollection to set
     */
    public void setCurrentAddressCollection(Address currentAddressCollection) {
        this.currentAddressCollection = currentAddressCollection;
    }

    /**
     * @return the forename
     */
    public String getForename() {
        return forename;
    }

    /**
     * @param forename the forename to set
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the ageOfApplicant
     */
    public Integer getAgeOfApplicant() {
        return ageOfApplicant;
    }

    /**
     * @param ageOfApplicant the ageOfApplicant to set
     */
    public void setAgeOfApplicant(Integer ageOfApplicant) {
        this.ageOfApplicant = ageOfApplicant;
    }

    /**
     * @return the dateOfBirth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth the dateOfBirth to set
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the maritalStatus
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * @param maritalStatus the maritalStatus to set
     */
    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    /**
     * @return the timeAtCurrentAddressMonths
     */
    public Integer getTimeAtCurrentAddressMonths() {
        return timeAtCurrentAddressMonths;
    }

    /**
     * @param timeAtCurrentAddressMonths the timeAtCurrentAddressMonths to set
     */
    public void setTimeAtCurrentAddressMonths(Integer timeAtCurrentAddressMonths) {
        this.timeAtCurrentAddressMonths = timeAtCurrentAddressMonths;
    }

    /**
     * @return the employername
     */
    public String getEmployername() {
        return employername;
    }

    /**
     * @param employername the employername to set
     */
    public void setEmployername(String employername) {
        this.employername = employername;
    }

    /**
     * @return the annualSalary
     */
    public BigDecimal getAnnualSalary() {
        return annualSalary;
    }

    /**
     * @param annualSalary the annualSalary to set
     */
    public void setAnnualSalary(BigDecimal annualSalary) {
        this.annualSalary = annualSalary;
    }

    /**
     * @return the mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * @param mobileNumber the mobileNumber to set
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    /**
     * @return the experian
     */
    public Experian getExperian() {
        return experian;
    }

    /**
     * @param experian the experian to set
     */
    public void setExperian(Experian experian) {
        this.experian = experian;
    }

}
