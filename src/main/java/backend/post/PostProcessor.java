package backend.post;

import java.util.Objects;

public class PostProcessor {

    private static PostProcessor instance = null;

    private PostProcessor(){};

    public static PostProcessor getInstance() {
        return Objects.requireNonNullElseGet(instance, PostProcessor::new);
    }
}
