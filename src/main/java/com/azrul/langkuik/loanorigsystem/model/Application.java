/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azrul.langkuik.loanorigsystem.model;

import com.azrul.langkuik.framework.entity.WebEntity;
import com.azrul.langkuik.framework.field.WebField;
import com.azrul.langkuik.framework.relationship.WebRelation;
import com.azrul.langkuik.custom.attachment.AttachmentsRenderer;
import com.azrul.langkuik.custom.attachment.AttachmentsContainer;
import com.azrul.langkuik.custom.money.Money;
import com.azrul.langkuik.custom.onetoone.OneToOneRenderer;
import com.azrul.langkuik.custom.relationToForm.RelationToFormRenderer;
import com.azrul.langkuik.framework.entity.WebEntityType;
import com.azrul.langkuik.framework.rights.FieldAccess;
import com.azrul.langkuik.framework.rights.FieldRights;
import com.azrul.langkuik.framework.rights.RelationAccess;
import com.azrul.langkuik.framework.rights.RelationRights;
import org.hibernate.search.engine.backend.types.Projectable;
import com.azrul.langkuik.framework.entity.WorkElement;
import com.azrul.langkuik.framework.field.FieldVisibility;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.envers.Audited;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.IndexedEmbedded;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;
import com.azrul.langkuik.custom.subform.SubForm;
import com.azrul.langkuik.custom.subform.SubFormType;
import java.time.LocalDateTime;
import org.hibernate.annotations.Where;
//@Indexed
//@Entity
//@WebEntity(name = "Application",type = WebEntityType.ROOT)
//public class Application extends WorkElement implements Serializable {
//    //other fields
//
//    @IndexedEmbedded(name = "Applicants")
//    @OneToMany(fetch = FetchType.LAZY, mappedBy="application")
//    @WebRelation(name = "Applicants", order = 800, minCount = 1, maxCount = 4)
//    private Set<Applicant> applicants;
//
//    //getters and setters
//}

/**
 *
 * @author azrulm
 */
@Indexed
@Entity
@WebEntity(name = "Application",
        type = WebEntityType.ROOT,
        fieldVisibility={
            @FieldVisibility(fieldName="priority", visibleInForm = "true", visibleInTable = "true"),
            @FieldVisibility(fieldName="creator", visibleInForm = "true", visibleInTable = "true"),
            @FieldVisibility(fieldName="workflowInfo", visibleInTable = "true")
    }
)
public class Application extends WorkElement implements Serializable {

    public Application() {
    }

    private static final long serialVersionUID = 1L;

