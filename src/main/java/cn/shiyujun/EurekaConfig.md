###### `EurekaClientConfigBean`


```

	/**
	 * 从 Eureka-Server 拉取注册信息频率，单位：秒
	 */
	private int registryFetchIntervalSeconds = 30;

	/**
     * 向 Eureka-Server 同步实例对象信息变化频率，单位：秒
	 */
	private int instanceInfoReplicationIntervalSeconds = 30;

	/**
     * 向 Eureka-Server 同步应用信息变化初始化延迟，单位：秒
	 */
	private int initialInstanceInfoReplicationIntervalSeconds = 40;

	/**
     * 轮询获取 Eureka-Server 地址变更频率，单位：秒
	 */
	private int eurekaServiceUrlPollIntervalSeconds = 5 * MINUTES;

	/**
	 * Eureka-Server 代理主机相关信息
	 */
	private String proxyPort;
	private String proxyHost;
	private String proxyUserName;
	private String proxyPassword;

	/**
	 * 读取超时时间，单位：秒
	 */
	private int eurekaServerReadTimeoutSeconds = 8;

	/**
	 *连接超时时间，单位：秒
	 */
	private int eurekaServerConnectTimeoutSeconds = 5;

	/**
	 * 获取备份注册中心实现类.
	 */
	private String backupRegistryImpl;

	/**
	 * 所有 Eureka-Server 总连接数
	 */
	private int eurekaServerTotalConnections = 200;

	/**
	 * 单个 Eureka-Server 总连接数
	 */
	private int eurekaServerTotalConnectionsPerHost = 50;

	/**
	 *当eureka服务器列表来自dns时的服务器的url上下文
	 */
	private String eurekaServerURLContext;

	/**
	 *端口
	 */
	private String eurekaServerPort;

	/**
	 * DNS名称
	 */
	private String eurekaServerDNSName;

	/**
	 * 当在AWS环境时的eureka的分区
	 */
	private String region = "us-east-1";

	/**
	 *  连接的空闲关闭时间，单位：秒
	 */
	private int eurekaConnectionIdleTimeoutSeconds = 30;

	/**
	 * 指定客户端是否只针对单独的客户端
	 */
	private String registryRefreshSingleVipAddress;

	/**
	 * 心跳执行线程池大小
	 */
	private int heartbeatExecutorThreadPoolSize = 2;

	/**
	 * 心跳执行超时后的延迟重试的时间
	 */
	private int heartbeatExecutorExponentialBackOffBound = 10;

	/**
	 * 注册信息缓存刷新线程池大小
	 */
	private int cacheRefreshExecutorThreadPoolSize = 2;

	/**
	 * 注册信息缓存刷新执行超时后的延迟重试的时间
	 */
	private int cacheRefreshExecutorExponentialBackOffBound = 10;

	

	/**
	 * 是否压缩传输内容
	 */
	private boolean gZipContent = true;

	/**
	 * 是否使用 DNS 获取 Eureka-Server 地址集合
	 */
	private boolean useDnsForFetchingServiceUrls = false;

	/**
	 * 是否向 Eureka-Server 注册自身服务
	 */
	private boolean registerWithEureka = true;

	/**
	 * 优先使用相同 Zone 的 Eureka-Server
	 */
	private boolean preferSameZoneEureka = true;

	/**
	 *指示是否记录eureka服务器和eureka之间的差异
	 */
	private boolean logDeltaDiff;

	/**
	 * 是否禁用Delta的获取
	 */
	private boolean disableDelta;

	/**
	 *获取哪些区域集合的注册信息
	 *
	 */
	private String fetchRemoteRegionsRegistry;

	/**
	 * 获得所在区域( Region ) 里可用区集合( Zone )
	 */
	private Map<String, String> availabilityZones = new HashMap<>();

	/**
	 * 是否过滤，只获取状态为开启的应用实例集合
	 */
	private boolean filterOnlyUpInstances = true;

	/**
	 *是否从 Eureka-Server 拉取注册信息
	 */
	private boolean fetchRegistry = true;

	/**
	 * Eureka-Server 序列化/反序列化信息时，将 $ 替换成的字符串
	 */
	private String dollarReplacement = "_-";

	/**
	 * Eureka-Server 序列化/反序列化信息时，将 _ 替换成的字符串
	 */
	private String escapeCharReplacement = "__";

	/**
	 * 是否允许被 Eureka-Server 重定向
	 */
	private boolean allowRedirects = false;

	/**
	 *是否同步应用实例状态到 Eureka-Server
	 */
	private boolean onDemandUpdateStatusChange = true;

	/**
	 * 编解码相关
	 */
	private String encoderName;

	private String decoderName;

	/**
	 * Eureka-Client 可接收数据类型
	 */
	private String clientDataAccept = EurekaAccept.full.name();

	/**
	 *当进程关闭时，是否向 Eureka-Server 取消注册自身服务
	 */
	private boolean shouldUnregisterOnShutdown = true;

	/**
	 * 指示客户端是否应在初始化期间注册
	 */
	private boolean shouldEnforceRegistrationAtInit = false;
```

###### `EurekaInstanceConfigBean`

```
/**
	 * 健康检查前缀
	 */
	private String actuatorPrefix = "/actuator";

	/**
	 * 应用名
	 */
	private String appname = UNKNOWN;

	/**
	 * 应用组名
	 */
	private String appGroupName;

	/**
	 * 应用初始化后是否开启
	 */
	private boolean instanceEnabledOnit;

	/**
	 * 应用http端口
	 */
	private int nonSecurePort = 80;

	/**
	 * 应用https端口
	 */
	private int securePort = 443;

	/**
	 * 是否开启http端口
	 */
	private boolean nonSecurePortEnabled = true;

	/**
	 *是否开启https端口
	 */
	private boolean securePortEnabled;

	/**
	 *租约续约频率，单位：秒。
	 */
	private int leaseRenewalIntervalInSeconds = 30;

	/**
	 * 租约过期时间，单位：秒
	 */
	private int leaseExpirationDurationInSeconds = 90;

	/**
	 * 虚拟主机名
	 */
	private String virtualHostName = UNKNOWN;

	/**
	 * 实例id
	 */
	private String instanceId;

	/**
	 *虚拟安全主机名
	 */
	private String secureVirtualHostName = UNKNOWN;

	/**
	 * 应用元数据
	 */
	private Map<String, String> metadataMap = new HashMap<>();

	/**
	 * 数据中心信息
	 */
	private DataCenterInfo dataCenterInfo = new MyDataCenterInfo(
			DataCenterInfo.Name.MyOwn);

	/**
	 * ip
	 */
	private String ipAddress;

	/**
	 * 实例状态获取页面地址
	 */
	private String statusPageUrlPath = actuatorPrefix + "/info";

	private String statusPageUrl;

	/**
	 * 主页
	 */
	private String homePageUrlPath = "/";

	private String homePageUrl;

	/**
	 * 健康检查地址
	 */
	private String healthCheckUrlPath = actuatorPrefix + "/health";

	private String healthCheckUrl;

	/**
	 * https健康检查地址
	 */
	private String secureHealthCheckUrl;

	/**
	 * 配置命名空间
	 */
	private String namespace = "eureka";

	/**
	 * 主机名
	 */
	private String hostname;

	/**
	 * 是否引用操作系统主机名
	 */
	private boolean preferIpAddress = false;

```
