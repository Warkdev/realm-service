package eu.getmangos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data @AllArgsConstructor @NoArgsConstructor
public class UptimeId implements Serializable {
    private static final long serialVersionUID = 1L;

    private int realmId;

    @Column(name = "starttime")
    private long started;
}
