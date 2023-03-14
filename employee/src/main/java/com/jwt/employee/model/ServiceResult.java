package com.jwt.employee.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for Service Messages and contains all enum related to app.
 * 
 * @author Ankur Kumar
 *
 */
public class ServiceResult {

	/**
	 * Variable to store status code.
	 */
	private int code;
	/**
	 * List containing the {@link }
	 */
	private List<ServiceMessage> resultmsg;
	/**
	 * Object containing service response object.
	 */
	private Object result;

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Default Constructor
	 */
	public ServiceResult() {
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor of the class.
	 * 
	 * @param code
	 * @param resultmsg
	 * @param result
	 */
	public ServiceResult(StatusCode code, List<ServiceMessage> resultmsg, Object result) {
		this.code = code.statusCode;
		this.resultmsg = resultmsg;
		this.result = result;
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor of the class.
	 * 
	 * @param code
	 * @param resultmsg
	 * @param result
	 */
	public ServiceResult(StatusCode code, ServiceMessage resultmsg, Object result) {
		this.code = code.statusCode;
		this.resultmsg = new ArrayList<ServiceMessage>();
		this.resultmsg.add(resultmsg);
		this.result = result;
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Constructor of the class.
	 * 
	 * @param code
	 * @param result
	 */
	public ServiceResult(StatusCode code, Object result) {
		this.code = code.statusCode;
		this.result = result;
	}

	// ------------------------------------------------------------------------------------------------------------------
	public int getCode() {
		return code;
	}

	public List<ServiceMessage> getResultmsg() {
		return resultmsg;
	}

	public Object getResult() {
		return result;
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * Enumeration for Status Codes.
	 * 
	 * @author Ankur Kumar.
	 *
	 */
	public enum StatusCode {

		SUCCESS(200), INTERNAL_SERVER_ERROR(500), WARNING(501), BAD_REQUEST(400), UNAUTHORIZED(401), TEMP_PIN_SET(
				402),
		FORBIDDEN(403), ACCOUNT_NOT_VERIFIED(407), SELECT_FIRM_ID(408), SUSPENDED(401), BRANCH_NOT_FOUND(413),
		INVALID_PHONE_NUMBER(420),CREATE(201),LEAD_ALREADY_SUBMITTED(415);

		private int statusCode;

		private StatusCode(int code) {
			this.statusCode = code;
		}

		public int getStatusCode() {
			return statusCode;
		}
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * This enum is used for define the Token Scope
	 * 
	 * @author Ankur Kumar
	 *
	 */
	public enum UserTokenScope {
		AUTH("auth"), SIGN_UP("signup");

		private String value;

		private UserTokenScope(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * This enum is used for define the Device Type
	 * 
	 * @author Ankur Kumar
	 *
	 */
	public enum DeviceType {
		ANDROID("android"), IOS("ios");

		private String value;

		private DeviceType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * This enum is used for define the user status.
	 * 
	 * @author Ankur Kumar
	 *
	 */
	public enum UserStatus {
		INACTIVE("in_active"), ACTIVE("active"), DELETED("deleted");

		private String value;

		private UserStatus(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * This enum is used for define the user role.
	 * 
	 * @author Ankur Kumar
	 *
	 */
	public enum UserRole {
		ADMIN("admin"), PARTNER("partner"), CUSTOMER("customer");

		private String value;

		private UserRole(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * This enum is used for define the active or inactive status.
	 * 
	 * @author Ankur Kumar
	 *
	 */
	public enum ActiveStatus {
		ACTIVE(true), INACTIVE(false);
		private Boolean value;

		private ActiveStatus(Boolean value) {
			this.value = value;
		}

		public Boolean getValue() {
			return value;
		}
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * This enum is used for define the apply loan status.
	 * 
	 * @author Ankur Kumar
	 *
	 */
	public enum ApplyLoanStatus {
		YES(true), NO(false);
		private Boolean value;

		private ApplyLoanStatus(Boolean value) {
			this.value = value;
		}

		public Boolean getValue() {
			return value;
		}
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * This enum is used for define the completion status
	 * 
	 * @author Ankur Kumar
	 *
	 */
	public enum CompletionStatus {
		YES(true), NO(false);
		private Boolean value;

		private CompletionStatus(Boolean value) {
			this.value = value;
		}

		public Boolean getValue() {
			return value;
		}
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * This enum is used for define the slider type.
	 * 
	 * @author Ankur Kumar
	 *
	 */
	public enum SliderTypeKey {
		SCHEME("scheme"), OTHERS("others");
		private String value;

		private SliderTypeKey(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * This enum is used for define the content type.
	 * 
	 * @author Ankur Kumar
	 *
	 */
	public enum ContentTypeKey {
		VIDEO("video"), IMAGE("image");
		private String value;

		private ContentTypeKey(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * This enum is used for define the display to scheme.
	 * 
	 * @author Ankur Kumar
	 *
	 */
	public enum DisplayToKey {
		DISPLAY_TO_PARTNER("display_to_partner"), DISPLAY_TO_CUSTOMER("display_to_customer"), DISPLAY_TO_BOTH(
				"display_to_both");
		private String value;

		private DisplayToKey(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	// ------------------------------------------------------------------------------------------------------------------
	/**
	 * This enum is used for define the slider recipient type.
	 * 
	 * @author Ankur Kumar
	 *
	 */
	public enum SliderRecipientType {
		REGISTERED("registered"), UNREGISTERED("unregistered"), BOTH("both");
		private String value;

		private SliderRecipientType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * @implNote This enumeration is used to check the extension of image file
	 * @author Suman Maity
	 */
	public enum ImageExtentionCheck {
		JPG("jpg"), JPEG("jpeg"), PNG("png");
		private String value;

		private ImageExtentionCheck(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * @implNote This enumeration is used to check the extension of video file
	 * @author Suman Maity
	 */
	public enum VideoExtentionCheck {
		MP4("mp4"), ThreeGP("3gp"), AVI("avi"), MKV("mkv"), WMV("wmv"), SVI("svi");
		private String value;

		private VideoExtentionCheck(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum StateCode {
		AP("Andhra Pradesh"), AR("Arunachal Pradesh"), AS("Assam"), BR("Bihar"), CG("Chattisgarh"), GA("Goa"),
		GJ("Gujarat"), HR("Haryana"), HP("Himachal Pradesh"), JK("Jammu & Kashmir"), JH("Jharkhand"), KA("Karnataka"),
		KL("Kerala"), MP("Madhya Pradesh"), MH("Maharashtra"), MN("Manipur"), ML("Meghalaya"), MZ("Mizoram"),
		NL("Nagaland"), OR("Orissa"), PB("Punjab"), RJ("Rajasthan"), SK("Sikkim"), TN("Tamil Nadu"), TS("Telangana"),
		TR("Tripura"), UK("Uttarakhand"), UP("Uttar Pradesh"), WB("West Bengal"), AN("Andaman & Nicobar"),
		CH("Chandigarh"), DN("Dadra and Nagar Haveli"), DD("Daman & Diu"), DL("Delhi"), LD("Lakshadweep"),
		PY("Pondicherry");

		private String name;

		private StateCode(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}
}
