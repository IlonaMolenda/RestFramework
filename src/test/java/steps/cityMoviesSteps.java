package steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import controller.RestExecutor;
import controller.RestResponse;
import controller.RestValidator;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;

public class cityMoviesSteps {

  RestExecutor restExecutor;
  RestResponse restResponse;
  RestValidator restValidator = new RestValidator(restResponse);
  Map<String, Object> responseMap = new HashMap<>();

  @Given("^the apis are up and running for \"([^\"]*)\"$")
  public void theApisAreUpAndRunningFor(String url) throws Throwable {
    restExecutor = new RestExecutor(url);
    restExecutor.get("").expectCode(200).printBody();//daje pusty "" w get, bo chce sprawdzic tylko czy dziala
    //ten glowny url, a w get do glownego dodawany jest path, a ja nie chce niczego dodwac.
    //Sprawdzam tu czy kod 200 jest ok i printuje odpowiedz.
  }

  @When("^a user performs a GET request to \"([^\"]*)\"$")
  public void aUserPerformsAGETRequestTo(String path) throws Throwable {
    this.restValidator = restExecutor.get(path);
    //restExecutor.get(path);
  }

  @Then("^the request code should be (\\d+)$")
  public void theRequestCodeShouldBe(int expectedCode) throws Throwable {
    restValidator.expectCode(expectedCode).printBody(); //dodatkowo drukuje body
    System.out.println();
    System.out.println(restValidator.getResponse().getResponseCode());//jesli asercja nie przejdzie
    //to mi tego nie wydrukuje. Wydrukuje, jesli dam to przed asercja
    System.out.println();
  }

  @And("^I should see json response with pairs on the filtered \"([^\"]*)\" node$")
  public void iShouldSeeJsonResponseWithPairs(String filter, DataTable dataTable) throws Throwable {

    //TO ZERZNELAM WPROST  ZPRZYKLADU Z RESTASSURED. DZIALA ALE KIEPSKO WYGLADA.
    //SKORO TA MA BYC FRAMEWORK, TO POWINNAM GDZIES NA ZEWNATRZ STWORZYC METODE, KTORA BY MI TO ZROBILA

    Map<String, String> query = new HashMap<>();

    for (DataTableRow row: dataTable.getGherkinRows()) {
      query.put(row.getCells().get(0), row.getCells().get(1));
    }
     // System.out.println(query);
   // System.out.println();

    ObjectReader reader= new ObjectMapper().reader(Map.class);

    responseMap = reader.readValue(restValidator.getResponse().getResponseBody());
    System.out.println(responseMap);
    responseMap = (Map<String, Object>) responseMap.get(filter);
    System.out.println();
    System.out.println(responseMap);

    for (String key : query.keySet()){
      Assert.assertTrue(responseMap.containsKey(key));
      Assert.assertEquals(query.get(key), responseMap.get(key).toString());
    }

//    restValidator.expectInBody("Success Location Details"); //A TU NA BAZIE FRAMEWORKA - MOGE SPARWDZIC JEDEN STRING A NIE JSONA

  }
}
