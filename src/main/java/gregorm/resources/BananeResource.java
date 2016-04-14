package gregorm.resources;

import java.util.List;

import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.*;
import gregorm.api.Narocilo;
import gregorm.api.NarociloDAO;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.common.base.Optional;



@Path("/narocila")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BananeResource {
	
	private final NarociloDAO narociloDAO;
	
	public BananeResource(NarociloDAO narociloDAO) {
		this.narociloDAO = narociloDAO;
	}
	
	@GET
	@UnitOfWork
	public List<Narocilo> preglejVsaNarocila(@QueryParam("tip") Optional<String> tip, @QueryParam("datum") Optional<DateTimeParam> datum) {
		if(tip.isPresent()) {
			return narociloDAO.najdiVsaPoTipu(tip.get());
		}
		if(datum.isPresent()) {
			DateTime datumNarocila = datum.get().get();
			return narociloDAO.najdiNarocilaNaDan(datumNarocila);
		}
		return narociloDAO.najdiVsa();
	}
	
	@GET
	@UnitOfWork
	@Path("/{stNarocila}")
	public Narocilo preglejNarocilo(@PathParam("stNarocila") IntParam stNarocila) {
		return narociloDAO.findById(stNarocila.get()
				);
	}
	
	@POST
	@UnitOfWork
	public Response dodajNarocilo(@Valid Narocilo narocilo) {
		return Response.created(UriBuilder.fromResource(BananeResource.class).build()).entity(narociloDAO.shraniVBazo(narocilo)).build();
	}
	
	@PUT
	@UnitOfWork
	@Path("/{stNarocila}")
	public Response urediNarocilo(@PathParam("stNarocila") IntParam stNarocila, @Valid Narocilo narocilo) {
		return Response.ok().entity(narociloDAO.uredi(stNarocila.get(), narocilo)).build();
		
	}
	
	@DELETE
	@UnitOfWork
	@Path("/{stNarocila}")
	public Response izbrisiNarocilo(@PathParam("stNarocila") IntParam stNarocila) {
		narociloDAO.izbrisiNarocilo(stNarocila.get());
		return Response.ok().build();
	}
}
