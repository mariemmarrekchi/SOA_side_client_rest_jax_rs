package ws.rest.client;

import com.google.gson.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import services.Equipe;
import services.Joueur;
import services.Result;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class java_client_reslt {
    public static void main( String[] args )
    {
        System.out.println("Jax_Rc_Client Resultat....");
        // Objet de configuration
        ClientConfig config = new DefaultClientConfig();
        //objet client
        Client client = Client.create(config);
        //créer l'uri
        URI uri = UriBuilder.fromUri("http://localhost:9999/webAppREST/rest/result").build();
        //obtenir une resource correspondante à l'uri du service web
        WebResource service = client.resource(uri);
        //Requête GET
        System.out.println("***********************************************" );
        System.out.println( "Méthode GET - Afficher toutes les result...." );
        //référencer la méthode "getAll"
        WebResource resource1= service.path("getAll");
        //passer la méthode "get"
        String reponse1= resource1.accept(MediaType.APPLICATION_JSON).get(String.class);
//Afficher la réponse textuelle
        System.out.println(reponse1);


        System.out.println("***********************************************" );


        System.out.println( "Méthode GET - Afficher toutes les results...." );
        //référencer la méthode "getAll"
        resource1= service.path("getAll");
        //passer la méthode "get"
        reponse1= resource1.accept(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(reponse1);

        System.out.println("***********************************************" );

        Gson gson = new GsonBuilder().create();
        if (!reponse1.equals("null")) //
        {
            JsonObject jo = new JsonParser().parse(reponse1).getAsJsonObject();
            if (jo.get("reslt").isJsonArray()) //
            {
                JsonArray jsonArray = jo.getAsJsonArray("reslt");
                Result[] lister = gson.fromJson(jsonArray, Result[].class);
                System.out.println("Liste des result (API gson)....");
                for (Result p : lister) {
                    System.out.println(p);
                }
            }
            else
            { // en cas d'un seul objet "Personne"
                JsonObject jsonObject = jo.getAsJsonObject("reslt");
                Result resl = gson.fromJson(jsonObject, Result.class);
                System.out.println("Une seule result (API gson)....");
                System.out.println(resl);
            }
        }
        else
        {
            System.out.println("Aucune result n'est trouvée..");
        }

    }
}
