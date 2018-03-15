package com.hudlow.familyfinder.client;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class AddFriendCall extends ServerCall {

    public void addFriend(String myUserId, String friendUserId) throws CommunicationException {
        ObjectNode requestJson = mapper.createObjectNode();
        requestJson.put("myUserId", myUserId);
        requestJson.put("friendUserId", friendUserId);

        httpHandler.makeRequest(requestJson.toString(), ADD_FRIEND_URL);
    }
}
