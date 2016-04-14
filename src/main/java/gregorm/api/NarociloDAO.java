package gregorm.api;

import java.sql.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.sql.Delete;
import org.joda.time.DateTime;

import com.google.common.base.Optional;

import io.dropwizard.hibernate.AbstractDAO;

public class NarociloDAO extends AbstractDAO<Narocilo>{

	public NarociloDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<Narocilo> najdiVsa() {
		return list(namedQuery("najdiVsa"));
	}
	
	public Narocilo findById(Integer stNarocila) {
		return get(stNarocila);
	}
	
	public Narocilo shraniVBazo(Narocilo narocilo) {
		currentSession().save(narocilo);
		return narocilo;
	}
	
	public void izbrisiNarocilo(Integer narocilo) {
		currentSession().delete(new Narocilo(narocilo, null, null, null));
	}
	
	public Narocilo uredi (Integer stNarocila, Narocilo narocilo) {
		narocilo.setStNarocila(stNarocila);
		currentSession().update(narocilo);
		return narocilo;
	}

	public List<Narocilo> najdiVsaPoTipu(String tip) {
		return list(namedQuery("najdiVsaPoTipu").setParameter("tip", tip));
	}

	public List<Narocilo> najdiNarocilaNaDan(DateTime datumNarocila) {
		System.out.println("najdi po datumu: " + datumNarocila);
		return list(namedQuery("najdiVsaPoDatumu").setParameter("datum", new Date(datumNarocila.getMillis())));
	}
}
