package com.danzi.pac.domain;

import mc_style.functions.soap.sap.document.sap_com.ZWEB_CALCULATEStub;

/**
 * Describe ：
 * <p>
 * Author   ：Lily
 * <p>
 * Date     ：2017/9/5.
 */
public class SapCalucate {
    private ZWEB_CALCULATEStub.Char10 ebeln;
    private ZWEB_CALCULATEStub.Char10 ebelp;
    private ZWEB_CALCULATEStub.Char10 matnr;
    private ZWEB_CALCULATEStub.Char10 datab;
    private ZWEB_CALCULATEStub.Char10 datbi;
    private ZWEB_CALCULATEStub.Char10 kbetr;
    private ZWEB_CALCULATEStub.Char10 konwa;
    private ZWEB_CALCULATEStub.Char10 kpein;
    private ZWEB_CALCULATEStub.Char10 kmein;

    public ZWEB_CALCULATEStub.Char10 getEbeln() {
        return ebeln;
    }

    public void setEbeln(ZWEB_CALCULATEStub.Char10 ebeln) {
        this.ebeln = ebeln;
    }

    public ZWEB_CALCULATEStub.Char10 getEbelp() {
        return ebelp;
    }

    public void setEbelp(ZWEB_CALCULATEStub.Char10 ebelp) {
        this.ebelp = ebelp;
    }

    public ZWEB_CALCULATEStub.Char10 getMatnr() {
        return matnr;
    }

    public void setMatnr(ZWEB_CALCULATEStub.Char10 matnr) {
        this.matnr = matnr;
    }

    public ZWEB_CALCULATEStub.Char10 getDatab() {
        return datab;
    }

    public void setDatab(ZWEB_CALCULATEStub.Char10 datab) {
        this.datab = datab;
    }

    public ZWEB_CALCULATEStub.Char10 getDatbi() {
        return datbi;
    }

    public void setDatbi(ZWEB_CALCULATEStub.Char10 datbi) {
        this.datbi = datbi;
    }

    public ZWEB_CALCULATEStub.Char10 getKbetr() {
        return kbetr;
    }

    public void setKbetr(ZWEB_CALCULATEStub.Char10 kbetr) {
        this.kbetr = kbetr;
    }

    public ZWEB_CALCULATEStub.Char10 getKonwa() {
        return konwa;
    }

    public void setKonwa(ZWEB_CALCULATEStub.Char10 konwa) {
        this.konwa = konwa;
    }

    public ZWEB_CALCULATEStub.Char10 getKpein() {
        return kpein;
    }

    public void setKpein(ZWEB_CALCULATEStub.Char10 kpein) {
        this.kpein = kpein;
    }

    public ZWEB_CALCULATEStub.Char10 getKmein() {
        return kmein;
    }

    public void setKmein(ZWEB_CALCULATEStub.Char10 kmein) {
        this.kmein = kmein;
    }

    @Override
    public String toString() {
        return "SapCalucate{" +
                "ebeln=" + ebeln +
                ", ebelp=" + ebelp +
                ", matnr=" + matnr +
                ", datab=" + datab +
                ", datbi=" + datbi +
                ", kbetr=" + kbetr +
                ", konwa=" + konwa +
                ", kpein=" + kpein +
                ", kmein=" + kmein +
                '}';
    }
}
