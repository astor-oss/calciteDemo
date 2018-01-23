package com.free.calcite.demo;

import org.apache.calcite.jdbc.CalciteConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {
	public static void main(String[] args) {
		// 1. get the model file
		String modelPath = args.length < 1 ? "/home/zongxing/school.json" : args[0];

		// 2. register calcite class
        try {
            Class.forName("org.apache.calcite.jdbc.Driver");

            // 3. connect the db and do some operation
            Properties properties = new Properties();
            Connection connection = DriverManager.getConnection("jdbc:calcite:model=" + modelPath, properties);
            CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);

            // 3.1 scan database schema
            System.out.println("Now Scan database...");
            ResultSet resultSet = calciteConnection.getMetaData().getColumns(
                    null,
                    null,
                    null,
                    null);

            while (resultSet.next()) {
                System.out.println("Catalog:" + resultSet.getString(1)
                        + ", DataBase:" + resultSet.getString(2)
                        + ", Table:" + resultSet.getString(3));
            }

            resultSet.close();

            // 3.2 scan database data
            resultSet = calciteConnection.getMetaData().getColumns(
                    null,
                    null,
                    "Student",
                    null);
            while(resultSet.next()) {
                System.out.println("name : " + resultSet.getString(4)
                        + ", type : " + resultSet.getString(5)
                        + ", typename : " + resultSet.getString(6));
            }
            resultSet.close();

            // 3.3 query the database
            Statement queryStatment = calciteConnection.createStatement();
            // String querySql = "select \"id\", \"name\" from \"Student\"";
            String querySql = "select * from \"Student\"";
            resultSet = queryStatment.executeQuery(querySql);
            while (resultSet.next()) {
                System.out.println("[" + resultSet.getString(1)
                        + "," + resultSet.getString(2)
                        + "," + resultSet.getString(3)
                        + "," + resultSet.getString(5)
                        + "]"
                );
            }
            resultSet.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Catch class Not Found Exception:" + e.toString());
            e.printStackTrace();
        } catch (SQLException sqlException) {
            System.out.println("Catch SqlException:" + sqlException.toString());
        } catch (Exception exception) {
            System.out.println("Cachte Other Exception:" + exception);
        }

	}
}
