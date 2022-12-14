package uk.co.eandrews.util;

import lombok.Builder;
import lombok.With;

@With
@Builder(toBuilder = true)
public record Vector2(long x, long y) {

    public static final Vector2 ZERO = new Vector2(0, 0);

    public Vector2 up(final long step) {
        return toBuilder().x(x).y(y + step).build();
    }

    public Vector2 down(final long step) {
        return toBuilder().x(x).y(y - step).build();
    }

    public Vector2 left(final long step) {
        return toBuilder().x(x - step).y(y).build();
    }

    public Vector2 right(final long step) {
        return toBuilder().x(x + step).y(y).build();
    }
}
