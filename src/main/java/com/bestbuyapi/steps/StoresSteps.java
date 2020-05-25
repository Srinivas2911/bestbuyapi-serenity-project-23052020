package com.bestbuyapi.steps;

/*
Created by SP
*/

import com.bestbuyapi.constant.Endpoints;
import com.bestbuyapi.model.StoresPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.given;

public class StoresSteps{

    static long storeid;

    @Step("Creating a new store with name:{0}, type:{1}. address:{2}, address2:{3}, city:{4}, state:{4}, zip:{5}, lat:{6}, lng:{7}, hours:{8}")

    public void createNewStore(String name, String type, String address, String address2,
                                              String city, String state, String zip, double lat, double lng,
                                              String hours) {

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);

        Response response =
                given()
                        .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                        .when()
                        .body(storesPojo)
                        .post(Endpoints.POST_A_STORE)
                        .then()
                        .statusCode(201)
                        .log().all()
                        .extract().response();
        storeid = response.body().jsonPath().getLong("id");
//       System.out.println("My created userid is " + storeid);

    }

    @Step("Getting the information of the store created by id:{0}")
    public ValidatableResponse getStoreById() {
        return SerenityRest.rest()
                .given()
                .when()
                .get(Endpoints.GET_SINGLE_STORE_BY_ID + storeid)
                .then().log().all();
    }

    @Step("Updating Store information with  name:{0}, type:{1}. address:{2}, address2:{3}, city:{4}, state:{5}, zip:{6}, lat:{7}, lng:{8}, hours:{9}")

    public ValidatableResponse updateStore(String name, String type, String address, String address2,
                                           String city, String state, String zip, double lat, double lng,
                                           String hours) {

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);

        return SerenityRest.rest().given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .body(storesPojo)
                .patch(Endpoints.UPDATE_STORE_BY_ID + storeid)
                .then();


    }

    @Step("Deleting the store with store Id : {0} ")

    public ValidatableResponse deleteStore() {
        return SerenityRest.rest()
                .given()
                .when()
                .delete(Endpoints.DELETE_STORE_BY_ID + storeid)
                .then();


    }

}
