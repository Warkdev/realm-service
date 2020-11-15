package eu.getmangos.rest.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;

import eu.getmangos.controllers.DAOException;
import eu.getmangos.controllers.RealmCharactersController;
import eu.getmangos.controllers.RealmController;
import eu.getmangos.controllers.UptimeController;
import eu.getmangos.dto.LinksDTO;
import eu.getmangos.dto.RealmDTO;
import eu.getmangos.dto.UptimeDTO;
import eu.getmangos.entities.Realm;
import eu.getmangos.entities.RealmCharacters;
import eu.getmangos.entities.RealmCharactersID;
import eu.getmangos.entities.Uptime;
import eu.getmangos.mapper.LinksMapper;
import eu.getmangos.mapper.RealmMapper;
import eu.getmangos.mapper.UptimeMapper;
import eu.getmangos.rest.RealmResource;

@RequestScoped
@Path("/v1")
public class RealmResourceService implements RealmResource {

    @Inject private Logger logger;

    @Inject private RealmController realmController;
    @Inject private RealmCharactersController realmCharactersController;
    @Inject private UptimeController uptimeController;

    @Inject private RealmMapper realmMapper;
    @Inject private LinksMapper linksMapper;
    @Inject private UptimeMapper uptimeMapper;

    public Response findRealm(Integer id) {
        logger.debug("find() entry.");

        if (id == null) {
                return Response.status(500).entity("The provided ID is null.").build();
        }

        Realm Realm = this.realmController.find(id);

        if(Realm == null) {
                return Response.status(404).entity("The provided ID has no match in the database.").build();
        }

        logger.debug("find() exit.");
        return Response.status(200).entity(Realm).build();
    }

    public Response findAllRealms() {
        logger.debug("findAll() entry.");

        List<RealmDTO> listRealms = new ArrayList<>();

        for(Realm r : this.realmController.findAll()) {
            listRealms.add(realmMapper.realmToDTO(r));
        }

        logger.debug("findAll() exit.");
        return Response.status(200).entity(listRealms).build();
    }

    public Response addRealm(RealmDTO entity) {
        try {
                this.realmController.create(realmMapper.dtoToEntity(entity));
        } catch (DAOException daoEx) {
                return Response.status(400).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(201).entity("Realm has been created.").build();
    }

    public Response editRealm(Integer id, RealmDTO entity) {
        try {
                entity.setId(id);
                this.realmController.update(realmMapper.dtoToEntity(entity));
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(200).entity("Realm has been updated.").build();
    }

    public Response deleteRealm(Integer id) {
        try {
                this.realmController.delete(id);
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(204).build();
    }

    public Response findRealmCharacters(Integer realmID, Integer accountID) {
        logger.debug("find() entry.");

        if (realmID == null || accountID == null) {
                return Response.status(500).entity("The provided ID is null.").build();
        }

        RealmCharacters link = this.realmCharactersController.find(new RealmCharactersID(realmID, accountID));

        if(link == null) {
                return Response.status(404).entity("The provided ID has no match in the database.").build();
        }

        logger.debug("find() exit.");
        return Response.status(200).entity(link).build();
    }

    public Response findRealmCharactersByRealmID(Integer realmID) {
        logger.debug("find() entry.");

        if (realmID == null) {
                return Response.status(500).entity("The provided ID is null.").build();
        }

        List<RealmCharacters> linkList = this.realmCharactersController.findByRealm(realmID);

        logger.debug("find() exit.");
        return Response.status(200).entity(linkList).build();
    }

    public Response findRealmCharactersByAccountID(Integer accountID) {
        logger.debug("find() entry.");

        if (accountID == null) {
                return Response.status(500).entity("The provided ID is null.").build();
        }

        List<RealmCharacters> linkList = this.realmCharactersController.findByAccount(accountID);

        logger.debug("find() exit.");
        return Response.status(200).entity(linkList).build();
    }

    public Response findAllRealmCharacters() {
        logger.debug("findAll() entry.");

        List<LinksDTO> listRealmCharacters = new ArrayList<>();

        for(RealmCharacters link : this.realmCharactersController.findAll()) {
            listRealmCharacters.add(linksMapper.linkToDTO(link));
        }

        logger.debug("findAll() exit.");
        return Response.status(200).entity(listRealmCharacters).build();
    }

    public Response addRealmCharacters(LinksDTO entity) {
        try {
                this.realmCharactersController.create(linksMapper.dtoToEntity(entity));
        } catch (DAOException daoEx) {
                return Response.status(400).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(201).entity("Link has been created.").build();
    }

    public Response editRealmCharacters(Integer accountID, Integer realmID, LinksDTO entity) {
        try {
                RealmCharacters link = linksMapper.dtoToEntity(entity);
                link.setId(new RealmCharactersID(accountID, realmID));
                this.realmCharactersController.update(link);
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(200).entity("Link has been updated.").build();
    }

    public Response deleteRealmList(Integer accountID, Integer realmID) {
        try {
                this.realmCharactersController.delete(new RealmCharactersID(realmID, accountID));
        } catch (DAOException daoEx) {
                return Response.status(404).entity(daoEx.getMessage()).build();
        } catch (Exception ex) {
                return Response.status(500).entity(ex.getMessage()).build();
        }
        return Response.status(204).build();
    }

    public Response getUptimesForRealm(Integer realmId) {
        logger.debug("getUptimesForRealm() entry.");

        if(realmId == null) {
            return Response.status(500).entity("The provided ID is null.").build();
        }

        List<UptimeDTO> uptimeList = new ArrayList<>();

        for(Uptime u : this.uptimeController.getUptimeForRealm(realmId)) {
            uptimeList.add(uptimeMapper.uptimeToDTO(u));
        }

        logger.debug("getUptimesForRealm() exit.");
        return Response.status(200).entity(uptimeList).build();
    }

    public Response getUptimes() {
        logger.debug("getUptimes() entry.");

        List<UptimeDTO> uptimeList = new ArrayList<>();

        for(Uptime u : this.uptimeController.getAllUptimes()) {
            uptimeList.add(uptimeMapper.uptimeToDTO(u));
        }

        logger.debug("getUptimes() exit.");
        return Response.status(200).entity(uptimeList).build();
    }
}
