package com.bestbuy.crudtest;

import com.bestbuy.model.ServicesPojo;
import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCrudTest extends TestBase {

    //HashMap List<String, ?>> serviceList = new ArrayList<>();
    static String name = "Watford" + TestUtils.getRandomValue();
    static String type = "smallbox";
    static String address = "13513 Ridgedale Dr";
    static String address2 = "";
    static String city = "London";
    static String state = "Hertfordshire";
    static String zip = "WD25";
    static Double lat =  44.969658;
    static Double ing = -93.449539;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static String service = "{}";

    @Before
    public void setup(){
        RestAssured.basePath ="/stores";
    }
    @Test
    public void postStore(){
        StorePojo storePojo=new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(city);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(ing);
        storePojo.setHours(hours);
        ServicesPojo servicePojo = new ServicesPojo();

        servicePojo.setName("service1");
        storePojo.setServices(servicePojo);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .post();
        response.then().log().all().statusCode(201);

    }
}
