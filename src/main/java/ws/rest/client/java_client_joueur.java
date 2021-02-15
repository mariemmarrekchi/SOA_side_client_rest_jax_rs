package ws.rest.client;

import com.google.gson.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import services.Equipe;
import services.Joueur;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class java_client_joueur {
    public static void main( String[] args )
    {
        System.out.println("Jax_Rc_Client Joueur....");
        // Objet de configuration
        ClientConfig config = new DefaultClientConfig();
        //objet client
        Client client = Client.create(config);
        //créer l'uri
        URI uri = UriBuilder.fromUri("http://localhost:9999/webAppREST/rest/joueur").build();
        //obtenir une resource correspondante à l'uri du service web
        WebResource service = client.resource(uri);
        //Requête GET
        System.out.println("***********************************************" );
        System.out.println( "Méthode GET - Afficher toutes les joueur...." );
        //référencer la méthode "getAll"
        WebResource resource1= service.path("getAll");
        //passer la méthode "get"
        String reponse1= resource1.accept(MediaType.APPLICATION_JSON).get(String.class);
//Afficher la réponse textuelle
        System.out.println(reponse1);


        System.out.println("***********************************************" );


        System.out.println( "Méthode GET - Afficher toutes les Joueur...." );
        //référencer la méthode "getAll"
        resource1= service.path("getAll");
        //passer la méthode "get"
        reponse1= resource1.accept(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(reponse1);

        System.out.println("***********************************************" );

        System.out.println( "Méthode POST - Ajouter   joueur...." );
        WebResource resource2= service.path("ajout");
        String reponse2= resource2.post(String.class,new services.Joueur(6,"hammi",1,"prenom",25));
        // Afficher la réponse textuelle
        System.out.println(reponse2) ;
        System.out.println("***********************************************" );

        System.out.println( "Méthode PUT -update joueur ...." );
        WebResource resource5= service.path("up");
        String reponse5= resource5.put(String.class, new services.Joueur(12,"sessi",1,"prenom",25) );
        System.out.println(reponse5);
        System.out.println("***********************************************" );


        System.out.println( "Méthode delete - suuprimer  Joueur...." );
        WebResource resource7= service.path("delete");
        String reponse7= resource7.delete(String.class,new services.Joueur(11,"hammi",1,"prenom",25));
        System.out.println(reponse7);
        System.out.println("***********************************************" );


        Gson gson = new GsonBuilder().create();
        if (!reponse1.equals("null")) //
        {
            JsonObject jo = new JsonParser().parse(reponse1).getAsJsonObject();
            if (jo.get("joueur").isJsonArray()) //
            {
                JsonArray jsonArray = jo.getAsJsonArray("joueur");
                Joueur[] listej = gson.fromJson(jsonArray, Joueur[].class);
                System.out.println("Liste des joueur (API gson)....");
                for (Joueur p : listej) {
                    System.out.println(p);
                }
            }
            else
            {
                JsonObject jsonObject = jo.getAsJsonObject("joueur");
                Joueur j = gson.fromJson(jsonObject, Joueur.class);
                System.out.println("Un seul joueur (API gson)....");
                System.out.println(j);
            }
        }
        else
        {
            System.out.println("Aucune joueur n'est trouvée..");
        }

    }
}
