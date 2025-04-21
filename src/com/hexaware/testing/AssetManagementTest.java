package com.hexaware.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hexaware.dao.AssetManagementServiceImpl;
import com.hexaware.entity.Asset;
import com.hexaware.myExceptions.AssetNotFoundException;

public class AssetManagementTest {

    AssetManagementServiceImpl service;

    @BeforeEach
    public void setup() {
        service = new AssetManagementServiceImpl(); 
    }

    @Test
    public void testAssetCreation() {
        Asset asset = new Asset("Printer", "Electronics", "SN12345", "2024-04-21", "Main Office", "Available", 1);
        boolean result = service.addAsset(asset);
        assertTrue("Asset should be created successfully",result);
    }

    @Test
    public void testAssetAddedToMaintenanceSuccessfully() {
        int assetId = 1;
        String maintenanceDate = "2024-04-21";
        String description = "Routine check";
        double cost = 100.0;

        boolean result = service.performMaintenance(assetId, maintenanceDate, description, cost);
        assertTrue("Asset should be added to maintenance successfully",result);
    }

    @Test
    public void testReserveAssetSuccessfully() {
        int assetId = 1; 
        int employeeId = 1; 
        String reservationDate = "2024-04-21";
        String startDate = "2024-04-22";
        String endDate = "2024-04-23";

        boolean result = service.reserveAsset(assetId, employeeId, reservationDate, startDate, endDate);
        assertTrue("Asset should be reserved successfully",result);
    }

    @Test
    public void testAssetNotFoundException() {
        int fakeAssetId = 9999; 
        int fakeEmployeeId = 9999;
        String reservationDate = "2024-04-21";
        String startDate = "2024-04-22";
        String endDate = "2024-04-23";

        boolean result = service.reserveAsset(fakeAssetId, fakeEmployeeId, reservationDate, startDate, endDate);
        assertFalse(result, "Reservation should fail if asset or employee not found");
    }
}
