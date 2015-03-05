/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package faxada;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.json.JsonObject;
import com.restfb.json.JsonStringer;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import com.restfb.types.User;
import entidades.Tarefa;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Joew
 */
public class FacebookManager {

    private String acessToken;
    private Version v;
    
    public FacebookManager(String acessToken, Version v) {
        this.acessToken = acessToken;
        v = v;
    }


    //retorna o usuário do facebook
    public User getUser() {
        FacebookClient facebookClient = new DefaultFacebookClient(this.acessToken,v);
        return facebookClient.fetchObject("me", User.class);
    }

    //retorna a lista de amigos do usuário
    public List<User> getAmigos() {
        FacebookClient facebookClient = new DefaultFacebookClient(this.acessToken,v);
        Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
        return myFriends.getData();
    }

    //retorna uma lista com o nome de todos os amigos
    public List<String> getNomesAmigos() {
        FacebookClient facebookClient = new DefaultFacebookClient(this.acessToken,v);
        Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
        List<User> amigos = myFriends.getData();
        List<String> nomesAmigos = new ArrayList<String>();
        for (User user : amigos) {
            nomesAmigos.add(user.getName());
        }
        return nomesAmigos;
    }

    public List<User> getListaAmigos() {
        FacebookClient facebookClient = new DefaultFacebookClient(this.acessToken,v);
        List<User> friends = new ArrayList<User>();
        Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);

        for (List<User> myFriendsList : myFriends) {
            for (User u : myFriendsList) {
                friends.add(u);
            }
        }
        return friends;
    }
    
    public String publishTask(Tarefa tarefa) {
        FacebookClient facebookClient = new DefaultFacebookClient(this.acessToken,v);
        String image = "http://www.ifpb.edu.br/arquivos/imagens/icones-mapas/logo-ifpb.png";
        
        String task = new JsonStringer().object().key("og:title").value(tarefa.getId())
                .key("og:description").value(tarefa.getDescricao())
                .key("og:image").value(image).key("og:url")
                .value(String.valueOf(tarefa.getId())).endObject().toString();

        FacebookType publish = facebookClient.publish(
                "me/projetofbpos:servico", Post.class,
                Parameter.with("pos", task),
                Parameter.with("message", "Status: " + tarefa.getStatus()
                        + " - Criador: " + this.getUser().getName()
                        + " - Responsável: @[" + tarefa.getIdAmigo() + "]"));

        return publish.getId();
    }

    //verifica se o usuário pode se conectar atravéz do token informado
    public boolean IsTokenValido() {
        try {
            FacebookClient facebookClient = new DefaultFacebookClient(this.acessToken);
            return true;
        } catch (FacebookOAuthException e) {
            return false;
        }
    }

}
