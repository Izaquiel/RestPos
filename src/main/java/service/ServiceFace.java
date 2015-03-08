package service;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.json.JsonStringer;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import com.restfb.types.User;
import entidade.Tarefa;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import persistencia.DaoRest;
import pos.rest.TokenFB;

/**
 *
 * @author Izaquiel
 */
@Stateless
public class ServiceFace implements Serializable {

    private final FacebookClient facebookClient;
    private TokenFB token = new TokenFB();

    @EJB
    DaoRest<Tarefa> daoT;

    public FacebookClient getFacebookClient() {
        return facebookClient;
    }

    public ServiceFace() {
        this.facebookClient = new DefaultFacebookClient(token.getToken());
    }

    public UserFb getUsuario() {
        return facebookClient.fetchObject("me", UserFb.class);
    }

    public List<UserFb> getAmigos() {
        User user = facebookClient.fetchObject("me", User.class, Parameter.with("metadata", 1));

        List<UserFb> friends = new ArrayList<>();
        Connection<UserFb> myFriends = facebookClient.fetchConnection("me/taggable_friends", UserFb.class);

        for (List<UserFb> myFriendsList : myFriends) {
            for (UserFb u : myFriendsList) {
                friends.add(u);
            }
        }

        return friends;
    }

    public String publicar(Tarefa t) {

        String task = new JsonStringer().object().key("Titulo da Tarefa:").value(t.getNome())
                .key("Descrição da Tarefa:").value(t.getDescricao())
                .key("URL:")
                .value(getPath() + String.valueOf(t.getId())).endObject().toString();

        FacebookType publishMessageResponse
                = facebookClient.publish("me/feed", FacebookType.class,
                        Parameter.with("message", "Status: " + t.getStatus()
                                + " - Criador: " + t.getIdCriador() + " Tarefa: "+task));
        System.out.println(publishMessageResponse.getId());
        return publishMessageResponse.getId();

    }

    public String logar(String token) {
        this.token.setToken(token);
        return "logado" + token;
    }

    public void salvarTarefa(Tarefa t) {
        daoT.salvar(t);
    }

    public Tarefa getTarefaPorId(Long id) {
        Map<String, Long> map = new HashMap<>();
        map.put("id", id);
        return daoT.buscar("buscarTarefaPorId", map);
    }

    private String getIp() {
        InetAddress ia = null;
        try {
            ia = InetAddress.getLocalHost();
            return ia.getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServiceFace.class.getName()).log(Level.SEVERE, null, ex);
            return "localhost";
        }
    }

    public String getPath() {
        return "http://" + this.getIp() + ":8080/RestFBPos/webresources/fbService/task";
    }

    public static class UserFb {

        @Facebook
        String uid;

        @Facebook
        String name;

        @Override
        public String toString() {
            return String.format(name);
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 53 * hash + Objects.hashCode(this.uid);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final UserFb other = (UserFb) obj;
            if (!Objects.equals(this.uid, other.uid)) {
                return false;
            }
            return true;
        }

    }

}
