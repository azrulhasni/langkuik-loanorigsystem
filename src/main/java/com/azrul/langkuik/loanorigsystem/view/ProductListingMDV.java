/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.azrul.langkuik.loanorigsystem.view;

import com.azrul.langkuik.loanorigsystem.model.ProductListing;
import com.azrul.langkuik.views.common.MainView;
import com.azrul.langkuik.views.table.TableView;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 *
 * @author azrul
 */
@Route(value = "product", layout = MainView.class)
@PageTitle("Product")
//@CssImport("styles/views/masterdetail/master-detail-view.css")
public class ProductListingMDV extends TableView<ProductListing>{
    public ProductListingMDV(){
        super(ProductListing.class, TableView.Mode.MAIN);
    }
}