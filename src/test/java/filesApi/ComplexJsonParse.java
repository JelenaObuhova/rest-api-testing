package filesApi;

import filesApi.methods.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args){
        JsonPath js = new JsonPath(Payload.CoursePrice());
        //Print №of courses returned by API
           int count = js.getInt("courses.size()");
        System.out.println(count);

        //Print Purchase Amount
       int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);

        //Print Title of the first course
        String titleFirstCourse = js.get("courses[2].title");
        System.out.println(titleFirstCourse);

        //Print all courses titles and their prices
        for(int i=0; i<count; i++) {
         String courseTitles = js.get("courses["+i+"].title");

         //Print No of copies sold by RPA
         if (courseTitles.equalsIgnoreCase("RPA")){
            int copies = js.get("courses["+i+"].copies");
             System.out.println(copies);
             break;
         }

        }
    }
}

