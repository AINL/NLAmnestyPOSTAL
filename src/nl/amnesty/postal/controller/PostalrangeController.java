/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amnesty.postal.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.amnesty.postal.sql.PostalrangeSQL;
import nl.amnesty.postal.db.Database;
import nl.amnesty.postal.entity.Postalrange;

/**
 *
 * @author evelzen
 */
public class PostalrangeController {

    public static Postalrange read(int postalcodenumeric, String postalcodealpha, int housenofrom) {
        Database database = null;
        Connection connection = null;
        Postalrange postalrange = new Postalrange();
        try {
            // Open database
            //database = new Database("jdbc/Postcode");
            //database = new Database(Database.DB_TYPE_MSSQL, "10.0.0.11", "", 0, "Postcode", "sa", "Pr0gr3ss");
            database = new Database(Database.DB_TYPE_MSSQL, "10.0.1.17", "CRM", 8484, "Postcode", "webservices", "kN0rr3p0T");
            connection = database.open();
            // Execute and return
            postalrange = PostalrangeSQL.read(connection, postalcodenumeric, postalcodealpha, housenofrom);
        } catch (Exception e) {
            Logger.getLogger(PostalrangeController.class.getName()).log(Level.SEVERE, null, e);
            return postalrange;
        } finally {
            try {
                if (connection != null) {
                    connection.commit();
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                }
                if (database != null) {
                    if (!database.isClosed()) {
                        database.close();
                    }
                }
            } catch (SQLException sqle) {
                Logger.getLogger(PostalrangeController.class.getName()).log(Level.SEVERE, null, sqle);
            }
        }
        return postalrange;
    }

    public static Postalrange readViaPostalcodeHouseno(int postalcodenumeric, String postalcodealpha, int houseno) {
        Database database = null;
        Connection connection = null;
        Postalrange postalrange = new Postalrange();
        try {
            // Open database
            //database = new Database("jdbc/Postcode");
            //database = new Database(Database.DB_TYPE_MSSQL, "10.0.0.11", "", 0, "Postcode", "sa", "Pr0gr3ss");
            database = new Database(Database.DB_TYPE_MSSQL, "10.0.1.17", "CRM", 8484, "Postcode", "webservices", "kN0rr3p0T");
            connection = database.open();
            // Execute and return
            postalrange = PostalrangeSQL.readViaPostalcodeHouseno(connection, postalcodenumeric, postalcodealpha, houseno);
        } catch (Exception e) {
            return postalrange;
        } finally {
            try {
                if (connection != null) {
                    connection.commit();
                    if (!connection.isClosed()) {
                        connection.close();
                    }
                }
                if (database != null) {
                    if (!database.isClosed()) {
                        database.close();
                    }
                }
            } catch (SQLException sqle) {
                Logger.getLogger(PostalrangeController.class.getName()).log(Level.SEVERE, null, sqle);
            }
        }
        return postalrange;
    }
}
