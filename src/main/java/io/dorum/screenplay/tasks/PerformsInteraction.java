package io.dorum.screenplay.tasks;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.interactions.Interaction;

public class PerformsInteraction<T> implements Task<T> {
    private Interaction<T> interaction;

    public PerformsInteraction(Interaction<T> interaction) {
        this.interaction = interaction;
    }

    @Override
    public void performAs(Actor actor) {
        interaction.performAs(actor);
    }

    public static <T> PerformsInteraction<T> interaction(Interaction<T> interaction) {
        return new PerformsInteraction<>(interaction);
    }
}
