/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
@WebEntity(name = "CTOS",
        type = WebEntityType.REF
)
public class Experian extends Element{

    
    @Column(name = "BUREAU_SCORE")
    @GenericField(name = "Bureau-score", searchable = Searchable.YES)
    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable = Searchable.NO)
    @Audited
    @WebField(
            displayName = "Bureau-score", 
            order = 1300, 
            visibleInForm = "true", 
            visibleInTable = "true")
    @Min(value = 1, message = "Bureau score cannot be zero")
    @NotNull(message = "Bureau scoe cannot be zero")
    private Integer bureauScore;
    
    @Column(name = "BUREAU_RESULT")
    @GenericField(name = "Bureau-result", searchable = Searchable.YES)
    @GenericField(projectable = Projectable.YES, sortable = Sortable.YES, searchable = Searchable.NO)
    @Audited
    @WebField(
            displayName = "Bureau-result", 
            order = 1300, 
            visibleInForm = "true", 
            visibleInTable = "true")
    private String bureauResult;
    
     @WebField(
            displayName = "Parent id", 
            order = 1400, 
            visibleInForm = "false", 
            visibleInTable = "false",
            referenceOwnerField = true)
    private Long parentId;
     
    @OneToOne(fetch = FetchType.LAZY)
    private Applicant applicant;
     /**
     * @return the bureauScore
     */
    public Integer getBureauScore() {
        return bureauScore;
    }

    /**
     * @param bureauScore the bureauScore to set
     */
    public void setBureauScore(Integer bureauScore) {
        this.bureauScore = bureauScore;
    }

    /**
     * @return the bureauResult
     */
    public String getBureauResult() {
        return bureauResult;
    }

    /**
     * @param bureauResult the bureauResult to set
     */
    public void setBureauResult(String bureauResult) {
        this.bureauResult = bureauResult;
    }

    

    /**
     * @return the parentId
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * @param parentId the parentId to set
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
  

