package bzh.dezrann.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DatabaseModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(EntityManager.class).toProvider(EntityManagerProvider.class).in(Singleton.class);
	}

	public static class EntityManagerProvider implements Provider<EntityManager> {

		@Override
		public EntityManager get() {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("mainUnit");
			return factory.createEntityManager();
		}
	}
}
