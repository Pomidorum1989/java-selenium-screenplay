package io.dorum.screenplay;

import io.dorum.screenplay.abilities.Ability;
import io.dorum.screenplay.interactions.Interaction;
import io.dorum.screenplay.questions.Question;
import io.dorum.screenplay.tasks.Task;
import lombok.Getter;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Actor {
    @Getter
    private final String name;
    private final Map<Class<? extends Ability>, Ability> abilities = new ConcurrentHashMap<>();

    private static final ThreadLocal<Actor> ACTOR_THREAD_LOCAL = new ThreadLocal<>();

    private Actor(String name) {
        this.name = name;
    }

    public static void createActor(String name) {
        Actor actor = new Actor(name);
        ACTOR_THREAD_LOCAL.set(actor);
    }

    public static Actor getActor() {
        return ACTOR_THREAD_LOCAL.get();
    }

    public <T extends Ability> void can(T ability) {
        abilities.put(ability.getClass(), ability);
    }

    @SuppressWarnings("unchecked")
    public <T extends Ability> T abilityTo(Class<T> abilityClass) {
        return (T) abilities.get(abilityClass);
    }

    public <T> Actor interactsWith(Interaction<T> interaction) {
        interaction.performAs(this);
        return this;
    }

    public <T> Actor attemptsTo(Task<T> task) {
        task.performAs(this);
        return this;
    }

    public <T> T asksFor(Question<T> question) {
        return question.answeredBy(this);
    }

    public static void removeActor() {
        if (Objects.nonNull(ACTOR_THREAD_LOCAL.get())) {
            ACTOR_THREAD_LOCAL.remove();
        }
    }
}