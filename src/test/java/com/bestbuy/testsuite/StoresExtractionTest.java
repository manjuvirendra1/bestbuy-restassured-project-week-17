package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);

    }

    //1. Extract the limit
    @Test
    public void extractLimit() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    //2. Extract the total
    @Test
    public void extractTotal() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th store
    @Test
    public void extractNameOf5thStore() {
        String storeName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of 5th Store name is : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the names of all the store
    @Test
    public void extractNameOfAllStores() {
        List<String> allStoreNames = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all Store name is : " + allStoreNames);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void extractNameOfAllIds() {
        List<String> allStoreId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of all Store id is : " + allStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void extractSize() {
        List<Integer> allStoreId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of all Store id is : " + allStoreId);
        System.out.println("The size of all Store id is : " + allStoreId.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void extractValue() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The values of the store St Cloud: " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void extractAddressOfStore() {
        String storeAddress = response.extract().path("data[8].address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of address of Store name Rochester is : " + storeAddress);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the services of 8th store
    @Test
    public void extractAllServicesOf8thStore() {
        List<HashMap<String, ?>> services = response.extract().path("data[7].services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of services of 8th store are : " + services);
        System.out.println("------------------End of Test---------------------------");
        //data[7].services

    }

    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void extractAllServicesOfWindowsStore() {
        HashMap<String, ?> windowsStore = response.extract().path("data[7].services[5].storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of  services of Windows Store : " + windowsStore);
        System.out.println("------------------End of Test---------------------------");
        //data[7].services[5].storeservices
    }

    //11. Get all the storeId of all the store
    @Test
    public void extractAllStoreId() {
        List<Integer> listOfStoreIds = response.extract().path("data.services.storeservices.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all Store Ids are : " + listOfStoreIds);
        System.out.println("------------------End of Test---------------------------");
        //data[*].services[*].storeservices.storeId
    }

    //12. Get id of all the store
    @Test
    public void extractAllIds() {
        List<Integer> listOfIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of all Ids of the store are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");
        //data[*].storeId
    }

    //13. Find the store names Where state = ND
    @Test
    public void extractState() {
        List<String> storeNames = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of store name where state is : " + storeNames);
        System.out.println("------------------End of Test---------------------------");
        //data[7].state
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void extractTotalNumberOfServices() {
        List<String> numberOfServices = response.extract().path("data[8].services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total number of services of Rochester store: " + numberOfServices.size());
        System.out.println("------------------End of Test---------------------------");
        //data[8].services[*]
    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void extractAllServicesForWindowsStore() {
        List<String> allServices = response.extract().path("data.services[6].createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of createdAt of all services: " + allServices);
        System.out.println("------------------End of Test---------------------------");
    }
    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void extractAllServicesForFargoStore() {
        List<String> allServices = response.extract().path("data.services[7]");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of name of all services of store Fargo: " + allServices);
        System.out.println("------------------End of Test---------------------------");
    }
    //17. Find the zip of all the store
    @Test
    public void extractZipOfAllStore() {
        List<String> zipOfAllStore = response.extract().path("data.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Zip of all store : " + zipOfAllStore);
        System.out.println("------------------End of Test---------------------------");
    }
    //18. Find the zip of store name = Roseville
    @Test
    public void extractZipOfRosevilleStore() {
        String zipOfRosevilleStore = response.extract().path("data[2].zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Zip Roseville store : " + zipOfRosevilleStore);
        System.out.println("------------------End of Test---------------------------");
    }
    //19. Find the store services details of the service name = Magnolia Home Theater
    @Test
    public void extractDetailsOfMagnoliaHomeTheater() {
        List<String> allServicesDetails = response.extract().path("data.services[5].name");
        List<String> allServices1Details = response.extract().path("data.services[7].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total service of Magnolia Home Theater  : " + allServicesDetails);
        System.out.println("The value of total service of Magnolia Home Theater  : " + allServices1Details);
        System.out.println("------------------End of Test---------------------------");
        //data[*].services[5].name
    }
        //20. Find the lat of all the stores
    @Test
    public void extract() {
        List<Double> lat = response.extract().path("data.lat");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value details of Magnolia Home Theater : " + lat);
        System.out.println("------------------End of Test---------------------------");
    }
}