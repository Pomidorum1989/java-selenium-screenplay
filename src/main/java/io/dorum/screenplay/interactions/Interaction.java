package io.dorum.screenplay.interactions;

import io.dorum.screenplay.Actor;

public interface Interaction<T> {
    void performAs(Actor actor);
}
