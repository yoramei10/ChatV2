package com.sherut.services.domainServices.implementations;


import com.sherut.config.ConfigurationVariablesApp;
import com.sherut.services.domainServices.interfaces.IValidateAdminService;

public class ValidateAdminService implements IValidateAdminService {

    String ADMIN_ID = ConfigurationVariablesApp.ADMIN_USER_ID;

    @Override
    public boolean validateAdmin(String adminId) {
        return (ADMIN_ID.equalsIgnoreCase(adminId));
    }
}
