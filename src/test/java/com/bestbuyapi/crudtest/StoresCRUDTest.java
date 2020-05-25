package com.bestbuyapi.crudtest;

/*
Created by SP
*/

import com.bestbuyapi.steps.StoresSteps;
import com.bestbuyapi.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class StoresCRUDTest extends TestBase {

    static String name = "Automation Store";
    static String type = "Rest Assured Tools";
    static String address = "260 Rockland Avenue";
    static String address2 = "Uxbridge";
    static String city = "London";
    static String state = "West";
    static String zip = "525255";
    static double lat = 45.958785;
    static double lng = -90.445596;
    static String hours = "Mon: 9-6; Tue: 9-6; Wed: 9-6; Thurs: 9-6; Fri: 9-6; Sat: 9-6; Sun: 9-6";


    @Steps
    StoresSteps storesSteps;

    @Title("Creating a new Store")
    @Test
    public void test001() {
        storesSteps.createNewStore(name, type, address, address2, city, state, zip, lat, lng, hours);

    }

    @Title("Getting a created Store")
    @Test
    public void test002() {
        storesSteps.getStoreById().statusCode(200);

    }

    @Title("This test will update the store information and verify the updated information")
    @Test

    public void test003() {

        name = name + "_Updated";
        type = type + "_Updated";
        address = "260 Rockland Avenue";
        address2 = "Uxbridge";
        city = "London";
        state = "West";
        zip = "UB10 8UU";
        lat = 40.854785;
        lng = -80.847596;
        hours = "Mon: 8-6; Tue: 8-6; Wed: 8-6; Thurs: 8-6; Fri: 8-6; Sat: 8-6; Sun: 8-6";


        storesSteps.updateStore(name, type, address, address2, city, state, zip, lat, lng, hours)
                .statusCode(200)
                .body("name", equalTo(name));

    }

    @Title("This test will delete the store and verify the store is deleted ")
    @Test
    public void test004() {
        storesSteps.deleteStore().statusCode(200);
        storesSteps.getStoreById().statusCode(404);
    }
}
