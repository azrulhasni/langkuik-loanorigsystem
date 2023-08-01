/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azrul.langkuik.loanorigsystem.model;

import com.azrul.langkuik.framework.entity.WebEntity;
import com.azrul.langkuik.framework.entity.WebEntityType;
import com.azrul.langkuik.framework.field.WebField;
import com.azrul.langkuik.framework.entity.Element;
import com.azrul.langkuik.framework.entity.WorkElement;
import com.azrul.langkuik.framework.field.FieldVisibility;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
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
@WebEntity(name="Relationship Manager",type=WebEntityType.REF)
public class RelationshipManager extends Element {
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
//    @Id
//    @Field(name="Id")
//    @NumericField(forField="Id")
//    @SortableField(forField="Id")
//    @Basic(optional = false)
//    @NotNull
//    @Audited
//    @Column(name = "APPLICANT_ID")
//    @WebField(displayName="Id")
    /*@GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )*/
    
//    @Id
//    @NumericField(forField="Id4sort")   
//    @SortableField(forField="Id4sort")
//    @Fields( {
//        @Field(name="Id",analyze = Analyze.YES, bridge = @FieldBridge(impl = LongBridge.class)),
//        @Field(name = "Id4sort", analyze = Analyze.NO)
//        } ) 
//    @Basic(optional = false)
//    @Column(name = "APPLICANT_ID")
//    @WebField(displayName="Id", order=1)
//    @Audited
//    private Long applicantId;
    
    
    
    //-----------Personal info---------------------------------------------//
    @GenericField(name="Forename",searchable=Searchable.YES)
    @GenericField(  sortable = Sortable.YES, projectable = Projectable.YES, searchable=Searchable.NO)
    @Audited
    @WebField(displayName="Forename",order=200)
    @NotEmpty(message="Forename cannot be empty")
    @Size(min = 5, max = 50, message="Forename must be at least 5 characters long")
    private String forename;  
      
    @GenericField(name="Surname",searchable=Searchable.YES)
    @GenericField(  sortable = Sortable.YES, projectable = Projectable.YES, searchable=Searchable.NO)
    @Audited
    @WebField(displayName="Surname",order=300)
    @NotEmpty(message="Surname cannot be empty")
    @Size(min = 3, max = 50, message="Surname must be at least 3 characters long")
    private String surname;
    
    
    @GenericField(name="Staff-id",searchable=Searchable.YES)
    @GenericField(  sortable = Sortable.YES, projectable = Projectable.YES, searchable=Searchable.NO)
    @Audited
    @WebField(displayName="Staff-id",order=400)
    @NotEmpty(message="Staff id cannot be empty")
    @Size(min = 3, max = 50, message="Staff id must be at least 3 characters long")
    private String staffId;

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
     * @return the staffId
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * @param staffId the staffId to set
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}
