package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

public class RestExecutor {

  private HttpClient client;
  private String url;

  /**
   * Constructor for RestExecutor
   *
   * @param url
   */

  public RestExecutor(String url) {
    client = HttpClientBuilder.create().build();
    //CloseableHttpClient httpclient = HttpClients.createDefault(); //nie wiem czy to nie bedzie lepsze
    //zamiast HttpClient, ktory jest interfejsem. Praktycznie wszyscy korzystaja z klasy, ktora go
    //implementuje czyli ClosableHttpCient
    this.url = url;
  }

  public RestValidator get(String path){
    return get(path, null);
  }

  /**
   * Executes GET req and returns response json.
   *
   * @param path
   * @param headers
   * @return
   */
  public RestValidator get (String path, HashMap<String, String> headers){

    HttpGet request = new HttpGet(url + path);
    HttpResponse response;
    	/*
		 * The response object which holds the details of the response.
		 */
    RestResponse restResponse = new RestResponse();
    StringBuffer responseString = new StringBuffer();

    try {
      /*
			 * Setting the headers for the request
			 */
      if(headers!=null){
        Set<String> keys = headers.keySet();
        for (String key:keys) {
          request.addHeader(key, headers.get(key));
        }
      }
      /*
			 * Executing the GET operation
			 */
      response = client.execute(request);

      /*
			 * Obtaining the response body from the response stream.
			 */
      BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      String line = "";
      while ((line = rd.readLine())!= null){
        responseString.append(line);
      }
      	/*
			 * Setting values for the response object
			 */
      	restResponse.setResponseBody(responseString.toString());
      	restResponse.setResponseCode(response.getStatusLine().getStatusCode());
      	restResponse.setResponseMessage(response.getStatusLine().getReasonPhrase());
        Header [] resheaders = response.getAllHeaders();
      for (Header header:resheaders) {
        restResponse.setHeader(header.getName(), header.getValue());
      }
    }catch (Exception e) {
      e.printStackTrace();
      }

      /*
		 * Returns the RestValidator object providing the response object
		 */
      return new RestValidator(restResponse);
  }



}
