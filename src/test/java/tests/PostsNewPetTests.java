package tests;

import static core.EndPoints.PET;
import static core.EndPoints.PET_BY_ID;

import core.models.PetModel;
import core.models.PetModel.Category;
import core.models.PetModel.Tag;
import io.restassured.RestAssured;
import java.util.ArrayList;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PostsNewPetTests extends BaseTest {

  private long petId;

  @BeforeTest
  private void beforeTest() {
    softAssertions = new SoftAssertions();
  }

  @AfterTest
  private void afterTest() {
    softAssertions.assertAll();
  }

  @Test
  public void getPetByNameTest() {
    ArrayList<Tag> listTags = new ArrayList<>();
    listTags.add(new Tag(20, "Silent"));
    listTags.add(new Tag(30, "Cute"));
    listTags.add(new Tag(31, "Small dog"));

    ArrayList<String> listUrl = new ArrayList<>();
    listUrl.add("https://unsplash.com/photos/v3-zcCWMjgM");
    listUrl.add("https://unsplash.com/photos/T-0EW-SEbsE");
    listUrl.add("https://unsplash.com/photos/BJaqPaH6AGQ");

    PetModel userModel = PetModel.builder()
        .name("Rex")
        .category(new Category(10, "Dogs"))
        .tags(listTags)
        .photoUrls(listUrl)
        .status("available")
        .build();

    PetModel response = RestAssured
        .given()
        .body(userModel)
        .when()
        .post(PET)
        .then()
        .statusCode(200).extract()
        .as(PetModel.class);

    petId = response.getId();

    softAssertions.assertThat(response.getId()).isNotEqualTo(0);
    softAssertions.assertThat(response).isEqualTo(userModel);
  }

  @Test(dependsOnMethods = {"getPetByNameTest"})
  public void getPetByIdTest() {
    PetModel responseById = RestAssured
        .given()
        .pathParam("petId", petId)
        .when()
        .get(PET_BY_ID)
        .then()
        .statusCode(200)
        .extract()
        .as(PetModel.class);

    softAssertions.assertThat(responseById.getName()).isEqualTo("Rex");
    softAssertions.assertThat(responseById.getStatus()).isEqualTo("available");
  }
}