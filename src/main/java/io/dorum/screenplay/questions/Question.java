package io.dorum.screenplay.questions;

import io.dorum.screenplay.Actor;

public interface Question<T> {
    T answeredBy(Actor actor);
}
