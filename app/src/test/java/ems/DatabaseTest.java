package ems;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    // Worked with chatgpt to make sure sql database comes up and runs correctly
    @Test
    void testDatabaseInitialization() throws SQLException {
        Database db = Database.getInstance();
        db.initializeDatabase();

        try (Connection conn = db.getConnection()) {
            // H2 stores table names in uppercase unless quoted
            ResultSet rs = conn.getMetaData().getTables(null, null, "EMPLOYEES", null);

            assertTrue(rs.next(), "Employees table should exist after initialization.");
        }
    }
}

