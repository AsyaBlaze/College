package subjects.java.twentyNineNov;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

// Переобразование,
public class MyStream<T> {
    private final Generator<T> generator;

    private MyStream(Generator<T> generator) {
        this.generator = generator;
    }

    public static <T> MyStream<T> of(Collection<T> collection) {
        return new MyStream<>(generatorContext ->
                collection.forEach(generatorContext::emit)
        );
    }

    public void forEach(Consumer<T> consumer) {
        generator.generate(consumer::accept);
    }

    public MyStream<T> filter(Predicate<T> predicate) {
        return new MyStream<>(generatorContext ->  generator.generate(value -> {
            if (predicate.test(value)) {
                generatorContext.emit(value);
            }
        }));
    }

    public <R> MyStream<R> map(Function<T, R> function) {
        return new MyStream<>(generatorContext -> generator.generate(
                value -> generatorContext.emit(function.apply(value))
        ));
    }
}
