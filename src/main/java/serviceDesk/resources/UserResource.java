package serviceDesk.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import serviceDesk.dao.IUserDAO;
import serviceDesk.dao.impl.UserDAO;
import serviceDesk.model.User;
import serviceDesk.resources.exceptions.UserNotFoundExceptioon;

@Path("/users")
public class UserResource {

	private static final Logger LOGGER = Logger.getLogger(UserResource.class);

	private IUserDAO<User, Long> dao;

	public UserResource() {
		this.dao = new UserDAO();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response save(User user) {
		try {
			dao.saveOrUpdate(user);
			return Response.status(200).entity("Registro inserido " + user.toString()).build();

		} catch (Exception e) {
			LOGGER.error(e);
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response update(@PathParam("id") Long id, User user) {
		try {
			User newUser = dao.findById(id);

			if (newUser == null) {
				throw new UserNotFoundExceptioon("Usuário não encontrado!");
			} else {
				newUser.setId(id);
				newUser.setName(user.getName());
				newUser.setPhone(user.getPhone());
				newUser.setEmail(user.getEmail());
				dao.saveOrUpdate(newUser);
			}

			return Response
					.status(200)
					.entity("Registro editado.")
					.build();
		} catch (UserNotFoundExceptioon e) {
			LOGGER.error(e);
			return Response
					.status(404)
					.entity("Usuário com o id: " + id + " não encontrado!")
					.build();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new WebApplicationException(e);
		}
	}

	@DELETE
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response delete(@PathParam("id") Long id) {
		try {
			User newUser = dao.findById(id);

			if (newUser == null) {
				throw new UserNotFoundExceptioon("Usuário não encontrado!");
			} else {
				dao.delete(id);
			}

			return Response.status(200).entity("Registro removido.").build();

		} catch (UserNotFoundExceptioon e) {
			LOGGER.error(e);
			return Response.status(404).entity("Usuário com o id: " + id + " não encontrado!").build();
		} catch (Exception ex) {
			LOGGER.error(ex);
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public User findById(@PathParam("id") Long id) {
		try {

			return dao.findById(id);

		} catch (Exception ex) {
			LOGGER.error(ex);
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByName(@PathParam("name") String name) {
		LOGGER.info("NAME : " + name);
		try {

			List<User> users = dao.findByName(name);

			GenericEntity<List<User>> entities = new GenericEntity<List<User>>(users) {
			};

			return Response.ok(entities).build();

		} catch (Exception ex) {
			LOGGER.error(ex);
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllUsers() {
		try {

			List<User> users = dao.findAll();
			GenericEntity<List<User>> entities = new GenericEntity<List<User>>(users) {
			};

			return Response.ok(entities).build();
		} catch (Exception e) {
			LOGGER.error(e);
			throw new WebApplicationException(500);
		}
	}
}
