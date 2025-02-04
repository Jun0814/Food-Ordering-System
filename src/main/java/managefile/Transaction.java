package managefile;

public class Transaction {
    private String transactionID;
    private String customerID;
    private String generalID;
    private String datetime;
    private String amount;
    private String transactionType;
    private String topupType;

    public Transaction(String transactionID, String customerID, String generalID, String datetime, String amount, String transactionType, String topupType) {
        this.transactionID = transactionID;
        this.customerID = customerID;
        this.generalID = generalID;
        this.datetime = datetime;
        this.amount = amount;
        this.transactionType = transactionType;
        this.topupType = topupType;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getGeneralID() {
        return generalID;
    }

    public void setGeneralID(String generalID) {
        this.generalID = generalID;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTopupType() {
        return topupType;
    }

    public void setTopupType(String topupType) {
        this.topupType = topupType;
    }
    
    
}
