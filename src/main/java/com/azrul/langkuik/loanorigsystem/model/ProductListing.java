/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azrul.langkuik.loanorigsystem.model;

import com.azrul.langkuik.custom.lookupchoice.Lookup;
import com.azrul.langkuik.framework.entity.Element;
import com.azrul.langkuik.framework.entity.WebEntity;
import com.azrul.langkuik.framework.entity.WebEntityType;
import com.azrul.langkuik.framework.field.WebField;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.Where;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

/**
 *
 * @author azrul
 */
@Indexed
@Entity
@Where(clause = "status<>3")
@WebEntity(name="Product",type=WebEntityType.REF)
public class ProductListing extends Element {

    @Column(name = "PRODUCT_NAME")
    @GenericField(name="Product",searchable=Searchable.YES)
    @GenericField(  sortable = Sortable.YES, projectable = Projectable.YES, searchable=Searchable.NO)
    @WebField(displayName = "Product", order = 200)
    @NotEmpty(message="Product cannot be empty")
    @Lookup(entity=ProductListing.class,field = "productName")
    private String productName;

    public ProductListing(){
        productName=null;
    }
   
    
    public ProductListing(String productName) {
       this.productName=productName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductListing other = (ProductListing) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String toString(){
        return productName;
    }
    
    

   

}
