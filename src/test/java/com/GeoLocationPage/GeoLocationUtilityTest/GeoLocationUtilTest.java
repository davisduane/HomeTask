package com.GeoLocationPage.GeoLocationUtilityTest;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import io.restassured.response.Response;
import org.junit.Test;


public class GeoLocationUtilTest {

    @Test
    public void testGetCoordinatesWithCity() {
        String location = "Madison, WI";
        String response = GeoLocationUtil.getCoordinates(location);
        assertThat(response, not(containsString("Location not found")));
        assertThat(response, containsString(","));
    }

    @Test
    public void testGetCoordinatesWithZip() {
        String location = "12345";
        String response = GeoLocationUtil.getCoordinates(location);
        assertThat(response, not(containsString("Location not found")));
        assertThat(response, containsString(","));
    }
}