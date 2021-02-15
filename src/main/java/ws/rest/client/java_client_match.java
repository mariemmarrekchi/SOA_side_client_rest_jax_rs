package ws.rest.client;

import com.google.gson.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import services.Equipe;
import services.Joueur;
import services.Match;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class java_client_match {

    public static void main( String[] args )
    {
        System.out.println("Jax_Rc_Client Match....");
        // Objet de configuration
        ClientConfig config = new DefaultClientConfig();
        //objet client
        Client client = Client.create(config);
        //créer l'uri
        URI uri = UriBuilder.fromUri("http://localhost:9999/webAppREST/rest/match").build();
        //obtenir une resource correspondante à l'uri du service web
        WebResource service = client.resource(uri);
        //Requête GET
        System.out.println("***********************************************" );
        System.out.println( "Méthode GET - Afficher toutes les match...." );
        //référencer la méthode "getAll"
        WebResource resource1= service.path("getAll");
        //passer la méthode "get"
        String reponse1= resource1.accept(MediaType.APPLICATION_JSON).get(String.class);
//Afficher la réponse textuelle
        System.out.println(reponse1);


        System.out.println("***********************************************" );


        System.out.println( "Méthode GET - Afficher toutes les match...." );
        //référencer la méthode "getAll"
        resource1= service.path("getAll");
        //passer la méthode "get"
        reponse1= resource1.accept(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(reponse1);

        System.out.println("***********************************************" );

        System.out.println( "Méthode POST - Ajouter   joueur...." );
        WebResource resource2= service.path("ajout");
        String reponse2= resource2.post(String.class,new services.Match(3,"asia","31/12/2020",2));
        // Afficher la réponse textuelle
        System.out.println(reponse2) ;
        System.out.println("***********************************************" );

        System.out.println( "Méthode PUT -update match ...." );
        WebResource resource5= service.path("up");
        String reponse5= resource5.put(String.class, new services.Match(1,"afrique","25/10/2020",1) );
        System.out.println(reponse5);
        System.out.println("***********************************************" );

        Gson gson = new GsonBuilder().create();
        if (!reponse1.equals("null")) // s'il existe au moins un objet ""
        {
            JsonObject jo = new JsonParser().parse(reponse1).getAsJsonObject();
            if (jo.get("mat").isJsonArray()) //
            {
                JsonArray jsonArray = jo.getAsJsonArray("mat");
                Match[] listem = gson.fromJson(jsonArray, Match[].class);
                System.out.println("Liste des mat (API gson)....");
                for (Match m : listem) {
                    System.out.println(m);
                }
            }
            else
            {
                JsonObject jsonObject = jo.getAsJsonObject("mat");
                Match mat = gson.fromJson(jsonObject, Match.class);
                System.out.println("Un seul match (API gson)....");
                System.out.println(mat);
            }
        }
        else
        {
            System.out.println("Aucune match n'est trouvée..");
        }

    }
}
