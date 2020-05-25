package com.bestbuyapi.steps;

/*
Created by SP
*/

import com.bestbuyapi.constant.Endpoints;
import com.bestbuyapi.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.rest.SerenityRest.given;

public class ProductSteps {

    static long userid;

    @Step("Creating a new product With name:{0}, type:{1}, upc:{2}, price:{3} description:{4},model:{5} ")
    public void createNewProduct(String name, String type, String upc, double price, String description,
                                 String model) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setUpc(upc);
        productPojo.setPrice(price);
        productPojo.setDescription(description);
        productPojo.setModel(model);

        Response response =
                given()
                        .headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().
                        body(productPojo)
                        .post(Endpoints.POST_A_PRODUCT)
                        .then()
                        .statusCode(201)
                        .log().all()
                        .extract().response();
        userid = response.body().jsonPath().getLong("id");
        System.out.println("My created userid is " + userid);
    }

    @Step("Getting the information of the product Created with UserId:{0}")
    public ValidatableResponse getProductInfoByID() {

        return SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(Endpoints.GET_SINGLE_PRODUCT_BY_ID + userid)
                .then()
                .log().all();
    }

    @Step("Updating the product With name:{1}, type:{2}, upc:{2}, price:{4} description:{5},model:{6} ")
    public ValidatableResponse updatingTheProductByID(String name, String type, String upc, double price, String description,
                                                      String model) {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setUpc(upc);
        productPojo.setPrice(price);
        productPojo.setDescription(description);
        productPojo.setModel(model);

        return SerenityRest.rest()
                .given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .patch(Endpoints.UPDATE_PRODUCT_BY_ID + userid)
                .then().log().all();
    }

    @Step("Deleting the Product information by UserId:{0} ")
    public ValidatableResponse deleteProductbyID() {
        return SerenityRest.rest()
                .given()
                .log().all()
                .when()
                .delete(Endpoints.DELETE_PRODUCT_BY_ID + userid)
                .then();
    }
}