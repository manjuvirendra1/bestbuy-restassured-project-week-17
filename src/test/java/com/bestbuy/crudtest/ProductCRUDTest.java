package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductCRUDTest extends TestBase {
    static String name = "Amazone5" + TestUtils.getRandomValue();
    static String type = "Battery";
    static Double price = 5.99;
    static Double shipping = 1.99;
    static String description = "Amazone Basic Home Battery1" + TestUtils.getRandomValue();
    static String model = "Amazon Basic 1.0" + TestUtils.getRandomValue();
    static String manufacturer = "Amazon China";
    static String upc = "Text";
    static String url = "http://www.amazon.co.uk/battery";
    static String image = "http://www.amazon.co.uk/battery/imag";
    static int id;


    @Before
    public void setup(){
        RestAssured.basePath ="/products";
    }

    @Test
    public void postProduct() {

        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                        .post();

        response.then().log().all().statusCode(201);
    }

    @Test
    public void getProductData() {
        given()
                .when()
                .get()
                .then().statusCode(200)
                .log().all();
    }
    @Test
    public void patchProduct() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setUpc(upc);
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .patch();
        response.then().log().all().statusCode(200);
    }

    @Test
    public void deleteProduct(){
        given()
                .pathParams("id", id)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(404);


    }
}
