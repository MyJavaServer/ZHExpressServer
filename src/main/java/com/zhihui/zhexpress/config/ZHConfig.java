package com.zhihui.zhexpress.config;

public class ZHConfig {

    /**
     * 001. all jzbconfig
     */
    //application global jzbconfig
    public static final String GLOBAL_APP_MODE_LIVE = "LIVE";
    public static final String GLOBAL_APP_MODE_TEST = "TEST";
    public static final String GLOBAL_APP_MODE_DEV = "DEV";
    public static final String SERVER_URL_TEST = "http://localhost:8443/ZHexpressServer";
    public static final String SERVER_URL_DEV = "http://120.26.48.110";
    public static final String SERVER_URL_LIVE = "https://www.doulongwan.world";


    // Request Codes type

    public static final String REQ_PARAMS = "params";
    public static final String REQ_PARAMS2 = "params2";


    //  Response Code & Values

    public static final String RESP_STATUS = "status";
    public static final String RESP_RESULT = "result";
    public static final String RESP_SUCCESS = "1";
    public static final String RESP_FAIL = "0";
    public static final String RESP_WARNING = "2";
    public static final String RESP_FORBIDDEN = "3";
    public static final String RESP_OPERATE_SUCCESS = "操作成功!";
    public static final String RESP_OPERATE_FAILED = "操作失败，请重试！";
    public static final String RESP_UNSUPPORT_INTERFACE = "不支持的接口！";
    public static final String RESP_ERR_PARAMS = "请求参数有误，请核对！";

    public static final String RESP_RESULT_CODE = "code";
    public static final String RESP_RESULT_DATA = "data";

    //global keys&value
    public static final String ID = "id";
    public static final String SECRET_KEY = "Doulw123";
    public static final String KEY_AUTHORIZATION = "Authorization";
    public static final String KEY_ACCESSTOKEN = "AccessToken";
    public static final String KEY_IS_MOBILE = "isMobile";
    public static final String KEY_IDENTIFY_CODE = "identifyCode";
    public static final String KEY_REQUIRED = "required";
    public static final String KEY_REGISTED = "registered";
    public static final String KEY_UNREGISTED = "unregistered";
    public static final String KEY_LIST = "list";
    public static final String KEY_SUGGESTS = "suggests";
    public static final String KEY_ACTIONE = "action";

    public static final String REQUEST_PARAM_ERR = "params err,please retry!";

    public static final String TIMESTAMP = "timestamp";

    public static final String KEY_PAGENUM = "pageNum";
    public static final String KEY_PAGESIZE = "pageSize";
    public static final String KEY_ORDERBY = "orderBy";
    public static final String KEY_NAME = "name";
    public static final String KEY_TYPE = "type";
    public static final String KEY_REMARKS = "remarks";
    public static final String KEY_SECRET = "secret";
    public static final String KEY_SECRET_VALUE = "ohcloud@123";
    public static final String KEY_SYSTEM = "system";
    public static final String KEY = "key";
    public static final String VALUE = "value";
    public static final String MONEY = "money";
    public static final String USER_ID = "userId";

    //user
    public static final String KEY_USER_LOGIN_NAME = "loginName";
    public static final String KEY_USER_PHONE = "phone";
    public static final String KEY_USER_NAME = "name";
    public static final String KEY_USER_PASSWORD = "password";
    public static final String USER_PASSWORD_OLD = "oldPassword";
    public static final String USER_PASSWORD_NEW = "newPassword";
    public static final String KEY_USER_EMAIL = "email";
    public static final String KEY_USER_NICK_NAME = "nickName";
    public static final String KEY_USER_COUNTRY = "country";
    public static final String KEY_CODE = "code";
    public static final String KEY_ICON = "iconurl";
    public static final String USER_SEX = "sex";
    public static final String USER_CITY = "city";
    public static final String USER_PROVINCE = "province";
    public static final String USER_COUNTRY = "country";
    public static final String USER_ICONURL = "iconurl";
    public static final String USER_LEVEL = "level";
    public static final String USER_GENDER = "gender";
    public static final String USER_JOB = "job";
    public static final String KEY_USER_INVITATION_CODE = "invitationCode";
    public static final String KEY_USER_IDCARD = "idcard";
    public static final String KEY_USER_BANKID = "bankId";

    public static final String KEY_NO_BIND_IDCARD = "未通过实名认证！";
    public static final String KEY_NO_BIND_BANKCARD = "未绑定银行卡！";

    //user_type
    public static final String KEY_USER_TYPE_PERSIONAL = "persional";
    public static final String KEY_USER_TYPE_TEAM = "team";

    public static final Integer USER_TYPE_PERSIONAL = 1;
    public static final Integer USER_TYPE_TEAM = 2;

    //user_level
    public static final Integer USER_LEVEL_CUSTOMER = 1; //消费者
    public static final Integer USER_LEVEL_CONSUME = 2;//消费商
    public static final Integer USER_LEVEL_AGENT1 = 3;//服务商
    public static final Integer USER_LEVEL_AGENT2 = 4;//服务商
    public static final Integer USER_LEVEL_AGENT3 = 5;//服务商
    public static final Integer USER_LEVEL_COMPANER = 6;//企业用户

    //commission_record
    public static final String COMMISSION_RECORD_EARN = "income";
    public static final String COMMISSION_RECORD_SPENT = "outcome";
    public static final String COMMISSION_RECORD_ANNUAL = "annualFee";


    //注册验证返回

