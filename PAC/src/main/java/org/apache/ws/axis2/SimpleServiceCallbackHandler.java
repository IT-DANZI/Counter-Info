/**
 * SimpleServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.6  Built on : Jul 30, 2017 (09:08:31 BST)
 */
package org.apache.ws.axis2;


/**
 *  SimpleServiceCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class SimpleServiceCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public SimpleServiceCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public SimpleServiceCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for getGreeting method
     * override this method for handling normal response from getGreeting operation
     */
    public void receiveResultgetGreeting(
        org.apache.ws.axis2.SimpleServiceStub.GetGreetingResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getGreeting operation
     */
    public void receiveErrorgetGreeting(Exception e) {
    }

    /**
     * auto generated Axis2 call back method for getPrice method
     * override this method for handling normal response from getPrice operation
     */
    public void receiveResultgetPrice(
        org.apache.ws.axis2.SimpleServiceStub.GetPriceResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from getPrice operation
     */
    public void receiveErrorgetPrice(Exception e) {
    }
}