    @Column(name = "ACCEPTED_DATE")
    @Audited
    @GenericField(name = "Accepted-date", searchable = Searchable.YES)
    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable = Searchable.NO)
    @WebField(displayName = "Accepted-date", order = 200, visibleInForm = "true", visibleInTable = "false")
    private LocalDate acceptedDate;


    @Size(max = 16)
    @Column(name = "ACCOUNT_NUMBER")
    @Audited
    @KeywordField(name = "Account-number", searchable = Searchable.YES)
    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable = Searchable.NO)
    @WebField(displayName = "Account-number", order = 300, visibleInForm = "true",visibleInTable = "false")
    @NotEmpty(message = "Account number cannot be empty")
    @Size(min = 8, message = "Account number must be more than 8 characters")
    private String accountNumber;

    @Column(name = "APPLICATION_DATE")
    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable = Searchable.YES)
    @GenericField(name = "Application-date", searchable = Searchable.YES)
    @Audited
    @WebField(displayName = "Application-date", order = 400, visibleInTable = "true")
    private LocalDate applicationDate;

    @Size(max = 14)
    @Column(name = "APPLICATION_NUMBER")
    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable = Searchable.NO)
    @KeywordField(name = "Application-number", searchable = Searchable.YES)
    @Audited
    @WebField(displayName = "Application-number", order = 500, visibleInTable = "false")
    @NotEmpty(message = "Application number cannot be empty")
    private String applicationNumber;

    private LocalDateTime startTime;
    
    private LocalDateTime stopTime;


    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.EAGER)
    @WebRelation(name = "Product", order = 600, minCount = 1, customComponentInTable = OneToOneRenderer.class)
    private ProductListing product;


    @JoinColumn(name = "RELATION_MANAGER_ID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @WebRelation(name = "Relationship-manager", order = 700)
    private RelationshipManager relationManager;

    @Audited
    @IndexedEmbedded(name = "Applicants")
    @OneToMany(fetch = FetchType.LAZY, mappedBy="application")
    @WebRelation(name = "Applicants", order = 800, minCount = 1, maxCount = 4,
            asSubForm = @SubForm(
                    active = "true",
                    type =SubFormType.IN_PARENT
            ))
    private Set<Applicant> applicants;

    @Audited
    @IndexedEmbedded(name = "Collateral")
    @OneToMany(fetch = FetchType.LAZY, mappedBy="application")
    @WebRelation(name = "Collateral", order = 900, minCount = 1,  rights = {
        @RelationAccess(atWorklist = "S3.VALUATION",currentOwner = RelationRights.CAN_ADD_AND_DELETE_OWN_ITEMS),
        @RelationAccess(atWorklist = "S0.MY",currentOwner = RelationRights.CAN_ONLY_READ),
        @RelationAccess(atWorklist = "S0.PH",currentOwner = RelationRights.CAN_ONLY_READ)
    })
    private Set<Collateral> collateral;

    @Column(name = "ADVANCE",precision = 18, scale=2)
    @GenericField(name = "Advance", searchable = Searchable.YES)
    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable = Searchable.NO)
    @Audited
    @Money(currency = "MYR")
    @WebField(displayName = "Advance", order = 1000, visibleInForm = "true", visibleInTable = "false")
    @Min(value = 1, message = "Advance cannot be zero")
    @NotNull(message = "Advance cannot be zero")
    private BigDecimal advance;

    @Column(name = "REQUESTED_LOAN_AMOUNT",precision = 18, scale=2)
    @GenericField(name = "Requested-loan-ammount", searchable = Searchable.YES)
    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable = Searchable.NO)
    @Audited
    @Money(currency = "MYR")
    @WebField(displayName = "Requested-loan-ammount", order = 1100, visibleInForm = "true", visibleInTable = "false", rights=@FieldAccess(atWorklist = "S4.UNDERWRITING",currentOwner=FieldRights.CAN_EDIT))
    @Min(value = 1, message = "Loan ammount cannot be zero")
    @NotNull(message = "Loan ammount cannot be zero")
    private BigDecimal requestedLoanAmount;


    @Column(name = "REQUESTED_LOAN_TERM")
    @GenericField(name = "Requested-loan-term", searchable = Searchable.YES)
    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable = Searchable.NO)
    @Audited
    @WebField(
            displayName = "Requested-loan-term",
            order = 1200,
            visibleInForm = "true",
            visibleInTable = "false",
            rights=@FieldAccess(atWorklist = "S4.UNDERWRITING",currentOwner=FieldRights.CAN_EDIT))
    @Min(value = 1, message = "Loan term cannot be zero")
    @NotNull(message = "Loan ammount cannot be zero")
    private Integer requestedLoanTerm;



    @GenericField(name = "Approved", searchable = Searchable.YES)
    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable = Searchable.NO)
    @WebField(displayName = "Approved", order = 1400, visibleInForm = "false", visibleInTable = "false")
    @Column(name = "APPROVED")
    @Audited
    private Boolean approved;


    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "attachments_id", referencedColumnName = "id")
    @WebRelation(name = "Attachments",
            order = 1500,
            maxCount = 5,
            asSubForm=@SubForm(
                    active = "true",
                    subFormRenderer = AttachmentsRenderer.class,
                    type = SubFormType.IN_PARENT
                    ))
    private AttachmentsContainer attachments;



    @IndexedEmbedded(name = "Checklist")
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "application")
    @Audited
    @WebRelation(name = "Checklist", order = 1700
            ,asSubForm = @SubForm(
                    subFormRenderer = RelationToFormRenderer.class,
                    active = "true",
                    type =SubFormType.AS_TAB
            )
    )
    private Checklist checklist;

 ////---Multiple workflow support---
