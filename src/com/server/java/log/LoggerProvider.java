package com.server.java.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志提供类
 * 
 * @author codingdd
 * 
 */
public class LoggerProvider {
	/**
	 * 物品logger
	 */
	private final static Logger itemLogger = getLogger(ItemLog.class);
	/**
	 * 平台logger
	 */
	private final static Logger PFLogger = getLogger(PlatForm.class);
	/**
	 * 商城log
	 */
	private final static Logger mallShopLogger = getLogger(MallShopLog.class);
	/**
	 * 关服log
	 */
	private final static Logger serverShutDownLogger = getLogger(ServerShutDownLog.class);
	/**
	 * 运营日志
	 */
	private final static Logger operatorLogger = getLogger(OperatorLog.class);
	/**
	 * 聊天log
	 */
	private final static Logger chatLogger = getLogger(ChatLog.class);
	/**
	 * 任务log
	 */
	private final static Logger questLogger = getLogger(QuestLog.class);
	/**
	 * 离线奖励
	 */
	private final static Logger dLRewardLogger = getLogger(DLRewardLog.class);
	/**
	 * 充值log
	 */
	private final static Logger rechargeLogger = getLogger(RechargeLog.class);
	/**
	 * 抽奖log
	 */
	private final static Logger essenceLogger = getLogger(EssenceLog.class);
	/**
	 * 打点log
	 */
	private final static Logger eventLogger = getLogger(EventLog.class);
	/**
	 * 游戏操作log
	 */
	private final static Logger operationLogger = getLogger(OperationLog.class);

	private final static Logger aimLog = getLogger(AimLog.class);

	private final static Logger farmLog = getLogger(FarmLog.class);

	private final static Logger oldRewardLog = getLogger(OldReawrdLog.class);

	private final static Logger compassLog = getLogger(CompassLog.class);

	private final static Logger propLog = getLogger(PropLog.class);

	/**
	 * @param clazz
	 * @return
	 */
	public static Logger getLogger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}

	/**
	 * 取得关服logger
	 * 
	 * @return
	 */
	public static Logger getShutDownLogger() {
		return serverShutDownLogger;
	}

	public static Logger getItemLogger() {
		return itemLogger;
	}

	public static Logger getPFLogger() {
		return PFLogger;
	}

	public static Logger getMallShopLogger() {
		return mallShopLogger;
	}

	public static Logger getOperatorLogger() {
		return operatorLogger;
	}

	public static Logger getChatLogger() {
		return chatLogger;
	}

	public static Logger getQuestLogger() {
		return questLogger;
	}

	public static Logger getDLReward() {
		return dLRewardLogger;
	}

	/**
	 * 取得充值log
	 * 
	 * @return
	 */
	public static Logger getRechargeLogger() {
		return rechargeLogger;
	}

	public static Logger getEssencelogger() {
		return essenceLogger;
	}

	public static Logger getEventlogger() {
		return eventLogger;
	}

	public static Logger getOperationlogger() {
		return operationLogger;
	}

	public static Logger getAimLogger() {
		return aimLog;
	}

	public static Logger getFarmLog() {
		return farmLog;
	}

	public static Logger getOldRewardLog() {
		return oldRewardLog;
	}

	public static Logger getCompassLog() {
		return compassLog;
	}

	public static Logger getPropLog() {
		return propLog;
	}

	public class ItemLog {

	}

	public class CPUMonitor {

	}

	public class PlatForm {

	}

	public class MallShopLog {
	}

	public class ServerShutDownLog {

	}

	public class OperatorLog {

	}

	public class ChatLog {

	}

	public class QuestLog {

	}

	public class DLRewardLog {

	}

	/**
	 * 充值log
	 * 
	 * @author haoxichuan
	 * 
	 */
	public class RechargeLog {

	}

	public class EssenceLog {

	}

	public class EventLog {

	}

	public class OperationLog {

	}

	public class AimLog {

	}

	public class FarmLog {

	}

	public class OldReawrdLog {

	}

	public class CompassLog {

	}

	public class PropLog {

	}
}