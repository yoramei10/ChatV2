package com.sherut.services.domainServices.implementations;


import com.sherut.services.domainServices.interfaces.IValidateAdminService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ValidateAdminService implements IValidateAdminService {

    @Value("${configuration.admin.user}")
    String ADMIN_ID;

    @Override
    public boolean validateAdmin(String adminId) {
        return (ADMIN_ID.equalsIgnoreCase(adminId));
    }
}
