package serverConfig;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.server.web.vo.jedis.WebJedisVo;

public class ServerConfig {
	private static ServerConfig instance;
	private int webServerPort;
	private int clientServerPort;
	private String redisIP;
	private int redisPort;
	private String redisSign;
	private String redisPassword;
	private String serverSign;

	public static ConcurrentHashMap<String, WebJedisVo> manager4Web = new ConcurrentHashMap<>();// 为了web用准备的

	// key:
	// 当前运行线程名字，value:

	private ServerConfig() {
		Properties properties = new Properties();
		try {
			String osInfo = System.getProperties().getProperty("os.name");
			String path = new String();
			if (osInfo.contains("Windows")) {
				path = System.getProperty("user.dir") + "\\";
			}
			if (osInfo.contains("Linux")) {
				path = System.getProperty("user.dir") + "/";
			}
			properties.load(new FileInputStream(path
					+ "serverConfig.properties"));
			this.redisIP = properties.getProperty("redisIP");
			this.redisPort = Integer.parseInt(properties
					.getProperty("redisPort"));
			this.webServerPort = Integer.parseInt(properties
					.getProperty("webServerPort"));
			this.clientServerPort = Integer.parseInt(properties
					.getProperty("clientServerPort"));
			this.redisSign = properties.getProperty("redisSign");
			this.serverSign = properties.getProperty("serverSign");
			this.redisPassword = properties.getProperty("redisPassword");
		} catch (Exception e) {

		}
	}

	public static ServerConfig getInstance() {
		if (instance == null) {
			instance = new ServerConfig();
		}
		return instance;
	}
	
	public boolean hasManagerJedisPool() {
		WebJedisVo jedis = getJedisVo();
		return jedis != null && jedis.getJedisPool() != null;
	}

	public boolean hasManagerSharedJedisPool() {
		WebJedisVo jedis = getJedisVo();
		return jedis != null && jedis.getShardedJedisPool() != null;
	}

	public int getWebServerPort() {
		return webServerPort;
	}

	public int getClientServerPort() {
		return clientServerPort;
	}

	public WebJedisVo getJedisVo() {
		WebJedisVo vo = null;
		try {
			Class<?> cls = Class.forName("com.vo.ManagerUtil");
			Method method = cls.getMethod("getFromSession", null);
			if (method != null) {
				Object obj = method.invoke(cls.newInstance(), null);
				if (obj != null) {
					vo = manager4Web.get(obj.toString());
				}
			}
		} catch (Exception e) {
		}
		if (vo == null) {
			vo = manager4Web.get(Thread.currentThread().getName());
		}
		return vo;
	}

	public String getRedisIP() {
		WebJedisVo vo = getJedisVo();
		if (vo != null) {
			return vo.getRedisIP();
		} else {
			return redisIP;
		}
	}

	public int getRedisPort() {
		WebJedisVo vo = getJedisVo();
		if (vo != null) {
			return vo.getPort();
		} else {
			return redisPort;
		}
	}

	public void setRedisIP(String redisIP) {
		this.redisIP = redisIP;
	}

	public void setRedisPort(int redisPort) {
		this.redisPort = redisPort;
	}

	public String getRedisSign() {
		return redisSign;
	}

	public void setRedisSign(String redisSign) {
		this.redisSign = redisSign;
	}

	public String getServerSign() {
		return serverSign;
	}

	public void setServerSign(String serverSign) {
		this.serverSign = serverSign;
	}

	public String getRedisPassword() {
		return redisPassword;
	}

	public void setRedisPassword(String redisPassword) {
		this.redisPassword = redisPassword;
	}

}
