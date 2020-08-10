
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class REST_testing {

    @Test (priority=1)
    public void test_GET (){
       given()
               .when()
                    .get("http://jsonplaceholder.typicode.com/posts")
               .then()
                    .statusCode(200);
    }
    @Test (priority=2)
    public void test_POST(){
        HashMap data1 = new HashMap();
                data1.put("id", "6");
                data1.put("email", "sauwaa@mail.ru");
                data1.put("first_name", "Nikolai");
                data1.put("last_name", "Ivanov");
                data1.put("avatar", "https://s3.amazonaws.com/uifaces/faces/twitter/stephenmoon/128.jpg");
        Response res =
                given()
                        .contentType("application/json")
                        .body(data1)
                .when()
                        .post("https://reqres.in/api/users")
                .then()
                        .statusCode(201)
                        .log().body()
                        .extract().response();

        String jsonString =res.asString();
        Assert.assertEquals(jsonString.contains(jsonString),true);
    }
    @Test (priority=3)
    public void test_PUT(){
        HashMap data = new HashMap();
        data.put("id", "2");
        data.put("email", "janet.weaver@reqres.in");
        data.put("first_name", "User 1");
        data.put("last_name", "Weaver");
        data.put("avatar", "https://s3.amazonaws.com/uifaces/faces/twitter/olegpogodaev/128.jpg");

        given()
                .contentType("application/json")
                .body(data)
        .when()
                .put("https://reqres.in/api/users/2")
        .then()
                .statusCode(200)
                .log().body()
                .body("id", equalTo("2"))
                .body("first_name", equalTo("User 1"));

    }
    @Test (priority=4)
    public void test_DELETE(){
        Response res=
        given()
                .when()
                    .delete("https://reqres.in/api/users/2")
                .then()
                    .statusCode(204)
                    .log().body()
                .extract().response();

       String jsonString = res.asString();
        System.out.println(" jsonString =" + jsonString);
       Assert.assertEquals(jsonString.contains(jsonString),true);
    }
}
