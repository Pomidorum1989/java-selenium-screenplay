package io.dorum.screenplay.interactions;

import io.dorum.screenplay.Actor;

public interface Interaction<Void> {
    void performAs(Actor actor);
}
