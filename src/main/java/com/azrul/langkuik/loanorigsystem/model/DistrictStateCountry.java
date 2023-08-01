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
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.annotations.Where;
import org.hibernate.search.engine.backend.types.Projectable;
import org.hibernate.search.engine.backend.types.Searchable;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
//import org.hibernate.search.annotations.Indexed;

/**
 *
 * @author azrul
 */
@Entity
@Indexed
@Where(clause = "status<>3")
@WebEntity(name = "District State Country", type = WebEntityType.REF)
public class DistrictStateCountry extends Element {

    @Column(name = "DISTRICT")
    @WebField(displayName = "District", order = 200)
    @GenericField(sortable = Sortable.YES, projectable = Projectable.YES, searchable = Searchable.YES)
    @GenericField(name = "District", searchable = Searchable.YES)
    private String district;

    @Column(name = "STATE")
    @GenericField(sortable = Sortable.YES, projectable = Projectable.YES, searchable = Searchable.YES)
    @GenericField(name = "State", searchable = Searchable.YES)
    @WebField(displayName = "State", order = 300)
    private String state;

    @Column(name = "COUNTRY")
    @GenericField(sortable = Sortable.YES, projectable = Projectable.YES, searchable = Searchable.YES)
    @GenericField(name = "Country", searchable = Searchable.YES)
    @WebField(displayName = "Country", order = 400)
    private String country;

    public DistrictStateCountry() {

    }

    public DistrictStateCountry(String district, String state, String country) {
        this.district = district;
        this.state = state;
        this.country = country;
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
        final DistrictStateCountry other = (DistrictStateCountry) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
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

}
