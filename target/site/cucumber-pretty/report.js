$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("city-movies.feature");
formatter.feature({
  "line": 1,
  "name": "City movies advanced",
  "description": "",
  "id": "city-movies-advanced",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Get locations",
  "description": "",
  "id": "city-movies-advanced;get-locations",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "the apis are up and running for \"http://cmapi.bananaappscenter.com/\"",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "a user performs a GET request to \"api/Location/LocationDetails\"",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the request code should be 100",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "http://cmapi.bananaappscenter.com/",
      "offset": 33
    }
  ],
  "location": "cityMoviesSteps.theApisAreUpAndRunningFor(String)"
});
formatter.result({
  "duration": 865216919,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "api/Location/LocationDetails",
      "offset": 34
    }
  ],
  "location": "cityMoviesSteps.aUserPerformsAGETRequestTo(String)"
});
formatter.result({
  "duration": 177275487,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "100",
      "offset": 27
    }
  ],
  "location": "cityMoviesSteps.theRequestCodeShouldBe(int)"
});
formatter.result({
  "duration": 3839641,
  "error_message": "java.lang.AssertionError: Incorrect Response Code expected:\u003c100\u003e but was:\u003c200\u003e\r\n\tat org.junit.Assert.fail(Assert.java:88)\r\n\tat org.junit.Assert.failNotEquals(Assert.java:834)\r\n\tat org.junit.Assert.assertEquals(Assert.java:645)\r\n\tat controller.RestValidator.expectCode(RestValidator.java:16)\r\n\tat steps.cityMoviesSteps.theRequestCodeShouldBe(cityMoviesSteps.java:39)\r\n\tat ✽.Then the request code should be 100(city-movies.feature:6)\r\n",
  "status": "failed"
});
});