package com.l2jfree.loginserver;

import com.l2jfree.L2Config;
import com.l2jfree.Shutdown;
import com.l2jfree.TerminationStatus;
import com.l2jfree.config.L2Properties;
import com.l2jfree.sql.L2Database;
import com.l2jfree.sql.L2DatabaseInstaller;
import com.l2jfree.util.concurrent.L2ThreadPool;

/**
 * @author NB4L1
 * @author savormix
 */
// TODO should be sorted into groups and redone with annotation loaders
@SuppressWarnings("synthetic-access")
public class Config extends L2Config
{
	static
	{
		L2Config.registerConfig(new DatabaseConfig());
		L2Config.registerConfig(new NetworkConfig());
		L2Config.registerConfig(new ServiceConfig());
		
		try
		{
			L2Config.loadConfigs();
		}
		catch (Exception e)
		{
			_log.fatal("Could not load configuration files!", e);
			Shutdown.exit(TerminationStatus.RUNTIME_INVALID_CONFIGURATION);
		}
		
		try
		{
			L2ThreadPool.initThreadPools(new L2LoginThreadPools());
		}
		catch (Exception e)
		{
			_log.fatal("Could not initialize thread pools!", e);
			Shutdown.exit(TerminationStatus.RUNTIME_INITIALIZATION_FAILURE);
		}
		
		try
		{
			L2Database.setDataSource("default", new L2LoginDataSource());
		}
		catch (Exception e)
		{
			_log.fatal("Could not initialize DB connections!", e);
			Shutdown.exit(TerminationStatus.RUNTIME_INITIALIZATION_FAILURE);
		}
		
		try
		{
			L2DatabaseInstaller.check();
		}
		catch (Exception e)
		{
			_log.fatal("Could not initialize DB tables!", e);
			Shutdown.exit(TerminationStatus.RUNTIME_INITIALIZATION_FAILURE);
		}
	}
	
	/** Maximum amount of database connections in pool */
	public static int DB_MAX_CONNECTIONS;
	/** Database driver class */
	public static String DB_DRIVER;
	/** Database JDBC URL */
	public static String DB_URL;
	/** Database login */
	public static String DB_USER;
	/** Database password */
	public static String DB_PASSWORD;
	/** Whether to optimize database tables */
	public static boolean DB_OPTIMIZE;
	/** Whether to install database tables */
	public static boolean DB_INSTALL;
	
	private static final class DatabaseConfig extends ConfigPropertiesLoader
	{
		@Override
		protected void loadImpl(L2Properties properties)
		{
			DB_MAX_CONNECTIONS = properties.getInteger("MaxConnectionsInPool", 5);
			DB_DRIVER = properties.getString("DriverClass");
			
			DB_URL = properties.getString("JdbcUrl");
			if (!DB_URL.startsWith("jdbc:"))
				DB_URL = "jdbc:" + DB_URL;
			
			DB_USER = properties.getString("Login");
			DB_PASSWORD = properties.getString("Password", "");
			
			DB_OPTIMIZE = properties.getBool("OptimizeTables", true);
			DB_INSTALL = properties.getBool("AutoCreateTables", false);
		}
		
		@Override
		protected String getName()
		{
			return "database";
		}
	}
	
	/** Login server listens for client connections on this IP address */
	public static String NET_LISTEN_IP;
	/** Login server listens for client connections on this port */
	public static int NET_LISTEN_PORT;
	
	/** Whether to listen for legacy game servers */
	public static boolean NET_ENABLE_LEGACY;
	/** Login server listens for legacy game server connections on this IP address */
	public static String NET_LEGACY_LISTEN_IP;
	/** Login server listens for legacy game server connections on this port */
	public static int NET_LEGACY_LISTEN_PORT;
	
	private static final class NetworkConfig extends ConfigPropertiesLoader
	{
		@Override
		protected void loadImpl(L2Properties properties)
		{
			NET_LISTEN_IP = properties.getString("ListenIP", "0.0.0.0");
			NET_LISTEN_PORT = properties.getInteger("ListenPort", 2106);
			
			NET_ENABLE_LEGACY = properties.getBool("EnableLegacyListener", true);
			NET_LEGACY_LISTEN_IP = properties.getString("LegacyListenIP", "127.0.0.1");
			NET_LEGACY_LISTEN_PORT = properties.getInteger("LegacyListenPort", 9014);
		}
		
		@Override
		protected String getName()
		{
			return "network";
		}
	}
	
	/** Whether to behave as a traditional login server */
	public static boolean SVC_FORCE_LEGACY;
	/** Whether to check for obvious signs that client has GameGuard disabled */
	public static boolean SVC_CHECK_GAMEGUARD;
	/** Whether to show NCSoft's EULA before the game server list */
	public static boolean SVC_SHOW_EULA;
	
	private static final class ServiceConfig extends ConfigPropertiesLoader
	{
		@Override
		protected void loadImpl(L2Properties properties)
		{
			SVC_FORCE_LEGACY = properties.getBool("ForceLegacyMode", false);
			SVC_CHECK_GAMEGUARD = properties.getBool("CheckGameGuard", true);
			SVC_SHOW_EULA = properties.getBool("ShowEULA", false);
		}
		
		@Override
		protected String getName()
		{
			return "service";
		}
	}
}