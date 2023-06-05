package com.example;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static java.util.Objects.requireNonNull;

@Path("/some-page")
public class SomePage {
    @Inject
    MailerService mailerService;


    @POST
    public Uni<Response> get() {
        try {
            mailerService.sendContactMail();
            return Uni.createFrom().item(Response.ok().build());
        } catch (Exception e) {
            return Uni.createFrom().item(Response.serverError().build());
        }
    }

}
