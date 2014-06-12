package bzh.dezrann.config;

import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DatabaseModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(EntityManager.class).toProvider(EntityManagerProvider.class).in(Singleton.class);
		bind(Gson.class).in(Singleton.class);
	}

	public static class EntityManagerProvider implements Provider<EntityManager> {

		@Inject
		private Gson gson;

		@Override
		public EntityManager get() {
			DatabaseConfig dbConfig = null;
			String root = File.listRoots()[0].getPath();
			String configPath = root + "dezrann" + File.separator + "database.json";
			try {
				dbConfig = gson.fromJson(
						Files.lines(Paths.get(configPath)).collect(Collectors.joining()),
						DatabaseConfig.class);
			} catch (IOException e) {
				System.out.println("Database config file loading error at " + configPath);
				e.printStackTrace();
			}
			Map<String, String> configuration = new HashMap<>();
			configuration.put("hibernate.connection.url", "jdbc:mysql://" + dbConfig.url);
			configuration.put("hibernate.connection.username", dbConfig.username);
			configuration.put("hibernate.connection.password", dbConfig.password);
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("mainUnit", configuration);
			return factory.createEntityManager();
		}

		private class DatabaseConfig{
			public String url;
			public String username;
			public String password;
		}
	}
}
