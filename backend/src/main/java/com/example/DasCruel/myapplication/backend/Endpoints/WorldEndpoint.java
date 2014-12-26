package com.example.DasCruel.myapplication.backend.Endpoints;

import com.example.DasCruel.myapplication.backend.Models.World;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p/>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "worldApi",
        version = "v1",
        resource = "world",
        namespace = @ApiNamespace(
                ownerDomain = "Models.backend.myapplication.DasCruel.example.com",
                ownerName = "Models.backend.myapplication.DasCruel.example.com",
                packagePath = ""
        )
)
public class WorldEndpoint {

    private static final Logger logger = Logger.getLogger(WorldEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(World.class);
    }

    /**
     * Returns the {@link World} with the corresponding ID.
     *
     * @param Name the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code World} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "world/{Name}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public World get(@Named("Name") String Name) throws NotFoundException {
        logger.info("Getting World with ID: " + Name);
        World world = ofy().load().type(World.class).id(Name).now();
        if (world == null) {
            throw new NotFoundException("Could not find World with ID: " + Name);
        }
        return world;
    }

    /**
     * Inserts a new {@code World}.
     */
    @ApiMethod(
            name = "insert",
            path = "world",
            httpMethod = ApiMethod.HttpMethod.POST)
    public World insert(World world) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that world.Name has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(world).now();
        logger.info("Created World.");

        return ofy().load().entity(world).now();
    }

    /**
     * Updates an existing {@code World}.
     *
     * @param Name  the ID of the entity to be updated
     * @param world the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code Name} does not correspond to an existing
     *                           {@code World}
     */
    @ApiMethod(
            name = "update",
            path = "world/{Name}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public World update(@Named("Name") String Name, World world) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(Name);
        ofy().save().entity(world).now();
        logger.info("Updated World: " + world);
        return ofy().load().entity(world).now();
    }

    /**
     * Deletes the specified {@code World}.
     *
     * @param Name the ID of the entity to delete
     * @throws NotFoundException if the {@code Name} does not correspond to an existing
     *                           {@code World}
     */
    @ApiMethod(
            name = "remove",
            path = "world/{Name}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("Name") String Name) throws NotFoundException {
        checkExists(Name);
        ofy().delete().type(World.class).id(Name).now();
        logger.info("Deleted World with ID: " + Name);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "world",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<World> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<World> query = ofy().load().type(World.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<World> queryIterator = query.iterator();
        List<World> worldList = new ArrayList<World>(limit);
        while (queryIterator.hasNext()) {
            worldList.add(queryIterator.next());
        }
        return CollectionResponse.<World>builder().setItems(worldList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(String Name) throws NotFoundException {
        try {
            ofy().load().type(World.class).id(Name).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find World with ID: " + Name);
        }
    }
}