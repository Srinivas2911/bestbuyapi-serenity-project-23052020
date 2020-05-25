package com.bestbuyapi.testsuite;

/*
Created by SP
*/

import com.bestbuyapi.constant.Endpoints;
import com.bestbuyapi.steps.StoresSteps;
import com.bestbuyapi.testbase.TestBase;
import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StoresTagsTest extends TestBase {

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
    @WithTags({
            @WithTag("bestbuyfeature:POSITIVE"),
            @WithTag("bestbuyfeature:REGRESSION")
    })
    @Title("This test will Creating a new Store")
    @Test
    public void creatingANewStore() {
        storesSteps.createNewStore(name, type, address, address2, city, state, zip, lat, lng, hours);

    }
    @WithTags({
            @WithTag("bestbuyfeature:SMOKE"),
            @WithTag("bestbuyfeature:REGRESSION")
    })
    @Title("Getting the information of all the stores")
    @Test
    public void gettingAllStores() {

        SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(Endpoints.GET_ALL_STORES)
                .then()
                .statusCode(200)
                .log().all();
    }

    @WithTags({
            @WithTag("bestbuyfeature:SANITY"),
            @WithTag("bestbuyfeature:REGRESSION")
    })
    @Title("Getting the information of the Store with ID")
    @Test
    public void gettingStorebyID() {
        storesSteps.getStoreById().statusCode(200);

    }
    @WithTags({
            @WithTag("bestbuyfeature:POSITIVE"),
            @WithTag("bestbuyfeature:REGRESSION")
    })
    @Title("This test will update the store information and verify the updated information")
    @Test

    public void updatingAStoreByID() {

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

    @WithTags({
            @WithTag("bestbuyfeature:NEGATIVE"),
            @WithTag("bestbuyfeature:REGRESSION")
    })
    @Title("Deleting the Store information by Id ")
    @Test
    public void deletingAProductbyID() {
        SerenityRest.rest()
                .given()
                .log().all()
                .when()
                .delete(Endpoints.DELETE_STORE_BY_ID+18)
                .then().statusCode(404).log().all();
    }




}