    public static final String RESP_SMS_IDENTIFY_OK = "验证码发送成功!";
    public static final String RESP_SMS_CHECK_TIME_OUT = "验证码错误，请检查!";
    public static final String RESP_SMS_FAIL3 = "验证码有误，请重试!";
    public static final String RESP_SMS_SENDSMS_SUCCESS = "短信发送成功!";
    public static final String VALUE_SMS_CODE_TEST = "333333";
    public static final String RESP_SMS_CHECK_OK_TEST = "验证码验证成功!";
    public static final String RESP_SMS_CHECK_OK = "验证成功，请继续!";
    public static final String RESP_SMS_GET_CODE_ERR = "获取验证码出错";
    public static final String RESP_REGISTER_PHONE_DUMPLICATE = "该用户名已被注册!";
    public static final String RESP_REGISTER_SUCCESS = "注册成功!";
    public static final String RESP_REGISTER_FAILED = "注册失败，请重试!";
    public static final String RESP_CHECK_INVITATION_FAILED2 = "邀请码错误,请核对后重试!";
    public static final String RESP_CHECK_INVITATION_SUCCESS = "邀请码正确!";

    /**
     * 实名认证
     */
    public static final String KEY_REAL_NAME = "realName";
    public static final String KEY_ID_CARD = "idCard";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_BANKCARD = "bankcard";
    public static final String KEY_BANK_NAME = "bankName";
    public static final String KEY_SMS_CODE = "smsCode";
    /**
     * 提现申请
     */
    public static final String KEY_AMOUNT = "amount";
    public static final String KEY_PAY_PASSWORD = "password";

    /*提现申请返回的结果编码*/
    /**
     * 成功
     */
    public static final int RESP_CODE_SUCCESS = 1;
    /**
     * 其他原因失败
     */
    public static final int RESP_CODE_FAILED = 0;
    /**
     * 未缴纳年费失败
     */
    public static final int RESP_CODE_PAY_ANNUAL_FEE = 2;


    public static final String KEY_BANK_SMS_PAYCARD_TYPE = "paycardType";
    public static final String KEY_BANK_SMS_PAY_TYPE = "payType";
    public static final String KEY_BANK_SMS_INS_CODE = "insCode";
    public static final String KEY_BANK_SMS_OPEN_ID = "openId";
    public static final String KEY_BANK_SMS_MONEY = "money";
    public static final String KEY_BANK_SMS_PAY_PASSWORD = "payPassword";
    /**
     * /**
     * 提现审批意见
     */
    public static final String KEY_CHECK_ADVICE = "advice";
    public static final String KEY_WITHDRAW_RECORD_ID = "withdrawRecordId";
    public static final String KEY_WITHDRAW_CHECK_STATUS = "withdrawCheckStatus";

    public static final String KEY_TARGET_ACCOUNT = "targetAccount";

    public static final String WITHDRAW_CHECK_STATUS_CHECKING = "checking";
    public static final String WITHDRAW_CHECK_STATUS_SUCCESS = "success";
    public static final String WITHDRAW_CHECK_STATUS_FAIL = "fail";
    public static final String WITHDRAW_PAY_STATUS_SUCCESS = "success";
    public static final String WITHDRAW_PAY_STATUS_FAIL = "fail";


    //用户登录校验关键字

    public static String KEY_ENCRYPT_RSA = "rsa"; //初次加密的密钥 对应数据库 "rsa" 字段
    public static String KEY_ENCRYPT_RSA1 = "rsa1"; //初次加密的密钥 对应数据库 "rsa" 字段
    public static String KEY_ENCRYPT_RSA2 = "rsa2"; //密钥2
    public static String KEY_ENCRYPT_RSA3 = "rsa3"; //密钥3
    public static String KEY_ENCRYPT_TIMESTAMP = "timestamp";   //请求时间时间戳
    public static String KEY_ENCRYPT_KEY = "kek";   //密码加密密钥
    public static String KEY_ENCRYPT_TEK = "tek";   //数据传输密钥
    public static String KEY_ENCRYPT_NCO_TOKEN = "ncoToken";    //加密token


    public static final String REQ_LOGIN = "login";
    public static final String REQ_REGIST = "regist";
    public static final String REQ_CONFIRM = "confirm";
    public static final String RESP_WARM = "warning";
    public static final String KEY_DEVICE = "device";    //设备型号
    public static final String KEY_DEVICE_MODEL = "deviceModel";    //设备型号
    public static final String KEY_DEVICE_TYPE = "deviceType";      //设备类别
    public static final String KEY_DEVICE_ID = "deviceId";      //设备类别
    public static final String KEY_IS_AUTOLOGIN = "isAutoLogin";    //是否是自动登录


    //登录返回

    public static final String RESP_LOGIN_PRE_FAILED = "登录已失效，请重新登录!";
    public static final String RESP_LOGIN_PRE_FAILED2 = "用户不存在!";
    public static final String RESP_LOGIN_OK = "登录成功!";
    public static final String RESP_LOGIN_FAIL_1 = "用户不存在!";
    public static final String RESP_LOGIN_FAIL_2 = "用户已被冻结!";
    public static final String RESP_LOGIN_FAIL_4 = "用户名或密码错误，请重试!";

    public static final String RESP_LOGIN_FAIL_CHECK_TIMEOUT = "验证信息不正确，请重新登录!";
    public static final String RESP_LOGINOUT_OK = "您已退出登录!";
    public static final String RESP_LOGINOUT_ERR = "用户信息不正确，请重试!";

    //change password and reset password

    public static final String RESP_PASSWORD_ERR = "密码错误，请重试!";
    public static final String RESP_PASSWORD_CHANGED_SUCCESS = "密码修改成功!";
    public static final String RESP_PASSWORD_RESET_ERROR = "用户与所输手机号不匹配，请重试!";

    public static final String AS_FILE_TYPE_IMAGE = "images";
    public static final String AS_FILE_TYPE_NORMAL_FILES = "files";
    public static final String AS_FILE_TYPE_NORMAL_VIEDO = "viedo";

}
