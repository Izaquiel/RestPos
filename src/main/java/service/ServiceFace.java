package service;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.Facebook;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.json.JsonObject;
import com.restfb.json.JsonStringer;
import com.restfb.types.FacebookType;
import com.restfb.types.Post;
import com.restfb.types.User;
import entidade.Tarefa;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import pos.rest.TokenFB;

/**
 *
 * @author Izaquiel
 */
public class ServiceFace implements Serializable {

    private final FacebookClient facebookClient;
    private TokenFB token = new TokenFB();

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
        String queryAmigos = "SELECT uid, name FROM user WHERE uid IN (SELECT target_id FROM connection WHERE source_id = " + user.getId() + " )";

        List<UserFb> amigos = facebookClient.executeQuery(queryAmigos, UserFb.class);

        return amigos;
    }

    public String publishTask(Tarefa tarefa) {
        String image = "http://www.ifpb.edu.br/arquivos/imagens/icones-mapas/logo-ifpb.png";

        String task = new JsonStringer().object().key("og:title").value(tarefa.getNome())
                .key("og:description").value(tarefa.getDescricao())
                .key("og:image").value(image).key("og:url")
                .value(getPath() + String.valueOf(tarefa.getId())).endObject().toString();

        FacebookType publish = facebookClient.publish(
                "me/projetopos:servico", Post.class,
                Parameter.with("pos", task),
                Parameter.with("message", "Status: " + tarefa.getStatus()
                        + " - Criador: " + this.getUsuario().getName()
                        + " - Responsável: @[" + tarefa.getIdResponsavel() + "]"));

        return publish.getId();
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
