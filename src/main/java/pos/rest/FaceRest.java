/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pos.rest;

import com.restfb.json.JsonObject;
import com.restfb.types.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import service.ServiceFace;
import service.ServiceFace.UserFb;

/**
 * REST Web Service
 *
 * @author Izaquiel
 */
@Path("fbService")
public class FaceRest implements Serializable{

    ServiceFace sf = new ServiceFace(); 

    public FaceRest() {
    }
    
    
    @GET
    @Path(value = "/friends")
    @Produces("application/json")
    public List<UserFb> getAmigos() {
        System.out.println(sf.getAmigos());
        return sf.getAmigos();
        
    }
    
    @GET
    @Path(value = "usuario")
    public UserFb getUsuario(){
        return sf.getUsuario();
    }
    
    

}
