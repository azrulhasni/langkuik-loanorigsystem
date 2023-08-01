/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azrul.langkuik.loanorigsystem.view;

import com.azrul.langkuik.loanorigsystem.model.DistrictStateCountry;
import com.azrul.langkuik.views.common.MainView;
import com.azrul.langkuik.views.table.TableView;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "districtStateCountry", layout = MainView.class)
@PageTitle("District-State-Country")
public class DistrictStateCountryMDV  extends TableView<DistrictStateCountry>{
     public DistrictStateCountryMDV(){
        super(DistrictStateCountry.class,  TableView.Mode.MAIN);
    }
}