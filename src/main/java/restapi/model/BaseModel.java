package restapi.model;

import restapi.common.GeneralEnumerationDefinitions;

public class BaseModel {

    public BaseModel() {
    }

    String responseCode = GeneralEnumerationDefinitions.RestApiResponse.SUCCESS.toString();

    String responseMessage = GeneralEnumerationDefinitions.RestApiResponse.SUCCESS.toString();

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
