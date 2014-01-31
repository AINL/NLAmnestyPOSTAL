/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amnesty.postal.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.amnesty.postal.entity.Postalrange;

/**
 *
 * @author evelzen
 */
public class PostalrangeSQL {

    public static Postalrange create(Connection connection, Postalrange range) {
        Statement statement = null;
        ResultSet resultset = null;
        try {
            String SQL = "SELECT * FROM reeks";
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            resultset = statement.executeQuery(SQL);
            resultset.moveToInsertRow();

            resultset = setResultsetColumns(resultset, range);

            if (resultset != null) {
                resultset.insertRow();

                //DEBUG
                //int postalcodenumeric = range.getPostcodenumeriek();
                //String postalcodealpha = range.getPostcodealpha();
                //int housenofrom = range.getHuisnummervan();
                //Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.INFO, "Created Range for postalcodenumeric {0} postalcodealpha {1} housenofrom {2}", new Object[]{postalcodenumeric, postalcodealpha, housenofrom});

            }
            range.setStatus(Postalrange.STATUS_NEW);
            return range;
        } catch (SQLException sqle) {
            Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.SEVERE, null, sqle);
            return range;
        } catch (Exception e) {
            Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.SEVERE, null, e);
            return range;
        } finally {
            try {
                if (resultset != null) {
                    if (!resultset.isClosed()) {
                        resultset.close();
                    }
                }
                if (statement != null) {
                    if (!statement.isClosed()) {
                        statement.close();
                    }
                }
            } catch (SQLException sqle) {
                Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.SEVERE, null, sqle);
            }
        }
    }

    public static Postalrange read(Connection connection, int postalcodenumeric, String postalcodealpha, int housenofrom) {
        if (connection == null) {
            return null;
        }
        if (postalcodenumeric == 0) {
            return null;
        }
        if (postalcodealpha == null) {
            return null;
        }
        if (postalcodealpha.isEmpty()) {
            return null;
        }
        if (housenofrom == 0) {
            return null;
        }
        String query = "";
        Statement statement = null;
        ResultSet resultset = null;
        try {
            query = "SELECT * FROM reeks "
                    + " WHERE " + Postalrange.FIELD_RANGE_POSTALCODENUMERIC + "=" + postalcodenumeric
                    + " AND " + Postalrange.FIELD_RANGE_POSTALCODEALPHA + "='" + postalcodealpha + "'"
                    + " AND " + Postalrange.FIELD_RANGE_HOUSENO_FROM + "=" + housenofrom;

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(query);
            if (resultset.first()) {
                Postalrange postalrange = setBeanProperties(resultset);
                return postalrange;
            } else {
                return null;
            }
        } catch (SQLException sqle) {
            Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.SEVERE, null, sqle);
            return null;
        } catch (Exception e) {
            Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            try {
                if (resultset != null) {
                    if (!resultset.isClosed()) {
                        resultset.close();
                    }
                }
                if (statement != null) {
                    if (!statement.isClosed()) {
                        statement.close();
                    }
                }
            } catch (SQLException sqle) {
                Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.SEVERE, null, sqle);
            }
        }
    }

    public static Postalrange readViaPostalcodeHouseno(Connection connection, int postalcodenumeric, String postalcodealpha, int houseno) {
        if (connection == null) {
            return null;
        }
        if (postalcodenumeric == 0) {
            return null;
        }
        if (postalcodealpha == null) {
            return null;
        }
        if (postalcodealpha.isEmpty()) {
            return null;
        }
        if (houseno == 0) {
            return null;
        }
        String query = "";
        Statement statement = null;
        ResultSet resultset = null;
        try {
            String queryfragment = "";
            if (houseno % 2 == 0) {
                queryfragment = " AND " + Postalrange.FIELD_RANGE_EVEN + "='1'";
            } else {
                queryfragment = " AND " + Postalrange.FIELD_RANGE_EVEN + "='0'";
            }
            query = "SELECT * FROM reeks "
                    + " WHERE " + Postalrange.FIELD_RANGE_POSTALCODENUMERIC + "=" + postalcodenumeric
                    + " AND " + Postalrange.FIELD_RANGE_POSTALCODEALPHA + "='" + postalcodealpha + "'"
                    + " AND " + Postalrange.FIELD_RANGE_HOUSENO_FROM + "<=" + houseno
                    + " AND " + Postalrange.FIELD_RANGE_HOUSENO_TO + ">=" + houseno
                    + queryfragment;

            // DEBUG
            //Logger.getLogger(PostalrangeImplementationSQL.class.getName()).log(Level.WARNING, "readViaPostalcodeHouseno(): query {0}", query);

            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultset = statement.executeQuery(query);
            if (resultset.first()) {
                Postalrange postalrange = setBeanProperties(resultset);

                //DEBUG
                //Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.INFO, "Found {0} {1} {2}", new Object[]{postalrange.getHuisnummervan(), postalrange.getHuisnummertotmet(), postalrange.getWoonplaatsnen()});

                return postalrange;
            } else {
                return null;
            }
        } catch (SQLException sqle) {
            Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.SEVERE, null, sqle);
            return null;
        } catch (Exception e) {
            Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.SEVERE, null, e);
            return null;
        } finally {
            try {
                if (resultset != null) {
                    if (!resultset.isClosed()) {
                        resultset.close();
                    }
                }
                if (statement != null) {
                    if (!statement.isClosed()) {
                        statement.close();
                    }
                }
            } catch (SQLException sqle) {
                Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.SEVERE, null, sqle);
            }
        }
    }

    public static boolean update(Connection connection, Postalrange range) {
        if (range.getPostcodenumeriek() == 0) {
            return false;
        }
        if (range.getPostcodealpha() == null) {
            return false;
        }
        if (range.getPostcodealpha().isEmpty()) {
            return false;
        }
        if (range.getHuisnummervan() == 0) {
            return false;
        }

        String sql = "";
        Statement statement = null;
        try {
            String marketingcode = range.getCebuco();
            String countycode = range.getGemeentecode();
            String county = range.getGemeentenaam();
            int housenoto = range.getHuisnummertotmet();
            //int housenofrom = range.getHuisnummervan();
            //String postalcodealpha = range.getPostcodealpha();
            //int postalcodenumeric = range.getPostcodenumeriek();
            String provincecode = range.getProvinciecode();
            String streetextract = range.getStraatnaamextract();
            String streetnormalized = range.getStraatnaamnen();
            String streetofficial = range.getStraatnaamofficieel();
            String streetprovider = range.getStraatnaamtnt();
            String cityextract = range.getWoonplaatsextract();
            String citynormalized = range.getWoonplaatsnen();
            String cityprovider = range.getWoonplaatstnt();
            String evenvalue = "";
            if (range.isEvenoneven()) {
                evenvalue = "1";
            } else {
                evenvalue = "0";
            }


            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "UPDATE range SET "
                    + Postalrange.FIELD_RANGE_CITY_EXTRACT + "='" + cityextract + "',"
                    + Postalrange.FIELD_RANGE_CITY_NORMALIZED + "='" + citynormalized + "',"
                    + Postalrange.FIELD_RANGE_CITY_PROVIDER + "='" + cityprovider + "',"
                    + Postalrange.FIELD_RANGE_COUNTY + "='" + county + "',"
                    + Postalrange.FIELD_RANGE_COUNTY_CODE + "='" + countycode + "',"
                    + Postalrange.FIELD_RANGE_EVEN + "='" + evenvalue + "',"
                    + Postalrange.FIELD_RANGE_HOUSENO_TO + "=" + housenoto + ","
                    + Postalrange.FIELD_RANGE_MARKETING_CODE + "='" + marketingcode + "',"
                    + Postalrange.FIELD_RANGE_PROVINCE_CODE + "='" + provincecode + "',"
                    + Postalrange.FIELD_RANGE_STREET_EXTRACT + "='" + streetextract + "',"
                    + Postalrange.FIELD_RANGE_STREET_NORMALIZED + "='" + streetnormalized + "',"
                    + Postalrange.FIELD_RANGE_STREET_OFFICIAL + "='" + streetofficial + "',"
                    + Postalrange.FIELD_RANGE_STREET_PROVIDER + "='" + streetprovider + "'"
                    + " WHERE " + Postalrange.FIELD_RANGE_POSTALCODENUMERIC + "=" + range.getPostcodenumeriek()
                    + " AND " + Postalrange.FIELD_RANGE_POSTALCODEALPHA + "='" + range.getPostcodealpha() + "'"
                    + " AND " + Postalrange.FIELD_RANGE_HOUSENO_FROM + "=" + range.getHuisnummervan();
            statement.executeUpdate(sql);

            statement.close();
            return true;
        } catch (SQLException sqle) {
            Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.SEVERE, null, sqle);
            return false;
        } catch (Exception e) {
            Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            try {
                if (statement != null) {
                    if (!statement.isClosed()) {
                        statement.close();
                    }
                }
            } catch (SQLException sqle) {
                Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.SEVERE, null, sqle);
            }
        }
    }

    public static boolean delete(Connection connection, int postalcodenumeric, String postalcodealpha, int housenofrom) {
        String sql = "";
        Statement statement = null;
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sql = "DELETE reeks WHERE "
                    + " WHERE " + Postalrange.FIELD_RANGE_POSTALCODENUMERIC + "=" + postalcodenumeric
                    + " AND " + Postalrange.FIELD_RANGE_POSTALCODEALPHA + "='" + postalcodealpha + "'"
                    + " AND " + Postalrange.FIELD_RANGE_HOUSENO_FROM + "=" + housenofrom;
            int rowcount = statement.executeUpdate(sql);
            statement.close();
            if (rowcount == 0) {
                Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.WARNING, "Range removal failed for postalcodenumeric {0} postalcodealpha {1} housenofrom {2} (range not found)", new Object[]{postalcodenumeric, postalcodealpha, housenofrom});
            } else {
                if (rowcount != 1) {
                    Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.WARNING, "Range removal resulted in multiple deletions for postalcodenumeric {0} postalcodealpha {1} housenofrom {2}  ({3} rows got deleted)", new Object[]{postalcodenumeric, postalcodealpha, housenofrom, rowcount});
                }
            }
            if (rowcount != 1) {
                if (rowcount == 0) {
                } else {
                }
                return false;
            } else {
                return true;
            }
        } catch (SQLException sqle) {
            Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.SEVERE, null, sqle);
            return false;
        } catch (Exception e) {
            Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            try {
                if (statement != null) {
                    if (!statement.isClosed()) {
                        statement.close();
                    }
                }
            } catch (SQLException sqle) {
                Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.SEVERE, null, sqle);
            }
        }
    }

    private static Postalrange setBeanProperties(ResultSet resultset) {
        Postalrange range = new Postalrange();
        try {
            //range.setRangeid(resultset.getLong(Range.FIELD_RANGE_RANGEID));
            range.setCebuco(resultset.getString(Postalrange.FIELD_RANGE_MARKETING_CODE));
            range.setEvenoneven(resultset.getBoolean(Postalrange.FIELD_RANGE_EVEN));
            range.setGemeentecode(resultset.getString(Postalrange.FIELD_RANGE_COUNTY_CODE));
            range.setGemeentenaam(resultset.getString(Postalrange.FIELD_RANGE_COUNTY));
            range.setHuisnummertotmet(resultset.getInt(Postalrange.FIELD_RANGE_HOUSENO_TO));
            range.setHuisnummervan(resultset.getInt(Postalrange.FIELD_RANGE_HOUSENO_FROM));
            range.setPostcodealpha(resultset.getString(Postalrange.FIELD_RANGE_POSTALCODEALPHA));
            range.setPostcodenumeriek(resultset.getInt(Postalrange.FIELD_RANGE_POSTALCODENUMERIC));
            range.setProvinciecode(resultset.getString(Postalrange.FIELD_RANGE_PROVINCE_CODE));
            range.setStraatnaamextract(resultset.getString(Postalrange.FIELD_RANGE_STREET_EXTRACT));
            range.setStraatnaamnen(resultset.getString(Postalrange.FIELD_RANGE_STREET_NORMALIZED));
            range.setStraatnaamofficieel(resultset.getString(Postalrange.FIELD_RANGE_STREET_OFFICIAL));
            range.setStraatnaamtnt(resultset.getString(Postalrange.FIELD_RANGE_STREET_PROVIDER));
            range.setWoonplaatsextract(resultset.getString(Postalrange.FIELD_RANGE_CITY_EXTRACT));
            range.setWoonplaatsnen(resultset.getString(Postalrange.FIELD_RANGE_CITY_NORMALIZED));
            range.setWoonplaatstnt(resultset.getString(Postalrange.FIELD_RANGE_CITY_PROVIDER));

            return range;
        } catch (SQLException ex) {
            Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private static ResultSet setResultsetColumns(ResultSet resultset, Postalrange range) {
        try {
            //resultset.updateInt(Range.FIELD_RANGE_RANGEID, range.getRangeid());
            resultset.updateString(Postalrange.FIELD_RANGE_CITY_EXTRACT, range.getWoonplaatsextract());
            resultset.updateString(Postalrange.FIELD_RANGE_CITY_NORMALIZED, range.getWoonplaatsnen());
            resultset.updateString(Postalrange.FIELD_RANGE_CITY_PROVIDER, range.getWoonplaatstnt());
            resultset.updateString(Postalrange.FIELD_RANGE_COUNTY, range.getGemeentenaam());
            resultset.updateString(Postalrange.FIELD_RANGE_COUNTY_CODE, range.getGemeentecode());
            resultset.updateBoolean(Postalrange.FIELD_RANGE_EVEN, range.isEvenoneven());
            resultset.updateInt(Postalrange.FIELD_RANGE_HOUSENO_FROM, range.getHuisnummervan());
            resultset.updateInt(Postalrange.FIELD_RANGE_HOUSENO_TO, range.getHuisnummertotmet());
            resultset.updateString(Postalrange.FIELD_RANGE_MARKETING_CODE, range.getCebuco());
            resultset.updateString(Postalrange.FIELD_RANGE_POSTALCODEALPHA, range.getPostcodealpha());
            resultset.updateInt(Postalrange.FIELD_RANGE_POSTALCODENUMERIC, range.getPostcodenumeriek());
            resultset.updateString(Postalrange.FIELD_RANGE_PROVINCE_CODE, range.getProvinciecode());
            resultset.updateString(Postalrange.FIELD_RANGE_STREET_EXTRACT, range.getStraatnaamextract());
            resultset.updateString(Postalrange.FIELD_RANGE_STREET_NORMALIZED, range.getStraatnaamnen());
            resultset.updateString(Postalrange.FIELD_RANGE_STREET_OFFICIAL, range.getStraatnaamofficieel());
            resultset.updateString(Postalrange.FIELD_RANGE_STREET_PROVIDER, range.getStraatnaamtnt());

            return resultset;
        } catch (SQLException ex) {
            Logger.getLogger(PostalrangeSQL.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
