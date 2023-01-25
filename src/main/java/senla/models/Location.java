package senla.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Location {
    private final Song song;
    @Setter
    private String path;
}
