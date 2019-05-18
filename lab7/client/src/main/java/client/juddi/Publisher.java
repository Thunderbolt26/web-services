/*
 * Copyright 2001-2010 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package client.juddi;

import org.apache.juddi.api_v3.*;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.*;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;

import java.util.Random;

/**
 * This shows you to interact with a UDDI server by publishing a Business,
 * Service and Binding Template. It uses some fairly generic code that should be
 * mostly portable to any other UDDI client library and is therefore consider
 * "portable". URLs are set in uddi.xml
 */
public class Publisher {

    private static UDDISecurityPortType security = null;
    private static UDDIPublicationPortType publish = null;
    private PublishBuilder pb;

    private Publisher(PublishBuilder publishBuilder) {
        try {
            // create a client and read the config in the archive;
            // you can use your config file name
            if (security == null || publish == null) {
                UDDIClient uddiClient = new UDDIClient("META-INF/uddi.xml");
                // a UddiClient can be a client to multiple UDDI nodes, so
                // supply the nodeName (defined in your uddi.xml.
                // The transport can be WS, inVM, RMI etc which is defined in the uddi.xml
                Transport transport = uddiClient.getTransport("default");
                // Now you create a reference to the UDDI API
                security = transport.getUDDISecurityService();
                publish = transport.getUDDIPublishService();
            }
            this.pb = publishBuilder;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PublishBuilder builder() {
        return new PublishBuilder();
    }

    public static class PublishBuilder {
        // Login aka retrieve its authentication token
        private String userId;
        private String cred;

        // Creating the parent business entity that will contain our service.
        private String businessName;

        //Creating a service to save
        private String serviceName;

        // Add binding templates
        private String wsdl;

        public String getUserId() {
            return userId;
        }

        public PublishBuilder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public String getCred() {
            return cred;
        }

        public PublishBuilder setCred(String cred) {
            this.cred = cred;
            return this;
        }

        public String getBusinessName() {
            return businessName;
        }

        public PublishBuilder setBusinessName(String businessName) {
            this.businessName = businessName;
            return this;
        }

        public String getServiceName() {
            return serviceName;
        }

        public PublishBuilder setServiceName(String serviceName) {
            this.serviceName = serviceName;
            return this;
        }

        public String getWsdl() {
            return wsdl;
        }

        public PublishBuilder setWsdl(String wsdl) {
            this.wsdl = wsdl;
            return this;
        }

        private static boolean isNullOrEmpty(String s) {
            return s == null || s.equals("");
        }

        private void validate() throws ValidationException {
            if (isNullOrEmpty(userId)) throw new ValidationException("userId is null or empty");
            if (isNullOrEmpty(cred)) throw new ValidationException("cred is null or empty");
            if (isNullOrEmpty(businessName)) throw new ValidationException("businessName is null or empty");
            if (isNullOrEmpty(serviceName)) throw new ValidationException("serviceName is null or empty");
            if (isNullOrEmpty(wsdl)) throw new ValidationException("wsdl is null or empty");
        }

        public Publisher build() throws ValidationException{
            validate();
            return new Publisher(this);
        }

        @Override
        public String toString() {
            return "Publish Builder {" +
                    "userId='" + userId + '\'' +
                    ", cred='" + cred + '\'' +
                    ", businessName='" + businessName + '\'' +
                    ", serviceName='" + serviceName + '\'' +
                    ", wsdl='" + wsdl + '\'' +
                    '}';
        }
    }

    /**
     * This function shows you how to publish to UDDI using a fairly generic
     * mechanism that should be portable (meaning use any UDDI v3 library
     * with this code)
     */
    public static final int CODE_ERR = 1;
    public static final int CODE_OK = 0;

    public static class PublishResult {
        // Login aka retrieve its authentication token
        private String myBusKey;
        private String myServKey;
    }

    PublishResult result = new PublishResult();

    public int publish() {
        try {
            // Login aka retrieve its authentication token
            GetAuthToken getAuthTokenMyPub = new GetAuthToken();
            getAuthTokenMyPub.setUserID(pb.userId);                    //your username
            getAuthTokenMyPub.setCred(pb.cred);                          //your password
            AuthToken myPubAuthToken = security.getAuthToken(getAuthTokenMyPub);
            System.out.println(getAuthTokenMyPub.getUserID() + "'s AUTHTOKEN = " + "******* never log auth tokens!");

            // Creating the parent business entity that will contain our service.
            BusinessEntity myBusEntity = new BusinessEntity();
            Name myBusName = new Name();
            myBusName.setValue(pb.businessName);
            myBusEntity.getName().add(myBusName);

            // Adding the business entity to the "save" structure, using our publisher's authentication info and saving away.
            SaveBusiness sb = new SaveBusiness();
            sb.getBusinessEntity().add(myBusEntity);
            sb.setAuthInfo(myPubAuthToken.getAuthInfo());
            BusinessDetail bd = publish.saveBusiness(sb);
            String myBusKey = bd.getBusinessEntity().get(0).getBusinessKey();
            result.myBusKey = myBusKey;

            // Creating a service to save.  Only adding the minimum data: the parent business key retrieved from saving the business
            // above and a single name.
            BusinessService myService = new BusinessService();
            myService.setBusinessKey(myBusKey);
            Name myServName = new Name();
            myServName.setValue(pb.serviceName);
            myService.getName().add(myServName);

            // Add binding templates, etc...
            BindingTemplate myBindingTemplate = new BindingTemplate();
            AccessPoint accessPoint = new AccessPoint();
            accessPoint.setUseType(AccessPointType.WSDL_DEPLOYMENT.toString());
            accessPoint.setValue(pb.wsdl);
            myBindingTemplate.setAccessPoint(accessPoint);
            BindingTemplates myBindingTemplates = new BindingTemplates();
            //optional but recommended step, this annotations our binding with all the standard SOAP tModel instance infos
            myBindingTemplate = UDDIClient.addSOAPtModels(myBindingTemplate);
            myBindingTemplates.getBindingTemplate().add(myBindingTemplate);

            myService.setBindingTemplates(myBindingTemplates);

            // Adding the service to the "save" structure, using our publisher's authentication info and saving away.
            SaveService ss = new SaveService();
            ss.getBusinessService().add(myService);
            ss.setAuthInfo(myPubAuthToken.getAuthInfo());
            ServiceDetail sd = publish.saveService(ss);
            result.myServKey = sd.getBusinessService().get(0).getServiceKey();

            security.discardAuthToken(new DiscardAuthToken(myPubAuthToken.getAuthInfo()));
            // Now you have published a business and service via
            // the jUDDI API!
            return CODE_OK;

        } catch (Exception e) {
            e.printStackTrace();
            return CODE_ERR;
        }
    }

    public static void main(String args[]) {
        Publisher.PublishBuilder builder = Publisher.builder();
        int id = (new Random()).nextInt();
        Publisher sp = null;
        try {
            sp = builder
                    .setUserId("bob")
                    .setCred("bob")
                    .setBusinessName("Business " + id)
                    .setServiceName("Service " + id)
                    .setWsdl("http://example.org/services/myservice?wsdl")
                    .build();
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
            return;
        }
        sp.publish();
    }
}
