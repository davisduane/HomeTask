package com.GeoLocationPage.GeoLocationUtilityTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GeoLocationUtil {
        private static final String API_KEY = "f897a99d971b5eef57be6fafa0d83239";
        private static final String BASE_URL = "http://api.openweathermap.org/geo/1.0/";

        public static String getCoordinates (String location){
            String coordinates;
            Response response = getLocationResponse(location);

            if (isSuccessfulResponse(response)) {
                coordinates = getLatLonFromResponse(response);
            } else {
                coordinates = "Location not found";
            }

            return coordinates;
        }

        private static Response getLocationResponse (String location){
            Response response;

            if (isZipCode(location)) {
                response = getZipCodeResponse(location);
            } else {
                response = getCityStateResponse(location);
            }

            return response;
        }

        private static Response getZipCodeResponse (String zipCode){
            return RestAssured
                    .given()
                    .queryParam("zip", zipCode + ",US")
                    .queryParam("appid", API_KEY)
                    .when()
                    .get(BASE_URL + "zip")
                    .then()
                    .extract()
                    .response();
        }

        private static Response getCityStateResponse (String cityState){
            return RestAssured
                    .given()
                    .queryParam("q", cityState)
                    .queryParam("appid", API_KEY)
                    .when()
                    .get(BASE_URL + "direct")
                    .then()
                    .extract()
                    .response();
        }

        private static boolean isSuccessfulResponse (Response response){
            return response.getStatusCode() == 200 && !response.jsonPath().getList("$").isEmpty();
        }

        private static boolean isZipCode (String location){
            return location.matches("\\d{5}");
        }

        private static String getLatLonFromResponse (Response response){
            String latitude = response.jsonPath().getString("[0].lat");
            String longitude = response.jsonPath().getString("[0].lon");
            return latitude + ", " + longitude;
        }
    }