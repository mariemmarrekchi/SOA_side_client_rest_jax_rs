package ws.rest.client;

import com.google.gson.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import services.Equipe;
import services.Jour;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class java_client_jour{
        public static void main( String[] args )
        {
            System.out.println("Jax_Rc_Client Journné....");
            // Objet de configuration
            ClientConfig config = new DefaultClientConfig();
            //objet client
            Client client = Client.create(config);
            //créer l'uri
            URI uri = UriBuilder.fromUri("http://localhost:9999/webAppREST/rest/jour/").build();
            //obtenir une resource correspondante à l'uri du service web
            WebResource service = client.resource(uri);
            //Requête GET
            System.out.println("***********************************************" );
            System.out.println( "Méthode GET - Afficher toutes les jour...." );
            //référencer la méthode "getAll"
            WebResource resource1= service.path("getAll");
            //passer la méthode "get"
            String reponse1= resource1.accept(MediaType.APPLICATION_JSON).get(String.class);
//Afficher la réponse textuelle
            System.out.println(reponse1);


            System.out.println("***********************************************" );


            System.out.println( "Méthode GET - Afficher toutes les jour...." );
            //référencer la méthode "getAll"
            resource1= service.path("getAll");
            //passer la méthode "get"
            reponse1= resource1.accept(MediaType.APPLICATION_JSON).get(String.class);
            System.out.println(reponse1);

            System.out.println("***********************************************" );

            System.out.println( "Méthode POST - Ajouter   jour...." );
            WebResource resource2= service.path("ajout");
            String reponse2= resource2.post(String.class,new services.Jour(6,"25/10/99",1,1,0,2,"2",2,0,0));
            // Afficher la réponse textuelle
            System.out.println(reponse2) ;
            System.out.println("***********************************************" );

            System.out.println( "Méthode PUT -update joueur ...." );
            WebResource resource5= service.path("update");
            String reponse5= resource5.put(String.class, new services.Jour(6,"25/10/2020",1,1,2,1,"2",2,0,1) );
            System.out.println(reponse5);
            System.out.println("***********************************************" );

            Gson gson = new GsonBuilder().create();
            if (!reponse1.equals("null")) //
            {
                JsonObject jo = new JsonParser().parse(reponse1).getAsJsonObject();
                if (jo.get("jour").isJsonArray()) //
                {
                    JsonArray jsonArray = jo.getAsJsonArray("jour");
                    Jour[] listej = gson.fromJson(jsonArray, Jour[].class);
                    System.out.println("Liste des journée (API gson)....");
                    for (Jour j : listej) {
                        System.out.println(j);
                    }
                }
                else
                { //
                    JsonObject jsonObject = jo.getAsJsonObject("jour");
                    Jour jour = gson.fromJson(jsonObject, Jour.class);
                    System.out.println("Une seule journée (API gson)....");
                    System.out.println(jour);
                }
            }
            else
            {
                System.out.println("Aucune Journéé n'est trouvée..");
            }

        }

}
