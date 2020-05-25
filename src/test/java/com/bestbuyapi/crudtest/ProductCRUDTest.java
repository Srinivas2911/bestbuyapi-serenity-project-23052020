package com.bestbuyapi.crudtest;

/*
Created by SP
*/

import com.bestbuyapi.steps.ProductSteps;
import com.bestbuyapi.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static org.hamcrest.Matchers.equalTo;


@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ProductCRUDTest extends TestBase {

    static String name = "JVM New Mobile";
    static String type = "iOS5656";
    static String upc = "36445464656";
    static double price = 99.99;
    static String description = "This is the latest high-tech design in mobile phones";
    static String model = "JVM13243UK";

    @Steps
    ProductSteps productSteps;

    @Title("Creating a new Product in the list")
    @Test
    public void test001() {

        productSteps.createNewProduct(name, type, upc, price, description, model);

    }

    @Title("Verify product was added to the list")
    @Test
    public void test002() {
        productSteps.getProductInfoByID().statusCode(200);

    }

    @Title("Updating the product information and verifying ")
    @Test
    public void test003() {

        name = name + "_updated";
        price = 89.99;
        upc = upc + "_updated";
        productSteps.updatingTheProductByID(name, type, upc, price, description, model).statusCode(200)
                .body("name", equalTo(name)).log().all();
    }

    @Title("Deleting the product information and verify the product is deleted ")
    @Test
    public void test004() {
        productSteps.deleteProductbyID().statusCode(200);
        productSteps.getProductInfoByID().statusCode(404);
    }
}