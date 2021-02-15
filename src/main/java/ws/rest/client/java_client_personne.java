package ws.rest.client;

import com.google.gson.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import services.Personne;


import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class java_client_personne {
    public static void main( String[] args )
    {
        System.out.println("Jax_Rc_Client peronne....");
        // Objet de configuration
        ClientConfig config = new DefaultClientConfig();
        //objet client
        Client client = Client.create(config);
        //créer l'uri
        URI uri = UriBuilder.fromUri("http://localhost:9999/webAppREST/rest/personne/").build();
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







// Récupérer des objets "Personne" en utilisant l'API gson de Google
     /*   Gson gson = new GsonBuilder().create();
        JsonObject jo = new JsonParser().parse(reponse1).getAsJsonObject();
        //System.out.println(jo.toString());
        JsonArray jsonArray =  jo.getAsJsonArray("person");
        Personne[] listePers = gson.fromJson(jsonArray,Personne[].class);
        System.out.println( "Liste des Personnes (API gson)....");


        for (Personne p: listePers)
        {
            System.out.println(p.toString());
        }*/


        Gson gson = new GsonBuilder().create();
        if (!reponse1.equals("null")) // s'il existe au moins un objet "Personne"
        {
            JsonObject jo = new JsonParser().parse(reponse1).getAsJsonObject();
            if (jo.get("person").isJsonArray()) // en cas de plusieurs personnes
            {
                JsonArray jsonArray = jo.getAsJsonArray("person");
                Personne[] lister = gson.fromJson(jsonArray, Personne[].class);
                System.out.println("Liste des personne (API gson)....");
                for (Personne p : lister) {
                    System.out.println(p);
                }
            }
            else
            { // en cas d'un seul objet "Personne"
                JsonObject jsonObject = jo.getAsJsonObject("person");
                Personne personne = gson.fromJson(jsonObject, Personne.class);
                System.out.println("Une seule personne (API gson)....");
                System.out.println(personne);
            }
        }
        else
        {
            System.out.println("Aucune personne n'est trouvée..");
        }

    }
}
