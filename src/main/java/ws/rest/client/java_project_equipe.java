package ws.rest.client;

import com.google.gson.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import services.Equipe;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class java_project_equipe {
    public static void main( String[] args )
    {
        System.out.println("Jax_Rc_Client Equipe....");
        // Objet de configuration
        ClientConfig config = new DefaultClientConfig();
        //objet client
        Client client = Client.create(config);
        //créer l'uri
        URI uri = UriBuilder.fromUri("http://localhost:9999/webAppREST/rest/equipe").build();
        //obtenir une resource correspondante à l'uri du service web
        WebResource service = client.resource(uri);
        //Requête GET
        System.out.println("***********************************************" );
        System.out.println( "Méthode GET - Afficher toutes les equipes...." );
        //référencer la méthode "getAll"
        WebResource resource1= service.path("getAll");
        //passer la méthode "get"
        String reponse1= resource1.accept(MediaType.APPLICATION_JSON).get(String.class);
//Afficher la réponse textuelle
        System.out.println(reponse1);
        System.out.println("***********************************************" );
        System.out.println( "Méthode POST - Ajouter   equipe...." );
        WebResource resource2= service.path("ajout");
        String reponse2= resource2.post(String.class,new services.Equipe(3,"jerba"));

        // Afficher la réponse textuelle
        System.out.println(reponse2) ;

        System.out.println("***********************************************" );


        System.out.println( "Méthode GET - Afficher toutes les equipes...." );
        //référencer la méthode "getAll"
        resource1= service.path("getAll");
        //passer la méthode "get"
        reponse1= resource1.accept(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(reponse1);

        System.out.println("***********************************************" );


        Gson gson = new GsonBuilder().create();
        if (!reponse1.equals("null")) // s'il existe au moins un objet ""
        {
            JsonObject jo = new JsonParser().parse(reponse1).getAsJsonObject();
            if (jo.get("equipe").isJsonArray()) // en cas de plusieurs
            {
                JsonArray jsonArray = jo.getAsJsonArray("equipe");
                Equipe[] listeEq = gson.fromJson(jsonArray, Equipe[].class);
                System.out.println("Liste des Equipe (API gson)....");
                for (Equipe p : listeEq) {
                    System.out.println(p);
                }
            }
            else
            { // en cas d'un seul objet "Personne"
                JsonObject jsonObject = jo.getAsJsonObject("equipe");
                Equipe eq = gson.fromJson(jsonObject, Equipe.class);
                System.out.println("Une seule equipe (API gson)....");
                System.out.println(eq);
            }
        }
        else
        {
            System.out.println("Aucune equipe n'est trouvée..");
        }

    }
}
