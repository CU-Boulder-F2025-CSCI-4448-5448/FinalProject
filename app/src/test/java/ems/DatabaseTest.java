package ems;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    @Test
    void testDatabaseInitialization() throws SQLException {
        // Attempt to run the schema creation
        Database Database = null;
        Database.initializeDatabase();

        // Try connecting and checking if the table exists
        try (Connection conn = Database.getConnection()) {
            ResultSet rs = conn.getMetaData().getTables(null, null, "EMPLOYEES", null);

            assertTrue(rs.next(), "Employees table should exist after initialization.");
        }
    }
}
