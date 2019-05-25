package com.example.sliit.spdc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sliit.spdc.entities.Session;

@Service("sessionService")
@Transactional
public interface SessionService {

	 Session getSessionOf(String uid);     // returns both authentication key and the user id as an Object.

	 List<Session> getAdminSessions();    // returns sessions that has an Admin role.

	 List<Session> getUserSessions();     // returns sessions that has user role.

	 String getUidOfSession(String authKey); // returns the user id of the user to whom the authentication key belongs.

	 String getRoleOfAuthentication(String authKey);

	 boolean authenticate(String autheKey);// simply checks if the authentication key exists in the database :-)

	 void saveSession(Session session);

	 void invokeSession(Session session);

	 void invokeSessionOfUser(String uid); // delete the session entry that belongs to the user id.

	 void invokeSessionOfKey(String authKey);// delete the session entry that contains the given auth key.
	 
}
