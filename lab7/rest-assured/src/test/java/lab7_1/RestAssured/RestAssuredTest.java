package lab7_1.RestAssured;

import static org.junit.Assert.assertTrue;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

public class RestAssuredTest 
{
    private static String toDos = "https://jsonplaceholder.typicode.com/todos";
    @Test
    public void whenGetTodos_thenStatusCode200()
    {
        given()
        .when()
            .get(toDos)
        .then()
            .statusCode(200);
            // .log().body();
    }

    @Test
    public void whenGetTodo4_thenCheckTitle(){
        String expected = "et porro tempora";
        given()
        .when()
            .get(toDos+"/4")
        .then()
            .statusCode(200)
            .log().body()
            .and().body("id", equalTo(4))
            .and().body("title", equalTo(expected));
    }

    @Test
    public void whenListAllTodos_thenCheckIds(){
        given()
        .when()
            .get(toDos)
        .then()
            .statusCode(200)
            .and().body("id", hasItems(198, 199));
    }
}