//     @IndexedEmbedded
//    @OneToOne(fetch = FetchType.LAZY,mappedBy = "application")
//    @Audited
//    @WebRelation(name = "CTOS-Data", order = 1600
//            ,visibleInForm = "'S0.PH'.equals(currentObject.getWorkflowInfo().getStartEventId())"
//            ,asSubForm = @SubForm(
//                    subFormRenderer = RelationToFormRenderer.class,
//                    active = "true",
//                    type = SubFormType.IN_PARENT
//            )
//    )
//    private CTOS ctos;
//
//    @IndexedEmbedded
//    @OneToOne(fetch = FetchType.LAZY,mappedBy = "application")
//    @Audited
//    @WebRelation(name = "CCRIS-Data", order = 1700
//            ,visibleInForm = "'S0.MY'.equals(currentObject.getWorkflowInfo().getStartEventId())"
//            ,asSubForm = @SubForm(
//                    subFormRenderer = RelationToFormRenderer.class,
//                    active = "true",
//                    type =SubFormType.IN_PARENT
//            )
//    )
//    private CCRIS ccris;

    @Transient
    private Map<String,Object> properties=new HashMap<>();

    public Application(Long id) {

        this.id = id;
        this.approved = false;
    }

    public LocalDate getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(LocalDate acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getApplicationNumber() {
        return applicationNumber;
    }

    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    public ProductListing getProduct() {
        if (product == null) {
            product = new ProductListing();
        }
        return product;
    }

    public void setProduct(ProductListing productId) {
        this.product = product;
    }

    @XmlTransient
    public Set<Applicant> getApplicants() {
        if (applicants == null) {
            applicants = new HashSet<Applicant>();
        }
        return applicants;
    }

    public void setApplicants(Set<Applicant> applicants) {
        this.applicants = applicants;
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
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + id + " ]";
    }

    @PrePersist
    void prePersist() {
        updateDates();
    }

    void updateDates() {
        if (applicationDate == null) {
            GregorianCalendar cal = (GregorianCalendar) GregorianCalendar.getInstance();
            cal.setTime(new Date());
            cal.set(Calendar.HOUR, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.HOUR_OF_DAY, 0);

            applicationDate = cal.getTime().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }
    }

    @PostPersist
    void assignApplicationNumber() {
        DecimalFormat df = new DecimalFormat("############");
        setApplicationNumber(df.format(id));
    }

    /**
     * @return the collaterals
     */
    public Set<Collateral> getCollateral() {
        return collateral;
    }

    /**
     * @param collaterals the collaterals to set
     */
    public void setCollateral(Set<Collateral> collaterals) {
        this.collateral = collateral;
    }

    public BigDecimal getAdvance() {
        return advance;
    }

    public void setAdvance(BigDecimal advance) {
        this.advance = advance;
    }

    public BigDecimal getRequestedLoanAmount() {
        return requestedLoanAmount;
    }

    public void setRequestedLoanAmount(BigDecimal requestedLoanAmount) {
        this.requestedLoanAmount = requestedLoanAmount;
    }

    public Integer getRequestedLoanTerm() {
        return requestedLoanTerm;
    }

    public void setRequestedLoanTerm(Integer requestedLoanTerm) {
        this.requestedLoanTerm = requestedLoanTerm;
    }

    /**
     * @return the attachmentCollection
     */
    public AttachmentsContainer getAttachments() {
        return attachments;
    }

    /**
     * @param attachmentCollection the attachmentCollection to set
     */
    public void setAttachments(AttachmentsContainer attachments) {
        this.attachments = attachments;
    }

    /**
     * @return the relationManager
     */
    public RelationshipManager getRelationManager() {
        return relationManager;
    }

    /**
     * @param relationManager the relationManager to set
     */
    public void setRelationManager(RelationshipManager relationManager) {
        this.relationManager = relationManager;
    }

    /**
     * @return the approved
     */
    public Boolean isApproved() {
        return getApproved();
    }

    /**
     * @param approved the approved to set
     */
    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    /**
     * @return the approved
     */
    public Boolean getApproved() {
        return approved;
    }



    /**
     * @return the properties
     */
    public Map<String,Object> getProperties() {
        if (properties==null) properties=new HashMap<>();
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(Map<String,Object> properties) {
        this.properties = properties;
    }



    /**
     * @return the checklist
     */
    public Checklist getChecklist() {
        return checklist;
    }

    /**
     * @param checklist the checklist to set
     */
    public void setChecklist(Checklist checklist) {
        this.checklist = checklist;
    }

    /**
     * @return the startTime
     */
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the stopTime
     */
    public LocalDateTime getStopTime() {
        return stopTime;
    }

    /**
     * @param stopTime the stopTime to set
     */
    public void setStopTime(LocalDateTime stopTime) {
        this.stopTime = stopTime;
    }


}
