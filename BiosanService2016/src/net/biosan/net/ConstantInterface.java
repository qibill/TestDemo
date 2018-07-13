package net.biosan.net;

public interface ConstantInterface {
	/**
	 * 批号类型——验收批
	 */
	public final static int BATCH_TYPE_CHECK = 1;
	/**
	 * 批号类型——递送批
	 */
	public final static int BATCH_TYPE_DELIVE = 2;
	/**
	 * 批号类型——实验室验收批
	 */
	public final static int BATCH_TYPE_EXPERIMENT = 3;

	/**
	 * 母亲的类型号
	 */
	public final static int PATIENT_MOTHER_TYPE = 1;
	/**
	 * 婴儿的类型号
	 */
	public final static int FETUS_MOTHER_TYPE = 2;

	/**
	 * 病人的账户类型号
	 */
	public final static int DEFAULT_PATIENT_ACCOUNT_TYPE = 1;

	/**
	 * 流程新筛类型
	 */
	public final static String PROCESS_FETUS_TYPE = "hsis002";
	public final static int PROCESS_FETUS_TYPE_ID = 2;
	/**
	 * 样本类型--血样
	 */
	public final static int SAMPLE_DEFAULT = 1;

	public static final String GROUND_APPLICATION_SERVICE = "GROUND_APPLICATION_SERVICE";
}
