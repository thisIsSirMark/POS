package transaction;

import java.sql.Date;

public class Transaction {

	private Integer transactionId, employeeNumber;
	private Date transacionDate;
	private Double grandTotal;
	private Boolean isVisible;

	public Transaction() {

	}

	public Transaction(int transactionId, int employeeNumber, Date transacionDate, double grandTotal) {
		super();
		this.transactionId = transactionId;
		this.employeeNumber = employeeNumber;
		this.transacionDate = transacionDate;
		this.grandTotal = grandTotal;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public Date getTransacionDate() {
		return transacionDate;
	}

	public double getGrandTotal() {
		return grandTotal;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public void setTransacionDate(Date transacionDate) {
		this.transacionDate = transacionDate;
	}

	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	@Override
	public String toString() {
		return "transactions [transactionId=" + transactionId + ", employeeNumber=" + employeeNumber
				+ ", transacionDate=" + transacionDate + ", grandTotal=" + grandTotal + "]";
	}

}
