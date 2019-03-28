package com.yrd.farm;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IrrigateApplicationTests {
	
	@Autowired
	DataSource source;

	@Test
	public void contextLoads() throws SQLException {
		System.out.println(source.getClass());
		
		Connection connection = source.getConnection();
		System.out.println(connection);
		connection.close();
	}

}

