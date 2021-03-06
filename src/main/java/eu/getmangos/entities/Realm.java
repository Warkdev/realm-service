package eu.getmangos.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import eu.getmangos.utils.FlagUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="realmlist", uniqueConstraints = {
    @UniqueConstraint(name="name", columnNames = {"name"})
})
@NamedQueries({
    @NamedQuery(name = "Realm.findAll", query = "SELECT r FROM Realm r"),
    @NamedQuery(name = "Realm.findById", query = "SELECT r FROM Realm r where r.id = :id"),
    @NamedQuery(name = "Realm.findByName", query = "SELECT r FROM Realm r where r.name = :name"),
    @NamedQuery(name = "Realm.findByType", query = "SELECT r FROM Realm r where r.icon = :type"),
    @NamedQuery(name = "Realm.findByZone", query = "SELECT r FROM Realm r where r.zone = :zone")
})
@Data @AllArgsConstructor @NoArgsConstructor
public class Realm implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String localAddress;

    @NotNull
    private String localSubnetMask;

    @NotNull
    private int port;

    @NotNull
    private short icon;

    @NotNull
    private int realmflags;

    @NotNull
    @Column(name = "timezone")
    private short zone;

    @NotNull
    private short allowedSecurityLevel;

    @NotNull
    private double population;

    @NotNull
    @Column(name = "realmbuilds")
    private String realmBuild;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getLocalSubnetMask() {
        return localSubnetMask;
    }

    public void setLocalSubnetMask(String localSubnetMask) {
        this.localSubnetMask = localSubnetMask;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public short getIcon() {
        return icon;
    }

    public void setIcon(short icon) {
        this.icon = icon;
    }

    public int getRealmflags() {
        return realmflags;
    }

    public void setRealmflags(int realmflags) {
        this.realmflags = realmflags;
    }

    public boolean isInvalid() {
        return FlagUtils.hasFlag(this.realmflags, 0x1);
    }

    public void setInvalid(boolean invalid) {
        this.realmflags = FlagUtils.setFlag(this.realmflags, 0x1, invalid);
    }

    public boolean isOffline() {
        return FlagUtils.hasFlag(this.realmflags, 0x2);
    }

    public void setOffline(boolean offline) {
        this.realmflags = FlagUtils.setFlag(this.realmflags, 0x2, offline);
    }

    public boolean isShowVersion() {
        return FlagUtils.hasFlag(this.realmflags, 0x4);
    }

    public void setShowVersion(boolean showVersion) {
        this.realmflags = FlagUtils.setFlag(this.realmflags, 0x4, showVersion);
    }

    public boolean isNewPlayers() {
        return FlagUtils.hasFlag(this.realmflags, 0x20);
    }

    public void setNewPlayers(boolean newPlayers) {
        this.realmflags = FlagUtils.setFlag(this.realmflags, 0x20, newPlayers);
    }

    public boolean isRecommended() {
        return FlagUtils.hasFlag(this.realmflags, 0x40);
    }

    public void setRecommended(boolean recommended) {
        this.realmflags = FlagUtils.setFlag(this.realmflags, 0x40, recommended);
    }

    public short getZone() {
        return zone;
    }

    public void setZone(short zone) {
        this.zone = zone;
    }

    public short getAllowedSecurityLevel() {
        return allowedSecurityLevel;
    }

    public void setAllowedSecurityLevel(short allowedSecurityLevel) {
        this.allowedSecurityLevel = allowedSecurityLevel;
    }

    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    public String getRealmBuild() {
        return realmBuild;
    }

    public void setRealmBuild(String realmBuild) {
        this.realmBuild = realmBuild;
    }
}
