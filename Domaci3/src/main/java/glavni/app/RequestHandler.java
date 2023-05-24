package glavni.app;

import glavni.app.controller.QuotesController;
import glavni.http.HttpMethod;
import glavni.http.Request;
import glavni.http.response.Response;

public class RequestHandler {
    //ako je get poziva doget iz kontrolera  isto i za dopost
    public Response handle(Request request){
        if (request.getPath().equals("/quotes") && request.getMethod().equals(HttpMethod.GET)) {
            return (new QuotesController(request)).doGET();
        } else if (request.getPath().equals("/save-quote") && request.getMethod().equals(HttpMethod.POST)) {
            return new QuotesController(request).doPOST();
        }
        return null;
    }
}
