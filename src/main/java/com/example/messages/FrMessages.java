package com.example.messages;

import io.quarkus.qute.i18n.Localized;
import io.quarkus.qute.i18n.Message;

@Localized("fr")
public interface FrMessages extends AppMessages {
    @Override
    @Message("Salut les gars !")
    String helloGuys();
}
