package gregorm;

import gregorm.api.Narocilo;
import gregorm.api.NarociloDAO;
import gregorm.resources.BananeResource;
import gregorm.resources.IndexResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class BananeApplication extends Application<BananeConfiguration> {

	
    private final HibernateBundle<BananeConfiguration> hibernateBundle =
    		new HibernateBundle<BananeConfiguration>(Narocilo.class) {

				@Override
				public DataSourceFactory getDataSourceFactory(
						BananeConfiguration configuration) {
					return configuration.getDataSourceFactory();
				}
			};

    @Override
    public void initialize(final Bootstrap<BananeConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }
    
	
    public static void main(final String[] args) throws Exception {
        new BananeApplication().run(args);
    }

    @Override
    public String getName() {
        return "Banane";
    }

    @Override
    public void run(final BananeConfiguration configuration,
                    final Environment environment) {
        final NarociloDAO narociloDAO = new NarociloDAO(hibernateBundle.getSessionFactory());
        
        environment.jersey().register(new BananeResource(narociloDAO));
        environment.jersey().register(new IndexResource());
    }

}
