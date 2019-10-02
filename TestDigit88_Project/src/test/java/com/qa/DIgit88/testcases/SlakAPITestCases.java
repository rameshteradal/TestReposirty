package com.qa.DIgit88.testcases;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.List;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.DIgit88.base.TestBase;
import com.qa.DIgit88.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class SlakAPITestCases extends TestBase {

	String cName;

	@BeforeClass

	public void init() {

		log = Logger.getLogger("");

	}

	@Test(priority = 1, enabled = true)

	public void createChannel() throws IOException {

		String posturl = prop.getProperty("baseurl_createchannel");

		System.out.println("Base URL is : " + posturl);

		Response response = given().contentType(ContentType.JSON).queryParam("token", prop.getProperty("oauth_token"))

				.queryParam("name", prop.getProperty("channelName"))

				// .param("pretty", "1")

				.when().log().all().post(posturl);

		Assert.assertEquals(response.getStatusCode(), TestUtil.RESPONSE_CODE_200);

		System.out.println(response.asString());

		JsonPath jsonPathEvaluator = response.jsonPath();

		String cName = jsonPathEvaluator.get("channel.name");

		System.out.println("Channel Name created is: " + cName);

		if (prop.getProperty("channelName").equalsIgnoreCase(cName)) {

			Assert.assertTrue(true);

		} else {

			Assert.assertTrue(false);

		}

	}

	@Test(priority = 2, enabled = true)

	public void joinChannel() {

		String joinposturl = prop.getProperty("baseurl_joinchannel");

		System.out.println("Base URL is : " + joinposturl);

		Response response = given().contentType(ContentType.JSON).queryParam("token", prop.getProperty("oauth_token"))

				.queryParam("name", prop.getProperty("channelName"))

				// .param("pretty", "1")

				.when().log().all().post(joinposturl);

		System.out.println(response.asString());

		JsonPath jsonPathEvaluator = response.jsonPath();

		boolean okText = jsonPathEvaluator.get("ok");

		Assert.assertTrue(okText);

	}

	@Test(priority = 3, enabled = true)

	public void renameChannel() {

		String renameposturl = prop.getProperty("baseurl_renamechannel");

		System.out.println("Base URL is : " + renameposturl);

		Response response = given().contentType(ContentType.JSON).queryParam("token", prop.getProperty("oauth_token"))

				.queryParam("channel", prop.getProperty("channelName"))

				.queryParam("name", prop.getProperty("channelRename"))

				.param("pretty", "1")

				.when()

//                                                         .log().all()

				.post(renameposturl);

		System.out.println(response.asString());

		JsonPath jsonPathEvaluator = response.jsonPath();

//                           boolean okText = jsonPathEvaluator.get("ok");

//                           Assert.assertTrue(okText);

		System.out.println("Response from end" + response.asString());

	}

	@Test(priority = 4, enabled = true)

	public void getChannels() {

		String geturl = prop.getProperty("baseurl_getchannels");

		System.out.println("Base URL is : " + geturl);

		Response response = given().contentType(ContentType.JSON).param("token", prop.getProperty("oauth_token"))

//                           .param("name", prop.getProperty("channelName"))

				.param("pretty", "1").when().log().all().get(geturl);

		System.out.println(response.asString());

	}

	@Test(priority = 5, enabled = true)

	public void archiveChannels() {

		String archiveposturl = prop.getProperty("baseurl_archiveChannel");

		System.out.println("Base URL is : " + archiveposturl);

		Response response = given().contentType(ContentType.JSON).queryParam("token", prop.getProperty("oauth_token"))

				.queryParam("channel", prop.getProperty("channelName"))

				.param("pretty", "1")

				.when().log().all().post(archiveposturl);

		System.out.println(response.asString());

		JsonPath jsonPathEvaluator = response.jsonPath();

		boolean okText = jsonPathEvaluator.get("ok");

		Assert.assertTrue(okText);

		System.out.println("Response from end" + response.asString());

	}

}