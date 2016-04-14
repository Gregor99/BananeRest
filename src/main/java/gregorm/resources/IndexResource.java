package gregorm.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonProperty;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class IndexResource {

	@JsonProperty
	private final String check = "URLju dodaj /narocila";
	
	public IndexResource() {}
	
	@GET
	public String preveri() {
		return check;
	}
}
