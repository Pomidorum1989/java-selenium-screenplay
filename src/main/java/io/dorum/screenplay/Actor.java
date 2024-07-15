package io.dorum.screenplay;

import io.dorum.screenplay.abilities.Ability;
import io.dorum.screenplay.questions.Question;
import io.dorum.screenplay.tasks.Task;
import lombok.Getter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Actor {
    @Getter
    private final String name;
    private final Map<Class<? extends Ability>, Ability> abilities = new ConcurrentHashMap<>();

    public Actor(String name) {
        this.name = name;
    }

    public <T extends Ability> void can(T ability) {
        abilities.put(ability.getClass(), ability);
    }

    @SuppressWarnings("unchecked")
    public <T extends Ability> T abilityTo(Class<T> abilityClass) {
        return (T) abilities.get(abilityClass);
    }

    public <T> Actor attemptsTo(Task<T> task) {
        task.performAs(this);
        return this;
    }

    public <T> T asksFor(Question<T> question) {
        return question.answeredBy(this);
    }
}
