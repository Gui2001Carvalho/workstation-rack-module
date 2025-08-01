package com.ctw.workstation.team;

import com.ctw.workstation.team.DTOs.TeamInputDTO;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import simple.CarService;

import java.util.UUID;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestHTTPEndpoint(TeamResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TeamResourceTest {

    @Order(0)
    @Test
    @DisplayName("Get All teams")
    void get_All_Teams() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(200);

        RestAssured.get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    @Order(1)
    @Test
    @DisplayName("Create Team")
    void create_Team() {
        // given
        String requestBody = """
            {
              "name": "Sporting",
              "product": "Futebol",
              "defaultLocation": "Lisboa"
            }
        """;

        // when
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post()
                .then()
                .statusCode(201);

        RestAssured.get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("find { it.name == 'Sporting' }", notNullValue())
                .body("find { it.name == 'Sporting' }.product", equalTo("Futebol"))
                .body("find { it.name == 'Sporting' }.defaultLocation", equalTo("Lisboa"));
    }

    @Order(2)
    @Test
    @DisplayName("Get teams size")
    void get_teams_size() {

        String requestBody2 = """
        {
          "name": "Benfica",
          "product": "Futebol",
          "defaultLocation": "Lisboa"
        }
    """;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody2)
                .when()
                .post()
                .then()
                .statusCode(201);

        RestAssured.get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("", hasSize(2))
                .body("name", hasItems("Sporting", "Benfica"));
    }


    @Order(3)
    @Test
    @DisplayName("Get Team by id")
    void get_Team_by_id() {

        String id = RestAssured.get()
                .then()
                .statusCode(200)
                .extract()
                .path("find { it.name == 'Benfica' }.id");


        RestAssured.given().pathParam("id", id)
                .contentType(ContentType.JSON)
                .when()
                .get("/{id}")
                .then()
                .log().all()
                .statusCode(200);


    }

    @Order(4)
    @Test
    @DisplayName("Get Team by location")
    void get_Team_by_location() {
        String requestBody3 = """
        {
          "name": "Porto",
          "product": "Futebol",
          "defaultLocation": "Porto"
        }
    """;

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody3)
                .when()
                .post()
                .then()
                .statusCode(201);

        //Aqui é queryParam
        RestAssured.given().queryParam("defaultLocation", "Lisboa")
                .contentType(ContentType.JSON)
                .when()
                .get("/location")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("", hasSize(2));
    }


    @Order(5)
    @Test
    @DisplayName("Delete team Benfica")
    void delete_team_benfica() {
        String id = RestAssured.get()
                .then()
                .statusCode(200)
                .extract()
                .path("find { it.name == 'Benfica' }.id");


        RestAssured.given().pathParam("id", id)
                .contentType(ContentType.JSON)
                .when()
                .delete("/{id}")
                .then()
                .log().all()
                .statusCode(204);

        RestAssured.get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("", hasSize(2));
    }

    @Order(6)
    @Test
    @DisplayName("Update Team")
    void update_team() {

        String id = RestAssured.get()
                .then()
                .statusCode(200)
                .extract()
                .path("find { it.name == 'Sporting' }.id");

        String location = "{\"defaultLocation\":\"Portugal\"}";

        RestAssured.given().pathParam("id", id)
                .contentType(ContentType.JSON)
                .body(location)
                .when()
                .patch("/{id}")
                .then()
                .statusCode(200);

        RestAssured.get()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("find { it.name == 'Sporting' }", notNullValue())
                .body("find { it.name == 'Sporting' }.product", equalTo("Futebol"))
                .body("find { it.name == 'Sporting' }.defaultLocation", equalTo("Portugal"));
    }

}

//Fazer o get com tudo vazio para ver se está a criar a base de dados.
//Depois criar 2 instancias, a 1ª para confirmar que os dados estão iguais e depois uma segunda.
//Fazer o get by id
//Fazer o delete de uma instancia.
//Fazer o get para ver se só está lá 1;
//Fazer o patch e acabou?
