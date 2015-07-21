package com.rxjavaexamples.models;

import com.google.gson.Gson;

/**
 * Created by chetan on 15/05/15.
 */

public class LoggedInUser  {


    private int ClubId;

    private int CustId;

    private int DistrictId;

    private int IsTeamLeader;

    private String LoginID;

    private String Name;

    private String Password;

    private String RefNo;

    private int StateId;

    private int TeamId;

    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
   /* {
        "ClubId":10, "CustId":1, "DistrictId":25, "IsTeamLeader":1, "LoginID":"namrata", "Name":
        "NAMRATA ENTERPRISES", "Password":"123456", "RefNo":"NAEN0001", "StateId":27, "TeamId":1
    }*/


    public String getString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /**
     * @return The ClubId
     */
    public int getClubId() {
        return ClubId;
    }

    /**
     * @param ClubId The ClubId
     */
    public void setClubId(int ClubId) {
        this.ClubId = ClubId;
    }

    public LoggedInUser withClubId(int ClubId) {
        this.ClubId = ClubId;
        return this;
    }

    /**
     * @return The CustId
     */
    public int getCustId() {
        return CustId;
    }

    /**
     * @param CustId The CustId
     */
    public void setCustId(int CustId) {
        this.CustId = CustId;
    }

    public LoggedInUser withCustId(int CustId) {
        this.CustId = CustId;
        return this;
    }

    /**
     * @return The DistrictId
     */
    public int getDistrictId() {
        return DistrictId;
    }

    /**
     * @param DistrictId The DistrictId
     */
    public void setDistrictId(int DistrictId) {
        this.DistrictId = DistrictId;
    }

    public LoggedInUser withDistrictId(int DistrictId) {
        this.DistrictId = DistrictId;
        return this;
    }

    /**
     * @return The IsTeamLeader
     */
    public int getIsTeamLeader() {
        return IsTeamLeader;
    }

    /**
     * @param IsTeamLeader The IsTeamLeader
     */
    public void setIsTeamLeader(int IsTeamLeader) {
        this.IsTeamLeader = IsTeamLeader;
    }

    public LoggedInUser withIsTeamLeader(int IsTeamLeader) {
        this.IsTeamLeader = IsTeamLeader;
        return this;
    }

    /**
     * @return The LoginID
     */
    public String getLoginID() {
        return LoginID;
    }

    /**
     * @param LoginID The LoginID
     */
    public void setLoginID(String LoginID) {
        this.LoginID = LoginID;
    }

    public LoggedInUser withLoginID(String LoginID) {
        this.LoginID = LoginID;
        return this;
    }

    /**
     * @return The Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name The Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    public LoggedInUser withName(String Name) {
        this.Name = Name;
        return this;
    }

    /**
     * @return The Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * @param Password The Password
     */
    public void setPassword(String Password) {
        this.Password = Password;
    }

    public LoggedInUser withPassword(String Password) {
        this.Password = Password;
        return this;
    }

    /**
     * @return The RefNo
     */
    public String getRefNo() {
        return RefNo;
    }

    /**
     * @param RefNo The RefNo
     */
    public void setRefNo(String RefNo) {
        this.RefNo = RefNo;
    }

    public LoggedInUser withRefNo(String RefNo) {
        this.RefNo = RefNo;
        return this;
    }

    /**
     * @return The StateId
     */
    public int getStateId() {
        return StateId;
    }

    /**
     * @param StateId The StateId
     */
    public void setStateId(int StateId) {
        this.StateId = StateId;
    }

    public LoggedInUser withStateId(int StateId) {
        this.StateId = StateId;
        return this;
    }

    /**
     * @return The TeamId
     */
    public int getTeamId() {
        return TeamId;
    }

    /**
     * @param TeamId The TeamId
     */
    public void setTeamId(int TeamId) {
        this.TeamId = TeamId;
    }

    public LoggedInUser withTeamId(int TeamId) {
        this.TeamId = TeamId;
        return this;
    }
}