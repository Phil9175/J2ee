package oth.metier.service;

/**
 * Objet de réponse de service suite à un appel.
 * 
 * @author badane.
 *
 */
public class ServiceResponse {
	private boolean error;
	private String message;
	private Object dataResult;

	/**
	 * Constructeur par défaut.
	 */
	public ServiceResponse() {

	}

	/**
	 * @return the error
	 */
	public boolean isError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(final boolean error) {
		this.error = error;
	}

	/**
	 * @return the errorMessage
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the errorMessage to set
	 */
	public void setMessage(final String message) {
		this.message = message;
	}

	/**
	 * @return the dataResult
	 */
	public Object getDataResult() {
		return dataResult;
	}

	/**
	 * @param dataResult
	 *            the dataResult to set
	 */
	public void setDataResult(final Object dataResult) {
		this.dataResult = dataResult;
	}

}
