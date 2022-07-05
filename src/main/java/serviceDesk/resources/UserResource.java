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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import serviceDesk.model.User;
import serviceDesk.model.dto.UserDTO;
import serviceDesk.model.dto.UserDTOInput;
import serviceDesk.resources.exceptions.UserNotFoundExceptioon;
import serviceDesk.services.UserService;

@Path("/users")
public class UserResource {

	private static final Logger LOGGER = Logger.getLogger(UserResource.class);

	private UserService service;

	public UserResource() {
		this.service = new UserService();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response save(UserDTOInput userDTO) {
		User user = service.insert(userDTO);
		return Response
				.status(200)
				.entity("Registro inserido " + user.toString())
				.build();
	}

	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response update(@PathParam("id") Long id, User user) {
		try {
			service.update(id, user);
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
		}		
	}

	@DELETE
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response delete(@PathParam("id") Long id) {
		try {
			service.delete(id);
			return Response
					.status(200)
					.entity("Registro removido.")
					.build();
		} catch (UserNotFoundExceptioon e) {
			LOGGER.error(e);
			return Response
					.status(404)
					.entity("Usuário com o id: " + id + " não encontrado!")
					.build();
		} 
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public User findById(@PathParam("id") Long id) {
		return service.findUserById(id);
	}

	@GET
	@Path("/name/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findByName(@PathParam("name") String name) {
		List<UserDTO> usersDTO = service.findUsersByName(name);
		GenericEntity<List<UserDTO>> entities = new GenericEntity<List<UserDTO>>(usersDTO) {
		};
		return Response
				.ok(entities)
				.build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllUsers() {
			List<UserDTO> users = service.findAll();
			GenericEntity<List<UserDTO>> entities = new GenericEntity<List<UserDTO>>(users) {
			};
			return Response
					.ok(entities)
					.build();
	}
}
