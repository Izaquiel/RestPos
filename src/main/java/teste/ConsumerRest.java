/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teste;

import com.restfb.types.User;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import service.ServiceFace.UserFb;

/**
 *
 * @author Izaquiel
 */
public class ConsumerRest {
    
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/RestFBPos/webresources";

    public ConsumerRest() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("fb");
    }
    
    public List<UserFb> getFriends() throws ClientErrorException {
        GenericType<List<UserFb>> customerType = new GenericType<List<UserFb>>() {};
        WebTarget resource = webTarget;
        resource = resource.path("friends");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(customerType);
    }
    
    public void publishTask(Object tarefa) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("publicar");        
        resource.request(MediaType.APPLICATION_JSON).post(Entity.json(tarefa));        
    }
    
}
