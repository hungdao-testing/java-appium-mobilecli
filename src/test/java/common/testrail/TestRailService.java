package common.testrail;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestRailService {
    private String username;
    private String password;

    private String baseUrl = "https://automationtc.testrail.io/";
    public TestRailService(String username, String password){
        this.username = username;
        this.password = password;
    }

    private RequestSpecification getAuthenticatedReq(){
        return RestAssured.given().auth().preemptive().basic(this.username, this.password).contentType(ContentType.JSON);
    }

    public Response getTestRun(String runId){
        return getAuthenticatedReq().pathParam("run_id", runId)
                .get(this.baseUrl + "index.php?/api/v2/get_run/{run_id}")
                .then().statusCode(200).log().ifValidationFails().extract().response();
    }

    public Response getResultsForTestRun(String runId){
        return getAuthenticatedReq().pathParam("run_id", runId)
                .get(this.baseUrl + "index.php?/api/v2/get_results_for_run/{run_id}")
                .then().statusCode(200).log().all().extract().response();
    }

    public Response importTestResult(String runId, Object testResult){
        return getAuthenticatedReq().pathParam("run_id", runId)
                .body(testResult)
                .post(this.baseUrl + "index.php?/api/v2/add_results_for_cases/{run_id}")
                .then().statusCode(200).extract().response();
    }

    public Response addTestResult(String runId, Object testResult){
        System.out.println(testResult);
        return getAuthenticatedReq().pathParam("run_id", runId)
                .body(testResult)
                .post(this.baseUrl + "index.php?/api/v2/add_results/{run_id}")
                .then().log().all().extract().response();
    }
}
