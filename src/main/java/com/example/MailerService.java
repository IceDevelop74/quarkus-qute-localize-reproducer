package com.example;

import com.example.messages.AppMessages;
import io.quarkus.mailer.MailTemplate;
import io.quarkus.qute.CheckedTemplate;
import io.smallrye.mutiny.unchecked.Unchecked;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.Duration;

@ApplicationScoped
public class MailerService {
    String replyRecipient = "guillaume.gorret.developer@gmail.com";
    String appName = "Custom App Name";

    @Inject
    AppMessages appMessages;

    public void sendContactMail() throws RuntimeException {
        var subject = appMessages.helloGuys();
        Templates.contact()
                .to(replyRecipient)
                .replyTo("some@email.com")
                .subject("[%s] %s".formatted(appName, subject))
                .send()
                .ifNoItem().after(Duration.ofSeconds(10)).fail()
                .subscribe()
                .with(
                        ok -> System.out.println("OK"),
                        Unchecked.consumer(error -> {
                            System.out.println("KO");
                            throw new RuntimeException("Mail not sent");
                        })
                );
    }

    @CheckedTemplate
    public static class Templates {
        public static native MailTemplate.MailTemplateInstance contact();
    }
}
