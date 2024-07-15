package io.dorum.screenplay.tasks;

import io.dorum.screenplay.Actor;

public interface Task<T> {
    void performAs(Actor actor);
}
