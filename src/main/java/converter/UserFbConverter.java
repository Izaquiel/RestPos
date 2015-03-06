/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import java.util.ArrayList;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import service.ServiceFace;
import service.ServiceFace.UserFb;

/**
 *
 * @author Izaquiel
 */
@FacesConverter(value = "userConverter")
public class UserFbConverter implements Converter{

    ServiceFace sf = new ServiceFace();
    List<UserFb> usuarios = new ArrayList<>();
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.equals("")) {
            return null;
        } else {         
            usuarios = sf.getAmigos();
            for (UserFb userFb : usuarios) {
                if(value.contains(userFb.toString()))
                    return userFb;
            }
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value instanceof UserFb){
            UserFb user = (UserFb) value;
            return user.toString();
        }
        return null;
    }
    
}
