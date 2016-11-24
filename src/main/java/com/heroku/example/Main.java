package com.heroku.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan("com.heroku.example")
@ImportResource("classpath*:applicationContext.xml")
public class Main {

	@Autowired
	private static DataSource dataSource;

	public static void main(String[] args) throws Exception {
		org.springframework.context.ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
		// GenericXmlApplicationContext ctx = new
		// GenericXmlApplicationContext("classpath:applicationContext.xml");
		// myRealMainMethod();
	}

	public static void myRealMainMethod() throws SQLException {
		Statement stmt = dataSource.getConnection().createStatement();
		stmt.executeUpdate("DROP TABLE IF EXISTS ticks");
		stmt.executeUpdate("CREATE TABLE ticks (tick timestamp)");
		stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
		ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");
		while (rs.next()) {
			System.out.println("Read from DB: " + rs.getTimestamp("tick"));
		}
	}

}