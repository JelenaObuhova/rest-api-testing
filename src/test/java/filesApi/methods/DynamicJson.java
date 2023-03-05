package filesApi.methods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class DynamicJson {
    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI="http://216.10.245.166";
        String response = given().header("Content-Type", "application/json")
                .body(Payload.AddBook(isbn, aisle)).when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js = ReUsableMethods.rawToJson(response);
        System.out.println(js);

        String id = js.get("ID");
        System.out.println(id);
    }
    //Delete Book
    @DataProvider(name = "BooksData")
    public Object[][] getData(){
        // Array = collection of elements;
        // Multidimensional array = collection of arrays;
        return new Object[][] {{"Edgar's", "Book"}, {"Jelena's", "Book"}, {"Boris's", "Book"}
        };
    }

}

