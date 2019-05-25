package com.example.sliit.spdc.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "session")
public class Session {

    @Id
    private String uid;
    private String authKeyOfUid;
    private String role;

    public Session() { }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAuthKeyOfUid() {
        return authKeyOfUid;
    }

    public void setAuthKeyOfUid(String authKeyOfUid) {
        this.authKeyOfUid = authKeyOfUid;
    }

    public String getRole() {
        return role;
    }

    // registering.
    public void setRole(String role) {
        this.role = role;
    }
}
