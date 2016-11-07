package fr.elecomte.ci.look.services.payloads.extracts;

import fr.elecomte.ci.look.data.model.Result;

/**
 * @author elecomte
 * @since 0.1.0
 */
public abstract class ResultPayloadExtract extends PayloadExtract {

	private Result associatedResult;

	/**
	 * @return the associatedResult
	 */
	public Result getAssociatedResult() {
		return this.associatedResult;
	}

	/**
	 * @param associatedResult
	 *            the associatedResult to set
	 */
	public void setAssociatedResult(Result associatedResult) {
		this.associatedResult = associatedResult;
	}

}
