package fr.elecomte.ci.panorama.services.payloads.extracts;

import java.util.List;

/**
 * <p>
 * Payload for an Audit. Model is :
 * 
 * <pre>
 * "payload": {
 *     "ncss": {{Int nbr of Non Commenting Source Statements (NCSS) on the project}},
 *     "coverage": {{Int % of code coverage by unit tests on the project, using standard conventions}},
 *     "reliability" : "{{Any convenient value on reliability : letter, level ...}}", 
 *     "security" : "{{Any convenient value on security : letter, level ...}}",
 *     "maintenance" : "{{Any convenient value on maintenance : letter, level ...}}",
 *     "violations": [
 *          {
 *              "type": "{{Name of violation type identified on the audit. Multiple violations can be defined}}",
 *              "minor": {{int nbr of minor violations for the identified type}}, 
 *              "major": {{int nbr of major violations for the identified type}}, 
 *              "url": "{{Freely specified for violation report access, or associated bug-tracker}}" 
 *          }
 *     ],
 *     "duration": {{int nbr of seconds for audit set execution}}
 * }
 * </pre>
 * </p>
 * 
 * @author elecomte
 * @since 0.1.0
 */
public class AuditResultPayloadExtract extends ResultPayloadExtract {

	private int ncss;

	private int coverage;

	private String reliability;

	private String security;

	private String maintenance;

	private List<Violation> violations;

	private int duration;

	/**
	 * 
	 */
	public AuditResultPayloadExtract() {
		super();
	}

	/**
	 * @return the ncss
	 */
	public int getNcss() {
		return this.ncss;
	}

	/**
	 * @param ncss
	 *            the ncss to set
	 */
	public void setNcss(int ncss) {
		this.ncss = ncss;
	}

	/**
	 * @return the violations
	 */
	public List<Violation> getViolations() {
		return this.violations;
	}

	/**
	 * @param violations
	 *            the violations to set
	 */
	public void setViolations(List<Violation> violations) {
		this.violations = violations;
	}

	/**
	 * @return the reliability
	 */
	public String getReliability() {
		return this.reliability;
	}

	/**
	 * @param reliability
	 *            the reliability to set
	 */
	public void setReliability(String reliability) {
		this.reliability = reliability;
	}

	/**
	 * @return the security
	 */
	public String getSecurity() {
		return this.security;
	}

	/**
	 * @param security
	 *            the security to set
	 */
	public void setSecurity(String security) {
		this.security = security;
	}

	/**
	 * @return the maintenance
	 */
	public String getMaintenance() {
		return this.maintenance;
	}

	/**
	 * @param maintenance
	 *            the maintenance to set
	 */
	public void setMaintenance(String maintenance) {
		this.maintenance = maintenance;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return this.duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return the coverage
	 */
	public int getCoverage() {
		return this.coverage;
	}

	/**
	 * @param coverage
	 *            the coverage to set
	 */
	public void setCoverage(int coverage) {
		this.coverage = coverage;
	}

	/**
	 * <p>
	 * Violation model :
	 * 
	 * <pre>
	 *  {
	 *      "type": "{{Name of violation type identified on the audit. Multiple violations can be defined}}",
	 *      "minor": {{int nbr of minor violations for the identified type}}, 
	 *      "major": {{int nbr of major violations for the identified type}}, 
	 *      "url": "{{Freely specified for violation report access, or associated bug-tracker}}" 
	 *  }
	 * </pre>
	 * </p>
	 * 
	 * @author elecomte
	 * @since 0.1.0
	 */
	public static class Violation {

		private String type;

		private int minor;

		private int major;

		private String url;

		/**
		 * @return the type
		 */
		public String getType() {
			return this.type;
		}

		/**
		 * @param type
		 *            the type to set
		 */
		public void setType(String type) {
			this.type = type;
		}

		/**
		 * @return the minor
		 */
		public int getMinor() {
			return this.minor;
		}

		/**
		 * @param minor
		 *            the minor to set
		 */
		public void setMinor(int minor) {
			this.minor = minor;
		}

		/**
		 * @return the major
		 */
		public int getMajor() {
			return this.major;
		}

		/**
		 * @param major
		 *            the major to set
		 */
		public void setMajor(int major) {
			this.major = major;
		}

		/**
		 * @return the url
		 */
		public String getUrl() {
			return this.url;
		}

		/**
		 * @param url
		 *            the url to set
		 */
		public void setUrl(String url) {
			this.url = url;
		}

	}
}
