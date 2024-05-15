package web;

import static spark.Spark.*;

/**
 * Hello world!
 *
 */
public class Principal {
	
    public static void main( String[] args ){
    	get("/", Views.paginaInicial);
        post("/consulta", Views.consulta);
		}
}