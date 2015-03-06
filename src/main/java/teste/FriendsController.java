/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teste;

import java.util.List;
import javax.faces.bean.ManagedBean;
import service.ServiceFace;
import service.ServiceFace.UserFb;

/**
 *
 * @author Izaquiel
 */
@ManagedBean
public class FriendsController {

    public FriendsController() {
    }
    
    public List<UserFb> getAmigos(){
        return new ConsumerRest().getFriends();
    }
    
}
