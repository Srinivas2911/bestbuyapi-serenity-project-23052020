package com.bestbuyapi.testsuite;

/*
Created by SP
*/

import com.bestbuyapi.constant.Endpoints;
import com.bestbuyapi.steps.ProductSteps;
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

import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductTagsTest extends TestBase {

    static String name = "JVM New Mobile";
    static String type = "iOS5656";
    static String upc = "36445464656";
    static double price = 99.99;
    static String description = "This is the latest high-tech design in mobile phones";
    static String model = "JVM13243UK";

    @Steps
    ProductSteps productSteps;

    @WithTags({
            @WithTag("bestbuyfeature:POSITIVE"),
            @WithTag("bestbuyfeature:REGRESSION")
    })
    @Title("This test will create a new Product in the list")
    @Test
    public void creatingANewProduct() {
        productSteps.createNewProduct(name, type, upc, price, description, model);
    }

    @WithTags({
            @WithTag("bestbuyfeature:SMOKE"),
            @WithTag("bestbuyfeature:REGRESSION")
    })
    @Title("Getting the information of all the products")
    @Test
    public void gettingAllProducts() {

        SerenityRest.rest()
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(Endpoints.GET_ALL_PRODUCTS)
                .then()
                .statusCode(200)
                .log().all();

    }
    @WithTags({
            @WithTag("bestbuyfeature:SANITY"),
            @WithTag("bestbuyfeature:REGRESSION")
    })
    @Title("Getting the information of the product with ID")
    @Test
    public void gettingProductbyID() {

        productSteps.getProductInfoByID().statusCode(200);

    }

    @WithTags({
            @WithTag("bestbuyfeature:POSITIVE"),
            @WithTag("bestbuyfeature:REGRESSION")
    })
    @Title("Updating the product information and verifying ")
    @Test
    public void upDateAProductbyID() {

        name = name + "_updated";
        price = 89.99;
        upc = upc + "_updated";
        productSteps.updatingTheProductByID(name, type, upc, price, description, model).statusCode(200)
                .body("name", equalTo(name)).log().all();
    }
    @WithTags({
            @WithTag("bestbuyfeature:NEGATIVE"),
            @WithTag("bestbuyfeature:REGRESSION")
    })
    @Title("Deleting the Product information by UserId ")
    @Test
    public void deletingAProductbyID() {
        SerenityRest.rest()
                .given()
                .log().all()
                .when()
                .delete(Endpoints.DELETE_PRODUCT_BY_ID + 9999726)
                .then().statusCode(404).log().all();
    }

}