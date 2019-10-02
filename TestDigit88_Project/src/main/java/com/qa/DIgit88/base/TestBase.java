package com.qa.DIgit88.base;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.Properties;

import org.apache.log4j.Logger;


public class TestBase {

	public static Properties prop;

	public static Logger log;

	public TestBase() {

		try {

			prop = new Properties();

			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/qa"

					+ "/DIgit88/config/config.properties");

			prop.load(ip);

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	
}
