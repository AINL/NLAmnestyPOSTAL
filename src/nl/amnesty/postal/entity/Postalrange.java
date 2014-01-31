/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.amnesty.postal.entity;

/**
 *
 * @author evelzen
 */
public class Postalrange {

    public final static int STATUS_NEW = 1;
    public final static int STATUS_MATCHED_NONE = 2;
    public final static int STATUS_MATCHED_ID = 3;
    //
    public static final String FIELD_RANGE_RANGEID = "";
    public static final String FIELD_RANGE_POSTALCODENUMERIC = "postcodenumeriek";
    public static final String FIELD_RANGE_POSTALCODEALPHA = "postcodealpha";
    public static final String FIELD_RANGE_EVEN = "evenoneven";
    public static final String FIELD_RANGE_HOUSENO_FROM = "huisnummervan";
    public static final String FIELD_RANGE_HOUSENO_TO = "huisnummertotmet";
    public static final String FIELD_RANGE_CITY_PROVIDER = "woonplaatstnt";
    public static final String FIELD_RANGE_CITY_NORMALIZED = "woonplaatsnen";
    public static final String FIELD_RANGE_STREET_PROVIDER = "straatnaamtnt";
    public static final String FIELD_RANGE_STREET_NORMALIZED = "straatnaamnen";
    public static final String FIELD_RANGE_STREET_OFFICIAL = "straatnaamofficieel";
    public static final String FIELD_RANGE_CITY_EXTRACT = "woonplaatsextract";
    public static final String FIELD_RANGE_STREET_EXTRACT = "straatnaamextract";
    public static final String FIELD_RANGE_COUNTY_CODE = "gemeentecode";
    public static final String FIELD_RANGE_COUNTY = "gemeentenaam";
    public static final String FIELD_RANGE_PROVINCE_CODE = "provinciecode";
    public static final String FIELD_RANGE_MARKETING_CODE = "cebuco";
    
    private int rangeid;
    private int postcodenumeriek;
    private String postcodealpha;
    private boolean evenoneven;
    private int huisnummervan;
    private int huisnummertotmet;
    private String woonplaatstnt;
    private String woonplaatsnen;
    private String straatnaamtnt;
    private String straatnaamnen;
    private String straatnaamofficieel;
    private String woonplaatsextract;
    private String straatnaamextract;
    private String gemeentecode;
    private String gemeentenaam;
    private String provinciecode;
    private String cebuco;
    private int status;

    public Postalrange() {
    }

    public Postalrange(int rangeid, int postcodenumeriek, String postcodealpha, boolean evenoneven, int huisnummervan, int huisnummertotmet, String woonplaatstnt, String woonplaatsnen, String straatnaamtnt, String straatnaamnen, String straatnaamofficieel, String woonplaatsextract, String straatnaamextract, String gemeentecode, String gemeentenaam, String provinciecode, String cebuco) {
        this.rangeid = rangeid;
        this.postcodenumeriek = postcodenumeriek;
        this.postcodealpha = postcodealpha;
        this.evenoneven = evenoneven;
        this.huisnummervan = huisnummervan;
        this.huisnummertotmet = huisnummertotmet;
        this.woonplaatstnt = woonplaatstnt;
        this.woonplaatsnen = woonplaatsnen;
        this.straatnaamtnt = straatnaamtnt;
        this.straatnaamnen = straatnaamnen;
        this.straatnaamofficieel = straatnaamofficieel;
        this.woonplaatsextract = woonplaatsextract;
        this.straatnaamextract = straatnaamextract;
        this.gemeentecode = gemeentecode;
        this.gemeentenaam = gemeentenaam;
        this.provinciecode = provinciecode;
        this.cebuco = cebuco;
    }

    public String getCebuco() {
        return cebuco;
    }

    public void setCebuco(String cebuco) {
        this.cebuco = cebuco;
    }

    public boolean isEvenoneven() {
        return evenoneven;
    }

    public void setEvenoneven(boolean evenoneven) {
        this.evenoneven = evenoneven;
    }

    public String getGemeentecode() {
        return gemeentecode;
    }

    public void setGemeentecode(String gemeentecode) {
        this.gemeentecode = gemeentecode;
    }

    public String getGemeentenaam() {
        return gemeentenaam;
    }

    public void setGemeentenaam(String gemeentenaam) {
        this.gemeentenaam = gemeentenaam;
    }

    public int getHuisnummertotmet() {
        return huisnummertotmet;
    }

    public void setHuisnummertotmet(int huisnummertotmet) {
        this.huisnummertotmet = huisnummertotmet;
    }

    public int getHuisnummervan() {
        return huisnummervan;
    }

    public void setHuisnummervan(int huisnummervan) {
        this.huisnummervan = huisnummervan;
    }

    public int getRangeid() {
        return rangeid;
    }

    public void setRangeid(int rangeid) {
        this.rangeid = rangeid;
    }

    public String getPostcodealpha() {
        return postcodealpha;
    }

    public void setPostcodealpha(String postcodealpha) {
        this.postcodealpha = postcodealpha;
    }

    public int getPostcodenumeriek() {
        return postcodenumeriek;
    }

    public void setPostcodenumeriek(int postcodenumeriek) {
        this.postcodenumeriek = postcodenumeriek;
    }

    public String getProvinciecode() {
        return provinciecode;
    }

    public void setProvinciecode(String provinciecode) {
        this.provinciecode = provinciecode;
    }

    public String getStraatnaamextract() {
        return straatnaamextract;
    }

    public void setStraatnaamextract(String straatnaamextract) {
        this.straatnaamextract = straatnaamextract;
    }

    public String getStraatnaamnen() {
        return straatnaamnen;
    }

    public void setStraatnaamnen(String straatnaamnen) {
        this.straatnaamnen = straatnaamnen;
    }

    public String getStraatnaamofficieel() {
        return straatnaamofficieel;
    }

    public void setStraatnaamofficieel(String straatnaamofficieel) {
        this.straatnaamofficieel = straatnaamofficieel;
    }

    public String getStraatnaamtnt() {
        return straatnaamtnt;
    }

    public void setStraatnaamtnt(String straatnaamtnt) {
        this.straatnaamtnt = straatnaamtnt;
    }

    public String getWoonplaatsextract() {
        return woonplaatsextract;
    }

    public void setWoonplaatsextract(String woonplaatsextract) {
        this.woonplaatsextract = woonplaatsextract;
    }

    public String getWoonplaatsnen() {
        return woonplaatsnen;
    }

    public void setWoonplaatsnen(String woonplaatsnen) {
        this.woonplaatsnen = woonplaatsnen;
    }

    public String getWoonplaatstnt() {
        return woonplaatstnt;
    }

    public void setWoonplaatstnt(String woonplaatstnt) {
        this.woonplaatstnt = woonplaatstnt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
