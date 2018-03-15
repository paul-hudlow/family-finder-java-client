package com.hudlow.familyfinder.client;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class GetLocationCall extends ServerCall {

    public LocationResponse getLocation(String myUserId, String friendUserId) throws CommunicationException {
        ObjectNode requestJson = mapper.createObjectNode();
        requestJson.put("myUserId", myUserId);
        requestJson.put("friendUserId", friendUserId);

        String rawResponse = httpHandler.makeRequest(requestJson.toString(), GET_LOCATION_URL);

        try {
             ObjectNode responseJson = mapper.readValue(rawResponse, ObjectNode.class);
             LocationResponse response = new LocationResponse();
             response.latitude = responseJson.get("lat").asDouble();
             response.longitude = responseJson.get("lng").asDouble();
             return response;
        } catch (IOException ex) {
            throw new CommunicationException("Could not parse server response.", ex);
        }
    }

    public class LocationResponse {
        public double latitude;
        public double longitude;
    }
}
