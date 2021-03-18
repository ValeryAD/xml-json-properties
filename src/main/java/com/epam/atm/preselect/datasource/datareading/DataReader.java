package com.epam.atm.preselect.datasource.datareading;

import com.epam.atm.preselect.datasource.model.AirCompany;

public interface DataReader {

    AirCompany readDataFromSource();
}
