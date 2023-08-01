/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azrul.langkuik.loanorigsystem.view;

import com.azrul.langkuik.loanorigsystem.model.Application;
import com.azrul.langkuik.views.common.MainView;
import com.azrul.langkuik.views.table.TableView;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

/**
 *
 * @author azrul
 */
@Route(value = "application", layout = MainView.class)
@RouteAlias(value = "main", layout = MainView.class)
@RouteAlias(value = "", layout = MainView.class)
@PageTitle("Application")
public class ApplicationMDV extends TableView<Application> {

    public ApplicationMDV(){
        super(Application.class,  TableView.Mode.MAIN);
     
    }
}
