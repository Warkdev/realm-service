package eu.getmangos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "realmcharacters")
@NamedQueries({
    @NamedQuery(name = "RealmCharacters.findAll", query = "SELECT rc FROM RealmCharacters rc"),
    @NamedQuery(name = "RealmCharacters.findById", query = "SELECT rc FROM RealmCharacters rc WHERE rc.id = :id"),
    @NamedQuery(name = "RealmCharacters.findByRealm", query = "SELECT rc FROM RealmCharacters rc WHERE rc.id.realmID = :realmID"),
    @NamedQuery(name = "RealmCharacters.findByAccount", query = "SELECT rc FROM RealmCharacters rc WHERE rc.id.accountID = :accountID"),
    @NamedQuery(name = "RealmCharacters.deleteDeadLink", query= "DELETE FROM RealmCharacters rc WHERE rc.id.realmID = :realmId AND rc.id.accountID = :accountId")
})
@Data @AllArgsConstructor @NoArgsConstructor
public class RealmCharacters implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RealmCharactersID id;

    @Column(name = "numchars")
    @NotNull
    private int numChars;
}
