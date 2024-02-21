package subjects.java.twentyNineNov;

@FunctionalInterface
public interface Generator<T> {
    void generate(GeneratorContext<T> context);
}
