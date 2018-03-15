package com.hudlow.familyfinder.client;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ServerCall {

    protected String GET_LOCATION_URL = "/get";
    protected String ADD_FRIEND_URL = "/add";
    protected String REMOVE_FRIEND_URL = "/remove";

    protected ObjectMapper mapper = new ObjectMapper();

    HttpHandler httpHandler = new HttpHandler();

}
