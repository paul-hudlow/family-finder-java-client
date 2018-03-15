package com.hudlow.familyfinder.client;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

public class HttpHandler {

    protected final String BASE_URL = "https://family-finder-server.appspot.com";

    protected static final int READ_TIMEOUT = 3000;
    protected static final int CONNECT_TIMEOUT = 3000;
    protected static final int MAX_MESSAGE_SIZE = 1000;

    /***
     * Blocking methods that makes an HTTP request to the server and returns the response.
     * @param messageBody Contents of the request in JSON format.
     * @return Contents of the response from the server.
     */
    public String makeRequest(String messageBody, String methodUrl) throws CommunicationException {
        OutputStream requestStream = null;
        InputStream responseStream = null;
        HttpsURLConnection connection = null;
        try {
            URL endpoint = new URL(BASE_URL + methodUrl);

            connection = (HttpsURLConnection) endpoint.openConnection();
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECT_TIMEOUT);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Length", Integer.toString(messageBody.getBytes().length));
            connection.connect();

            requestStream = connection.getOutputStream();
            requestStream.write(messageBody.getBytes());

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                throw new CommunicationException(responseCode);
            }

            responseStream = connection.getInputStream();
            String response = readStream(responseStream, MAX_MESSAGE_SIZE);

            return response;
        } catch (IOException ex) {
            throw new CommunicationException(ex);
        } catch (CommunicationException ex) {
            throw ex;
        } finally {
            if (requestStream != null) {
                try {
                    requestStream.close();
                } catch (IOException e) {
                    // Eat secondary exception.
                }
            }
            if (responseStream != null) {
                try {
                    responseStream.close();
                } catch (IOException e) {
                    // Eat secondary exception.
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * Converts the contents of an InputStream to a String.
     */
    public String readStream(InputStream stream, int maxReadSize) throws IOException {
        Reader reader = new InputStreamReader(stream, "UTF-8");
        char[] rawBuffer = new char[maxReadSize];
        int totalRead = 0;
        StringBuffer buffer = new StringBuffer();
        while (totalRead < maxReadSize) {
            int readChars = reader.read(rawBuffer, 0, maxReadSize);
            if (readChars == -1) {
                break; // End of stream.
            }
            totalRead += readChars;
            buffer.append(rawBuffer);
        }
        return buffer.toString();
    }
}
