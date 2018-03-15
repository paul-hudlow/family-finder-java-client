package com.hudlow.familyfinder.client;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class RemoveFriendCall extends ServerCall {

    public void removeFriend(String myUserId, String friendUserId) throws CommunicationException {
        ObjectNode requestJson = mapper.createObjectNode();
        requestJson.put("myUserId", myUserId);
        requestJson.put("friendUserId", friendUserId);

        httpHandler.makeRequest(requestJson.toString(), REMOVE_FRIEND_URL);
    }
}
