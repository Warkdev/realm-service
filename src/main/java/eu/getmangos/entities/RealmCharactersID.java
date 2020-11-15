package eu.getmangos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data @AllArgsConstructor @NoArgsConstructor
public class RealmCharactersID implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "realmid")
    private int realmID;

    @Column(name = "acctid")
    private int accountID;
}
